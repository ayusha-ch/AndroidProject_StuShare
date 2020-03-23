package com.example.stu_share;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class AdminEventDetail extends AppCompatActivity {
    private Button btnLogout,btnJoin, btnHome,btnContact1;
    private TextView txtEvtTitle,txtEvtDetail,txtStDate,txtStTime,txtEndTime,txtEndDate;
   // DBHelper dbHelper = null;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_event_detail);
        btnJoin=findViewById(R.id.btnSus);
        btnLogout=findViewById(R.id.btnLogout111);
        txtEvtTitle=findViewById(R.id.txtEventTitle);
        txtEvtDetail=findViewById(R.id.txtEvtDetail);
        txtStDate=findViewById(R.id.txtStDate);
        txtStTime=findViewById(R.id.txtStTime);
        txtEndDate=findViewById(R.id.txtEndDate);
        txtEndTime=findViewById(R.id.txtEndTime);
        user=(User)getIntent().getSerializableExtra("user");
        final EventCoordinator.Event event=(EventCoordinator.Event)getIntent().getSerializableExtra("args");
        txtEvtTitle.setText(event.eventTitle);
        txtEvtDetail.setText(event.eventDetail);
        txtStTime.setText(event.startTime);
        txtStDate.setText(event.startDate);
        txtEndDate.setText(event.endDate);
        txtEndTime.setText(event.endTime);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "you have successfully suspended the event",
                        Toast.LENGTH_LONG).show();
                update("https://w0044421.gblearn.com/stu_share/EventSuspended.php");
                //update("https://w0044421.gblearn.com/stu_share/eventRegDeleteByAdmin.php");
            }
        });


        btnHome = findViewById(R.id.btnHome8);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenMenuActivity();
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

    private void update(final String urlWebService) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    //jsonParam.put("userId", user2.id);
                    final EventCoordinator.Event event = (EventCoordinator.Event) getIntent().getSerializableExtra("args");
                    jsonParam.put("eventId", event.id);

                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));

                    os.writeBytes(jsonParam.toString());

                    os.flush();
                    os.close();
                    conn.connect();
                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG", conn.getResponseMessage());

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    DataInputStream is = new DataInputStream(conn.getInputStream());

                    StringBuilder total = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        total.append(line).append('\n');
                    }
                    Log.d("TAG", "Server Response is: " + total.toString() + ": ");


                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();



    }
    public void OpenMenuActivity(){
        Intent intent = new Intent(this, AdminEventList.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
}

