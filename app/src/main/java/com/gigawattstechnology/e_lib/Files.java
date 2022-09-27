package com.gigawattstechnology.e_lib;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class Files extends AppCompatActivity {
FloatingActionButton addfile;
String subject;
StorageReference storageReference;
DatabaseReference databaseReference;
RecyclerView recyclerView;
String name;
ArrayList<FileDet> details;
RecycleAdapter recycleAdapter;
String n,u;
SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        addfile=findViewById(R.id.addfile);
        searchView=findViewById(R.id.search);
        subject=getIntent().getStringExtra("subjectname");
        storageReference= FirebaseStorage.getInstance().getReference();
        recyclerView=findViewById(R.id.fileView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        details=new ArrayList<>();
        databaseReference=FirebaseDatabase.getInstance().getReference(subject);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                details.clear();
                for(DataSnapshot postSnapshot : snapshot.getChildren()){

                    details.add(new FileDet(postSnapshot.getKey(),postSnapshot.getValue(String.class)));

                }

                recycleAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recycleAdapter=new RecycleAdapter(this,details);
        recyclerView.setAdapter(recycleAdapter);
        addfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPDF();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                search(s);
                return true;
            }
        });
    }
    private void selectPDF() {

        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"SELECT PDF FILE"),12);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==12&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            name=data.getDataString().substring(data.getDataString().lastIndexOf("/")+1);
            uploadPDFFileFirebase(data.getData());
        }
    }

    private void uploadPDFFileFirebase(Uri data) {

        final ProgressDialog progressDialog=new ProgressDialog(this);

        EditText fname=new EditText(this);
        final AlertDialog.Builder Dialog=new AlertDialog.Builder(this);
        Dialog.setTitle("Specify file name by which visible to all");
        LinearLayout ll=new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(fname);
        fname.setHint("Give Name to file");
        Dialog.setView(ll);
        Dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

        progressDialog.setTitle("Uploading the file");
        progressDialog.show();
        StorageReference reference=storageReference.child("pdfFile"+System.currentTimeMillis()+".pdf");
        reference.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                        while(!uriTask.isComplete());
                        Uri uri=uriTask.getResult();
                       // putPDF putPDF=new putPDF(name,uri.toString());
                        databaseReference.child(fname.getText().toString().replace(".","").replace("/","")).setValue(uri.toString());
                        progressDialog.dismiss();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progress=(100.0* snapshot.getBytesTransferred())/ snapshot.getTotalByteCount();
                progressDialog.setMessage("File uploaded.."+(int)progress+"%");
            }
        });

            }
        });

        Dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        Dialog.create().show();
    }

    private void search(String s) {
        ArrayList<FileDet> mylist=new ArrayList<>();
        for(FileDet object: details ){
            if(object.getSubjectname().toLowerCase().contains(s.toLowerCase())){
                mylist.add(object);
            }
        }
        RecycleAdapter randomNumListAdapter=new RecycleAdapter(Files.this,mylist);
        recyclerView.setAdapter(randomNumListAdapter);
    }


}