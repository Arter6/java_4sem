package com.example.coursejava.Stats;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StatViewModel extends AndroidViewModel
{
	private StatRepo statRepo;
	private LiveData<List<Stat>> statlist;
	
	public StatViewModel(@NonNull Application application)
	{
		super(application);
		statRepo = new StatRepo(application);
		statlist = statRepo.getAllData();
	}
	
	public void insert(Stat stat)
	{
		statRepo.insertData(stat);
	}
	public void update(Stat stat)
	{
		statRepo.updateData(stat);
	}
	public void delete(Stat stat)
	{
		statRepo.deleteData(stat);
	}
	public LiveData<List<Stat>> getAllStats()
	{
		return statlist;
	}
}
