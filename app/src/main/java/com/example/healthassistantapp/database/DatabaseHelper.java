package com.example.healthassistantapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.healthassistantapp.model.Contact;
import com.example.healthassistantapp.model.Message;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final Context context;
    private static final String DB_NAME = "HealthAssistantApp.db";
    private static final int DB_VERSION = 4;

    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "contact_name";
    private static final String COLUMN_NUMBER = "contact_number";

    private static final String MSG_TABLE_NAME = "sos_message";
    private static final String MSG_COLUMN_ID = "_id";
    public static final String MSG_COLUMN_MSG = "message";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        String contactsQuery =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_NUMBER + " TEXT UNIQUE);";

        String messageQuery =
                "CREATE TABLE " + MSG_TABLE_NAME +
                        " (" + MSG_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        MSG_COLUMN_MSG + " TEXT);";

        db.execSQL(messageQuery);
        db.execSQL(contactsQuery);

        ContentValues values = new ContentValues();
        values.put(MSG_COLUMN_MSG, "Please help, it's an emergency.");
        db.insert(MSG_TABLE_NAME, null, values);
    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MSG_TABLE_NAME);
        onCreate(db);
    }

    public int updateMsg(@NonNull Message message) {
        SQLiteDatabase db = this.getWritableDatabase();
        int status;

        ContentValues values = new ContentValues();
        values.put(MSG_COLUMN_MSG, message.getMessage());
        status = db.update(MSG_TABLE_NAME, values, MSG_COLUMN_ID + " = ?", new String[]{"1"});
        db.close();

        return status;
    }

    public Message getStoredMsg() {
        SQLiteDatabase db = getReadableDatabase();
        String storedMsg = "";

        Cursor cursor = db.query(MSG_TABLE_NAME, new String[]{MSG_COLUMN_MSG}, MSG_COLUMN_ID + " = ?",
                new String[]{"1"}, null, null, null);

        if(cursor != null && cursor.moveToFirst()){
            storedMsg = cursor.getString(cursor.getColumnIndexOrThrow(MSG_COLUMN_MSG));
            cursor.close();
        }
        db.close();

        return new Message(storedMsg);
    }

    public boolean addContact(@NonNull Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        if(getContactsCount() < 3) {
            if(!isDuplicate(contact.getContactNumber())) {
                ContentValues values = new ContentValues();
                values.put(COLUMN_NAME, contact.getContactName());
                values.put(COLUMN_NUMBER, contact.getContactNumber());
                db.insert(TABLE_NAME, null, values);
                db.close();
                return true;
            }else{
                Toast.makeText(context, "Contact number already exists", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else {
            Toast.makeText(context, "You can only add up to 3 contacts.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                String number = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NUMBER));
                contactList.add(new Contact(name, number, id));
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();

        return contactList;
    }

    public int getContactsCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public boolean isDuplicate(String contact){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NUMBER + " = '" + contact + "'";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean delete(int contactID){
        String id = String.valueOf(contactID);
        SQLiteDatabase db = this.getWritableDatabase();
        long deleteStatus = db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{id});

        if(deleteStatus == -1){
             return false;
        }else{
            return true;
        }
    }
}
