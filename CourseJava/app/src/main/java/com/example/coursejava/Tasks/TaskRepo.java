package com.example.coursejava.Tasks;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepo
{
	private TaskDao taskDao;
	private LiveData<List<Task>> tasklist;
	
	public TaskRepo (Application application)
	{
		TaskDatabase taskDatabase = TaskDatabase.getInstance(application);
		taskDao = taskDatabase.taskDao();
		tasklist = taskDao.getAllData();
	}
	
	public void insertData(Task task){new Thread(new InsertTask(taskDao,task)).start();}
	public void updateData(Task task){new Thread(new UpdateTask(taskDao,task)).start();}
	public void deleteData(Task task){new Thread(new DeleteTask(taskDao,task)).start();}
	public LiveData<List<Task>> getAllData()
	{
		return tasklist;
	}
	
	private static class InsertTask implements Runnable
	{
		private TaskDao taskDao;
		private Task task;
		
		public InsertTask(TaskDao taskDao,Task task)
		{
			this.taskDao = taskDao;
			this.task = task;
		}


		@Override
		public void run()
		{
			taskDao.insert(task);
		}
	}

	private static class UpdateTask implements Runnable
	{
		private TaskDao taskDao;
		private Task task;

		public UpdateTask(TaskDao taskDao,Task task)
		{
			this.taskDao = taskDao;
			this.task = task;
		}


		@Override
		public void run()
		{
			taskDao.update(task);
		}
	}

	private static class DeleteTask implements Runnable
	{
		private TaskDao taskDao;
		private Task task;

		public DeleteTask(TaskDao taskDao,Task task)
		{
			this.taskDao = taskDao;
			this.task = task;
		}


		@Override
		public void run()
		{
			taskDao.delete(task);
		}
	}
}
