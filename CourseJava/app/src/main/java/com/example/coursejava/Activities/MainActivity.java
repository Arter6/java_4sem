package com.example.coursejava.Activities;

import android.os.Bundle;

import com.example.coursejava.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.example.coursejava.databinding.ActivityMainBinding;

import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
{
	
	ActivityMainBinding binding;
	BottomNavigationView bottomNavigationView;
	StatisticsFragment statisticsFragment = new StatisticsFragment();
	ListFragment listFragment = new ListFragment();
	SettingsFragment settingsFragment = new SettingsFragment();
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		
		hideSystemUI();
		
		bottomNavigationView = binding.bottomNavigation;
		bottomNavigationView.getMenu().getItem(1).setChecked(true);
		getSupportFragmentManager().beginTransaction().replace(R.id.container,listFragment).commit();
		
		bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener()
		{
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item)
			{
				int itemId = item.getItemId();
				if (itemId == R.id.statistics)
				{
					getSupportFragmentManager().beginTransaction().replace(R.id.container, statisticsFragment).commit();
					return true;
				} else if (itemId == R.id.list)
				{
					getSupportFragmentManager().beginTransaction().replace(R.id.container, listFragment).commit();
					return true;
				} else if (itemId == R.id.settings)
				{
					getSupportFragmentManager().beginTransaction().replace(R.id.container, settingsFragment).commit();
					return true;
				}
				return false;
			}
		});
	}
	
	private void hideSystemUI() {
		View decorView = getWindow().getDecorView();
		int uiOptions = decorView.getSystemUiVisibility();
		int newUiOptions = uiOptions;
		newUiOptions |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
		newUiOptions |= View.SYSTEM_UI_FLAG_FULLSCREEN;
		newUiOptions |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
		newUiOptions |= View.SYSTEM_UI_FLAG_IMMERSIVE;
		newUiOptions |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
		decorView.setSystemUiVisibility(newUiOptions);
	}
}