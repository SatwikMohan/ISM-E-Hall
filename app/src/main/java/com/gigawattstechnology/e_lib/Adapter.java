package com.gigawattstechnology.e_lib;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<Modal> {
    Context context;
    public Adapter(Context context, ArrayList<Modal> subjects){
        super(context, 0, subjects);
        this.context=context;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.items, parent, false);
        }

        final Modal sub=getItem(position);
        TextView subject=(TextView) listItemView.findViewById(R.id.subject);
        subject.setText(sub.getSubject());
        ImageView subjecticon=(ImageView) listItemView.findViewById(R.id.subjecticon);
        subjecticon.setImageResource(sub.getSubjecticon());

        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(subject.getText().equals("Doubt Smash")) {
                    Intent i=new Intent(context,chat.class);
                    context.startActivity(i);

                }
                else{

                    Intent i = new Intent(context, Files.class);
                    i.putExtra("subjectname", subject.getText());
                    context.startActivity(i);

                }
            }
        });

        return listItemView;
    }
}
