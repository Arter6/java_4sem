package com.example.java3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondFragment extends Fragment
{
	
	private String RRR = "SecondFragment";
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Log.d(RRR,"onCreate");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.constraint_layout, container, false);
		TextView textView = view.findViewById(R.id.textView3);
		Bundle bundle = getArguments();
		if (bundle != null)
		{
			int value = bundle.getInt("value", 0);
			textView.setText(Integer.toString(value));
			Button button = view.findViewById(R.id.button3);
			button.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View view)
				{
					bundle.putInt("value", value + 1);
					getParentFragmentManager().setFragmentResult("second", bundle);
				}
			});
		}
		return view;
	}
	
	@Override
	public void onStart()
	{
		super.onStart();
		Log.d(RRR,"onStart");
		Toast.makeText(getContext(), "onStart", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onStop()
	{
		super.onStop();
		Log.d(RRR,"onStop");
		Toast.makeText(getContext(), "onStop", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onPause()
	{
		super.onPause();
		Log.d(RRR,"onPause");
		Toast.makeText(getContext(), "onPause", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		Log.d(RRR,"onResume");
		Toast.makeText(getContext(), "onResume", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		Log.d(RRR,"onDestroy");
		Toast.makeText(getContext(), "onDestroy", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onDetach()
	{
		super.onDetach();
		Log.d(RRR,"onDetach");
		Toast.makeText(getContext(), "onDetach", Toast.LENGTH_SHORT).show();
	}
}