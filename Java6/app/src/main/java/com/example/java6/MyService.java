package com.example.java6;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

public class MyService extends Service
{
	public MyService()
	{
	}
	
	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
//		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.overlay, null);
		Button button = view.findViewById(R.id.button);
		WindowManager.LayoutParams params = new WindowManager.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
				PixelFormat.TRANSLUCENT);
		
		button.setOnClickListener(v ->
		{
			Intent i = new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(i);
		});
		params.gravity = Gravity.CENTER;
		WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
		windowManager.addView(view, params);
		
		return START_STICKY;
	}
}