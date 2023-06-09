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
	
	public void insertData(Stat stat){new Thread(new InsertStat(statDao,stat)).start();}
	public void updateData(Stat stat){new Thread(new UpdateStat(statDao,stat)).start();}
	public void deleteData(Stat stat){new Thread(new DeleteStat(statDao,stat)).start();}
	public LiveData<List<Stat>> getAllData()
	{
		return statlist;
	}
	
	private static class InsertStat implements Runnable
	{
		private StatDao statDao;
		private Stat stat;
		
		public InsertStat(StatDao statDao,Stat stat)
		{
			this.statDao = statDao;
			this.stat = stat;
		}

		@Override
		public void run() {
			statDao.insert(stat);
		}
	}

	private static class UpdateStat implements Runnable
	{
		private StatDao statDao;
		private Stat stat;

		public UpdateStat(StatDao statDao,Stat stat)
		{
			this.statDao = statDao;
			this.stat = stat;
		}

		@Override
		public void run() {
			statDao.update(stat);
		}
	}

	private static class DeleteStat implements Runnable
	{
		private StatDao statDao;
		private Stat stat;

		public DeleteStat(StatDao statDao,Stat stat)
		{
			this.statDao = statDao;
			this.stat = stat;
		}

		@Override
		public void run() {
			statDao.delete(stat);
		}
	}
}
