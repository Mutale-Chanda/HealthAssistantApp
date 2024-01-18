package com.example.healthassistantapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView BMI_opt = findViewById(R.id.BMI_option);
        CardView healthFacilities_opt = findViewById(R.id.location_option);
        CardView SOS_opt = findViewById(R.id.SOS_option);
        CardView aboutApp_opt = findViewById(R.id.about_option);
        TextView aboutDev_opt = findViewById(R.id.aboutDev_TextView);

        BMI_opt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BMICalculatorActivity.class));
            }
        });

        healthFacilities_opt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"nearby locations clicked", Toast.LENGTH_SHORT).show();
            }
        });

        SOS_opt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SOSMainActivity.class));
            }
        });

        aboutApp_opt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"a clicked", Toast.LENGTH_SHORT).show();
            }
        });

        aboutDev_opt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"1001 clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}