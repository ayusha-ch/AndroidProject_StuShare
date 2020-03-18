package com.example.stu_share;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EventPastDetail extends AppCompatActivity {
    private Button btnLogout, btnHome, btnDeReg;
    private TextView txtEvtTitle, txtEvtDetail, txtStDate, txtStTime, txtEndTime, txtEndDate;
    //DBHelper dbHelper = null;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_past_detail);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        OpenMenuActivity();
                        break;
                    case R.id.action_message:
                        Intent intent = new Intent(getBaseContext(), MessageList.class);
//              intent.putExtra("args", userReg);
                        intent.putExtra("user",user);
                        startActivity(intent);
                        break;
                    case R.id.action_myevents:
                        openMyEventsActivity();
                        break;

//                    case R.id.action_profile:
//                        Intent i= new Intent(getBaseContext(),MyProfile.class);
//                        i.putExtra("user",user);
//                        startActivity(i);
//                        break;
                }
                return false;
            }
        });
        //btnDeReg = findViewById(R.id.btnReg123);
        btnLogout = findViewById(R.id.btnAlLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        user=(User)getIntent().getSerializableExtra("user");
        btnHome = findViewById(R.id.btnHome);
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
        /*btnDeReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete from evenreg table
                dbHelper = new DBHelper(getBaseContext());
                final SQLiteDatabase db = dbHelper.getWritableDatabase();
                if(dbHelper.eventDeReg(db, event1.id, "2")){
                    evt.remove(Integer.valueOf(position));
                    Toast.makeText(getBaseContext(),"selected"+position,Toast.LENGTH_LONG);

                }
                else {
                    evt.remove(Integer.valueOf(position));
                    Toast.makeText(getBaseContext(),"Not"+position,Toast.LENGTH_LONG);

                }
            }
        });
    }*/
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
