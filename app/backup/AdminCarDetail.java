package com.example.stu_share;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminCarDetail extends AppCompatActivity {
    private TextView textTitle,textDate,textBrand,textModel,textYear,textMile,textLocation,textDetail,textPrice;
    private Button home,logout,terminate,btnContact1;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);
        user=(User)getIntent().getSerializableExtra("user");
        Button btnEmail = findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),EmailActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });


        textTitle=findViewById(R.id.txtCarTitle);
        textDate=findViewById(R.id.txtCarDate);
        textBrand=findViewById(R.id.txtCarBrand);
        textModel=findViewById(R.id.txtCarModel);
        textYear=findViewById(R.id.txtCarYear3);
        textMile=findViewById(R.id.txtCarMileage);
        textLocation=findViewById(R.id.txtCarLocation);
        textDetail=findViewById(R.id.txtCarDetail3);
        textPrice=findViewById(R.id.txtCarPrice);
        terminate=findViewById(R.id.btnAdmCarTerminate);
        terminate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminCarDetail.this,"Your post has been terminated",Toast.LENGTH_LONG).show();
                Intent i=new Intent(getBaseContext(),AdminDashboard.class);
                i.putExtra("user",user);
                startActivity(i);
            }
        });
        home=findViewById(R.id.btnHome3);
        logout=findViewById(R.id.btnLogout2);

        CarCoordinator.Car car=(CarCoordinator.Car)getIntent().getSerializableExtra("car");
        textTitle.setText(car.title);
        textDate.setText(car.availableDate);
        textBrand.setText(car.brand);
        textModel.setText(car.model);
        textYear.setText(car.year);
        textMile.setText(car.mileage);
        textLocation.setText(car.location);
        textDetail.setText(car.detail);
        textPrice.setText(car.price);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),AdminDashboard.class);
                i.putExtra("user",user);
                startActivity(i);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);
            }
        });
        btnContact1=findViewById(R.id.btnContact);
        btnContact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(), MessageCreate.class);
                i.putExtra("user",user);
                i.putExtra("id","admin");
                startActivity(i);

            }
        });

    }


}
