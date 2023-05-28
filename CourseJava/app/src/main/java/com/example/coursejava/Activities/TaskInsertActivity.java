package com.example.coursejava.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.coursejava.R;
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
		
		binding.addTask.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.putExtra("title",binding.addTaskTitle.getText().toString());
				intent.putExtra("desc",binding.addTaskTitle.getText().toString());
				intent.putExtra("date",binding.addTaskTitle.getText().toString());
				intent.putExtra("time",binding.addTaskTitle.getText().toString());
				setResult(RESULT_OK,intent);
				finish();
			}
		});
	}
}