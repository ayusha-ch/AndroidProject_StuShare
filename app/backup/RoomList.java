package com.example.stu_share;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import static com.example.stu_share.RoomCoordinator.ROOM_LIST;

public class RoomList extends AppCompatActivity {

    private ListView listRoom;
    private Button home, logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        listRoom=findViewById(R.id.listRoom1);
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ROOM_LIST);
        listRoom.setAdapter(arrayAdapter);
        listRoom.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position1,
                                    long arg3)
            {
                arrayAdapter.notifyDataSetChanged();
                RoomCoordinator.Room room =(RoomCoordinator.Room) adapter.getItemAtPosition(position1);
                Intent intent =new Intent(getBaseContext(), RoomCurrentDetail.class);
                intent.putExtra("room",room);
                intent.putExtra("position1",position1);
                startActivity(intent);
            }
        });

        home=findViewById(R.id.btnHome6);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),RoomMenu.class);
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
