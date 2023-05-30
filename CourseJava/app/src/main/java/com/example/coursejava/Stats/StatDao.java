package com.example.coursejava.Stats;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StatDao
{
	@Insert
	public void insert(Stat stat);
	
	@Update
	public void update(Stat stat);
	
	@Delete
	public void delete(Stat stat);
	
	@Query("SELECT * FROM stats")
	public LiveData<List<Stat>> getAllData();
}
