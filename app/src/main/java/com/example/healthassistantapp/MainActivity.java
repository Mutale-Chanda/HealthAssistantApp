package com.example.healthassistantapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CardView BMI_opt, healthFacilities_opt, SOS_opt, aboutApp_opt;
    TextView aboutDev_opt, toolbarTitle;
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
        aboutDev_opt = findViewById(R.id.aboutDev_TextView);
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

    public void configActionBar(String title){
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        View customView = getLayoutInflater().inflate(R.layout.custom_toolbar_layout, null);
        if(actionBar != null){
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(customView);
            //actionBar.setDisplayHomeAsUpEnabled(true); //enable back button
            toolbarTitle = customView.findViewById(R.id.toolBarTitle);
            toolbarTitle.setText(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}