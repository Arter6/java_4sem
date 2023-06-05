package com.example.java8;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DataDao
{
	@Query("SELECT * FROM datatable")
	List<Data> getAllData();
	
	@Insert
	void insertData(Data data);
}
