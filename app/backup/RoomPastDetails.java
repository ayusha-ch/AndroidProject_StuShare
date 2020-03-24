package com.example.stu_share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.stu_share.RoomCoordinator.ROOM_LIST;
import static com.example.stu_share.RoomCoordinator.ROOM_PAST_LIST;

public class RoomPastDetails extends AppCompatActivity {

    private EditText textTitle,textDate,textRooms,textlease,textPets, textOffering,textTypeHouse,textLocation,textDetail,textRent;
    private Button home,logout, reactivate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_past_details);

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
        reactivate=findViewById(R.id.buttonAReactivate);


        final RoomCoordinator.Room room=(RoomCoordinator.Room)getIntent().getSerializableExtra("room");
        final int position1=(int)getIntent().getSerializableExtra("position1");
        reactivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RoomPastDetails.this, "Your room share is now activated!", Toast.LENGTH_SHORT).show();
                ROOM_PAST_LIST.remove(room);
                ROOM_LIST.add(room);

                Intent i=new Intent(getBaseContext(),RoomMenu.class);
                startActivity(i);
            }
        });


        home=findViewById(R.id.btnHome3);
        logout=findViewById(R.id.btnLogout2);
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
