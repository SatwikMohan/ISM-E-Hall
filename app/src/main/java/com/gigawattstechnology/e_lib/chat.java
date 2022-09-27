package com.gigawattstechnology.e_lib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class chat extends AppCompatActivity {
FloatingActionButton send;
RecyclerView recyclerView;
EditText message;
DatabaseReference databaseReference;
chatadapter chatadapter;
int maxchat;
    String chatmessage;
    ArrayList<String> allmessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        //Toast.makeText(this, authtochat.giveusername(), Toast.LENGTH_SHORT).show();
        send=findViewById(R.id.send);
        recyclerView=findViewById(R.id.chatrecycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        //linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        allmessage=new ArrayList<>();
        message=findViewById(R.id.message);

        chatadapter=new chatadapter(allmessage);
        recyclerView.setAdapter(chatadapter);

        databaseReference= FirebaseDatabase.getInstance().getReference("Chats");
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chatmessage=message.getText().toString();
                message.setText("");
                    long i = System.currentTimeMillis();
                    String child = "" +i;
                    //A=65 Z=90
                    databaseReference.child(child).setValue(chatmessage.replace(".", "").replace("/", ""));
            }
        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                allmessage.clear();
                for(DataSnapshot postSnapshot : snapshot.getChildren()){
                    allmessage.add(postSnapshot.getValue(String.class));
                }

                chatadapter.notifyDataSetChanged();
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.smoothScrollToPosition(chatadapter.getItemCount());
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}