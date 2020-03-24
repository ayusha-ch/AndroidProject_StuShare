package com.example.stu_share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AlumnaiDashboard extends AppCompatActivity {
    private Button buttonBooks,buttonMyProfile,buttonMessageCenter,buttonLogout,buttonContactUs;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnai_dashboard);
        user=(User)getIntent().getSerializableExtra("user");
        buttonBooks = findViewById(R.id.btnAlBooks);
        buttonMyProfile = findViewById(R.id.btnAlMyProfile);
        buttonMessageCenter = findViewById(R.id.btnAlMessageCenter);
        buttonLogout = findViewById(R.id.btnAlLogout);
        buttonContactUs = findViewById(R.id.btnAlContactUs);

        buttonBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),MyProfile.class);
                i.putExtra("user",user);
                startActivity(i);

            }
        });

        buttonMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),MyProfile.class);
                i.putExtra("user",user);
                startActivity(i);

            }
        });

        buttonMessageCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(), MessageList.class);
               i.putExtra("user",user);
                startActivity(i);

            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);
            }
        });

        buttonContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(), MessageCreate.class);
                i.putExtra("user",user);
                i.putExtra("id","admin");
                startActivity(i);

            }
        });
    }
}
