package com.diligre.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.widget.TextView;

public class Second extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = (TextView)findViewById(R.id.vi);
        Intent intent = getIntent();
        String name = intent.getStringExtra("json");
        textView.setText(name);
    }
}
