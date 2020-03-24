package com.example.stu_share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.stu_share.RoomCoordinator.ROOM_LIST;

public class RoomCreate extends AppCompatActivity {
    private EditText textTitle,textDate,textRooms,textlease,textPets, textOffering,textTypeHouse,textLocation,textDetail,textRent;
    private Button home,logout,create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_create);
        textTitle=findViewById(R.id.txtRoomTitle);
        textDate=findViewById(R.id.txtCarDate);
        textOffering=findViewById(R.id.txtOffering);
        textlease=findViewById(R.id.txtlease);
        textRooms=findViewById(R.id.txtRooms);
        textPets=findViewById(R.id.txtPets);
        textTypeHouse=findViewById(R.id.txtTypeHouse);
        textLocation=findViewById(R.id.txtLocation);
        textDetail=findViewById(R.id.txtDetail);
        textRent=findViewById(R.id.txtRent);
        home=findViewById(R.id.btnHome3);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getBaseContext(), RoomMenu.class);
                startActivity(i);
            }
        });
        create=findViewById(R.id.buttonCrt);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RoomCoordinator.Room temp=new RoomCoordinator.Room("1","1",textTitle.getText().toString(),
                        textDate.getText().toString(),
                        textRooms.getText().toString(),
                        textlease.getText().toString(),textOffering.getText().toString(),
                        textTypeHouse.getText().toString(),textPets.getText().toString(),textLocation.getText().toString(),
                        textDetail.getText().toString(),textRent.getText().toString(),"active","today");
                ROOM_LIST.add(temp);
                Toast.makeText(RoomCreate.this, "You car share information has been recorded!", Toast.LENGTH_SHORT).show();
                Intent i =new Intent(getBaseContext(),RoomMenu.class);
                startActivity(i);
            }
        });
        logout=findViewById(R.id.btnLogout2);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);
            }
        });

    }
    }

