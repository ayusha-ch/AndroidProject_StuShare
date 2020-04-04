package com.example.stu_share;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.willy.ratingbar.RotationRatingBar;

public class EventPastDetail extends AppCompatActivity {
    private Button btnLogout, btnJoin,btnContact1, btnHome3,btnHome;
    private TextView txtEvtTitle, txtEvtDetail, txtStDate, txtStTime, txtEndTime, txtEndDate,txtEventC;
    private  User user;
    private ImageView shareImage,imageCheck;
    private RotationRatingBar rotationRatingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_past_detail);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.include);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        OpenMenuActivity();
                        break;
                    case R.id.action_message:
                        Intent intent = new Intent(getBaseContext(), MessageList.class);
                        intent.putExtra("user",user);
                        startActivity(intent);
                        break;
                    case R.id.action_myevents:
                        openMyEventsActivity();
                        break;
                }
                return false;
            }
        });
        btnLogout = findViewById(R.id.btnLogout2);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        user=(User)getIntent().getSerializableExtra("user");
        btnHome = findViewById(R.id.btnHome3);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenHome();
            }
        });
        txtEvtTitle = findViewById(R.id.txtEventTitle);
        txtEvtDetail = findViewById(R.id.txtEvtDetail);
        txtStDate = findViewById(R.id.txtStDate);
        txtStTime = findViewById(R.id.txtStTime);
        txtEndDate = findViewById(R.id.txtEndDate);
        txtEndTime = findViewById(R.id.txtEndTime);
        //final String position = getIntent().getSerializableExtra("position").toString();
        final EventCoordinator.Event event1 = (EventCoordinator.Event) getIntent().getSerializableExtra("args");
        txtEvtTitle.setText(event1.eventTitle);
        txtEvtDetail.setText(event1.eventDetail);
        txtStTime.setText(event1.startTime);
        txtStDate.setText(event1.startDate);
        txtEndDate.setText(event1.endDate);
        txtEndTime.setText(event1.endTime);
        rotationRatingBar=findViewById(R.id.ratingBarDetail);
        rotationRatingBar.setRating(event1.rating);
        txtEventC=findViewById(R.id.txtEventCode);
        txtEventC.setText(event1.eventCode);
    }

        public void OpenHome () {
            Intent intent = new Intent(this, EventMenu.class);
            intent.putExtra("user",user);
            startActivity(intent);
        }
        public void logout () {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    public void openMyEventsActivity(){
        Intent intent =new Intent(this, EventMyEvents.class);
        intent.putExtra("user",user);
        Log.d("TAG","Menu to MyEvent"+user.id);
        startActivity(intent);
    }
    public void OpenMenuActivity() {
        Intent intent = new Intent(this, EventMenu.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
}
