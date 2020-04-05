package com.example.stu_share;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventMyEvents extends AppCompatActivity {
    private Button  btnOwnedEvents, btnPstEvt,btnJoin,btnCreateEvent,btnHome, btnLogout,btnReg;
    private static User user;
    @BindView(R.id.toolbar)
    public Toolbar toolBar;
    ImageView buttonImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_my_events);

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

        user=(User)getIntent().getSerializableExtra("user");
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
        DrawerUtil.getDrawer(this,toolBar);

        btnCreateEvent=findViewById(R.id.btnCreate);
        btnCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateEvent();
            }
        });

        btnOwnedEvents = findViewById(R.id.btnOwnedEvents);
        btnOwnedEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOwnedEventActivity();
            }
        });
        btnJoin = findViewById(R.id.btnJoinEvt);
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openJoinActivity();
            }
        });
        btnReg = findViewById(R.id.btnReg);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegActivity();
            }
        });
        btnPstEvt = findViewById(R.id.btnDeAct);
        btnPstEvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPstEvtAct();
            }
        });

    }

    private void openRegActivity() {
        Intent intent =new Intent(this, EventListJoined.class);
        String[] tt=new String[]{"2","2"};
        //intent.putExtra("args",tt);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    public void openOwnedEventActivity(){
        Intent intent =new Intent(this, EventOwnedList.class);;
        intent.putExtra("user",user);
        startActivity(intent);
    }
    public void openPstEvtAct(){
        Intent intent =new Intent(this, EventPastList.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
    public void openJoinActivity(){
        Intent intent =new Intent(this, EventList.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
    public void openCreateEvent(){
        Intent intent =new Intent(this, EventCreateDescription.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    public void logout(){
        Intent intent =new Intent(this, MainActivity.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
    public void OpenMenuActivity() {
        Intent intent = new Intent(this, EventList.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
    public boolean onTouchEvent(MotionEvent touchEvent){
        return onTouchEvent(touchEvent,getApplicationContext());
    }
    public static float x1,x2,y1,y2;

    //To allow swipe left or right gesure
    public static boolean onTouchEvent(MotionEvent touchEvent, Context context){
        switch(touchEvent.getAction()){
            //Start point
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            //End point
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1 < x2){

                    Intent i = new Intent(context,EventList.class );
                    i.putExtra("user",user);
                    //Regular class call activity need use .setFlags method
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }else if(x1 >  x2){
                    Intent i = new Intent(context, MessageList.class);
                    i.putExtra("user",user);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
                break;
        }
        return false;
    }
    public void OpenCreateActivity() {
        Intent intent = new Intent(this, EventCreateDescription.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
}

