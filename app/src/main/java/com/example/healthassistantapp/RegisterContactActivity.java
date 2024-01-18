package com.example.healthassistantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthassistantapp.database.DatabaseHelper;
import com.example.healthassistantapp.model.Contact;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterContactActivity extends AppCompatActivity {

    TextInputEditText contactName;
    TextInputEditText contactNumber;
    Button saveContact;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_contact);

        contactName = findViewById(R.id.contactName);
        contactNumber = findViewById(R.id.contactNumber);
        saveContact = findViewById(R.id.saveContact_btn);
        db = new DatabaseHelper(RegisterContactActivity.this);

        saveContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = contactName.getText().toString().trim();
                String number = contactNumber.getText().toString().trim();

                hideKeyboard(contactName, contactNumber);

                if(isValid(name,number)){
                    if(number.length() < 10 || number.length() > 12){
                        toastMsg("contact number is invalid");
                    }else{
                        Contact contact = new Contact(name, number);
                        boolean saveStatus = db.addContact(contact);
                        if(saveStatus){
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

    public void hideKeyboard(EditText view1, EditText view2){
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view1.getWindowToken(), 0);
        inputMethodManager.hideSoftInputFromWindow(view2.getWindowToken(), 0);
    }

    public void toastMsg(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void clearInputs(){
        contactName.setText("");
        contactNumber.setText("");
    }

}