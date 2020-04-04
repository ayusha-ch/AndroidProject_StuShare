package com.example.stu_share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MessageReceivedDetail extends AppCompatActivity {
    Button btnReply, btnDelete, btnCancel;
    TextView msgT,msgB;
    MessageCoordinator.Message message1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_received_detail);
        message1=(MessageCoordinator.Message)getIntent().getSerializableExtra("message");
        Log.i("MSGTOSTRING",message1.toString());
        msgT=findViewById(R.id.textMsgSubject);
        msgB=findViewById(R.id.textMessageBody);
        msgT.setText(message1.title);
        msgB.setText(message1.detail);
        btnReply = findViewById(R.id.btnReply);
        btnReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MessageCreate.class);
                startActivity(intent);
            }
        });
        btnCancel = findViewById(R.id.btnMsg_CancelReply);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MessageList.class);
                startActivity(intent);
            }
        });
    }
}
