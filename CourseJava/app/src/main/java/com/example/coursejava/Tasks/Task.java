package com.example.coursejava.Tasks;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity(tableName = "tasks")
public class Task
{
	private String title;
	private String desc;
	@TypeConverters({DateTimeConverter.class})
	private LocalDateTime date;
	
	@PrimaryKey(autoGenerate = true)
	private int id;
	
	public void setId(int id) {this.id = id;}
	
	public int getId()
	{
		return id;
	}
	
	
	
	public Task(String title, String desc, LocalDateTime date)
	{
		this.title = title;
		this.desc = desc;
		this.date = date;
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
	
	public LocalDateTime getDate()
	{
		return date;
	}
	
	public void setDate(LocalDateTime date)
	{
		this.date = date;
	}
}
