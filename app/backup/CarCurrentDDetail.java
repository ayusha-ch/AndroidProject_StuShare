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

public class CarCurrentDDetail extends AppCompatActivity {
    private EditText textTitle,textDate,textBrand,textModel,textYear,textMile,textLocation,textDetail,textPrice;
    private Button home,logout,terminate,update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_current_ddetail);
        textTitle=findViewById(R.id.txtCarTitle);
        textDate=findViewById(R.id.txtCarDate);
        textBrand=findViewById(R.id.txtCarBrand);
        textModel=findViewById(R.id.txtCarModel);
        textYear=findViewById(R.id.txtCarYear2);
        textMile=findViewById(R.id.txtCarMileage);
        textLocation=findViewById(R.id.txtCarLocation);
        textDetail=findViewById(R.id.txtCarDetail4);
        textPrice=findViewById(R.id.txtCarPrice);
        terminate=findViewById(R.id.btnTerminate1);
        final CarCoordinator.Car car=(CarCoordinator.Car)getIntent().getSerializableExtra("car");
        final int position=(int)getIntent().getSerializableExtra("position");
        terminate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CAR_LIST.remove(car);
                CAR_PAST_LIST.add(car);
                Toast.makeText(CarCurrentDDetail.this,"Your car share has been terminated.",Toast.LENGTH_LONG).show();
            }
        });
        home=findViewById(R.id.btnHome3);
        logout=findViewById(R.id.btnLogout2);

        update=findViewById(R.id.btnUpdate);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getBaseContext(),"Your information has been updated!",Toast.LENGTH_LONG);
                Intent i=new Intent(getBaseContext(),CarMenu.class);
                startActivity(i);
            }
        });

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
