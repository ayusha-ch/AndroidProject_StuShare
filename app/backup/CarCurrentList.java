package com.example.stu_share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import static com.example.stu_share.CarCoordinator.CAR_LIST;

public class CarCurrentList extends AppCompatActivity {
    private ListView listCar;
    private Button home, logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_current_list);
        listCar=findViewById(R.id.listCar1);
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,CAR_LIST);
        listCar.setAdapter(arrayAdapter);
        listCar.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                arrayAdapter.notifyDataSetChanged();
                CarCoordinator.Car car =(CarCoordinator.Car) adapter.getItemAtPosition(position);
                Intent intent =new Intent(getBaseContext(), CarCurrentDDetail.class);
                intent.putExtra("car",car);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
        home=findViewById(R.id.btnHome5);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),CarMenu.class);
                startActivity(i);
            }
        });
        logout=findViewById(R.id.btnLogout5);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}
