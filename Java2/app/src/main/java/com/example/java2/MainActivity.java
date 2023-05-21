package com.example.java2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;

import com.example.java2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{
	
	private AppBarConfiguration appBarConfiguration;
	private ActivityMainBinding binding;
	
	FirstFragment firstFragment = new FirstFragment();
	SecondFragment secondFragment = new SecondFragment();
	ThirdFragment thirdFragment = new ThirdFragment();
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		
		getSupportFragmentManager().beginTransaction().replace(R.id.container,firstFragment).commit();
		
		findViewById(R.id.button1).setOnClickListener(this::onClick);
		findViewById(R.id.button2).setOnClickListener(this::onClick);
		findViewById(R.id.button3).setOnClickListener(this::onClick);
	}
	
	public void onClick(View view)
	{
		if (view == findViewById(R.id.button1))
		{
			getSupportFragmentManager().beginTransaction().replace(R.id.container,firstFragment).commit();
		}
		else if (view == findViewById(R.id.button2))
		{
			getSupportFragmentManager().beginTransaction().replace(R.id.container,secondFragment).commit();
		}
		else if (view == findViewById(R.id.button3))
		{
			getSupportFragmentManager().beginTransaction().replace(R.id.container,thirdFragment).commit();
		}
	}
}