package com.example.healthassistantapp.adaptor;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthassistantapp.R;

public class ContactViewHolder extends RecyclerView.ViewHolder {

    TextView contactName, contactNumber, contactID;

    public ContactViewHolder(@NonNull View itemView) {
        super(itemView);
        contactID = itemView.findViewById(R.id.contactID);
        contactName = itemView.findViewById(R.id.contactName);
        contactNumber = itemView.findViewById(R.id.contactNumber);
    }
}
