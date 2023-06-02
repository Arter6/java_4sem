package com.example.coursejava.Stats;

import android.util.Log;

import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class BarChartXAxisValueFormatter extends IndexAxisValueFormatter
{
	@Override
	public String getFormattedValue(float value)
	{
		long emissionsMilliSince1970Time = ((long) value) * 24 * 60 * 60 * 1000;
		
		LocalDate timeMilliseconds = new Date(emissionsMilliSince1970Time).toInstant().atZone(ZoneId.of("Europe/Moscow")).toLocalDate();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM");
		
		return formatter.format(timeMilliseconds);
	}
}