package com.example.healthassistantapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    CardView BMI_opt, healthFacilities_opt, SOS_opt, aboutApp_opt;
    TextView toolbarTitle;
    Toolbar toolbar;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BMI_opt = findViewById(R.id.BMI_option);
        healthFacilities_opt = findViewById(R.id.location_option);
        SOS_opt = findViewById(R.id.SOS_option);
        aboutApp_opt = findViewById(R.id.about_option);
        toolbar = findViewById(R.id.main_toolbar);

        configActionBar("Health Assistant App");

        BMI_opt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BMICalculatorActivity.class));
            }
        });

        healthFacilities_opt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"coming soon.", Toast.LENGTH_SHORT).show();
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
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
            }
        });
    }

    public void configActionBar(String title) {
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            toolbarTitle = findViewById(R.id.toolBarTitle);
            toolbarTitle.setText(title);
        }
    }

}