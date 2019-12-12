package com.socialmedia.friends;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class find_friends extends Fragment {
    findfriend_adapter madapter;
    String frined_count = "friend";
    static  int count;
    ArrayList<user_info_class> examplelist =new ArrayList<user_info_class>();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference server = FirebaseDatabase.getInstance().getReference();




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        server.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childl : dataSnapshot.getChildren()) {


                    if (childl.child("data").child("uid").getValue().toString().equals(user.getUid())) {
                        count = Integer.parseInt(childl.child("frined_count").child("count").getValue().toString());


                    }



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return inflater.inflate(R.layout.frame_find_friends,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerview);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);




        madapter = new findfriend_adapter(examplelist);
       recyclerView.setAdapter(madapter);
       loadusers();


       madapter.setonitemclickedlistner(new findfriend_adapter.OnitemClickListner() {
           @Override
           public void onItemClick(int position) {

           }

           @Override
           public void ondeleteclick(int position) {

               frined_count = frined_count + count;
               count= count + 1;
               Toast.makeText(getContext(),""+count,Toast.LENGTH_LONG).show();
               server.child(user.getUid()).child("friend").child(frined_count).setValue(examplelist.get(position).getUid());
               server.child(user.getUid()).child("friend").child(examplelist.get(position).getUid()).setValue(examplelist.get(position).getName());

               server.child(user.getUid()).child("frined_count").child("count").setValue(count);
               frined_count ="friend";
           }
       });
    }




    public void loadusers()
    {

        server.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    examplelist.clear();
                for (DataSnapshot childl : dataSnapshot.getChildren()) {
                    String str =childl.child("data").child("name").getValue().toString();

                    if(!str.matches("none")) {
                        examplelist.add(new user_info_class(str,"scsc",childl.child("data").child("uid").getValue().toString()));
                    }
                    madapter.notifyDataSetChanged();
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }
}
