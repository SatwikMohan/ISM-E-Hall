package com.gigawattstechnology.e_lib;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class chatadapter extends RecyclerView.Adapter<chatadapter.RecyclerViewHolder>{
    ArrayList<String> allmessage;

    public chatadapter(ArrayList<String> allmessage) {
        this.allmessage = allmessage;
    }
    @Override
    public int getItemViewType(final int position) {
        return R.layout.messagecontainer;}
    @NonNull
    @Override
    public chatadapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new chatadapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull chatadapter.RecyclerViewHolder holder, int position) {

        holder.usermessage.setText(allmessage.get(position));
    }

    @Override
    public int getItemCount() {
        return allmessage.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView usermessage;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            usermessage=itemView.findViewById(R.id.usermessage);
        }
    }
}
