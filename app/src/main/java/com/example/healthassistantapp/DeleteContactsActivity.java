package com.example.healthassistantapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.healthassistantapp.adaptor.ContactAdaptor;
import com.example.healthassistantapp.adaptor.DeleteContactAdaptor;
import com.example.healthassistantapp.database.DatabaseHelper;

public class DeleteContactsActivity extends AppCompatActivity {

    private RecyclerView deleteContactsRV;
    private DatabaseHelper db;
    private DeleteContactAdaptor deleteContactAdaptor;
    TextView emptyText;
    ImageView emptyImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_contacts);

        db = new DatabaseHelper(this);
        deleteContactsRV = findViewById(R.id.deleteContactsRV);
        emptyImg = findViewById(R.id.emptyIconDel);
        emptyText = findViewById(R.id.emptyDeleteContactsTxt);
        deleteContactsRV.setHasFixedSize(true);
        loadContacts();
        toggleIconVisibility();
    }

    private void loadContacts() {
        deleteContactAdaptor = new DeleteContactAdaptor(this, db.getAllContacts(), emptyText, emptyImg);
        deleteContactsRV.setAdapter(deleteContactAdaptor);
    }

    @Override
    protected void onResume(){
        super.onResume();
        loadContacts();
    }

    private void toggleIconVisibility(){
        if(db.getContactsCount() == 0){
            emptyImg.setVisibility(View.VISIBLE);
            emptyText.setVisibility(View.VISIBLE);
        }else {
            emptyImg.setVisibility(View.GONE);
            emptyText.setVisibility(View.GONE);
        }
    }
}