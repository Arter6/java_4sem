package com.example.coursejava.Activities;

//import static com.example.coursejava.DateTimeConverter.formatter;

import static com.example.coursejava.DateTimeConverter.dateFormatter;
import static com.example.coursejava.DateTimeConverter.timeFormatter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.coursejava.R;
import com.example.coursejava.RVAdapter;
import com.example.coursejava.Task;
import com.example.coursejava.TaskViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import com.example.coursejava.databinding.ActivityMainBinding;

import android.view.MenuItem;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
	
	ActivityMainBinding binding;
	BottomNavigationView bottomNavigationView;
	TaskViewModel taskViewModel;
	ActivityResultLauncher activityResultLauncher;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		
		hideSystemUI(this);
		
		bottomNavigationView = binding.bottomNavigation;
		bottomNavigationView.setSelectedItemId(R.id.list);
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
					Task task = new Task(title, desc, localDate, localTime);
					taskViewModel.insert(task);
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
					Task task = new Task(title, desc, localDate, localTime);
					task.setId(result.getData().getIntExtra("id",0));
					taskViewModel.update(task);
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
					taskViewModel.delete(rvAdapter.getTask(viewHolder.getAdapterPosition()));
				}
				else
				{
					Intent intent = new Intent(MainActivity.this, TaskInsertActivity.class);
					intent.putExtra("type","update");
					intent.putExtra("title",rvAdapter.getTask(viewHolder.getAdapterPosition()).getTitle());
					intent.putExtra("desc",rvAdapter.getTask(viewHolder.getAdapterPosition()).getDesc());
					intent.putExtra("date",rvAdapter.getTask(viewHolder.getAdapterPosition()).getDate().format(dateFormatter));
					intent.putExtra("time",rvAdapter.getTask(viewHolder.getAdapterPosition()).getTime().format(timeFormatter));
					intent.putExtra("id",rvAdapter.getTask(viewHolder.getAdapterPosition()).getId());
					activityResultLauncher.launch(intent);
				}
			}
		}).attachToRecyclerView(binding.recyclerView);
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
	
	@Override
	protected void onResume()
	{
		super.onResume();
		hideSystemUI(this);
	}
}