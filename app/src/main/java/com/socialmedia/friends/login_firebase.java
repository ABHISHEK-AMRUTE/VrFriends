package com.socialmedia.friends;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login_firebase extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button singin,singup;
    EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_firebase);
        initialise();

        mAuth=FirebaseAuth.getInstance();
        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login_firebase.this,signup_firebase.class));

            }
        });
        singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_f=email.getText().toString();
                String pass = password.getText().toString();
                if(TextUtils.isEmpty(email_f))
                {
                    Toast.makeText(login_firebase.this, "Please enter email id..", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(pass))
                {
                    Toast.makeText(login_firebase.this, "Please enter password..", Toast.LENGTH_SHORT).show();
                }
                mAuth.signInWithEmailAndPassword(email_f,pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isComplete())
                                {
                                    Toast.makeText(login_firebase.this, "Logged in...", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(login_firebase.this,MainActivity.class));
                                    finish();
                                }
                                else
                                {

                                }
                            }
                        });


            }
        });
    }
    private void initialise() {
        singin=findViewById(R.id.singin);
        singup=findViewById(R.id.singup);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
    }
}
