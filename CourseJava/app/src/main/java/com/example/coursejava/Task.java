package com.example.coursejava;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task
{
	private String title;
	private String desc;
	
	@PrimaryKey(autoGenerate = true)
	private int id;
	
	public int getId()
	{
		return id;
	}
	
	public Task(String title, String desc)
	{
		this.title = title;
		this.desc = desc;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getDesc()
	{
		return desc;
	}
	
	public void setDesc(String desc)
	{
		this.desc = desc;
	}
}
