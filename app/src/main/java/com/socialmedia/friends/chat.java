package com.socialmedia.friends;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class chat extends AppCompatActivity {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference server = FirebaseDatabase.getInstance().getReference();
    String chat_str  = "ruined";
    String startfrom ,other;
    int turn=0;
    String chat_content="";
    int first_who =0;String name,uid;
    ImageView send;
    EditText message;
    chat_adapter madapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<chat_content> examplelist =new ArrayList<chat_content>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        RecyclerView recyclerView = findViewById(R.id.chat);

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        madapter = new chat_adapter(examplelist);
        recyclerView.setAdapter(madapter);


         name = getIntent().getStringExtra("name_of_frined");
        uid = getIntent().getStringExtra("uid_of_frined");
         send =findViewById(R.id.send);
         message =findViewById(R.id.message);
           send.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if(message.getText().toString().matches(""))
                   {
                       Toast.makeText(chat.this,"Message field is empty ",Toast.LENGTH_LONG).show();
                   }
                   else
                   {
                       if(chat_content.matches("nulll"))
                      {

                           if (startfrom.matches(user.getUid())){

                           chat_content = message.getText().toString();

                           }
                           else
                           {
                                chat_content = "nulll|";
                                chat_content = chat_content + message.getText().toString();
                           }

                      }
                        else
                      {
                          if(startfrom.matches(user.getUid()))
                          chat_content = chat_content + "," + message.getText().toString();
                          else
                          chat_content = chat_content + "|" + message.getText().toString();



                      }

                       server.child(chat_str).child("chat_string").setValue(chat_content);
                        server.child(chat_str).child("start_from").setValue(user.getUid());
                   }
               }
           });

        chat_str = get_c(uid,user.getUid());
        first_who = uid.compareTo(user.getUid());


        server.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot childl : dataSnapshot.getChildren())
                {
                 if(childl.child("data").child("name").getValue().toString().matches("nulll"))
                    {   if(childl.child("id").getValue().toString().matches(chat_str))
                           chat_content = childl.child("chat_string").getValue().toString();
                           startfrom = childl.child("start_from").getValue().toString();
                           if(startfrom.matches(user.getUid()))
                           {
                               turn=0;
                           }
                           else
                           {
                               turn=1;
                           }
                    }
//                    else
//                    {
//                        server.child(chat_str).child("chat_string").setValue("null|");
//                    }
                    Loadcontent();
                   turn=0;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void Loadcontent() {
          if(chat_content.equals("nulll"))
          {  examplelist.clear();
              Toast.makeText(this,"No talks yet",Toast.LENGTH_LONG).show();
          }
          else
          {
               examplelist.clear();
               String temp = "";
               for(int x=0;x<chat_content.length();x++)
               {
                   if(chat_content.charAt(x)=='|')
                   {   if(temp.matches("nulll"))
                       {
                          temp="";
                          turn++;
                       }
                       else
                        {
                            if(turn%2==0)
                                examplelist.add(new chat_content(temp,user.getDisplayName()));
                            else
                                examplelist.add(new chat_content(temp,name));
                           temp="";
                           turn++;
                            madapter.notifyDataSetChanged();
                        }
                   }
                   else if(x==chat_content.length()-1)
                   {   temp = temp +chat_content.charAt(x);
                       if(turn%2==0)
                           examplelist.add(new chat_content(temp,user.getDisplayName()));
                       else
                           examplelist.add(new chat_content(temp,name));
                       temp="";
                       turn++;
                       madapter.notifyDataSetChanged();
                   }
                   else
                   {   if(chat_content.charAt(x)==',')
                      {
                          if(turn%2==0)
                          examplelist.add(new chat_content(temp,user.getDisplayName()));
                          else
                          examplelist.add(new chat_content(temp,name));
                          temp="";
                          madapter.notifyDataSetChanged();
                      }
                      else
                      {
                       temp = temp +chat_content.charAt(x);
                      }
                   }

               }
          }

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


}
