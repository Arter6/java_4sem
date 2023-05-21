package com.example.java_0711;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
	
	private Button btn;
	private TextView textView;
	private int counter=0;
	public static final String KEY="ccc";
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn=findViewById(R.id.Button);
		textView=findViewById(R.id.textView);
		btn.setOnClickListener(view -> {
			if(counter>10)
			{
				Intent intent = new Intent(MainActivity.this, MainActivity2.class);
				intent.putExtra(KEY, counter);
				startActivity(intent);
			}
			else
			{
				counter++;
				textView.setText(Integer.toString(counter));
			}
		});
	}
	
	@Override
	public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState)
	{
		super.onSaveInstanceState(outState, outPersistentState);
	}
	
	@Override
	protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState)
	{
		super.onRestoreInstanceState(savedInstanceState);
	}
}