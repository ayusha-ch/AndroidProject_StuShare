package com.example.stu_share;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EmailActivity extends AppCompatActivity {
    private EditText mEditTextTo;
    private EditText mEditTextSubject;
    private EditText mEditTextMessage;
    private User user;
    private EventCoordinator.Event event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        user=(User)getIntent().getSerializableExtra("user");
        event=(EventCoordinator.Event)getIntent().getSerializableExtra("event");
        mEditTextTo = findViewById(R.id.edit_text_to);
        mEditTextTo.setText(event.orgEmail);
        mEditTextSubject = findViewById(R.id.edit_text_subject);
        String title="Re: About your posting: "+event.eventTitle;
        mEditTextSubject.setText(title);
        mEditTextMessage = findViewById(R.id.edit_text_message);
        String message="Hi, I just saw it in your posting : "+ event.eventDetail+"\n\n\n\n\n\n"+user.firstName+" "+user.lastName+"\n"+user.email;
        mEditTextMessage.setText(message);
        Button buttonSend = findViewById(R.id.button_send);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });

        Button buttonCancel= findViewById(R.id.button_cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), EventList.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }

    private void sendMail() {

    }
}