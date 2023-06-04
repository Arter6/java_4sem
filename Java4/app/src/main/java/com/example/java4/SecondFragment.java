package com.example.java4;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondFragment extends Fragment
{
	View viewLayout;
	private ArrayList<String> texts = new ArrayList<>();
	private ArrayList<Integer> colors = new ArrayList<>();
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState)
	{
		viewLayout = inflater.inflate(R.layout.fragment_second, container, false);
		ListView listView = viewLayout.findViewById(R.id.list_view);
		for (int i=0;i<200;i++)
		{
			texts.add(Integer.toString(i));
			colors.add(Color.rgb(10*i % 256,10*i % 256,10*i % 256));
		}
		
		ListAdapter listAdapter = new ListAdapter(this.getContext(),texts.toArray(new String[0]),colors.toArray(new Integer[0]));
		listView.setAdapter(listAdapter);
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
			{
				Log.d("ListView","LV number " + i);
				Toast.makeText(view.getContext(),"LV number " + i,Toast.LENGTH_SHORT).show();
			}
		});
		return viewLayout;
	}
}