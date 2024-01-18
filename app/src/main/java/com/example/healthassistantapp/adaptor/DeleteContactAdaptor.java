package com.example.healthassistantapp.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthassistantapp.R;
import com.example.healthassistantapp.database.DatabaseHelper;
import com.example.healthassistantapp.model.Contact;

import java.util.List;

public class DeleteContactAdaptor extends RecyclerView.Adapter<DeleteContactViewHolder> {

    private final Context context;
    private List<Contact> contactList;
    private final DatabaseHelper db;
    public DeleteContactAdaptor(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
        db = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public DeleteContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.deletecontact_item,parent,false);
        return new DeleteContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeleteContactViewHolder holder, int position) {
        if(getItemCount() > 0) {
            int adaptorPosition = position;
            Contact contact = contactList.get(position);
            holder.deleteContactName.setText(contact.getContactName());
            holder.deleteContactNumber.setText(contact.getContactNumber());
            holder.deleteContactID.setText(String.valueOf(position + 1));
            holder.deleteContactBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isDeleted = db.delete(contact.getContactID());
                    if(!isDeleted){
                        Toast.makeText(context, "error deleting contact.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "success deleting contact.", Toast.LENGTH_SHORT).show();
                        contactList.remove(adaptorPosition);
                        notifyItemRemoved(adaptorPosition);
                        notifyItemRangeChanged(adaptorPosition, getItemCount());
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
}
