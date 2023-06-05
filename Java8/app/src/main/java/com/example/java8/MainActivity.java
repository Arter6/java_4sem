package com.example.java8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.BuildCompat;
import androidx.room.Dao;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.util.Half;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		EditText editText = findViewById(R.id.editTextText);
		TextView textView = findViewById(R.id.textView);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && !Environment.isExternalStorageManager())
		{
			Uri uri = Uri.parse("package:" + BuildConfig.APPLICATION_ID);
			startActivity(new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, uri));
		}
		
		findViewById(R.id.button1).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				writeFile(editText.getText().toString());
				textView.setText(readFile());
			}
		});
		
		findViewById(R.id.button2).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
				{
					if (Environment.isExternalStorageManager())
					{
						Log.d("FFF","Granted");
						writeFileCommon(editText.getText().toString());
						textView.setText(readFileCommon());
					}
					else
					{
						Log.d("FFF","Denied");
					}
				}
			}
		});
		
		findViewById(R.id.button3).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = sharedPreferences.edit();
				editor.putString("shared",editText.getText().toString());
				editor.apply();
				textView.setText(sharedPreferences.getString("shared",""));
			}
		});
		
		findViewById(R.id.button4).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				Database database = Room.databaseBuilder(getApplicationContext(),Database.class,"database")
						.allowMainThreadQueries().fallbackToDestructiveMigration().build();
				DataDao dao = database.dataDao();
				Runnable runnable = new Runnable()
				{
					@Override
					public void run()
					{
						dao.insertData(new Data(editText.getText().toString()));
					}
				};
				new Handler().postDelayed(runnable,1);
				textView.setText(dao.getAllData().toString());
			}
		});
	}
	
	void writeFile(String text)
	{
		try
		{
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
					getApplicationContext().openFileOutput("somefile", Context.MODE_PRIVATE)));
			bufferedWriter.write(text);
			bufferedWriter.close();
			Log.d("RRR","Write success");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	String readFile()
	{
		StringBuilder output = new StringBuilder();
		try
		{
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
					getApplicationContext().openFileInput("somefile")));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return output.toString();
	}
	
	void writeFileCommon(String text)
	{
		File file = new File(Environment.getExternalStorageDirectory(), "example");
		try
		{
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
			outputStreamWriter.write(text);
			outputStreamWriter.close();
			fileOutputStream.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	String readFileCommon()
	{
		File file = new File(Environment.getExternalStorageDirectory(), "example");
		StringBuilder stringBuilder = new StringBuilder();
		try
		{
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			String s;
			while ((s = bufferedReader.readLine()) != null)
			{
				stringBuilder.append(s);
			}
			bufferedReader.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}
}