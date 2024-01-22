package com.example.healthassistantapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.healthassistantapp.database.DatabaseHelper;
import com.example.healthassistantapp.model.Contact;
import com.example.healthassistantapp.model.Message;

import java.util.List;

public class SOSMainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    Button SOSMenu_btn;
    Button SOS_btn;
    Message SOSMessage;
    List<Contact> contactList;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sosmain);

        SOSMenu_btn = findViewById(R.id.SOSMenu_btn);
        SOS_btn = findViewById(R.id.SOS_btn);
        db = new DatabaseHelper(this);

        SOSMenu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SOSMainActivity.this, SOSMenuActivity.class));
            }
        });

        SOS_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadContacts();
                loadMessage();

                if(contactList.size() > 0) {
                    requestPermissionAndSendSMS();
                }else{
                    //show error dialog for empty contact list || toast
                    Toast.makeText(getApplicationContext(), "Error, you have not registered any contacts.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void requestPermissionAndSendSMS(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_CODE);
        }else{
            sendMessage();
        }
    }

    private void sendMessage() {
        String msg = SOSMessage.getMessage();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            contactList.forEach(contact -> {
                smsManager.sendTextMessage(contact.getContactNumber(), null, msg, null, null);
                Toast.makeText(this, "sent message to " + contact.getContactName(), Toast.LENGTH_SHORT).show();
            });
        }catch (Exception e){
            Toast.makeText(this, "error sending message.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                int grantResult = grantResults[i];
                if (permission.equals(Manifest.permission.SEND_SMS) && grantResult == PackageManager.PERMISSION_GRANTED) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        sendMessage();
                    } else {
                        Toast.makeText(this, "Permission denied. Message not sent.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    private void loadMessage() {
        SOSMessage = db.getStoredMsg();
    }

    private void loadContacts() {
        contactList = db.getAllContacts();
    }

}
