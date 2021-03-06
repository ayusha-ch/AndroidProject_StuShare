package com.example.stu_share;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import butterknife.BindView;
import butterknife.ButterKnife;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import butterknife.ButterKnife;

import static com.example.stu_share.MessageCoordinator.MESSAGE_LIST;

public class AdminMessageList extends AppCompatActivity {



    Button btnLogout, btnHome;
    ListView msgListView;
    MessageAdapter mAdapter;
    private  User user;
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.toolbar)
    public Toolbar toolBar;
    private static ArrayAdapter arrayAdapter_msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_message_list);
        user=(User)getIntent().getSerializableExtra("user");
        ButterKnife.bind(this);
        toolBar.setTitle("Messages List");
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMsgList();

                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        // Stop animation (This will be after 3 seconds)
                        swipeLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        setSupportActionBar(toolBar);
        AdminDrawerUtil.getDrawer(this,toolBar);
        View myview=findViewById(R.id.masterView);
        myview.setOnTouchListener(new View.OnTouchListener() {
            float x1;
            float x2;
            float y1;
            float y2;
            @Override
            public boolean onTouch(View v, MotionEvent touchEvent) {
                switch(touchEvent.getAction()){
                    //Start point

                    case MotionEvent.ACTION_DOWN:
                        x1 = touchEvent.getX();
                        Log.i("X1down",String.valueOf(x1));
                        y1 = touchEvent.getY();
                        break;
                    //End point
                    case MotionEvent.ACTION_UP:
                        x2 = touchEvent.getX();
                        y2 = touchEvent.getY();
                        if(x2 - x1>50){
                            Intent i = new Intent(getApplicationContext(), AdminUserList.class);
                            i.putExtra("user",user);
                            //Regular class call activity need use .setFlags method
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(i);
                        }else if(x1 -  x2>50){
                            Log.i("X1-X21",String.valueOf(x1));
                            Log.i("X1-X22",String.valueOf(x2));
                            Intent i = new Intent(getApplicationContext(), MyProfile.class);
                            i.putExtra("user",user);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(i);
                        }
                        break;
                }
                return true;
            }
        });

        BottomNavigationView navigation = findViewById(R.id.include2);
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
        msgListView=findViewById(R.id.adminMessageList);
        mAdapter = new MessageAdapter(this, MESSAGE_LIST);
        msgListView.setAdapter(mAdapter);
        msgListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                MessageCoordinator.Message message =(MessageCoordinator.Message) adapter.getItemAtPosition(position);
                Intent intent =new Intent(getBaseContext(), AdminMessageDetail.class);
                intent.putExtra("user",user);
                intent.putExtra("message",message);
                startActivityForResult(intent,2);
            }
        });
        getMsgList();
    }
    @Override
    public void onResume() {
        super.onResume();
        getMsgList();
    }

    public void getMsgList() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://w0044421.gblearn.com/stu_share/MessageList.php");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("userID", user.id);

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

                    loadIntoListView(total.toString().trim());


                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();

    }
    private void loadIntoListView(final String json) throws JSONException {


        runOnUiThread(new Runnable() {
            public void run()
            {
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(json);
                    MESSAGE_LIST.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        MessageCoordinator.Message message = new MessageCoordinator.Message();
                        message.setId( Integer.valueOf(obj.getString("_id")));
                        message.setTitle(obj.getString("title"));
                        message.setSender_email(obj.getString("sender_id"));
                        message.setReceiver_email(obj.getString("receiver_id"));
                        message.setDetail(obj.getString("details"));

                        MESSAGE_LIST.add(message);
                        Log.i("MSGLIST",MESSAGE_LIST.get(i).toString());
                    }

                    mAdapter = new MessageAdapter(getApplicationContext(), MESSAGE_LIST);
                    msgListView.setAdapter(mAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }});

    }
}
