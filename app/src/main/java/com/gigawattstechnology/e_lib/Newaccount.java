package com.gigawattstechnology.e_lib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Set;

public class Newaccount extends AppCompatActivity {
EditText Email,SetPassword,ConfirmPassword;
Button register;
String s="@iitism.ac.in";
ProgressBar progressBar;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newaccount);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        Email=findViewById(R.id.emailup);
        SetPassword=findViewById(R.id.setpassword);
        ConfirmPassword=findViewById(R.id.confirmpassword);
        register=findViewById(R.id.register);
        fAuth=FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        progressBar=findViewById(R.id.progressBar);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=Email.getText().toString().trim();
                String password=SetPassword.getText().toString().trim();
                String cpassword=ConfirmPassword.getText().toString().trim();
                if(!password.equals(cpassword))
                {
                    SetPassword.setError("* Password and Confirm Password are not same");
                    ConfirmPassword.setError("* Password and Confirm Password are not same");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    SetPassword.setError("* Password is required");
                    return;
                }
                if(TextUtils.isEmpty(cpassword))
                {
                    ConfirmPassword.setError("* Password is required");
                    return;
                }
                if(TextUtils.isEmpty(email))
                {
                    Email.setError("* Email is required");
                    return;
                }

                if(!email.substring(8).equals(s)){
                    Email.setError("IIT Dhanbad Institute email Id only");
                    return;
                }

                if(TextUtils.isEmpty(password))
                {
                    SetPassword.setError("* Password is required");
                    return;
                }
                if(password.length()<8)
                {
                    SetPassword.setError("* Password should contain at least 8 characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Newaccount.this,"Successfully Registered",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            progressBar.setVisibility(View.INVISIBLE);

                        }else{
                            Toast.makeText(Newaccount.this,"ERROR!!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });
    }
}