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

import com.example.coursejava.Stats.BarChartXAxisValueFormatter;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
		
		statViewModel.getAllStats().observe(this, new Observer<List<Stat>>()
		{
			@Override
			public void onChanged(List<Stat> stats)
			{
				
				for (Stat stat : stats)
				{
					if (stat.getEnd() != null && stat.getEnd().isBefore(LocalDateTime.now().minusDays(6)))
					{
						statViewModel.delete(stat);
					}
				}
				
				BarChart barChart = binding.StatBar;
				barChart.getXAxis().setValueFormatter(new BarChartXAxisValueFormatter());
				
				ArrayList<BarEntry> barEntryArrayList = new ArrayList<>();
				
				int[] yAxis = new int[7];
				
				for (Stat stat : stats)
				{
					for (int i=0;i<7;i++)
					{
						LocalDate dayBefore = LocalDate.now().minusDays(7-i);
						LocalDate dayAfter = LocalDate.now().minusDays(5-i);
						if (stat.getEnd() != null && stat.getEnd().toLocalDate().isAfter(dayBefore) && stat.getEnd().toLocalDate().isBefore(dayAfter) && stat.getEnd().isAfter(stat.getStart()))
						{
							yAxis[i]+=1;
							break;
						}
					}
				}
				
				for (int i=0;i<7;i++)
				{
					long dayTime = Calendar.getInstance().getTimeInMillis() - (6 - i) * (24 * 3600000);
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
		startActivity(new Intent(this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
	}
}