package com.example.coursejava.Stats;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.coursejava.Tasks.DateTimeConverter;

import java.time.LocalDateTime;

@Entity(tableName = "stats")
public class Stat
{
	@TypeConverters({DateTimeConverter.class})
	private LocalDateTime start;
	@TypeConverters({DateTimeConverter.class})
	private LocalDateTime end;
	@PrimaryKey(autoGenerate = false)
	private int id;
	
	public Stat(LocalDateTime start)
	{
		this.start = start;
		this.end = null;
	}
	
	public LocalDateTime getStart()
	{
		return start;
	}
	
	public void setStart(LocalDateTime start)
	{
		this.start = start;
	}
	
	public LocalDateTime getEnd()
	{
		return end;
	}
	
	public void setEnd(LocalDateTime end)
	{
		this.end = end;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
}
