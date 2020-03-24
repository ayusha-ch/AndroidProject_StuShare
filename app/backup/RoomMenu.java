package com.example.stu_share;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RoomMenu extends AppCompatActivity {
    private Button buttonMyRooms,buttonViewRooms,buttonCreateRooms,buttonMessageCenter,buttonMain,buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_menu);


        buttonCreateRooms=findViewById(R.id.btnCrtRooms);
        buttonCreateRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),RoomCreate.class);
                startActivity(i);
            }
        });
        buttonMessageCenter=findViewById(R.id.btnAlMessageCenter);
        buttonMessageCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(), MessageList.class);
                startActivity(i);
            }
        });
        buttonMyRooms=findViewById(R.id.btnMyRooms);
        buttonMyRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),RoomMyMenu.class);
                startActivity(i);
            }
        });
        buttonViewRooms=findViewById(R.id.btnViewRooms);
        buttonViewRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),RoomList.class);
                startActivity(i);
            }
        });
        buttonMain=findViewById(R.id.btnMainMenu1);
        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),MainMenu.class);
                startActivity(i);
            }
        });
        buttonLogout=findViewById(R.id.btnAlLogout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);
            }
        });

    }
}
