package com.example.stu_share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;



public class CarMenu extends AppCompatActivity {
    private Button buttonMyCars,buttonViewCars,buttonCreateCars,buttonMessageCenter,buttonMain,buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_menu);

        buttonCreateCars=findViewById(R.id.btnCreateCar);
        buttonCreateCars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),CarCreate.class);
                startActivity(i);
            }
        });
        buttonMessageCenter=findViewById(R.id.btnMessageCenter);
        buttonMessageCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),MessageListActivity.class);
                startActivity(i);
            }
        });
        buttonMyCars=findViewById(R.id.btnMyCars);
        buttonMyCars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),CarMyMenu.class);
                startActivity(i);
            }
        });
        buttonViewCars=findViewById(R.id.btnViewCars);
        buttonViewCars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),CarList.class);
                startActivity(i);
            }
        });
        buttonMain=findViewById(R.id.btnMainMenu);
        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),MainMenu.class);
                startActivity(i);
            }
        });
        buttonLogout=findViewById(R.id.btnLogout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}
