package com.example.java8;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "datatable")
public class Data
{
	@PrimaryKey(autoGenerate = true)
	private int id;
	
	private String text;
	
	public Data(String text)
	{
		this.text = text;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getText()
	{
		return text;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
}
