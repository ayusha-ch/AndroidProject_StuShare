package com.example.stu_share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.stu_share.CarCoordinator.CAR_LIST;
import static com.example.stu_share.CarCoordinator.CAR_PAST_LIST;

public class CarPastDetail extends AppCompatActivity {
    private EditText textTitle,textDate,textBrand,textModel,textYear,textMile,textLocation,textDetail,textPrice;
    private Button home,logout,reactivate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_past_detail);
        textTitle=findViewById(R.id.txtCarTitle);
        textDate=findViewById(R.id.txtCarDate);
        textBrand=findViewById(R.id.txtCarBrand);
        textModel=findViewById(R.id.txtCarModel);
        textYear=findViewById(R.id.txtCarYear4);
        textMile=findViewById(R.id.txtCarMileage);
        textLocation=findViewById(R.id.txtCarLocation);
        textDetail=findViewById(R.id.txtCarDetail2);
        textPrice=findViewById(R.id.txtCarPrice);
        reactivate=findViewById(R.id.btnReActivate);
        final CarCoordinator.Car car=(CarCoordinator.Car)getIntent().getSerializableExtra("car");
        final int position =(int)getIntent().getSerializableExtra("position");
        reactivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CarPastDetail.this, "Your car share is now activated!", Toast.LENGTH_SHORT).show();
                CAR_PAST_LIST.remove(car);
                CAR_LIST.add(position,car);

                Intent i=new Intent(getBaseContext(),CarMenu.class);
                startActivity(i);
            }
        });
        home=findViewById(R.id.btnHome3);
        logout=findViewById(R.id.btnLogout2);


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
                Intent i= new Intent(getBaseContext(),CarMenu.class);
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

    }
}
