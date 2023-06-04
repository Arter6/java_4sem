package com.example.java4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, FirstFragment.class, null).commit();
		
		
	}
	
	@Override
	public void onBackPressed()
	{
		if (getSupportFragmentManager().getFragments().get(0).getId()==R.layout.fragment_first)
		{
			super.onBackPressed();
		}
		else
		{
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.fragmentContainerView,FirstFragment.class,null)
					.commit();
		}
	}
}