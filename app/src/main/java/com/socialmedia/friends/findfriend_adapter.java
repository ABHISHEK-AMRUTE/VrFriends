package com.socialmedia.friends;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class findfriend_adapter extends RecyclerView.Adapter<findfriend_adapter.Exampleviewholder> {

    private ArrayList<user_info_class> mexamplelist;
    private findfriend_adapter.OnitemClickListner mlistner;
   static String frn_uid = "151311";



    public interface OnitemClickListner
    {
        void onItemClick(int position);
        void ondeleteclick(int position);


    }



    public void setonitemclickedlistner(findfriend_adapter.OnitemClickListner listner)
    {
        mlistner=listner;
    }
    public static  class Exampleviewholder extends RecyclerView.ViewHolder
    {
        public TextView owner_name;
        public ImageView featured_image;
        public Button add_friend;

        public Exampleviewholder(@NonNull final View itemView, final findfriend_adapter.OnitemClickListner listner) {
            super(itemView);
            owner_name=itemView.findViewById(R.id.name);

            featured_image=itemView.findViewById(R.id.image);

             add_friend =itemView.findViewById(R.id.send);
             itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listner!=null)
                    {
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            listner.onItemClick(position);
                        }
                    }
                }
            });


            add_friend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if(listner!=null)
                    {
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            listner.ondeleteclick(position);
                        }
                    }
                }
            });


        }
    }
    public findfriend_adapter(ArrayList<user_info_class> exaplelist){

        mexamplelist=exaplelist;
    }

    @NonNull
    @Override
    public findfriend_adapter.Exampleviewholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.findfriend_adapter_ele_layout,parent,false);
        findfriend_adapter.Exampleviewholder evh= new findfriend_adapter.Exampleviewholder(v,mlistner);
        return  evh;
    }

    @Override
    public void onBindViewHolder(@NonNull final findfriend_adapter.Exampleviewholder holder, int i) {

        final user_info_class currentitem =mexamplelist.get(i);
        // holder.featured_image.setImageResource(R.drawable.home);
        Picasso.get().load( "https://api.androidhive.info/images/sample.jpg").into(holder.featured_image);
        holder.owner_name.setText(currentitem.getName());
        frn_uid = currentitem.getUid();
    }


    @Override
    public int getItemCount() {
        return mexamplelist.size();
    }
}