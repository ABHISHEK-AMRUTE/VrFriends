package com.socialmedia.friends;

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
    ProgressBar pb;
    TextView txt_in;
    static  int count;
    ArrayList<user_info_class> examplelist =new ArrayList<user_info_class>();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference server = FirebaseDatabase.getInstance().getReference();




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frame_find_friends,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerview);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);


        pb= getView().findViewById(R.id.progressBar4);
        txt_in = getView().findViewById(R.id.information);

        madapter = new findfriend_adapter(examplelist);
       recyclerView.setAdapter(madapter);
       loadusers();


       madapter.setonitemclickedlistner(new findfriend_adapter.OnitemClickListner() {
           @Override
           public void onItemClick(int position) {
                  Intent in =new Intent(getContext(),profile_show.class);
                  in.putExtra("uid",examplelist.get(position).getUid());
                  startActivity(in);



           }

           @Override
           public void ondeleteclick(int position) {

               frined_count = frined_count + count;
               count= count + 1;

               String t =get_c(user.getUid(),examplelist.get(position).getUid());
               server.child(t).child("data").child("uid").setValue("nulll");
               server.child(t).child("data").child("name").setValue("nulll");
               server.child(t).child("friend").setValue("nulll");
               server.child(t).child("frined_count").setValue("nulll");
               server.child(t).child("id").setValue(t);
               server.child(user.getUid()).child("friend").child(frined_count).setValue(examplelist.get(position).getUid());
               server.child(user.getUid()).child("friend").child(examplelist.get(position).getUid()).setValue(examplelist.get(position).getName());
               if(get_cc(user.getUid(),examplelist.get(position).getUid()).matches(user.getUid())){
               server.child(t).child("chat_string"+user.getUid()).setValue("nulll");
                   server.child(t).child("chat_string"+examplelist.get(position).getUid()).setValue(" ");}
               else{ server.child(t).child("chat_string"+user.getUid()).setValue(" ");
                   server.child(t).child("chat_string"+examplelist.get(position).getUid()).setValue("nulll");

               }
               server.child(user.getUid()).child("frined_count").child("count").setValue(count);
               server.child(user.getUid()).child("friend").child("image_code"+frined_count).setValue(examplelist.get(position).getImage_code());
               frined_count ="friend";
               server.child(t).child("start_from").setValue(get_cc(user.getUid(),examplelist.get(position).getUid()));
               Toast.makeText(getContext(), "Added to your friend list....", Toast.LENGTH_SHORT).show();
           }
       });
    }




    public void loadusers()
    {

        server.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    examplelist.clear();
                for (DataSnapshot childl : dataSnapshot.getChildren()) {
                    String str =childl.child("data").child("uid").getValue().toString();

                    if(!str.matches("nulll")) {
                        if(!childl.child("data").child("name").getValue().toString().equals(user.getDisplayName())) {
                            if(!childl.child("friend").child(user.getUid()).exists())
               examplelist.add(new user_info_class(childl.child("data").child("name").getValue().toString(), "scsc", str,Integer.parseInt(childl.child("data").child("image_code").getValue().toString()),childl.child("data").child("bio").getValue().toString()));
                        }
                    }
                    madapter.notifyDataSetChanged();
                }
                pb.setVisibility(View.INVISIBLE);
                txt_in.setVisibility(View.INVISIBLE);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }
    public String get_c(String ui, String na)
    {
        String chat_st;
        if(ui.compareTo(na)>0)
        {
            chat_st = na + ui;
        }
        else
        {
            chat_st= ui+na;
        }
        return chat_st;
    }
    public String get_cc(String ui, String na)
    {
        String chat_st;
        if(ui.compareTo(na)>0)
        {
            chat_st = na;
        }
        else
        {
            chat_st= ui;
        }
        return chat_st;
    }
}
