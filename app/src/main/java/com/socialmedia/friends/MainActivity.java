package com.socialmedia.friends;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView mBottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomNav=findViewById(R.id.navgation_bar);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new home()).commit();


        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment setfrs=null;
                switch (item.getItemId())
                {

                    case R.id.profile:
                        setfrs= new profile();

                        break;
                    case R.id.home:
                        setfrs= new home();

                        break;
                    case R.id.find_friends:
                        setfrs= new find_friends();
                         break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,setfrs).commit();
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            Toast.makeText(this, "Name: "+name+" Email : "+email, Toast.LENGTH_SHORT).show();
            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        } else {
            startActivity(new Intent(this,login_firebase.class));
            finish();
        }
    }
}
