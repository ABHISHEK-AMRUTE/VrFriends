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
import com.google.firebase.auth.UserInfo;

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
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {






                String name = user.getDisplayName();
                String email = user.getEmail();
                Uri photoUrl = user.getPhotoUrl();
                Toast.makeText(this, "Name: "+name+" Email : "+email, Toast.LENGTH_SHORT).show();






        } else {
            startActivity(new Intent(this,login_firebase.class));
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
