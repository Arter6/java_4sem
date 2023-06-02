package com.example.coursejava.Tasks;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao
{
	@Insert
	public void insert(Task task);
	
	@Insert
	public long insertWithId(Task task);
	
	@Update
	public void update(Task task);
	
	@Delete
	public void delete(Task task);
	
	@Query("SELECT * FROM tasks")
	public LiveData<List<Task>> getAllData();
	
	@Query("DELETE FROM tasks")
	public void clearTaskDatabase();
}
