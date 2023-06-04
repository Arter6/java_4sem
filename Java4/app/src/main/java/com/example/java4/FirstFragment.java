package com.example.java4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FirstFragment extends Fragment
{
	
	View viewLayout;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState)
	{
		viewLayout = inflater.inflate(R.layout.fragment_first, container, false);
		
		viewLayout.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				getParentFragmentManager().beginTransaction()
						.replace(R.id.fragmentContainerView, SecondFragment.class,null)
						.commit();
			}
		});
		
		viewLayout.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				getParentFragmentManager().beginTransaction()
						.replace(R.id.fragmentContainerView,ThirdFragment.class,null)
						.commit();
			}
		});
		
		return viewLayout;
	}
}