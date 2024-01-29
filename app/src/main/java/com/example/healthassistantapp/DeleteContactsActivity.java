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
import com.example.healthassistantapp.adaptor.DeleteContactAdaptor;
import com.example.healthassistantapp.database.DatabaseHelper;

public class DeleteContactsActivity extends AppCompatActivity {

    private RecyclerView deleteContactsRV;
    private DatabaseHelper db;
    private DeleteContactAdaptor deleteContactAdaptor;
    TextView emptyText, toolbarTitle;
    ImageView emptyImg;
    Toolbar toolbar;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_contacts);

        db = DatabaseHelper.getInstance(getApplicationContext());
        deleteContactsRV = findViewById(R.id.deleteContactsRV);
        emptyImg = findViewById(R.id.emptyIconDel);
        emptyText = findViewById(R.id.emptyDeleteContactsTxt);
        toolbar = findViewById(R.id.deleteContact_toolbar);
        deleteContactsRV.setHasFixedSize(true);

        loadContacts();
        toggleIconVisibility();
        configActionBar("DELETE CONTACTS");
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