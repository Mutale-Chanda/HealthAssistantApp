package com.example.healthassistantapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthassistantapp.database.DatabaseHelper;
import com.example.healthassistantapp.model.Message;
import com.google.android.material.textfield.TextInputEditText;

public class SOSMessageActivity extends AppCompatActivity {

    Button saveMsgBtn;
    TextInputEditText msgInput;
    Message storedMsg;
    DatabaseHelper db;
    TextView toolbarTitle;
    Toolbar toolbar;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sosmessage);

        saveMsgBtn = findViewById(R.id.saveMessage_btn);
        msgInput = findViewById(R.id.SOSMessage);
        toolbar = findViewById(R.id.message_toolbar);
        db = DatabaseHelper.getInstance(getApplicationContext());

        configActionBar("SOS MESSAGE");
        loadMessage();

        saveMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msgInputString = msgInput.getText().toString();

                if(!msgInputString.isEmpty()) {
                    if(msgInputString.length() < 7){
                        Toast.makeText(SOSMessageActivity.this, "SOS message must be at least 7 characters.", Toast.LENGTH_SHORT).show();
                    }else {
                        boolean isUpdated = db.updateMsg(new Message(msgInputString));
                        statusBasedToast(isUpdated);
                    }
                }else{
                    Toast.makeText(SOSMessageActivity.this, "Please enter your SOS message.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadMessage() {
        Message msg = db.getStoredMsg();
        msgInput.setText(msg.getMessage());
    }

    private void statusBasedToast(boolean isUpdated){
        if(isUpdated){
            Toast.makeText(SOSMessageActivity.this, "Message updated successfully.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(SOSMessageActivity.this, "Error updating message.", Toast.LENGTH_SHORT).show();
        }
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

}