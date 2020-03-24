package com.example.stu_share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.stu_share.CarCoordinator.CAR_LIST;

public class CarCreate extends AppCompatActivity {
    private EditText textTitle,textDate,textBrand,textModel,textYear,textMile,textLocation,textDetail,textPrice;
    private Button home,logout,create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_create);
        textTitle=findViewById(R.id.txtCarTitle);
        textDate=findViewById(R.id.txtCarDate);
        textBrand=findViewById(R.id.txtCarBrand);
        textModel=findViewById(R.id.txtCarModel);
        textYear=findViewById(R.id.txtCarYear);
        textMile=findViewById(R.id.txtCarMileage);
        textLocation=findViewById(R.id.txtCarLocation);
        textDetail=findViewById(R.id.txtCarDetail);
        textPrice=findViewById(R.id.txtCarPrice);
        home=findViewById(R.id.btnHome3);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getBaseContext(),CarMenu.class);
                startActivity(i);
            }
        });
        create=findViewById(R.id.buttonCrt);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarCoordinator.Car temp=new CarCoordinator.Car("1","1",textTitle.getText().toString(),textDate.getText().toString(),textBrand.getText().toString(),textModel.getText().toString(),textYear.getText().toString(),
                        textMile.getText().toString(),textLocation.getText().toString(),textDetail.getText().toString(),textPrice.getText().toString(),"active","today");
                CAR_LIST.add(temp);
                Toast.makeText(CarCreate.this, "You car share information has been recorded!", Toast.LENGTH_SHORT).show();
                Intent i =new Intent(getBaseContext(),CarMenu.class);
                startActivity(i);
            }
        });
        logout=findViewById(R.id.btnLogout2);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);
            }
        });

    }
}
