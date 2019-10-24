package com.socialmedia.friends;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class signup_firebase extends AppCompatActivity {
   private FirebaseAuth mAuth;
   Button singup,singin;
   EditText email,password,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_firebase);

        initialise();

        mAuth=FirebaseAuth.getInstance();
        singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signup_firebase.this,login_firebase.class));

            }
        });
        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_f=email.getText().toString();
                String pass = password.getText().toString();
                if(TextUtils.isEmpty(email_f))
                {
                    Toast.makeText(signup_firebase.this, "Please enter email id..", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(pass))
                {
                    Toast.makeText(signup_firebase.this, "Please enter password..", Toast.LENGTH_SHORT).show();
                }
                mAuth.createUserWithEmailAndPassword(email_f,pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(signup_firebase.this, "sign up successful..", Toast.LENGTH_SHORT).show();
                                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(name.getText().toString())
                                            .setPhotoUri(Uri.parse("https://api.androidhive.info/images/sample.jpg"))
                                            .build();
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    user.updateProfile(profileUpdates)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {

                                                        startActivity(new Intent(signup_firebase.this,MainActivity.class));
                                                        finish();
                                                    }
                                                    else
                                                    {
                                                        Toast.makeText(signup_firebase.this, "some error creeped in....", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });



                                } else {
                                    Toast.makeText(signup_firebase.this, "Failed", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();

    }


    private void initialise() {
        singup=findViewById(R.id.singup);
        singin=findViewById(R.id.singup);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        name = findViewById(R.id.name);
    }
}
