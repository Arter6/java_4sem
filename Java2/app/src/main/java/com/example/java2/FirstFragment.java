package com.example.java2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.java2.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment
{
	
	private FragmentFirstBinding binding;
	
	@Override
	public View onCreateView(
			LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState
	)
	{
		
		binding = FragmentFirstBinding.inflate(inflater, container, false);
		return binding.getRoot();
		
	}
	
	@Override
	public void onPause()
	{
		super.onPause();
		Log.d("RRR","onPuase");
	}
	
	@Override
	public void onStop()
	{
		super.onStop();
		Log.d("RRR","onStop");
	}
	
	@Override
	public void onDestroyView()
	{
		super.onDestroyView();
		Log.d("RRR","onDestroyView");
		Bundle bundle = new Bundle();
		bundle.putString("str","string");
		setArguments(bundle);
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		Log.d("RRR","onDestroy");
	}
	
	@Override
	public void onDetach()
	{
		super.onDetach();
		Log.d("RRR","onDetach");
	}
}