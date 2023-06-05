package com.example.java6;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_first, container, false);
	}
	
	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		view.findViewById(R.id.button_notification).setOnClickListener(v ->
		{
			if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED)
			{
				showNotification();
			}
			else
			{
				ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
			}
		});
		
		view.findViewById(R.id.button_service).setOnClickListener(v ->
		{
			if(Settings.canDrawOverlays(getActivity().getApplicationContext()))
			{
				Intent intent = new Intent(getActivity().getApplicationContext(), MyService.class);
				getActivity().startService(intent);
			}
			else
			{
				Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
				startActivity(intent);
			}
			
		});
	}
	
	private void showNotification()
	{
		NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(getContext().NOTIFICATION_SERVICE);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
		{
			NotificationChannel notificationChannel = new NotificationChannel("channelId","channelName", NotificationManager.IMPORTANCE_HIGH);
			notificationChannel.setDescription("channelDesc");
			notificationManager.createNotificationChannel(notificationChannel);
		}
		
		Notification.Builder builder = new Notification.Builder(getContext(),"some_id")
				.setSmallIcon(R.drawable.ic_launcher_background)
				.setContentTitle("Java6")
				.setContentText("Сделал уведомление")
				.setAutoCancel(true);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) builder.setChannelId("channelId");
		
		notificationManager.notify(0,builder.build());
	}
}