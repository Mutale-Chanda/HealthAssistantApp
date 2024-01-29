package com.example.healthassistantapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SOSMenuActivity extends AppCompatActivity {

    CardView addContact_opt, viewContacts_opt, deleteContacts_opt, editSOSMsg;
    Toolbar toolbar;
    ActionBar actionBar;
    TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sosmenu);

        addContact_opt = findViewById(R.id.addContact_option);
        viewContacts_opt = findViewById(R.id.viewContacts_option);
        deleteContacts_opt = findViewById(R.id.deleteContacts_option);
        editSOSMsg = findViewById(R.id.editMsg_option);
        toolbar = findViewById(R.id.sos_toolbar);

        configActionBar("SOS MENU");

        addContact_opt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SOSMenuActivity.this, RegisterContactActivity.class));
            }
        });

        viewContacts_opt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SOSMenuActivity.this, ViewContactsActivity.class));
            }
        });

        deleteContacts_opt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SOSMenuActivity.this, DeleteContactsActivity.class));
            }
        });

        editSOSMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SOSMenuActivity.this, SOSMessageActivity.class));
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
        findViewById(R.id.back_btn).setOnClickListener(view -> onBackPressed());
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        if (item.getItemId() == android.R.id.home) {
//            onBackPressed();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}