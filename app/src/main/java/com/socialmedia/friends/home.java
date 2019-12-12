package com.socialmedia.friends;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class home extends Fragment {
    TextView txt;
    Context context;
    static int count=9999999;
    String text = "Frineds list : \n";
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference server = FirebaseDatabase.getInstance().getReference();
   DatabaseReference q =server.child("vrfriends-f30c2");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        server.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childl : dataSnapshot.getChildren()) {


                    if (childl.child("data").child("uid").getValue().toString().equals(user.getUid())) {
                        count = Integer.parseInt(childl.child("frined_count").child("count").getValue().toString());
                        for(int x=0;x<count;x++) {
                            String st = "friend";
                            st =st +x;
                            String temp = childl.child("friend").child(st).getValue().toString();
                            text = text + "NAME : " + childl.child("friend").child(temp).getValue().toString() + "\n";
                        }

                    }



                }
                txt.setText(text);
                Toast.makeText(context,""+count,Toast.LENGTH_LONG).show();
                 //txt.setText(text);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return inflater.inflate(R.layout.frame_home,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txt =view.findViewById(R.id.friends);



    }

    @Override
    public void onAttach(@NonNull Context context1) {
        super.onAttach(context);
        context =context1;
    }
}
