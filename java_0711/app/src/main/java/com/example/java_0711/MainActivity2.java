package com.example.java_0711;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity
{
	
	private TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		textView=findViewById(R.id.textView2);
		Bundle bundle= getIntent().getExtras();
		if (bundle!=null)
		{
			textView.setText(Integer.toString(bundle.getInt(MainActivity.KEY)));
		}
	}
}