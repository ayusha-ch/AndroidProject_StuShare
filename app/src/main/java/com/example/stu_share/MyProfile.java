package com.example.stu_share;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import butterknife.BindView;
import butterknife.ButterKnife;
public class MyProfile extends AppCompatActivity {
    Button btnHome4, btnLogout4, btnEdit;
    TextView txtFn,txtLn,txtEm,txtQ;
    private static User userTemp;
    ImageView buttonImg;
    @BindView(R.id.toolbar)
    public Toolbar toolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userTemp=(User)getIntent().getSerializableExtra("user");
        setContentView(R.layout.activity_profile_my_profile);
        btnEdit = findViewById(R.id.btnEdit);
        txtFn=findViewById(R.id.txtFName);
        txtLn=findViewById(R.id.txtLName);
        txtEm=findViewById(R.id.txtEmail);
        txtQ=findViewById(R.id.txtSecQues);
        txtFn.setText(userTemp.firstName);
        txtLn.setText(userTemp.lastName);
        txtEm.setText(userTemp.email);
        txtQ.setText(userTemp.question);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditProfile();
            }
        });
        View myview=findViewById(R.id.masterView);
        myview.setOnTouchListener(new View.OnTouchListener() {
            float x1;
            float x2;
            float y1;
            float y2;
            @Override
            public boolean onTouch(View v, MotionEvent touchEvent) {
                switch(touchEvent.getActionMasked()){
                    //Start point

                    case MotionEvent.ACTION_DOWN:
                        x1 = touchEvent.getX();
                        Log.i("X1down",String.valueOf(x1));
                        y1 = touchEvent.getY();
                        return true;

                    case MotionEvent.ACTION_UP:
                        x2 = touchEvent.getX();
                        y2 = touchEvent.getY();
                        Log.i("X2UP",x2+"||"+x1);
                        Class cc;
                        if(x2- x1<50){
                            if(userTemp.role.equals("admin")){
                                cc = AdminEventList.class;
                            }else{
                                cc=EventList.class;
                            }
                            Intent i = new Intent(getApplicationContext(),cc );
                            i.putExtra("user",userTemp);
                            //Regular class call activity need use .setFlags method
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(i);
                        }else if(x2 -  x1>50){
                            if(userTemp.role.equals("admin")){
                                cc = AdminMessageList.class;
                            }else{
                                cc= MessageList.class;
                            }
                            Intent i = new Intent(getApplicationContext(), cc);
                            i.putExtra("user",userTemp);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(i);
                        }
                        break;
                }
                return false;
            }});

        ButterKnife.bind(this);
        toolBar.setTitle(getResources().getString(R.string.profile));
        setSupportActionBar(toolBar);
        buttonImg = findViewById(R.id.buttonImg) ;
        buttonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCreateActivity();
            }
        });
        DrawerUtil.getDrawer(this,toolBar);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        OpenMenuActivity();
                        break;
                    case R.id.action_message:
                        Intent intent = new Intent(getBaseContext(), MessageList.class);
                        intent.putExtra("user",userTemp);
                        startActivity(intent);
                        break;
                    case R.id.action_myevents:
                        openMyEventsActivity();
                        break;
                    case R.id.action_profile:
                        Intent i= new Intent(getBaseContext(),MyProfile.class);
                        i.putExtra("user",userTemp);
                        startActivity(i);
                        break;
                }
                return false;
            }
        });
    }

    public void openEditProfile(){
        Intent intent =new Intent(this, MyProfileEdit.class);
        intent.putExtra("user",userTemp);
        startActivity(intent);
    }

    public void logout(){
        Intent intent =new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void OpenMenuActivity() {
        Intent intent = new Intent(this, EventList.class);
        intent.putExtra("user",userTemp);
        startActivity(intent);
    }
    public void openMyEventsActivity(){
        Intent intent =new Intent(this, EventMyEvents.class);
        intent.putExtra("user",userTemp);
        startActivity(intent);
    }
    public void OpenCreateActivity() {
        Intent intent = new Intent(this, EventCreateDescription.class);
        intent.putExtra("user",userTemp);
        startActivity(intent);
    }
}
