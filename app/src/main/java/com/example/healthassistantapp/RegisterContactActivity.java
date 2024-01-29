package com.example.healthassistantapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthassistantapp.database.DatabaseHelper;
import com.example.healthassistantapp.model.Contact;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterContactActivity extends AppCompatActivity {

    TextInputEditText contactName;
    TextInputEditText contactNumber;
    Button saveContact;
    DatabaseHelper db;
    TextView toolbarTitle;
    Toolbar toolbar;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_contact);

        contactName = findViewById(R.id.contactName);
        contactNumber = findViewById(R.id.contactNumber);
        saveContact = findViewById(R.id.saveContact_btn);
        toolbar = findViewById(R.id.addContact_toolbar);
        db = DatabaseHelper.getInstance(getApplicationContext());

        configActionBar("REGISTER CONTACT");

        saveContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = contactName.getText().toString().trim();
                String number = contactNumber.getText().toString().trim();

                hideKeyboard(contactNumber);

                if(isValid(name,number)){
                    if(number.length() < 10 || number.length() > 12){
                        toastMsg("contact number is invalid");
                    }else{
                        Contact contact = new Contact(name, number);
                        boolean isSaved = db.addContact(contact);
                        if(isSaved){
                            clearInputs();
                            startActivity(new Intent(RegisterContactActivity.this, ViewContactsActivity.class));
                        }
                    }
                }else{
                    toastMsg("Invalid input. Please enter both values.");
                }

            }
        });
    }

    public boolean isValid(String value1, String value2){
        return !value1.isEmpty() && !value2.isEmpty();
    }

    public void hideKeyboard(EditText view){
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void toastMsg(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void clearInputs(){
        contactName.setText("");
        contactNumber.setText("");
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