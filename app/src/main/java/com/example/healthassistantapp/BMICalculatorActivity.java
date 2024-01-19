package com.example.healthassistantapp;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class BMICalculatorActivity extends AppCompatActivity {

    Toolbar toolbar;
    ActionBar actionBar;
    TextView toolbarTitle, resultArea;
    EditText userHeight, userWeight;
    Button calculateBtn, clearBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator);

        userHeight = findViewById(R.id.userHeight_input);
        userWeight = findViewById(R.id.userWeight_input);
        calculateBtn = findViewById(R.id.calculateBMI_btn);
        clearBtn = findViewById(R.id.clear_btn);
        resultArea = findViewById(R.id.BMIResult);
        toolbar = findViewById(R.id.bmi_toolbar);

        configActionBar("BMI CALCULATOR");
        
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String heightStr = userHeight.getText().toString().trim();
                String weightStr = userWeight.getText().toString().trim();

                hideKeyboard(userHeight, userWeight);

                if(isValid(heightStr,weightStr)){
                    float userHeight = Float.parseFloat(heightStr);
                    float userWeight = Float.parseFloat(weightStr);

                    if(userHeight == 0){
                        toastMsg("Height can not be Zero.");
                    }else{
                        float result = calculateBMI(userHeight,userWeight);
                        toastMsg("Result: " + result);
                        resultArea.setText(String.valueOf(result));
                    }
                }else{
                    toastMsg("Invalid input. Please enter both values.");
                }
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userHeight.setText("");
                userWeight.setText("");
                resultArea.setText("-");
            }
        });
    }

    public float calculateBMI(float height, float weight){
        float bmi = weight / (height * height); //h: 180 && w:55 BMI:17
        float rounded = Math.round(bmi * 10);
        return rounded / 10;
    }

    public boolean isValid(String value1, String value2){
        return !value1.isEmpty() && !value2.isEmpty();
    }

    public void hideKeyboard(EditText view1, EditText view2){
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view1.getWindowToken(), 0);
        inputMethodManager.hideSoftInputFromWindow(view2.getWindowToken(), 0);
    }

    public void toastMsg(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void configActionBar(String title){
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        View customView = getLayoutInflater().inflate(R.layout.custom_toolbar_layout, null);
        if(actionBar != null){
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(customView);
            actionBar.setDisplayHomeAsUpEnabled(true); //enable back button
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