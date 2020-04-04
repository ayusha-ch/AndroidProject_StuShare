package com.example.stu_share;


import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventCreateDescription extends AppCompatActivity {

    ImageView buttonImg;
    @BindView(R.id.toolbar)
    public Toolbar toolBar;
    private Button btnNextCreate;
    private User user;
    private EditText txtEvtTitle,txtEvtDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_description);

        ButterKnife.bind(this);
        toolBar.setTitle(getResources().getString(R.string.Events));
        setSupportActionBar(toolBar);
        buttonImg = findViewById(R.id.buttonImg) ;
        buttonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCreateActivity();
            }
        });

        DrawerUtil.getDrawer(this,toolBar);

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
                    case R.id.action_profile:
                        Intent i= new Intent(getBaseContext(),MyProfile.class);
                        i.putExtra("user",user);
                        startActivity(i);
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

    public void OpenCreateActivity() {
        Intent intent = new Intent(this, EventCreateDescription.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
}
