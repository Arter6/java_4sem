package com.example.coursejava.Tasks;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.coursejava.R;

public class ReminderBroadcast extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{
		if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED)
		{
			Integer id = intent.getIntExtra("id", 0);
			Log.d("onReceive",id.toString());
			String title = intent.getStringExtra("title");
			String desc = intent.getStringExtra("desc");
			
			NotificationCompat.Builder builder = new NotificationCompat.Builder(context, id.toString())
					.setSmallIcon(R.drawable.logo)
					.setContentTitle(title)
					.setContentText(desc)
					.setPriority(NotificationCompat.PRIORITY_MAX)
					.setVibrate(new long[]{100, 1000, 200, 340})
					.setAutoCancel(false)
					.setTicker("Notification")
					.setChannelId(id.toString());
			
			NotificationManagerCompat m = NotificationManagerCompat.from(context);
			m.notify(id, builder.build());
		}
		
	}
}
