package com.example.stu_share;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import butterknife.BindView;
import butterknife.ButterKnife;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import butterknife.ButterKnife;

import static com.example.stu_share.MessageCoordinator.MESSAGE_LIST;

public class AdminMessageList extends AppCompatActivity {


    @BindView(R.id.toolbar)
    public Toolbar toolBar;
    Button btnLogout, btnHome;
    ListView msgListView;
    private  User user;
    private static ArrayAdapter arrayAdapter_msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_message_list);
        user=(User)getIntent().getSerializableExtra("user");
        Log.i("USER",user.id+"id");
        ButterKnife.bind(this);
        toolBar.setTitle("Messages List");
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
        msgListView=findViewById(R.id.adminMessageList);
        arrayAdapter_msg= new ArrayAdapter(this, android.R.layout.simple_list_item_1,MESSAGE_LIST);
        msgListView.setAdapter(arrayAdapter_msg);
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

    public boolean onTouchEvent(MotionEvent touchEvent){
        return onTouchEvent(touchEvent,getApplicationContext());
    }
    public  float x1,x2,y1,y2;

    //To allow swipe left or right gesure
    public  boolean onTouchEvent(MotionEvent touchEvent, Context context){
        switch(touchEvent.getAction()){
            //Start point
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            //End point
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1 < x2){
                    Intent i = new Intent(context, MyProfile.class);
                    i.putExtra("user",user);
                    //Regular class call activity need use .setFlags method
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }else if(x1 >  x2){
                    Intent i = new Intent(context, AdminUserList.class);
                    i.putExtra("user",user);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
                break;
        }
        return false;
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

                    arrayAdapter_msg = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,MESSAGE_LIST);
                    msgListView.setAdapter(arrayAdapter_msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }});

    }
}
