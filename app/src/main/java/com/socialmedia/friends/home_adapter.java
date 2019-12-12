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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class home_adapter extends RecyclerView.Adapter<home_adapter.Exampleviewholder> {

    private ArrayList<user_info_class> mexamplelist;
    private home_adapter.OnitemClickListner mlistner;
    static String frn_uid = "151311";



    public interface OnitemClickListner
    {
        void onItemClick(int position);



    }



    public void setonitemclickedlistner(home_adapter.OnitemClickListner listner)
    {
        mlistner=listner;
    }
    public static  class Exampleviewholder extends RecyclerView.ViewHolder
    {
        public TextView owner_name;


        public Exampleviewholder(@NonNull final View itemView, final home_adapter.OnitemClickListner listner) {
            super(itemView);
            owner_name=itemView.findViewById(R.id.Name);


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
    public home_adapter(ArrayList<user_info_class> exaplelist){

        mexamplelist=exaplelist;
    }

    @NonNull
    @Override
    public home_adapter.Exampleviewholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.findfriend_adapter_ele_layout,parent,false);
        home_adapter.Exampleviewholder evh= new home_adapter.Exampleviewholder(v,mlistner);
        return  evh;
    }

    @Override
    public void onBindViewHolder(@NonNull final home_adapter.Exampleviewholder holder, int i) {

        final user_info_class currentitem =mexamplelist.get(i);
        // holder.featured_image.setImageResource(R.drawable.home);
//        Picasso.get().load( "https://api.androidhive.info/images/sample.jpg").into(holder.featured_image);
        holder.owner_name.setText(currentitem.getName());
        frn_uid = currentitem.getUid();
    }


    @Override
    public int getItemCount() {
        return mexamplelist.size();
    }
}