package com.example.healthassistantapp.adaptor;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.healthassistantapp.R;

public class DeleteContactViewHolder extends RecyclerView.ViewHolder{

    TextView deleteContactName, deleteContactNumber, deleteContactID;
    ImageView deleteContactBtn;

    public DeleteContactViewHolder(@NonNull View itemView) {
        super(itemView);
        deleteContactID = itemView.findViewById(R.id.deleteContactID);
        deleteContactName = itemView.findViewById(R.id.deleteContactName);
        deleteContactNumber = itemView.findViewById(R.id.deleteContactNumber);
        deleteContactBtn = itemView.findViewById(R.id.deleteContactBtn);
    }
}
