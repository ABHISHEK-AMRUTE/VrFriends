package com.socialmedia.friends;


import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class chat_adapter extends RecyclerView.Adapter<chat_adapter.Exampleviewholder> {

    private ArrayList<chat_content> mexamplelist;
    private chat_adapter.OnitemClickListner mlistner;
    static String frn_uid = "151311";
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    public interface OnitemClickListner
    {
        void onItemClick(int position);



    }



    public void setonitemclickedlistner(chat_adapter.OnitemClickListner listner)
    {
        mlistner=listner;
    }
    public static  class Exampleviewholder extends RecyclerView.ViewHolder
    {
        public TextView owner_name,chat_field;
            LinearLayout lay;
            RelativeLayout card;
        public Exampleviewholder(@NonNull final View itemView, final chat_adapter.OnitemClickListner listner) {
            super(itemView);
            owner_name=itemView.findViewById(R.id.Name);
            chat_field = itemView.findViewById(R.id.message);
            lay = itemView.findViewById(R.id.linera);
            card =itemView.findViewById(R.id.relativeLayout);
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


        }
    }
    public chat_adapter(ArrayList<chat_content> exaplelist){

        mexamplelist=exaplelist;
    }

    @NonNull
    @Override
    public chat_adapter.Exampleviewholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_adapter_layout,parent,false);
        chat_adapter.Exampleviewholder evh = new chat_adapter.Exampleviewholder(v,mlistner);
        return  evh;
    }

    @Override
    public void onBindViewHolder(@NonNull final chat_adapter.Exampleviewholder holder, int i) {
        final chat_content currentitem =mexamplelist.get(i);
        holder.owner_name.setText(currentitem.getName());
        holder.chat_field.setText(currentitem.getMessage());
        if(currentitem.getName().equals(user.getDisplayName()))
        {
            holder.card.setGravity(Gravity.RIGHT);
            holder.lay.setBackgroundColor(Color.parseColor("#96b6e0"));
        }
        else
        {
            holder.card.setGravity(Gravity.LEFT);
            holder.lay.setBackgroundColor(Color.parseColor("#d9a191"));
        }

    }


    @Override
    public int getItemCount() {
        return mexamplelist.size();
    }
}