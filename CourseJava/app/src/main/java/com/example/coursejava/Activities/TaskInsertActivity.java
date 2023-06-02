package com.example.coursejava.Activities;

import static com.example.coursejava.Activities.MainActivity.showSystemUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coursejava.Tasks.Task;
import com.example.coursejava.databinding.ActivityTaskInsertBinding;

public class TaskInsertActivity extends AppCompatActivity
{
	ActivityTaskInsertBinding binding;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		binding = ActivityTaskInsertBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		
//		showSystemUI(this);
		
		binding.backButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				startActivity(new Intent(TaskInsertActivity.this,MainActivity.class));
			}
		});
		
		String type = getIntent().getStringExtra("type");
		if (type.equals("update"))
		{
			setTitle("update");
			String text = getIntent().getStringExtra("title");
			String desc = getIntent().getStringExtra("desc");
			String date = getIntent().getStringExtra("date");
			String time = getIntent().getStringExtra("time");
			binding.addTaskTitle.setText(text);
			binding.addTaskDescription.setText(desc);
			binding.taskDate.setText(date);
			binding.taskTime.setText(time);
			int id = getIntent().getIntExtra("id",0);
			binding.addTask.setText("Update task");
			binding.addTask.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View view)
				{
					Intent intent = new Intent();
					intent.putExtra("title", binding.addTaskTitle.getText().toString());
					intent.putExtra("desc", binding.addTaskDescription.getText().toString());
					intent.putExtra("date", binding.taskDate.getText().toString());
					intent.putExtra("time", binding.taskTime.getText().toString());
					intent.putExtra("id",id);
					setResult(2, intent);
					finish();
				}
			});
		}
		else
		{
			setTitle("add");
			binding.addTask.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					Intent intent = new Intent();
					intent.putExtra("title", binding.addTaskTitle.getText().toString());
					intent.putExtra("desc", binding.addTaskDescription.getText().toString());
					intent.putExtra("date", binding.taskDate.getText().toString());
					intent.putExtra("time", binding.taskTime.getText().toString());
					setResult(1, intent);
					finish();
				}
			});
		}
	}
	
	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
		startActivity(new Intent(TaskInsertActivity.this, MainActivity.class));
	}
}