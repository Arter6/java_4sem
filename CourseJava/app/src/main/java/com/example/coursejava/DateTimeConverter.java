package com.example.coursejava;

import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateTimeConverter
{
	public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	public static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
	@TypeConverter
	public static LocalDate toDate(String dateString)
	{
		if (dateString == null)
		{
			return null;
		}
		else
		{
			return LocalDate.parse(dateString,dateFormatter);
		}
	}
	
	@TypeConverter
	public static String toTimeString(LocalDate date)
	{
		if (date == null)
		{
			return null;
		}
		else
		{
			return date.format(dateFormatter);
		}
	}
	
	@TypeConverter
	public static LocalTime toTime(String timeString)
	{
		if (timeString == null)
		{
			return null;
		}
		else
		{
			return LocalTime.parse(timeString,timeFormatter);
		}
	}
	
	@TypeConverter
	public static String toTimeString(LocalTime time)
	{
		if (time == null)
		{
			return null;
		}
		else
		{
			return time.format(timeFormatter);
		}
	}
}
