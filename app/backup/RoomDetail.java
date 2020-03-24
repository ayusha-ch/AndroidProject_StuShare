package com.example.stu_share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RoomDetail extends AppCompatActivity {
    private TextView textTitle, textDate, textRooms, textlease, textPets, textOffering, textTypeHouse, textLocation, textDetail, textRent;
    private Button home, logout, email, message,btnContact1;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);
        user=(User)getIntent().getSerializableExtra("user");
        Button btnEmail = findViewById(R.id.buttonEmail1);
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), EmailActivity.class);
                startActivity(intent);
            }
        });

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

        email=findViewById(R.id.buttonEmail1);
        home=findViewById(R.id.btnHome3);
        logout=findViewById(R.id.btnLogout2);
        message=findViewById(R.id.buttonMessage1);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(), MessageCreate.class);
                startActivity(i);
            }
        });
        btnContact1=findViewById(R.id.btnContact);
        btnContact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(), MessageCreate.class);
                i.putExtra("user",user);
                i.putExtra("id","admin");
                startActivity(i);

            }
        });

        final RoomCoordinator.Room room=(RoomCoordinator.Room)getIntent().getSerializableExtra("room");
        textTitle.setText(room.title);
        textDate.setText(room.availableDate);
        textRooms.setText(room.rooms);
        textTypeHouse.setText(room.type);
        textOffering.setText(room.category);
        textPets.setText(room.petFriendly);
        textLocation.setText(room.location);
        textDetail.setText(room.detail);
        textRent.setText(room.rent);
        textlease.setText(room.lease);

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


    }
}
