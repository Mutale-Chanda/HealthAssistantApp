package com.example.healthassistantapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SOSMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sosmenu);

        CardView addContact_opt = findViewById(R.id.addContact_option);
        CardView viewContacts_opt = findViewById(R.id.viewContacts_option);
        CardView deleteContacts_opt = findViewById(R.id.deleteContacts_option);
        CardView editSOSMsg = findViewById(R.id.editMsg_option);

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
}