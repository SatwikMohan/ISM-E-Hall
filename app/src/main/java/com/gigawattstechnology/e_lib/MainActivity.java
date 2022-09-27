package com.gigawattstechnology.e_lib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
ImageView logo;
EditText Email,Password;
Button Login,createaccount;
TextView forgotpassword,newaccount;
ProgressBar progressBar;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newmain);
       // this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.setTheme(androidx.appcompat.R.style.Theme_AppCompat_Light);
        getSupportActionBar().hide();
        //logo=findViewById(R.id.logo);
        Email=findViewById(R.id.email);
        Password=findViewById(R.id.password);
        Login=findViewById(R.id.login);
        //forgotpassword=findViewById(R.id.forgotpassword);
        //createaccount=findViewById(R.id.createaccount);
        newaccount=findViewById(R.id.newaccount);
       progressBar=findViewById(R.id.progressBar2);
        fAuth=FirebaseAuth.getInstance();
        //logo.animate().translationY(-888).setDuration(1000).setStartDelay(2000);
        if (fAuth.getCurrentUser() != null) {
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
            finish();
        }
        /*final Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Email.setVisibility(View.VISIBLE);
                    Password.setVisibility(View.VISIBLE);
                    Login.setVisibility(View.VISIBLE);
                    forgotpassword.setVisibility(View.VISIBLE);
                    createaccount.setVisibility(View.VISIBLE);
                }
            },3100);*/

            newaccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(MainActivity.this,Newaccount.class);
                    startActivity(i);
                }
            });

            Login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String email=Email.getText().toString().trim();
                    String password=Password.getText().toString().trim();
                    if(TextUtils.isEmpty(email))
                    {
                        Email.setError("* Email is required");
                        return;
                    }
                    if(TextUtils.isEmpty(password))
                    {
                        Password.setError("* Password is required");
                        return;
                    }
                    if(password.length()<8)
                    {
                        Password.setError("* Password should contain at least 8 characters");
                        return;
                    }
                    authtochat.storename(email.replace(".","").replace("-","").substring(0,email.indexOf("@")));
                   progressBar.setVisibility(View.VISIBLE);
                    fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {

                                finish();
                                Intent i=new Intent(MainActivity.this,MenuActivity.class);
                                startActivity(i);
                                //progressBar.setVisibility(View.INVISIBLE);

                            }else{
                                Toast.makeText(MainActivity.this,"ERROR!!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                progressBar.setVisibility(View.INVISIBLE);
                            }

                        }
                    });
                }
            });
           /* forgotpassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText resetmail=new EditText(view.getContext());
                    final AlertDialog.Builder passwordResetDialog=new AlertDialog.Builder(view.getContext());
                    passwordResetDialog.setTitle("Reset Forgotten Password");
                    passwordResetDialog.setMessage("Enter an email "+resetmail.getText().toString().trim());
                    passwordResetDialog.setView(resetmail);
                    passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String mail=resetmail.getText().toString().trim();
                            fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(MainActivity.this,"Link sent successfully",Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(MainActivity.this,"ERRORR!!"+e.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    });
                    passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    passwordResetDialog.create().show();
                }
            });*/
    }
}