package com.example.coursejava.Stats;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.coursejava.Tasks.Task;
import com.example.coursejava.Tasks.TaskDao;
import com.example.coursejava.Tasks.TaskRepo;

import java.util.List;

public class StatRepo
{
	private StatDao statDao;
	private LiveData<List<Stat>> statlist;
	
	public StatRepo(Application application)
	{
		StatDatabase statDatabase = StatDatabase.getInstance(application);
		statDao = statDatabase.statDao();
		statlist = statDao.getAllData();
	}
	
	public void insertData(Stat stat){new StatRepo.InsertStat(statDao).execute(stat);}
	public void updateData(Stat stat){new StatRepo.UpdateStat(statDao).execute(stat);}
	public void deleteData(Stat stat){new StatRepo.DeleteStat(statDao).execute(stat);}
	public LiveData<List<Stat>> getAllData()
	{
		return statlist;
	}
	
	private static class InsertStat extends AsyncTask<Stat,Void,Void>
	{
		private StatDao statDao;
		
		public InsertStat(StatDao statDao)
		{
			this.statDao = statDao;
		}
		
		@Override
		protected Void doInBackground(Stat... stats)
		{
			statDao.insert(stats[0]);
			return null;
		}
	}
	
	private static class DeleteStat extends AsyncTask<Stat,Void,Void>
	{
		private StatDao statDao;
		
		public DeleteStat(StatDao statDao)
		{
			this.statDao = statDao;
		}
		
		@Override
		protected Void doInBackground(Stat... stats)
		{
			statDao.delete(stats[0]);
			return null;
		}
	}
	
	private static class UpdateStat extends AsyncTask<Stat,Void,Void>
	{
		private StatDao statDao;
		
		public UpdateStat(StatDao statDao)
		{
			this.statDao = statDao;
		}
		
		@Override
		protected Void doInBackground(Stat... stats)
		{
			statDao.update(stats[0]);
			return null;
		}
	}
}
