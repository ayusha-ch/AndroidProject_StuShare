package com.example.stu_share;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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

public class EventRegDetail extends AppCompatActivity {
    private Button btnLogout,  btnHome, btnDeReg;
    private TextView txtEvtTitle, txtEvtDetail, txtStDate, txtStTime, txtEndTime, txtEndDate;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_reg_detail);
        final User user = (User) getIntent().getSerializableExtra("user");
        final EventCoordinator.Event event = (EventCoordinator.Event) getIntent().getSerializableExtra("args");
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        OpenMenuActivity();
                        break;
                    case R.id.action_message:
                        Intent intent = new Intent(getBaseContext(), MessageList.class);
                        intent.putExtra("user",user);
                        startActivity(intent);
                        break;
                    case R.id.action_myevents:
                        openMyEventsActivity();
                        break;

                    case R.id.action_profile:
                        Intent i= new Intent(getBaseContext(),MyProfile.class);
                        i.putExtra("user",user);
                        startActivity(i);
                        break;
                }
                return false;
            }
        });

        btnDeReg = findViewById(R.id.btnReg123);
        txtEvtTitle = findViewById(R.id.txtEventTitle123);
        txtEvtDetail = findViewById(R.id.txtEvtDetail123);
        txtStDate = findViewById(R.id.txtStDate123);
        txtStTime = findViewById(R.id.txtStTime123);
        txtEndDate = findViewById(R.id.txtEndDate123);
        txtEndTime = findViewById(R.id.txtEndTime123);
        txtEvtTitle.setText(event.eventTitle);
        txtEvtDetail.setText(event.eventDetail);
        txtStTime.setText(event.startTime);
        txtStDate.setText(event.startDate);
        txtEndDate.setText(event.endDate);
        txtEndTime.setText(event.endTime);
        btnDeReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deRegEvent(event.id,user.id,"https://w0044421.gblearn.com/stu_share/EventDeReg.php");
                //delete from evenreg table

            }
});
}

    private void deRegEvent(final String eventid, final String userid, final String urlWebService) {
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
                    jsonParam.put("userId", userid);
                    final EventCoordinator.Event event = (EventCoordinator.Event) getIntent().getSerializableExtra("args");
                    jsonParam.put("eventId", eventid);

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


                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void logout(){
        Intent intent =new Intent(this, MainActivity.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
    public void OpenMenuActivity() {
        Intent intent = new Intent(this, EventList.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    public void openMyEventsActivity(){
        Intent intent =new Intent(this, EventMyEvents.class);
        intent.putExtra("user",user);
        Log.d("TAG","Menu to MyEvent"+user.id);
        startActivity(intent);
    }
}
