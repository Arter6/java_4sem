package com.example.java2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.java2.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment
{
	
	private FragmentSecondBinding binding;
	
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		String string = savedInstanceState.getString("str");
		Log.d("RRR", string);
	}
	
	@Override
	public View onCreateView(
			LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState
	)
	{
		
		binding = FragmentSecondBinding.inflate(inflater, container, false);
		return binding.getRoot();
		
	}
	
	public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		
		
	}
	
	@Override
	public void onDestroyView()
	{
		super.onDestroyView();
		binding = null;
	}
	
}