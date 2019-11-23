package com.example.stu_share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class MyProfile extends AppCompatActivity {
    Button btnHome4, btnLogout4, btnEdit;
    TextView txtFn,txtLn,txtEm,txtQ;
    private User userTemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userTemp=(User)getIntent().getSerializableExtra("user");
        setContentView(R.layout.activity_my_profile);
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
        Intent intent =new Intent(this, EditProfile.class);
        intent.putExtra("user",userTemp);
        startActivity(intent);
    }
    public void OpenMenuActivity() {
        Intent intent = new Intent(this, EventMenu.class);
        intent.putExtra("user",userTemp);
        startActivity(intent);
    }

    public void logout(){
        Intent intent =new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
