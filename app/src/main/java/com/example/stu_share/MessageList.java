package com.example.stu_share;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.stu_share.MessageCordinator.MESSAGE_LIST;

public class MessageList extends AppCompatActivity {
    Button msgHome, msgLogout;
    ListView messageList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);

        messageList=findViewById(R.id.messageList);
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,MESSAGE_LIST);
        messageList.setAdapter(arrayAdapter);
        messageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {

                MessageCordinator.Message message =(MessageCordinator.Message) adapter.getItemAtPosition(position);
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
}