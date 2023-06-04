package com.example.java4;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{
	private final RecyclerInterface recyclerInterface;
	private String[] texts;
	private Integer[] colors;
	
	public RecyclerAdapter(String[] texts, Integer[] colors, RecyclerInterface recyclerInterface)
	{
		this.texts = texts;
		this.colors = colors;
		this.recyclerInterface = recyclerInterface;
	}
	
	@NonNull
	@Override
	public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.view_element,parent,false);
		return new ViewHolder(view, recyclerInterface);
	}
	
	@Override
	public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position)
	{
		holder.textView.setText(texts[position]);
		holder.imageView.setBackgroundColor(colors[position]);
	}
	
	@Override
	public int getItemCount()
	{
		return texts.length;
	}
	
	public static class ViewHolder extends RecyclerView.ViewHolder
	{
		final TextView textView;
		final ImageView imageView;
		public ViewHolder(@NonNull View itemView, RecyclerInterface recyclerInterface)
		{
			super(itemView);
			textView = itemView.findViewById(R.id.textView);
			imageView = itemView.findViewById(R.id.imageView);
			itemView.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View view)
				{
					if (recyclerInterface != null)
					{
						int pos = getAdapterPosition();
						
						if (pos != RecyclerView.NO_POSITION)
						{
							recyclerInterface.onItemClick(itemView, pos);
						}
					}
				}
			});
		}
	}
	
}
