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
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.willy.ratingbar.RotationRatingBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import butterknife.BindView;

import butterknife.ButterKnife;

import static com.example.stu_share.EventAdapter.eventList;
import static com.example.stu_share.EventDetail.url_update;
import androidx.appcompat.app.AppCompatActivity;

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
                Class cc;
                if(x1 < x2){
                    if(userTemp.role.equals("admin")){
                        cc = AdminEventList.class;
                    }else{
                        cc=EventMyEvents.class;
                    }
                    Intent i = new Intent(context,cc );
                    i.putExtra("user",userTemp);
                    //Regular class call activity need use .setFlags method
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }else if(x1 >  x2){
                    if(userTemp.role.equals("admin")){
                        cc = AdminMessageList.class;
                    }else{
                        cc= MessageList.class;
                    }
                    Intent i = new Intent(context, cc);
                    i.putExtra("user",userTemp);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
                break;
        }
        return false;
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
