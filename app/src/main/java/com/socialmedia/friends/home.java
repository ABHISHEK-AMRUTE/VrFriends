package com.socialmedia.friends;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class home extends Fragment {
    home_adapter madapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<user_info_class> examplelist =new ArrayList<user_info_class>();
    ProgressBar pb ;
    TextView txt;
    Context context;
    static int count=9999999;
    String text = "Frineds list : \n";

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference server = FirebaseDatabase.getInstance().getReference();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        server.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                examplelist.clear();
                for (DataSnapshot childl : dataSnapshot.getChildren()) {

                      if(!childl.child("data").child("uid").getValue().equals("nulll")) {
                          if(childl.child("data").child("uid").getValue().toString().equals(user.getUid())) {
                              if(!childl.child("frined_count").child("count").getValue().equals(null))
                              count = Integer.parseInt(childl.child("frined_count").child("count").getValue().toString());
                              else
                                  count =0;
                              for(int x=0;x<count;x++) {
                                  String st = "friend";
                                  st = st + x;
                                  String temp = childl.child("friend").child(st).getValue().toString();
                                  // text = text + "NAME : " + childl.child("friend").child(temp).getValue().toString() + "\n";
                                  examplelist.add(new user_info_class(childl.child("friend").child(temp).getValue().toString(),"scsc",childl.child("friend").child(st).getValue().toString(),Integer.parseInt(childl.child("friend").child("image_code"+st).getValue().toString()),"nulllll"));
                                  madapter.notifyDataSetChanged();
                              }

                      }
                      }



                }
                if(examplelist.isEmpty()){
                    pb.setVisibility(View.INVISIBLE);
                    txt.setText("No friends Yet!");
               }
                else
                {
                    pb.setVisibility(View.INVISIBLE);
                    txt.setVisibility(View.INVISIBLE);
                }
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
        RecyclerView recyclerView = getView().findViewById(R.id.recyler);
        pb = getView().findViewById(R.id.pr);
        txt =getView().findViewById(R.id.Information);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        madapter = new home_adapter(examplelist);
        recyclerView.setAdapter(madapter);
        //txt =view.findViewById(R.id.friends);

             madapter.setonitemclickedlistner(new home_adapter.OnitemClickListner() {
                 @Override
                 public void onItemClick(int position) {

                     Intent intent = new Intent(context,chat.class);
                     intent.putExtra("name_of_frined",examplelist.get(position).getName());
                     intent.putExtra("uid_of_frined",examplelist.get(position).getUid());
                     startActivity(intent);
                 }
             });

    }

    @Override
    public void onAttach(@NonNull Context context1) {
        super.onAttach(context);
        context =context1;
    }
}
