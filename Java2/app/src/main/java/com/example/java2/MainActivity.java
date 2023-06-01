package com.example.java2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

	public void onButtonClickDecl (View view)
	{
		Log.d("RRR","declarative");
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.constraint_layout);
		TextView textView=findViewById(R.id.textView3);
		Button button = findViewById(R.id.button3);
		EditText editText = findViewById(R.id.editTextText3);
		ImageView imageView = findViewById(R.id.imageView3);
		textView.setText("Java_3");
		imageView.setImageResource(R.drawable.splash_icon);
		button.setText("Нажми на меня");
		
		button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				Log.d("RRR","program");
			}
		});
		
		Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
		intent.putExtra("value","xxx");
		startActivity(intent);
	}
}