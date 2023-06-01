package com.example.java4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ListAdapter extends ArrayAdapter
{
	private LayoutInflater inflater;
	private String[] texts;
	private Integer[] colors;
	private Context context;
	
	public ListAdapter(Context context, String[] texts, Integer[] colors)
	{
		super(context,R.layout.view_element,texts);
		this.texts = texts;
		this.colors = colors;
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}
	
	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
	{
		View view = inflater.inflate(R.layout.view_element,parent,false);
		
		TextView textView = view.findViewById(R.id.textView);
		ImageView imageView = view.findViewById(R.id.imageView);
		textView.setText(texts[position]);
		imageView.setBackgroundColor(colors[position]);
		return view;
	}
}
