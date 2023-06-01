package com.example.java3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentResultListener;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Bundle bundle = new Bundle();
		bundle.putInt("value",1);
		getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,FirstFragment.class,bundle).commit();
		
		getSupportFragmentManager().setFragmentResultListener("first", this, new FragmentResultListener()
		{
			@Override
			public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result)
			{
				int value = result.getInt("value",0);
				Log.d("RRR",Integer.toString(value));
				getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
						.replace(R.id.fragmentContainerView, SecondFragment.class,result)
						.commit();
			}
		});
		
		
		getSupportFragmentManager().setFragmentResultListener("second", this, new FragmentResultListener()
		{
			@Override
			public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result)
			{
				int value = result.getInt("value",0);
				Log.d("RRR",Integer.toString(value));
				getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
						.replace(R.id.fragmentContainerView, FirstFragment.class,result)
						.commit();
			}
		});
	}
}