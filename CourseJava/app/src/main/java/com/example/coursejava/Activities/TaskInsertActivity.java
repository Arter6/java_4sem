package com.example.coursejava.Activities;

import static com.example.coursejava.Activities.MainActivity.showSystemUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
					if (binding.taskDate.getText().toString().matches("^(?:(?:31(\\/|-|\\.)(?:0[13578]|1[02]))\\1|(?:(?:29|30)(\\.)(?:0[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)\\d{2})$|^(?:29(\\.)02\\3(?:(?:(?:1[6-9]|[2-9]\\d)(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0[1-9]|1\\d|2[0-8])(\\.)(?:(?:0[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)\\d{2})$")
						&& binding.taskTime.getText().toString().matches("^([0-1][0-9]|2[0-3]):[0-5][0-9]$"))
					{
						Intent intent = new Intent();
						intent.putExtra("title", binding.addTaskTitle.getText().toString());
						intent.putExtra("desc", binding.addTaskDescription.getText().toString());
						intent.putExtra("date", binding.taskDate.getText().toString());
						intent.putExtra("time", binding.taskTime.getText().toString());
						intent.putExtra("id", id);
						setResult(2, intent);
						finish();
					}
					else
					{
						Toast.makeText(TaskInsertActivity.this,"Wrong date or time format",Toast.LENGTH_SHORT).show();
					}
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