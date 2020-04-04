package com.example.stu_share;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EventCreateDescription extends AppCompatActivity {

    private Button btnNextCreate, btnHome2, btnLogout;
    private User user;
    private EditText txtEvtTitle,txtEvtDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_description);
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
        txtEvtDetail=findViewById(R.id.txtEvtDetail);
        txtEvtTitle=findViewById(R.id.txtEvtTitle);
        user= (User)getIntent().getSerializableExtra("user");
        btnNextCreate = findViewById(R.id.btnNextCreate);
        btnNextCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtEvtTitle.length()<2||txtEvtTitle.length()>20){
                    Toast.makeText(getApplicationContext(),"Event title length doesn't meet requirement!",Toast.LENGTH_LONG).show();
                }
                else if(txtEvtDetail.length()<10||txtEvtTitle.length()>100){
                    Toast.makeText(getApplicationContext(),"Event detail length doesn't meet requirement!",Toast.LENGTH_LONG).show();
                }else{
                    EventCoordinator.Event event=new EventCoordinator.Event();
                    event.setEventTitle(txtEvtTitle.getText().toString());
                    event.setEventDetail(txtEvtDetail.getText().toString());
                    Intent intent =new Intent(getBaseContext(), EventCreate.class);
                    intent.putExtra("user",user);
                    intent.putExtra("event",event);
                    startActivity(intent);
                }
            }
        });

        btnLogout = findViewById(R.id.btnAlLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        btnHome2 = findViewById(R.id.btnHome2);
        btnHome2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenMenuActivity();
            }
        });
    }

    public void openCreateActivity(){

    }

    public void OpenMenuActivity() {
        Intent intent = new Intent(this, EventList.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    public void logout(){
        Intent intent =new Intent(this, MainActivity.class);
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
