package com.example.stu_share;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.stu_share.MessageCoordinator.MESSAGE_LIST;

public class MessageList extends AppCompatActivity {
    Button msgHome, msgLogout;
    ListView messageList;
    private static User userTemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        userTemp=(User)getIntent().getSerializableExtra("user");
        messageList=findViewById(R.id.messageList);
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,MESSAGE_LIST);
        messageList.setAdapter(arrayAdapter);
        messageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                MessageCoordinator.Message message =(MessageCoordinator.Message) adapter.getItemAtPosition(position);
                Intent intent =new Intent(getBaseContext(), MessageReceivedDetail.class);
                intent.putExtra("message",message);
                startActivity(intent);
            }
        });


       msgHome = findViewById(R.id.btnMsgHome);
       msgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), EventList.class);
//              intent.putExtra("args", userReg);
                startActivity(intent);
            }
        });

        msgLogout = findViewById(R.id.btnMsgLogout);
        msgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
//              intent.putExtra("args", userReg);
                startActivity(intent);



            }

        });
    }
    public boolean onTouchEvent(MotionEvent touchEvent){
        return onTouchEvent(touchEvent,getApplicationContext());
    }
    public static float x1,x2,y1,y2;
    public static boolean onTouchEvent(MotionEvent touchEvent, Context context){
        switch(touchEvent.getAction()){
            //Start point
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            //End point
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                Class cc;
                if(x1 < x2){
                    cc=MyProfile.class;
                    Intent i = new Intent(context,cc );
                    i.putExtra("user",userTemp);
                    //Regular class call activity need use .setFlags method
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
                else if(x1 >  x2){
                    cc= EventList.class;
                    Intent i = new Intent(context, cc);
                    i.putExtra("user",userTemp);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
                break;
        }
        return false;
    }
}