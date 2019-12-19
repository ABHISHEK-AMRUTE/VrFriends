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
        public TextView owner_name,uid_field;
        public ImageView featured_image;

        public Exampleviewholder(@NonNull final View itemView, final home_adapter.OnitemClickListner listner) {
            super(itemView);
            owner_name=itemView.findViewById(R.id.Name);
            uid_field = itemView.findViewById(R.id.uid);
            featured_image=itemView.findViewById(R.id.image_tag);
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


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_adapter_layout,parent,false);
        home_adapter.Exampleviewholder evh= new home_adapter.Exampleviewholder(v,mlistner);
        return  evh;
    }

    @Override
    public void onBindViewHolder(@NonNull final home_adapter.Exampleviewholder holder, int i) {

        final user_info_class currentitem =mexamplelist.get(i);
        holder.owner_name.setText(currentitem.getName());
        holder.uid_field.setText(currentitem.getUid());
        frn_uid = currentitem.getUid();
        int imagecode= currentitem.getImage_code();
        if(imagecode == 1)
        {
            holder.featured_image.setImageResource(R.drawable.boy);
        }else  if(imagecode == 2)
        {holder.featured_image.setImageResource(R.drawable.btw);

        }else if(imagecode == 3)
        {holder.featured_image.setImageResource(R.drawable.girl);

        }else if(imagecode == 4)
        { holder.featured_image.setImageResource(R.drawable.gtw);

        }else if(imagecode == 5)
        {holder.featured_image.setImageResource(R.drawable.man);

        }else if(imagecode ==6 )
        {
            holder.featured_image.setImageResource(R.drawable.manfive);
        }else if(imagecode == 7)
        {
            holder.featured_image.setImageResource(R.drawable.manth);
        }else if(imagecode ==8 )
        {
            holder.featured_image.setImageResource(R.drawable.mantw);
        }else if(imagecode ==9 )
        {
            holder.featured_image.setImageResource(R.drawable.mfo);
        }

    }


    @Override
    public int getItemCount() {
        return mexamplelist.size();
    }
}