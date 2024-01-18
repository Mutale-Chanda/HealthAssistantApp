package com.example.healthassistantapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.healthassistantapp.adaptor.ContactAdaptor;
import com.example.healthassistantapp.database.DatabaseHelper;

public class ViewContactsActivity extends AppCompatActivity {

    private RecyclerView contactRV;
    private DatabaseHelper db;
    private ContactAdaptor contactAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacts);

        db = new DatabaseHelper(this);
        contactRV = findViewById(R.id.viewContactsRV);
        contactRV.setHasFixedSize(true);
        loadContacts();
    }

    private void loadContacts() {
        contactAdaptor = new ContactAdaptor(this,db.getAllContacts());
        contactRV.setAdapter(contactAdaptor);
    }

    @Override
    protected void onResume(){
        super.onResume();
        loadContacts();
    }
}