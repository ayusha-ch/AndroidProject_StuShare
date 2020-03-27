package com.example.stu_share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import static com.example.stu_share.MessageCordinator.MESSAGE_LIST;

public class AdminMessageList extends AppCompatActivity {
    Button btnLogout, btnHome;
    ListView msgListView;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_message_list);
        user=(User)getIntent().getSerializableExtra("user");
        msgListView=findViewById(R.id.adminMessageList);
        final ArrayAdapter arrayAdapter_msg = new ArrayAdapter(this, android.R.layout.simple_list_item_1,MESSAGE_LIST);
        msgListView.setAdapter(arrayAdapter_msg);
        msgListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                MessageCordinator.Message message =(MessageCordinator.Message) adapter.getItemAtPosition(position);
                Intent intent =new Intent(getBaseContext(), MessageReceivedDetail.class);
                intent.putExtra("user",user);
                intent.putExtra("message",message);
                startActivity(intent);
            }
        });

        btnHome = findViewById(R.id.btnAdminMsgHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        btnLogout = findViewById(R.id.btnAdminMsgLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
//              intent.putExtra("args", userReg);
                startActivity(intent);
            }
        });
    }
}
