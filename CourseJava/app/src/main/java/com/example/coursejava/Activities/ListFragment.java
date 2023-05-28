package com.example.coursejava.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.coursejava.R;
import com.example.coursejava.Task;
import com.example.coursejava.TaskViewModel;
import com.example.coursejava.databinding.FragmentListBinding;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ListFragment extends Fragment
{
	private TaskViewModel taskViewModel;
	FragmentListBinding binding;
	ActivityResultLauncher<Intent> activityResultLauncher;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState)
	{
		binding = FragmentListBinding.inflate(inflater,container,false);
		View view = binding.getRoot();
		return view;
	}
	
	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		
		taskViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getActivity().getApplication())).get(TaskViewModel.class);
		
		activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>()
		{
			@Override
			public void onActivityResult(ActivityResult result)
			{
				if (result.getResultCode() == 1)
				{
					String title = result.getData().getStringExtra("title");
					String desc = result.getData().getStringExtra("desdc");
					String date = result.getData().getStringExtra("date");
					String time = result.getData().getStringExtra("time");
					LocalDate localDate = LocalDate.parse(date);
					LocalTime localTime = LocalTime.parse(time);
					LocalDateTime localDateTime = localDate.atTime(localTime);
					Task task = new Task(title, desc, localDateTime);
					taskViewModel.insert(task);
					Toast.makeText(ListFragment.this.getActivity(),"task added", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		binding.floatingActionButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				Intent intent = new Intent(getActivity(), TaskInsertActivity.class);
				activityResultLauncher.launch(intent);
			}
		});
	}
}