package com.example.coursejava.Activities;

import static com.example.coursejava.Activities.MainActivity.hideSystemUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coursejava.R;
import com.example.coursejava.databinding.ActivitySettingsBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class SettingsActivity extends AppCompatActivity
{
	ActivitySettingsBinding binding;
	BottomNavigationView bottomNavigationView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		binding = ActivitySettingsBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		
		hideSystemUI(this);
		
		bottomNavigationView = binding.bottomNavigation;
		bottomNavigationView.setSelectedItemId(R.id.settings);
//		bottomNavigationView.getMenu().getItem(1).setChecked(true);
		
		bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener()
		{
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item)
			{
				int itemId = item.getItemId();
				if (itemId == R.id.statistics)
				{
					startActivity(new Intent(getApplicationContext(),StatisticsActivity.class));
					overridePendingTransition(0,0);
					return true;
				} else if (itemId == R.id.list)
				{
					startActivity(new Intent(getApplicationContext(),MainActivity.class));
					overridePendingTransition(0,0);
					return true;
				} else if (itemId == R.id.settings)
				{
					return true;
				}
				return false;
			}
		});
	}
}