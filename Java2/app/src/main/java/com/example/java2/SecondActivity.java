package com.example.java2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private String text;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative_layout);
        text = getIntent().getStringExtra("value");
        TextView textView = findViewById(R.id.textView2);
        textView.setText(text);
    }
}