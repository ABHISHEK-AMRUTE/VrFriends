package com.socialmedia.friends;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile_show extends AppCompatActivity {
    String name,bio_data,dob_data,tosee_uid;

    TextView bio;
    int friend_count = 0;
    TextView name_f,friend,dob;
    int imagecode=1;
    ImageView imageView;  FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference server = FirebaseDatabase.getInstance().getReference();
ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_show);
        imageView = findViewById(R.id.imageView);
        bio = findViewById(R.id.bio);
        dob = findViewById(R.id.dob);
        friend = findViewById(R.id.frn);

 pb =findViewById(R.id.progressBar);

        name_f = findViewById(R.id.name);
        tosee_uid =getIntent().getStringExtra("uid");



        server.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot childl : dataSnapshot.getChildren()) {

                    if(!childl.child("data").child("uid").getValue().equals("nulll")) {
                        if(childl.child("data").child("uid").getValue().toString().equals(tosee_uid)) {

                            imagecode = Integer.parseInt(childl.child("data").child("image_code").getValue().toString());
                            friend_count = Integer.parseInt(childl.child("frined_count").child("count").getValue().toString());
                            bio_data = childl.child("data").child("bio").getValue().toString();
                            name = childl.child("data").child("name").getValue().toString();
                            dob_data = childl.child("data").child("dob").getValue().toString();
                            friend.setText(friend_count+"");
                            dob.setText(dob_data);
                            name_f.setText(name);
                            bio.setText(bio_data);

                            if(imagecode == 1)
                            {
                                imageView.setImageResource(R.drawable.boy);
                            }else  if(imagecode == 2)
                            {imageView.setImageResource(R.drawable.btw);

                            }else if(imagecode == 3)
                            {imageView.setImageResource(R.drawable.girl);

                            }else if(imagecode == 4)
                            { imageView.setImageResource(R.drawable.gtw);

                            }else if(imagecode == 5)
                            {imageView.setImageResource(R.drawable.man);

                            }else if(imagecode ==6 )
                            {
                                imageView.setImageResource(R.drawable.manfive);
                            }else if(imagecode == 7)
                            {
                                imageView.setImageResource(R.drawable.manth);
                            }else if(imagecode ==8 )
                            {
                                imageView.setImageResource(R.drawable.mantw);
                            }else if(imagecode ==9 )
                            {
                                imageView.setImageResource(R.drawable.mfo);
                            }
                            pb.setVisibility(View.INVISIBLE);
                        }

                    }



                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
