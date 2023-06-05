package com.example.java5;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SecondFragment extends Fragment
{
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_second, container, false);
	}
	
	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		if(getArguments() != null)
		{
			TextView textView = view.findViewById(R.id.textView2);
			textView.setText(Integer.toString(getArguments().getInt("value",0)));
		}
		view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Bundle bundle = new Bundle();
				if (getArguments() != null)
				{
					bundle.putInt("value", getArguments().getInt("value", 0) + 1);
				} else
				{
					bundle.putInt("value",1);
				}
				Navigation.findNavController(view).navigate(R.id.action_secondFragment_to_thirdFragment,bundle);
			}
		});
	}
}