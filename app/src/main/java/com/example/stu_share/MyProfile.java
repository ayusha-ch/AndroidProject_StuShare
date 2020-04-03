package com.example.stu_share;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MyProfile extends AppCompatActivity {
    Button btnHome4, btnLogout4, btnEdit;
    TextView txtFn,txtLn,txtEm,txtQ;
    private static User userTemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userTemp=(User)getIntent().getSerializableExtra("user");
        setContentView(R.layout.activity_profile_my_profile);
        btnHome4 = findViewById(R.id.btnHome4);
        btnLogout4 = findViewById(R.id.btnLogout4);
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
        btnLogout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        btnHome4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenMenuActivity();
            }
        });
    }
    public void openEditProfile(){
        Intent intent =new Intent(this, MyProfileEdit.class);
        intent.putExtra("user",userTemp);
        startActivity(intent);
    }
    public void OpenMenuActivity() {
        if(userTemp.role.equals("admin")){
            Intent intent = new Intent(this, AdminUserList.class);
            intent.putExtra("user",userTemp);
            startActivity(intent);
        }else  if(userTemp.role.equals("alumni")){
            Intent intent = new Intent(this, AlumnaiDashboard.class);
            intent.putExtra("user",userTemp);
            startActivity(intent);
        }else{Intent intent = new Intent(this, EventList.class);
            intent.putExtra("user",userTemp);
            startActivity(intent);}
    }
    public void logout(){
        Intent intent =new Intent(this, MainActivity.class);
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
}
