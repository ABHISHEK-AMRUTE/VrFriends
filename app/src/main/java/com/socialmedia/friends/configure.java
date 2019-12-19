package com.socialmedia.friends;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class configure extends AppCompatActivity {
FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
TextView profile_name;
Button next;
ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9,profile_img;
imagecode img;
    DatabaseReference server = FirebaseDatabase.getInstance().getReference();
EditText bio , dob;
int im_code = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_configure);
        profile_name =findViewById(R.id.Name);
        profile_name.setText(user.getDisplayName());
        img1 = findViewById(R.id.boy);
        profile_img = findViewById(R.id.profile_image);
        img2 = findViewById(R.id.btw);
        dob =findViewById(R.id.dob);
        bio = findViewById(R.id.bio);
        img3 = findViewById(R.id.girl);
        img4 = findViewById(R.id.gtw);
        img5 = findViewById(R.id.man);
        img6 = findViewById(R.id.manfive);
        img7 = findViewById(R.id.manth);
        img8 = findViewById(R.id.mantw);
        next =findViewById(R.id.signin);
        img9 = findViewById(R.id.mfo);
        server.child(user.getUid()).child("data").child("bio").setValue("NA");
        server.child(user.getUid()).child("data").child("dob").setValue("NA");
        server.child(user.getUid()).child("data").child("image_code").setValue(im_code);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bio_data  =  bio.getText().toString();
                if(bio_data.equals(null)) bio_data = " ";
                String dob_data = dob.getText().toString();
                if(dob_data.equals(null)) dob_data = "NA";
                server.child(user.getUid()).child("data").child("bio").setValue(bio_data);
                server.child(user.getUid()).child("data").child("dob").setValue(dob_data);
                server.child(user.getUid()).child("data").child("image_code").setValue(im_code);
                startActivity(new Intent(configure.this,MainActivity.class));
            }
        });
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1.setBackgroundColor(getResources().getColor(R.color.selected_image));
                img2.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img3.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img4.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img5.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img6.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img7.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img8.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img9.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                im_code = 1;
                profile_img.setImageResource(R.drawable.boy);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img2.setBackgroundColor(getResources().getColor(R.color.selected_image));
                img3.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img4.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img5.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img6.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img7.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img8.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img9.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                im_code = 2;

                profile_img.setImageResource(R.drawable.btw);           }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img2.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img3.setBackgroundColor(getResources().getColor(R.color.selected_image));
                img4.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img5.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img6.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img7.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img8.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img9.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                im_code = 3;
                profile_img.setImageResource(R.drawable.girl);            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img2.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img3.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img4.setBackgroundColor(getResources().getColor(R.color.selected_image));
                img5.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img6.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img7.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img8.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img9.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                im_code = 4;
                profile_img.setImageResource(R.drawable.gtw);        }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img2.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img3.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img4.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img5.setBackgroundColor(getResources().getColor(R.color.selected_image));
                img6.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img7.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img8.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img9.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                im_code = 5;
                profile_img.setImageResource(R.drawable.man);            }
        });

        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img2.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img3.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img4.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img5.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img6.setBackgroundColor(getResources().getColor(R.color.selected_image));
                img7.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img8.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img9.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                im_code = 6;
                profile_img.setImageResource(R.drawable.manfive);            }
        });
        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img2.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img3.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img4.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img5.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img6.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img7.setBackgroundColor(getResources().getColor(R.color.selected_image));
                img8.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img9.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                im_code = 7;
                profile_img.setImageResource(R.drawable.manth);            }
        });
        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img2.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img3.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img4.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img5.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img6.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img7.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img8.setBackgroundColor(getResources().getColor(R.color.selected_image));
                img9.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                im_code = 8;
                profile_img.setImageResource(R.drawable.mantw);        }
        });
        img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img2.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img3.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img4.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img5.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img6.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img7.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img8.setBackgroundColor(getResources().getColor(R.color.unselected_image));
                img9.setBackgroundColor(getResources().getColor(R.color.selected_image));
                im_code = 9;
                profile_img.setImageResource(R.drawable.mfo);
            }
        });

    }
//    public void setimage( int code)
//    {
//        switch(code)
//        {
//            case  1: profile_img.setImageResource(R.drawable.boy);
//            case  2:profile_img.setImageResource(R.drawable.btw);
//            case  3:profile_img.setImageResource(R.drawable.girl);
//            case  4: profile_img.setImageResource(R.drawable.gtw);
//            case  5: profile_img.setImageResource(R.drawable.man);
//            case  6: profile_img.setImageResource(R.drawable.manfive);
//            case  7:profile_img.setImageResource(R.drawable.manth);
//            case  8: profile_img.setImageResource(R.drawable.mantw);
//            case  9:profile_img.setImageResource(R.drawable.mfo);
//            default:profile_img.setImageResource(R.drawable.boy);
//
//        }
//    }
}
