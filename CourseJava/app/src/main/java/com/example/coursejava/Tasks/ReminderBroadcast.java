package com.example.coursejava.Tasks;

import android.Manifest;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.coursejava.Activities.MainActivity;
import com.example.coursejava.R;

public class ReminderBroadcast extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{
		if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED)
		{
			Integer id = intent.getIntExtra("id", 0);
			String title = intent.getStringExtra("title");
			String desc = intent.getStringExtra("desc");
			Log.d("onReceive",title);
			Log.d("onReceive",desc);
			Intent notificationIntent = new Intent(context, MainActivity.class);
			notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
			
			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);
			NotificationCompat.Builder builder = new NotificationCompat.Builder(context, id.toString())
					.setContentIntent(pendingIntent)
					.setContentTitle(title)
					.setContentText(desc)
					.setSmallIcon(R.drawable.logo)
					.setAutoCancel(true)
					.setChannelId("channelId");
			
			NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
			
			notificationManagerCompat.notify(id, builder.build());
		}
	}
}
