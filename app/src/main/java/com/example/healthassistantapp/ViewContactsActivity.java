package com.example.healthassistantapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthassistantapp.adaptor.ContactAdaptor;
import com.example.healthassistantapp.database.DatabaseHelper;

public class ViewContactsActivity extends AppCompatActivity {

    private RecyclerView contactRV;
    private DatabaseHelper db;
    private ContactAdaptor contactAdaptor;
    TextView emptyText, toolbarTitle;
    ImageView emptyImg;
    Toolbar toolbar;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacts);

        db = DatabaseHelper.getInstance(getApplicationContext());
        contactRV = findViewById(R.id.viewContactsRV);
        emptyImg = findViewById(R.id.emptyIcon);
        emptyText = findViewById(R.id.emptyContactsTxt);
        toolbar = findViewById(R.id.viewContact_toolbar);
        contactRV.setHasFixedSize(true);

        loadContacts();
        configActionBar("REGISTERED CONTACTS");

        if (db.getContactsCount() <= 0) {
            emptyImg.setVisibility(View.VISIBLE);
            emptyText.setVisibility(View.VISIBLE);
        } else {
            emptyImg.setVisibility(View.GONE);
            emptyText.setVisibility(View.GONE);
        }
    }

    private void loadContacts() {
        contactAdaptor = new ContactAdaptor(this, db.getAllContacts());
        contactRV.setAdapter(contactAdaptor);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadContacts();
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
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == android.R.id.home) {
//            onBackPressed();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}