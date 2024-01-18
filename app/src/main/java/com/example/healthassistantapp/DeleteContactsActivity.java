package com.example.healthassistantapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.healthassistantapp.adaptor.ContactAdaptor;
import com.example.healthassistantapp.adaptor.DeleteContactAdaptor;
import com.example.healthassistantapp.database.DatabaseHelper;

public class DeleteContactsActivity extends AppCompatActivity {

    private RecyclerView deleteContactsRV;
    private DatabaseHelper db;
    private DeleteContactAdaptor deleteContactAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_contacts);

        db = new DatabaseHelper(this);
        deleteContactsRV = findViewById(R.id.deleteContactsRV);
        deleteContactsRV.setHasFixedSize(true);
        loadContacts();
    }

    private void loadContacts() {
        deleteContactAdaptor = new DeleteContactAdaptor(this, db.getAllContacts());
        deleteContactsRV.setAdapter(deleteContactAdaptor);
    }

    @Override
    protected void onResume(){
        super.onResume();
        loadContacts();
    }
}