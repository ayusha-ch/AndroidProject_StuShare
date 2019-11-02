package com.example.stu_share;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Owned extends AppCompatActivity {
    private Button btnCancel, btnEdit, btnViewAttend, btnCheckIn;
    ListView listView;
    DBHelper dbHelper=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owned);
        dbHelper=new DBHelper(this);
        final SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] args=(String[])getIntent().getSerializableExtra("args");
        dbHelper.updateEventList(db,dbHelper.getEventCursorOwn(db,args[0]),args[0]);
        listView = (ListView) findViewById(R.id.listV1);
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,EventCoordinator.EVENTS);
        listView.setAdapter(arrayAdapter);
    }
}
