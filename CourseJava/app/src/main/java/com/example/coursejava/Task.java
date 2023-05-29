package com.example.coursejava;


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
	private LocalDate date;
	
	@TypeConverters({DateTimeConverter.class})
	private LocalTime time;
	
	@PrimaryKey(autoGenerate = true)
	private int id;
	
	public void setId(int id) {this.id = id;}
	
	public int getId()
	{
		return id;
	}
	
	
	
	public Task(String title, String desc, LocalDate date, LocalTime time)
	{
		this.title = title;
		this.desc = desc;
		this.date = date;
		this.time = time;
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
	
	public LocalDate getDate() {return date;}
	
	public void setDate(LocalDate date) {this.date = date;}
	
	public LocalTime getTime()
	{
		return time;
	}
	
	public void setTime(LocalTime time)
	{
		this.time = time;
	}
}
