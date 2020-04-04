package com.example.stu_share;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import butterknife.BindView;
import butterknife.ButterKnife;



public class AdminMessageDetail extends AppCompatActivity {
    Button btnDelete, btnCancel,btnReply;
    private User user;
    private  MessageCoordinator.Message message1;
    MessageCoordinator.Message message;
    @BindView(R.id.toolbar)
    public Toolbar toolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_message_detail);
        user=(User)getIntent().getSerializableExtra("user") ;




        ButterKnife.bind(this);
        toolBar.setTitle("Message Details");
        setSupportActionBar(toolBar);


        AdminDrawerUtil.getDrawer(this,toolBar);

        BottomNavigationView navigation = findViewById(R.id.navigation1);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_adminEventList:
                        Intent intent = new Intent(getBaseContext(), AdminEventList.class);
                        intent.putExtra("user",user);
                        startActivity(intent);
                        break;
                    case R.id.action_message:
                        Intent intent1 = new Intent(getBaseContext(), AdminMessageList.class);
                        intent1.putExtra("user",user);
                        startActivity(intent1);
                        break;
                    case R.id.action_adminUserList:
                        Intent intent2 = new Intent(getBaseContext(), AdminUserList.class);
                        intent2.putExtra("user",user);
                        startActivity(intent2);
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

        message1 =(MessageCoordinator.Message)getIntent().getSerializableExtra("message");
        btnCancel = findViewById(R.id.btnCancel_Admin);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("user",user);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        message=new MessageCoordinator.Message();
        message.id=message1.id;
        message.title="Re: "+message1.title;
        message.detail="In you previous message, you mentioned:\n"+message1.detail+"\n";
        message.receiver_email=message1.sender_email;
        message.sender_email=user.id;
        btnDelete=findViewById(R.id.btnDelete_admin);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMessage(message.id);
            }
        });
        btnReply=findViewById(R.id.btnReply_admin);
        btnReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AdminMessageCreate.class);
                intent.putExtra("message",message);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

    }

    public void deleteMessage(final Integer id) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://w0044421.gblearn.com/stu_share/MessageDelete.php");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("messageID", id);

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
                    runOnUiThread(new Runnable() {
                        public void run()
                        {
                            Intent intent=new Intent();
                            intent.putExtra("user",user);
                            setResult(RESULT_OK,intent);
                            finish();
                        }});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }
}
