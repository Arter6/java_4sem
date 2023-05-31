package com.example.coursejava.Activities;

import static com.example.coursejava.Activities.MainActivity.hideSystemUI;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.coursejava.BarChartXAxisValueFormatter;
import com.example.coursejava.R;
import com.example.coursejava.Stats.Stat;
import com.example.coursejava.Stats.StatDatabase;
import com.example.coursejava.Stats.StatViewModel;
import com.example.coursejava.databinding.ActivityStatisticsBinding;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class StatisticsActivity extends AppCompatActivity
{
	ActivityStatisticsBinding binding;
	BottomNavigationView bottomNavigationView;
	StatViewModel statViewModel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistics);
		
		binding = ActivityStatisticsBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		
		hideSystemUI(this);
		
		bottomNavigationView = binding.bottomNavigation;
		bottomNavigationView.setSelectedItemId(R.id.statistics);
//		bottomNavigationView.getMenu().getItem(1).setChecked(true);
		
		bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener()
		{
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item)
			{
				int itemId = item.getItemId();
				if (itemId == R.id.statistics)
				{
					return true;
				} else if (itemId == R.id.list)
				{
					startActivity(new Intent(getApplicationContext(),MainActivity.class));
					overridePendingTransition(0,0);
					return true;
				} else if (itemId == R.id.settings)
				{
					startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
					overridePendingTransition(0,0);
					return true;
				}
				return false;
			}
		});
		
		statViewModel = new ViewModelProvider(this,(ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(StatViewModel.class);
		List<Stat> statList = StatDatabase.getInstance(getApplicationContext()).statDao().getListData();
		for (Stat stat : statList)
		{
			if (stat.getEnd() != null && stat.getEnd().isBefore(LocalDateTime.now().minusDays(6).plusHours(3)))
			{
				statViewModel.delete(stat);
			}
		}
		
		statViewModel.getAllStats().observe(this, new Observer<List<Stat>>()
		{
			@Override
			public void onChanged(List<Stat> stats)
			{
				BarChart barChart = binding.StatBar;
				barChart.getXAxis().setValueFormatter(new BarChartXAxisValueFormatter());
				
				ArrayList<BarEntry> barEntryArrayList = new ArrayList<>();
				
				int[] yAxis = new int[7];
				
				for (Stat stat : stats)
				{
					for (int i=0;i<7;i++)
					{
						LocalDateTime dayBefore = LocalDateTime.now().plusHours(3).minusDays(7-i);
						LocalDateTime dayAfter = LocalDateTime.now().plusHours(3).minusDays(6-i);
						if (stat.getEnd() != null && stat.getEnd().isAfter(dayBefore) && stat.getEnd().isBefore(dayAfter) && stat.getEnd().isAfter(stat.getStart()))
						{
							yAxis[i]+=1;
							break;
						}
					}
				}
				
				for (int i=0;i<7;i++)
				{
					long dayTime = Calendar.getInstance().getTimeInMillis() - (6 - i) * (24 * 3600000) + 3 * 60 * 60 * 1000;
					dayTime = TimeUnit.MILLISECONDS.toDays(dayTime);
					barEntryArrayList.add(new BarEntry(dayTime,yAxis[i]));
				}
				
				BarDataSet barDataSet = new BarDataSet(barEntryArrayList, "Опоздания");
				barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
				barDataSet.setValueTextColor(Color.BLACK);
				barDataSet.setValueTextSize(16f);
				
				BarData barData = new BarData(barDataSet);
				
				barChart.setFitBars(true);
				barChart.setData(barData);
				barChart.getDescription().setText("Bar Chart X");
				barChart.animateY(2000);
			}
		});
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		hideSystemUI(this);
	}
	
	@Override
	public void onBackPressed()
	{
		startActivity(new Intent(this,MainActivity.class));
	}
}