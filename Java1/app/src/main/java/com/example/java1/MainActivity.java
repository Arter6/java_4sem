package com.example.java1;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.java1.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
	
	private AppBarConfiguration appBarConfiguration;
	private ActivityMainBinding binding;
	
	Context context = this;
	int duration = Toast.LENGTH_SHORT;
	
	String TAG = "RRR";
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		
		Toast toast = Toast.makeText(context,"onCreate",duration);
		toast.show();
		Log.d(TAG,"onCreate");
	}
	
	@Override
	protected void onStart()
	{
		super.onStart();
		Toast toast = Toast.makeText(context,"onStart",duration);
		toast.show();
		Log.d(TAG,"onStart");
	}
	
	@Override
	protected void onStop()
	{
		super.onStop();
		Toast toast = Toast.makeText(context,"onStop",duration);
		toast.show();
		Log.d(TAG,"onStop");
	}
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		Toast toast = Toast.makeText(context,"onDestroy",duration);
		toast.show();
		Log.d(TAG,"onDestroy");
	}
	
	@Override
	protected void onPause()
	{
		super.onPause();
		Toast toast = Toast.makeText(context,"onPause",duration);
		toast.show();
		Log.d(TAG,"onPause");
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		Toast toast = Toast.makeText(context,"onResume",duration);
		toast.show();
		Log.d(TAG,"onResume");
	}
}