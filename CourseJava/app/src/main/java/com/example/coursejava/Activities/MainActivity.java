package com.example.coursejava.Activities;

import static com.example.coursejava.Tasks.DateTimeConverter.dateFormatter;
import static com.example.coursejava.Tasks.DateTimeConverter.timeFormatter;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursejava.R;
import com.example.coursejava.Stats.Stat;
import com.example.coursejava.Stats.StatDao;
import com.example.coursejava.Stats.StatDatabase;
import com.example.coursejava.Stats.StatViewModel;
import com.example.coursejava.Tasks.RVAdapter;
import com.example.coursejava.Tasks.ReminderBroadcast;
import com.example.coursejava.Tasks.Task;
import com.example.coursejava.Tasks.TaskDao;
import com.example.coursejava.Tasks.TaskDatabase;
import com.example.coursejava.Tasks.TaskViewModel;
import com.example.coursejava.databinding.ActivityMainBinding;
import com.example.coursejava.databinding.ActivityTaskInsertBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formattable;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{
	AlarmManager alarmManager;
	NotificationManager notificationManager;
	ActivityResultLauncher<String[]> mPermissionResultLauncher;
	private boolean isPostNotificationGranted = false;
	ActivityMainBinding binding;
	BottomNavigationView bottomNavigationView;
	TaskViewModel taskViewModel;
	StatViewModel statViewModel;
	ActivityResultLauncher activityResultLauncher;
	int backButtonCount;
	Toast backPressed;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		createNotificationChannel();
		
		alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		
		mPermissionResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>()
		{
			@Override
			public void onActivityResult(Map<String, Boolean> result)
			{
				if (result.get(Manifest.permission.POST_NOTIFICATIONS) != null)
				{
					isPostNotificationGranted = result.get(Manifest.permission.POST_NOTIFICATIONS);
				}
			}
		});
		
		requestPermission();
		
		backButtonCount = 0;
		backPressed = Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT);
		binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		
		hideSystemUI(this);
		
		bottomNavigationView = binding.bottomNavigation;
		bottomNavigationView.setSelectedItemId(R.id.list);
		
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
					return true;
				} else if (itemId == R.id.settings)
				{
					startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
					overridePendingTransition(0,0);
					return true;
				}
				return false;
			}
		});
		
		taskViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(TaskViewModel.class);
		statViewModel = new ViewModelProvider(this,(ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(StatViewModel.class);
		
		activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>()
		{
			@Override
			public void onActivityResult(ActivityResult result)
			{
				if (result.getResultCode() == 1)
				{
					String title = result.getData().getStringExtra("title");
					String desc = result.getData().getStringExtra("desc");
					String date = result.getData().getStringExtra("date");
					String time = result.getData().getStringExtra("time");
					LocalDate localDate = LocalDate.parse(date,dateFormatter);
					LocalTime localTime = LocalTime.parse(time,timeFormatter);
					LocalDateTime localDateTime = localDate.atTime(localTime);
					Task task = new Task(title, desc, localDateTime);
					int taskId = (int) TaskDatabase.getInstance(getApplicationContext()).taskDao().insertWithId(task);
					Stat stat = new Stat(localDateTime);
					stat.setId((int)taskId);
					statViewModel.insert(stat);
					
					if (!localDateTime.minusHours(1).isBefore(LocalDateTime.now()))
					{
						Intent intent = new Intent(MainActivity.this, ReminderBroadcast.class);
						intent.putExtra("id",taskId);
						intent.putExtra("title",task.getTitle());
						intent.putExtra("desc",task.getDesc());
						PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);
						long timeAlarm = ZonedDateTime.of(localDateTime.minusHours(1), ZoneId.systemDefault()).toInstant().toEpochMilli();
						alarmManager.set(AlarmManager.RTC_WAKEUP, timeAlarm, pendingIntent);
					}
					
					Toast.makeText(MainActivity.this,"task added", Toast.LENGTH_SHORT).show();
				}
				else if (result.getResultCode() == 2)
				{
					String title = result.getData().getStringExtra("title");
					String desc = result.getData().getStringExtra("desc");
					String date = result.getData().getStringExtra("date");
					String time = result.getData().getStringExtra("time");
					LocalDate localDate = LocalDate.parse(date,dateFormatter);
					LocalTime localTime = LocalTime.parse(time,timeFormatter);
					LocalDateTime localDateTime = localDate.atTime(localTime);
					Task task = new Task(title, desc, localDateTime);
					task.setId(result.getData().getIntExtra("id",0));
					taskViewModel.update(task);
					Stat stat = new Stat(localDateTime);
					stat.setId(task.getId());
					statViewModel.update(stat);
					
					if (!localDateTime.minusHours(1).isBefore(LocalDateTime.now()))
					{
						Intent intent = new Intent(MainActivity.this, ReminderBroadcast.class);
						intent.putExtra("id",task.getId());
						intent.putExtra("title",task.getTitle());
						intent.putExtra("desc",task.getDesc());
						PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);
						long timeAlarm = ZonedDateTime.of(localDateTime.minusHours(1), ZoneId.systemDefault()).toInstant().toEpochMilli();
						alarmManager.set(AlarmManager.RTC_WAKEUP, timeAlarm, pendingIntent);
					}
					
					Toast.makeText(MainActivity.this,"task updated", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		binding.floatingActionButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				Intent intent = new Intent(MainActivity.this, TaskInsertActivity.class);
				intent.putExtra("type","add");
				activityResultLauncher.launch(intent);
			}
		});
		
		binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
		binding.recyclerView.setHasFixedSize(true);
		RVAdapter rvAdapter = new RVAdapter();
		binding.recyclerView.setAdapter(rvAdapter);
		
		
		taskViewModel.getAllTasks().observe(this, new Observer<List<Task>>()
		{
			@Override
			public void onChanged(List<Task> tasks)
			{
				rvAdapter.submitList(tasks);
			}
		});
		
		new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT)
		{
			@Override
			public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target)
			{
				return false;
			}
			
			@Override
			public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction)
			{
				if (direction == ItemTouchHelper.RIGHT)
				{
					Task task = rvAdapter.getTask(viewHolder.getAdapterPosition());
					Stat stat = StatDatabase.getInstance(getApplicationContext()).statDao().getById(task.getId());
					stat.setEnd(LocalDateTime.now());
					taskViewModel.delete(task);
					statViewModel.update(stat);
					Toast.makeText(MainActivity.this,"Task deleted",Toast.LENGTH_SHORT).show();
				}
				else
				{
					Intent intent = new Intent(MainActivity.this, TaskInsertActivity.class);
					intent.putExtra("type","update");
					intent.putExtra("title",rvAdapter.getTask(viewHolder.getAdapterPosition()).getTitle());
					intent.putExtra("desc",rvAdapter.getTask(viewHolder.getAdapterPosition()).getDesc());
					intent.putExtra("date",rvAdapter.getTask(viewHolder.getAdapterPosition()).getDate().format(dateFormatter));
					intent.putExtra("time",rvAdapter.getTask(viewHolder.getAdapterPosition()).getDate().format(timeFormatter));
					intent.putExtra("id",rvAdapter.getTask(viewHolder.getAdapterPosition()).getId());
					activityResultLauncher.launch(intent);
				}
			}
		}).attachToRecyclerView(binding.recyclerView);
		
		
	}
	
	public void requestPermission()
	{
		isPostNotificationGranted = ContextCompat.checkSelfPermission(this,Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED;
		
		List<String> permissionRequest = new ArrayList<String>();
		
		if (!isPostNotificationGranted)
		{
			permissionRequest.add(Manifest.permission.POST_NOTIFICATIONS);
		}
		
		if (!permissionRequest.isEmpty())
		{
			mPermissionResultLauncher.launch(permissionRequest.toArray(new String[0]));
		}
	}
	
	private void createNotificationChannel()
	{
		int importance = NotificationManager.IMPORTANCE_HIGH;
		NotificationChannel channel = new NotificationChannel("channelId","channelTitle",importance);
		channel.setDescription("channelDesc");
		
		notificationManager = getSystemService(NotificationManager.class);
		notificationManager.createNotificationChannel(channel);
	}
	
	public static void hideSystemUI(Activity activity) {
		View decorView = activity.getWindow().getDecorView();
		int uiOptions = decorView.getSystemUiVisibility();
		int newUiOptions = uiOptions;
		newUiOptions |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
		newUiOptions |= View.SYSTEM_UI_FLAG_FULLSCREEN;
		newUiOptions |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
		newUiOptions |= View.SYSTEM_UI_FLAG_IMMERSIVE;
		newUiOptions |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
		decorView.setSystemUiVisibility(newUiOptions);
	}
	
	public static void showSystemUI(Activity activity) {
		View decorView = activity.getWindow().getDecorView();
		int uiOptions = decorView.getSystemUiVisibility();
		int newUiOptions = uiOptions;
		newUiOptions &= ~View.SYSTEM_UI_FLAG_LOW_PROFILE;
		newUiOptions &= ~View.SYSTEM_UI_FLAG_FULLSCREEN;
		newUiOptions &= ~View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
		newUiOptions &= ~View.SYSTEM_UI_FLAG_IMMERSIVE;
		newUiOptions &= ~View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
		decorView.setSystemUiVisibility(newUiOptions);
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		hideSystemUI(this);
	}
	
	@Override
	public void onBackPressed()
	{
		if(backButtonCount >= 1)
		{
			backPressed.cancel();
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
		}
		else
		{
			backPressed.show();
			backButtonCount++;
		}
	}
}