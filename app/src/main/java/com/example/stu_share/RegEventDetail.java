package com.example.stu_share;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.stu_share.ListJoinedEventActivity.evt;

public class RegEventDetail extends AppCompatActivity {
    private Button btnLogout, btnDeReg;
    private TextView txtEvtTitle, txtEvtDetail, txtStDate, txtStTime, txtEndTime, txtEndDate;
    DBHelper dbHelper = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_event_detail);



        btnDeReg = findViewById(R.id.btnReg123);
        btnLogout = findViewById(R.id.btnLogout111);
        txtEvtTitle = findViewById(R.id.txtEventTitle123);
        txtEvtDetail = findViewById(R.id.txtEvtDetail123);
        txtStDate = findViewById(R.id.txtStDate123);
        txtStTime = findViewById(R.id.txtStTime123);
        txtEndDate = findViewById(R.id.txtEndDate123);
        txtEndTime = findViewById(R.id.txtEndTime123);
        final String position = getIntent().getSerializableExtra("position").toString();
        final EventCoordinator.Event event = (EventCoordinator.Event) getIntent().getSerializableExtra("args");
        txtEvtTitle.setText(event.eventTitle);
        txtEvtDetail.setText(event.eventDetail);
        txtStTime.setText(event.startTime);
        txtStDate.setText(event.startDate);
        txtEndDate.setText(event.endDate);
        txtEndTime.setText(event.endTime);
        btnDeReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete from evenreg table
                dbHelper = new DBHelper(getBaseContext());
                final SQLiteDatabase db = dbHelper.getWritableDatabase();
                if(dbHelper.eventDeReg(db, event.id, "2")){
                    evt.remove(Integer.valueOf(position));
                    Toast.makeText(getBaseContext(),"selected"+position,Toast.LENGTH_LONG);

                }
                else {
                    evt.remove(Integer.valueOf(position));
                    Toast.makeText(getBaseContext(),"Not"+position,Toast.LENGTH_LONG);

                }
            }
});
}}
