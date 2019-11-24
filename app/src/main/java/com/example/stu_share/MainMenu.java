package com.example.stu_share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.stu_share.CarCoordinator.CAR_LIST;
import static com.example.stu_share.CarCoordinator.CAR_PAST_LIST;

public class MainMenu extends AppCompatActivity {
    private Button buttonBooks,buttonRooms,buttonCars,buttonEvents,buttonMyProfile,buttonMessageCenter,buttonLogout,buttonContactUs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        final User user=(User)getIntent().getSerializableExtra("user");
        buttonBooks=findViewById(R.id.btnBooks);
        buttonBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i= new Intent(getBaseContext(),BookMenu.class);
                //i.putExtra("user",user);
                //startActivity(i);
            }
        });
        buttonRooms=findViewById(R.id.btnRooms);
        buttonRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i= new Intent(getBaseContext(),RoomMenu.class);
                //i.putExtra("user",user);
                //startActivity(i);
            }
        });
        buttonEvents=findViewById(R.id.btnEvents);
        buttonEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),EventMenu.class);
                i.putExtra("user",user);
                startActivity(i);
            }
        });
        buttonCars=findViewById(R.id.btnCars);
        buttonCars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),CarMenu.class);
                i.putExtra("user",user);
                startActivity(i);
            }
        });
        buttonMyProfile=findViewById(R.id.btnMyProfile);
        buttonMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),MyProfile.class);
                i.putExtra("user",user);
                startActivity(i);
            }
        });
        buttonMessageCenter=findViewById(R.id.btnMessageCenter);
        buttonMessageCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),MessageListActivity.class);
                i.putExtra("user",user);
                startActivity(i);
            }
        });
        buttonLogout=findViewById(R.id.btnLogout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);
            }
        });
        buttonContactUs=findViewById((R.id.btnContactUs));
        buttonContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),MessageActivity.class);
                i.putExtra("user",user);
                i.putExtra("id","admin");
                startActivity(i);
            }
        });

    }


}
