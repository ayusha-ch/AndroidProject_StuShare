package com.example.stu_share;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminDashboard extends AppCompatActivity {
    Button profile,viewUser,viewEvents, btnLogout, btnViewMessage, btnViewBooks, btnViewCars,btnViewRoomShare;
    public static User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        btnLogout = findViewById(R.id.btnAdminLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        user=(User)getIntent().getSerializableExtra("user");
        profile = findViewById(R.id.btnAdmProfile);
        viewUser=findViewById(R.id.btnViewUsers);
        viewEvents=findViewById(R.id.btnViewCurEvent);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMyProfileEvent();
            }
        });
        viewEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), AdminEventList.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });
        viewUser.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(getBaseContext(), AdminUserList.class);
            intent.putExtra("user",user);
            startActivity(intent);
        }
        });
        btnViewMessage = findViewById(R.id.btnAdmViewMessage);
        btnViewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AdminMessageList.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        btnViewBooks = findViewById(R.id.btnViewBooks);
        btnViewBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getBaseContext(), AdminBookList.class);
                intent.putExtra("user",user);
               startActivity(intent);
            }
        });

        btnViewCars = findViewById(R.id.btnViewCarShares);
        btnViewCars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),AdminCarList.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });
        btnViewRoomShare = findViewById(R.id.btnViewRentalRoom);
        btnViewRoomShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(getBaseContext(), AdminRoomList.class);
                intent.putExtra("user",user);
              startActivity(intent);
            }
        });
        }
    public void openMyProfileEvent(){

        Intent intent =new Intent(this, MyProfile.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
    public void logout(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
}
