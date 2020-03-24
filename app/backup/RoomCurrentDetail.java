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

public class RoomCurrentDetail extends AppCompatActivity {
    private EditText textTitle,textDate,textRooms,textlease,textPets, textOffering,textTypeHouse,textLocation,textDetail,textRent;
    private Button home,logout,terminate,update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_current_detail);

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
        terminate=findViewById(R.id.buttonTerminate);
        final RoomCoordinator.Room room=(RoomCoordinator.Room)getIntent().getSerializableExtra("room");
        final int position1 = (int)getIntent().getSerializableExtra("position1");
        terminate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ROOM_LIST.remove(room);
                ROOM_PAST_LIST.add(room);
                Toast.makeText(RoomCurrentDetail.this,"Your room share has been terminated.",Toast.LENGTH_LONG).show();
            }
        });
        home=findViewById(R.id.btnHome3);
        logout=findViewById(R.id.btnLogout2);

        update=findViewById(R.id.buttonUpdate);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getBaseContext(),"Your information has been updated!",Toast.LENGTH_LONG);
                Intent i=new Intent(getBaseContext(),RoomMenu.class);
                startActivity(i);
            }
        });

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
