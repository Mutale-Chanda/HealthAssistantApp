package com.example.healthassistantapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.healthassistantapp.adaptor.ContactAdaptor;
import com.example.healthassistantapp.database.DatabaseHelper;

public class ViewContactsActivity extends AppCompatActivity {

    private RecyclerView contactRV;
    private DatabaseHelper db;
    private ContactAdaptor contactAdaptor;
    TextView emptyText;
    ImageView emptyImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacts);

        db = new DatabaseHelper(this);
        contactRV = findViewById(R.id.viewContactsRV);
        emptyImg = findViewById(R.id.emptyIcon);
        emptyText = findViewById(R.id.emptyContactsTxt);
        contactRV.setHasFixedSize(true);
        loadContacts();

        if(db.getContactsCount() <= 0){
            emptyImg.setVisibility(View.VISIBLE);
            emptyText.setVisibility(View.VISIBLE);
        }else{
            emptyImg.setVisibility(View.GONE);
            emptyText.setVisibility(View.GONE);
        }
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