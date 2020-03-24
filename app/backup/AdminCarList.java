package com.example.stu_share;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.stu_share.CarCoordinator.CAR_LIST;

public class AdminCarList extends AppCompatActivity {
    private ListView listCar;
    private Button home, logout;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);
        user=(User)getIntent().getSerializableExtra("user");
        listCar=findViewById(R.id.listCar1);
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,CAR_LIST);
        listCar.setAdapter(arrayAdapter);
        listCar.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                CarCoordinator.Car car =(CarCoordinator.Car) adapter.getItemAtPosition(position);
                Intent intent =new Intent(getBaseContext(), CarDetail.class);
                intent.putExtra("car",car);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });
        home=findViewById(R.id.btnHome6);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),AdminDashboard.class);
                i.putExtra("user",user);
                startActivity(i);
            }
        });
        logout=findViewById(R.id.btnLogout12);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}