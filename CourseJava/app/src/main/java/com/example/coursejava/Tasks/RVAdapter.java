package com.example.coursejava.Tasks;

import static com.example.coursejava.Tasks.DateTimeConverter.dateFormatter;
import static com.example.coursejava.Tasks.DateTimeConverter.datetimeFormatter;
import static com.example.coursejava.Tasks.DateTimeConverter.timeFormatter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursejava.R;
import com.example.coursejava.databinding.RvBinding;

public class RVAdapter extends ListAdapter<Task,RVAdapter.ViewHolder>
{
	
	public RVAdapter()
	{
		super(CALLBACK);
	}
	
	private static final DiffUtil.ItemCallback<Task> CALLBACK = new DiffUtil.ItemCallback<Task>()
	{
		@Override
		public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem)
		{
			return oldItem.getId()== newItem.getId();
		}
		
		@Override
		public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem)
		{
			return oldItem.getId() != newItem.getId()
					&& oldItem.getTitle().equals(newItem.getTitle())
					&& oldItem.getDesc().equals(newItem.getDesc())
					&& oldItem.getDate().equals(newItem.getDate());
		}
	};
	
	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv,parent,false);
		return new ViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position)
	{
		Task task =getItem(position);
		holder.binding.titleRv.setText(task.getTitle());
		holder.binding.descRv.setText(task.getDesc());
		holder.binding.dateRv.setText(task.getDate().format(datetimeFormatter));
	}
	
	public Task getTask(int position)
	{
		return getItem(position);
	}
	
	public class ViewHolder extends RecyclerView.ViewHolder
	{
		RvBinding binding;
		public ViewHolder(@NonNull View itemView)
		{
			super(itemView);
			binding = RvBinding.bind(itemView);
		}
	}
}
