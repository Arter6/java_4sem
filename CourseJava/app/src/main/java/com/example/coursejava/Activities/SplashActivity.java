package com.example.coursejava.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.coursejava.R;

public class SplashActivity extends AppCompatActivity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
		setContentView(R.layout.activity_splash);
		hideSystemUI();
		Handler handler = new Handler();
		handler.postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				startActivity(new Intent(SplashActivity.this,MainActivity.class));
				overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
				finish();
			}
		},2000);
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