package com.example.coursejava.Tasks;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Task.class,version = 6)
public abstract class TaskDatabase extends RoomDatabase
{
	private static TaskDatabase instance;
	public abstract TaskDao taskDao();
	public static synchronized TaskDatabase getInstance(Context context)
	{
		if (instance == null)
		{
			instance = Room.databaseBuilder(context.getApplicationContext()
					,TaskDatabase.class,"task_database").fallbackToDestructiveMigration()
					.allowMainThreadQueries()
					.build();
		}
		return instance;
	}
}
