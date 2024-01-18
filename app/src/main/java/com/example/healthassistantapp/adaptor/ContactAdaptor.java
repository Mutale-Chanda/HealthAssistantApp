package com.example.healthassistantapp.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthassistantapp.R;
import com.example.healthassistantapp.model.Contact;

import java.util.List;

public class ContactAdaptor extends RecyclerView.Adapter<ContactViewHolder> {

    private Context context;
    private List<Contact> contactList;

    public ContactAdaptor(Context context, List<Contact> contactList){
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_item,parent,false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        if(getItemCount() > 0) {
            Contact contact = contactList.get(position);
            holder.contactName.setText(contact.getContactName());
            holder.contactNumber.setText(contact.getContactNumber());
            holder.contactID.setText(String.valueOf(position + 1));
        }
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
}
