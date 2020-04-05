package com.example.stu_share;

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
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.stu_share.MessageCoordinator.MESSAGE_LIST;
import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

public class MessageList extends AppCompatActivity {
    ListView messageList;
    MessageAdapter mAdapter;
    private static User userTemp;
    public static ArrayAdapter arrayAdapter;
    ImageView buttonImg;
    @BindView(R.id.toolbar)
    public Toolbar toolBar;
    SwipeRefreshLayout swipeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        userTemp=(User)getIntent().getSerializableExtra("user");
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
                        Class cc;
                        if(x2-x1>50){
                            cc=MyProfile.class;
                            Intent i = new Intent(getApplicationContext(),cc );
                            i.putExtra("user",userTemp);
                            //Regular class call activity need use .setFlags method
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(i);
                        }
                        else if(x2-x1<50){
                            cc= EventMyEvents.class;
                            Intent i = new Intent(getApplicationContext(), cc);
                            i.putExtra("user",userTemp);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(i);
                        }
                        break;
                }
                return true;
            }
        });
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
        ButterKnife.bind(this);
        toolBar.setTitle(getResources().getString(R.string.messageList));
        setSupportActionBar(toolBar);
        buttonImg = findViewById(R.id.buttonImg) ;
        buttonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCreateActivity();

            }
        });
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        OpenMenuActivity();
                        break;
                    case R.id.action_message:
                        Intent intent = new Intent(getBaseContext(), MessageList.class);
                        intent.putExtra("user",userTemp);
                        startActivity(intent);
                        break;
                    case R.id.action_myevents:
                        openMyEventsActivity();
                        break;

                    case R.id.action_profile:
                        Intent i= new Intent(getBaseContext(),MyProfile.class);
                        i.putExtra("user",userTemp);
                        startActivity(i);
                        break;
                }
                return false;
            }
        });
        DrawerUtil.getDrawer(this,toolBar);
        messageList=findViewById(R.id.messageList);
        getMsgList();
        mAdapter = new MessageAdapter(this, MESSAGE_LIST);
        messageList.setAdapter(mAdapter);
        messageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                MessageCoordinator.Message message =(MessageCoordinator.Message) adapter.getItemAtPosition(position);
                Intent intent =new Intent(getBaseContext(), MessageReceivedDetail.class);
                intent.putExtra("message",message);
                intent.putExtra("user",userTemp);
                //startActivityForResult(intent,1);
                startActivity(intent);
            }
        });
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
                    jsonParam.put("userID", userTemp.id);
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
                    final StringBuilder total = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null)
                    {
                        total.append(line).append('\n');
                    }
                    Log.d("TAG", "Server Response is: " + total.toString() + ": " );
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                loadIntoListView(total.toString().trim());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }});
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
                    messageList.setAdapter(mAdapter);





                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }});

    }



    public void OpenMenuActivity() {
        Intent intent = new Intent(this, EventList.class);
        intent.putExtra("user",userTemp);
        startActivity(intent);
    }

    public void openMyEventsActivity(){
        Intent intent =new Intent(this, EventMyEvents.class);
        intent.putExtra("user",userTemp);
        Log.d("TAG","Menu to MyEvent"+userTemp.id);
        startActivity(intent);
    }

    public void OpenCreateActivity() {
        Intent intent = new Intent(this, EventCreateDescription.class);
        intent.putExtra("user",userTemp);
        startActivity(intent);
    }
}