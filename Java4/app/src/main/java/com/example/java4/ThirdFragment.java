package com.example.java4;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ThirdFragment extends Fragment implements RecyclerInterface
{
	View viewLayout;
	ArrayList<String> texts = new ArrayList<>();
	ArrayList<Integer> colors = new ArrayList<>();
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState)
	{
		viewLayout = inflater.inflate(R.layout.fragment_third, container, false);
		RecyclerView recyclerView = viewLayout.findViewById(R.id.recycler_view);
		for (int i=0;i<200;i++)
		{
			texts.add(Integer.toString(i));
			colors.add(Color.rgb((10*i) % 256,(10*i) % 256,(10*i) % 256));
		}
		
		RecyclerAdapter recyclerAdapter = new RecyclerAdapter(texts.toArray(new String[0]), colors.toArray(new Integer[0]),this);
		recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
		recyclerView.setAdapter(recyclerAdapter);
		
		return viewLayout;
	}
	
	@Override
	public void onItemClick(View view, int position)
	{
		Log.d("RecyclerView","RV number " + position);
		Toast.makeText(view.getContext(),"RV number " + position,Toast.LENGTH_SHORT).show();
	}
}