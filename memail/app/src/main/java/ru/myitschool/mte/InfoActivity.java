package ru.myitschool.mte;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ru.myitschool.mte.databinding.ActivityInfoBinding;

public class InfoActivity extends AppCompatActivity {

    private ActivityInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}