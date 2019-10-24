package com.socialmedia.friends;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;


public class profile extends Fragment {
    String name,email;
    Uri photoUrl;
    TextView name_f,email_f;
    ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.frame_profile,container,false);


    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {



                 name = user.getDisplayName();
                 email = user.getEmail();
                 photoUrl = user.getPhotoUrl();

        }
        name_f = getView().findViewById(R.id.name);
        email_f =getView().findViewById(R.id.email);
        imageView = getView().findViewById(R.id.imageView);
        imageView.setImageURI(photoUrl);
        int loader =R.drawable.profile;

        String image_url = "https://api.androidhive.info/images/sample.jpg";
        Picasso.get().load(image_url).into(imageView);

        name_f.setText(name);
        email_f.setText(email);
        email_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(),login_firebase.class));
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

    }
}
