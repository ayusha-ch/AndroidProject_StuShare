package com.example.stu_share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class AdUserDetail extends AppCompatActivity {
    TextView txtDetail;
    Button btnDeact,btnLogout,btnAct, btnHome;
    //DBHelper dbHelper = null;
    private User userDetail;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_detail);
        userDetail=(User)getIntent().getSerializableExtra("args");
        user=(User)getIntent().getSerializableExtra("user");
        txtDetail=findViewById(R.id.txtAcctDerail);
        btnAct=findViewById(R.id.btnAct);
        btnLogout=findViewById(R.id.btnLogout2);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getBaseContext(),MainActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });
        btnDeact=findViewById(R.id.btnDeAct);
        txtDetail.setText(userDetail.toString());
        btnAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getBaseContext(), "User is activated!",
                        Toast.LENGTH_LONG).show();
                Intent intent =new Intent(getBaseContext(),AdminUserList.class);
                sendPost("active");
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });
        btnDeact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getBaseContext(), "User is suspended!",
                        Toast.LENGTH_LONG).show();
                sendPost("suspended");
                Intent intent =new Intent(getBaseContext(),AdminUserList.class);
                intent.putExtra("user",user);
                startActivity(intent);

            }
        });
        btnHome = findViewById(R.id.btnHome19);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenMenuActivity();
            }
        });
    }
    public void sendPost(final String status) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://w0044421.gblearn.com/stu_share/user_status_update.php");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("status", status);
                    jsonParam.put("userId", userDetail.id);



                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    os.writeBytes(jsonParam.toString());
                    os.flush();
                    os.close();
                    conn.connect();
                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG" , conn.getResponseMessage());

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    DataInputStream is=new DataInputStream(conn.getInputStream());

                    StringBuilder total = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null)
                    {
                        total.append(line).append('\n');
                    }
                    Log.d("TAG", "Server Response is: " + total.toString() + ": " );

                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();

    }

    public void OpenMenuActivity() {
        Intent intent = new Intent(this, AdminEventList.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
}