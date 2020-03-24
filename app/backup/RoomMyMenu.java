package com.example.stu_share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RoomMyMenu extends AppCompatActivity {
    private Button home,logout,viewCurrent,viewPast,create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_my_menu);

        home=findViewById(R.id.btnMainMenu2);
        logout=findViewById(R.id.btnLogout3);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),RoomMenu.class);
                startActivity(i);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);
            }
        });
        viewCurrent=findViewById(R.id.btnViewCurrentRoom);
        viewCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(),RoomCurrentList.class);
                startActivity(i);
            }
        });
        viewPast=findViewById(R.id.btnViewPastRooms);
        viewPast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(),RoomPastList.class);
                startActivity(i);
            }
        });
        create=findViewById(R.id.btnCrtRoom3);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(),RoomCreate.class);
                startActivity(i);
            }
        });


    }
}
