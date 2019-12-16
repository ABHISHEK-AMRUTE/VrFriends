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

    String startstring="",anotherstring="",startname="",othername="";
    int sartwith =0;
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
                   if(message.getText().toString().equals(null))
                   {
                       Toast.makeText(chat.this,"Message field is empty..",Toast.LENGTH_LONG).show();
                   }
                   else
                   {  String write = message.getText().toString();
                      if(startfrom.equals(user.getUid()))
                      {
                         if(startfrom.equals("nulll"))
                         {
                            startstring = write;
                            anotherstring = anotherstring + "|";
                         }
                         else if(startstring.charAt(startstring.length()-1)=='|')
                         {
                             //insert as it is with | in opposite string
                             startstring += write;
                             anotherstring = anotherstring + "|";
                         }
                         else
                         {     startstring = startstring + "~" + write;
                             if(anotherstring.charAt(anotherstring.length()-1)!='|')
                             anotherstring = anotherstring + "|";
                             //insert ~ in the string with | in opposite string
                         }
                         server.child(chat_str).child("chat_string"+user.getUid()).setValue(startstring);
                          server.child(chat_str).child("chat_string"+uid).setValue(anotherstring);

                      }
                     else
                     {
                         if(anotherstring.charAt(anotherstring.length()-1)=='|')
                         {
                             //insert as it is with | in start string
                             anotherstring = anotherstring + write;
                             startstring = startstring + "|";
                         }
                         else
                         {
                             //insert ~ in the string with | in start string
                             anotherstring = anotherstring + "~" +write;
                             if(startstring.charAt(startstring.length()-1)!='|')
                             startstring = startstring + "|";
                         }
                         server.child(chat_str).child("chat_string"+user.getUid()).setValue(anotherstring);
                         server.child(chat_str).child("chat_string"+uid).setValue(startstring);
                     }
                   }
               }
           });
        Toast.makeText(chat.this,"inside",Toast.LENGTH_LONG).show();
        chat_str = get_c(uid,user.getUid());
        first_who = uid.compareTo(user.getUid());


        server.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot childl : dataSnapshot.getChildren())
                {
                 if(childl.child("data").child("name").getValue().toString().matches("nulll"))
                    {   if(childl.child("id").getValue().toString().matches(chat_str))
                    {  startfrom = childl.child("start_from").getValue().toString();
                           if(startfrom.matches(user.getUid()))
                           {
                               startstring = childl.child("chat_string"+user.getUid()).getValue().toString();
                               anotherstring=childl.child("chat_string"+uid).getValue().toString();
                               startname= user.getDisplayName();
                               othername= name;
                           }
                           else
                           {
                               startstring = childl.child("chat_string"+uid).getValue().toString();
                               anotherstring=childl.child("chat_string"+user.getUid()).getValue().toString();
                               othername= user.getDisplayName();
                               startname = name;
                           }

                    }
                    }
                    Toast.makeText(chat.this,"contetn loading",Toast.LENGTH_LONG).show();
                    Loadcontent();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void Loadcontent() {
        Toast.makeText(chat.this,"lodaign askmdkas",Toast.LENGTH_LONG).show();
        examplelist.clear();

       int start_len = startstring.length();
       int ano_len = anotherstring.length();

       String temp="";

       for(int x=0,y=0;x<start_len;)
       {
           if(startstring.charAt(x)=='~')
           {
               examplelist.add(new chat_content(temp,startname));
               madapter.notifyDataSetChanged();
               x++;
               temp = "";

           }
           else if(startstring.charAt(x)=='|')
           {
               if(!temp.equals("nulll"))
               {   examplelist.add(new chat_content(temp,startname));
                   madapter.notifyDataSetChanged();}
                   temp="";
                   x++;
                  while(y<ano_len)
                  {

                      if(anotherstring.charAt(y)=='~')
                      {
                          examplelist.add(new chat_content(temp,othername));
                          madapter.notifyDataSetChanged();
                          y++;
                          temp = "";
                      }
                      else if(anotherstring.charAt(y)=='|')
                      {   examplelist.add(new chat_content(temp,othername));
                          madapter.notifyDataSetChanged();
                          temp="";
                                  y++;
                         break;
                      }
                      else if(y==ano_len-1)
                      {   temp = temp + anotherstring.charAt(y) ;
                          if(!temp.equals("nulll"))
                          {examplelist.add(new chat_content(temp,othername));
                          madapter.notifyDataSetChanged();}
                          temp = "";y++;
                      }
                      else if(anotherstring.charAt(y)!='|')
                      {
                          temp = temp + anotherstring.charAt(y) ;
                          y++;
                      }

                  }

           }
           else if(x==start_len-1)
           {    temp = temp + startstring.charAt(x) ;
               if(!temp.equals("nulll"))
               {
                   examplelist.add(new chat_content(temp,startname));
               madapter.notifyDataSetChanged();}
               temp = "";x++;
           }
           else if(startstring.charAt(x)!='|')
           {
               temp = temp + startstring.charAt(x) ;
               x++;
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
