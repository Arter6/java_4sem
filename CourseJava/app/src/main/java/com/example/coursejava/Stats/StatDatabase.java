package com.example.coursejava.Stats;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Stat.class, version = 1)
public abstract class StatDatabase extends RoomDatabase
{
	private static StatDatabase instance;
	public abstract StatDao statDao();
	
	public static synchronized StatDatabase getInstance(Context context)
	{
		if (instance == null)
		{
			instance = Room.databaseBuilder(context.getApplicationContext()
					,StatDatabase.class,"stat_database").fallbackToDestructiveMigration()
					.allowMainThreadQueries()
					.build();
		}
		return instance;
	}
}
