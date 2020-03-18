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
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EventJoinedDetail extends AppCompatActivity {
    private Button btnLogout,btnDeReg;
    private TextView txtEvtTitle,txtEvtDetail,txtStDate,txtStTime,txtEndTime,txtEndDate;
    //DBHelper dbHelper = null;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_joined_detail);
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
////                        Intent i= new Intent(getBaseContext(),MyProfile.class);
////                        i.putExtra("user",user);
////                        startActivity(i);
////                        break;
                }
                return false;
            }
        });
        btnDeReg=findViewById(R.id.btnDereg);
        btnLogout=findViewById(R.id.btnLogout111);
        txtEvtTitle=findViewById(R.id.txtEventTitle99);
        txtEvtDetail=findViewById(R.id.txtEvtDetail9);
        txtStDate=findViewById(R.id.txtStDate99);
        txtStTime=findViewById(R.id.txtStTime99);
        txtEndDate=findViewById(R.id.txtEndDate99);
        txtEndTime=findViewById(R.id.txtEndTime99);
        user=(User)getIntent().getSerializableExtra("user");
        final EventCoordinator.Event event=(EventCoordinator.Event)getIntent().getSerializableExtra("args");
        txtEvtTitle.setText(event.eventTitle);
        txtEvtDetail.setText(event.eventDetail);
        txtStTime.setText(event.startTime);
        txtStDate.setText(event.startDate);
        txtEndDate.setText(event.endDate);
        txtEndTime.setText(event.endTime);
        btnDeReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete record

                Toast.makeText(getBaseContext(), "Deregister successfully!",
                        Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getBaseContext(),EventMenu.class);
                intent.putExtra("user",user);
                intent.putExtra("event",event);
                startActivity(intent);
            }
        });
    }
    public void OpenMenuActivity() {
        Intent intent = new Intent(this, EventMenu.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
    public void openMyEventsActivity(){
        Intent intent =new Intent(this, EventMyEvents.class);
        intent.putExtra("user",user);
        Log.d("TAG","Menu to MyEvent"+user.id);
        startActivity(intent);
    }
}
