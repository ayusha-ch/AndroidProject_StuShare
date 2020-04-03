package com.example.stu_share;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

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

import static com.example.stu_share.MessageCoordinator.MESSAGE_LIST;

public class MessageList extends AppCompatActivity {
    Button msgHome, msgLogout;
    ListView messageList;
    private static User userTemp;
    private ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        userTemp=(User)getIntent().getSerializableExtra("user");
        messageList=findViewById(R.id.messageList);
        getMsgList();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,MESSAGE_LIST);
        messageList.setAdapter(arrayAdapter);
        messageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                MessageCoordinator.Message message =(MessageCoordinator.Message) adapter.getItemAtPosition(position);
                Intent intent =new Intent(getBaseContext(), MessageReceivedDetail.class);
                intent.putExtra("message",message);
                startActivityForResult(intent,1);
            }
        });


       msgHome = findViewById(R.id.btnMsgHome);
       msgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), EventList.class);
                intent.putExtra("user", userTemp);
                startActivity(intent);
            }
        });

        msgLogout = findViewById(R.id.btnMsgLogout);
        msgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("user", userTemp);
                startActivity(intent);



            }

        });
    }
    public boolean onTouchEvent(MotionEvent touchEvent){
        return onTouchEvent(touchEvent,getApplicationContext());
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

                    arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,MESSAGE_LIST);
                    messageList.setAdapter(arrayAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }});

    }
    public static float x1,x2,y1,y2;
    public static boolean onTouchEvent(MotionEvent touchEvent, Context context){
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
                Class cc;
                if(x1 < x2){
                    cc=MyProfile.class;
                    Intent i = new Intent(context,cc );
                    i.putExtra("user",userTemp);
                    //Regular class call activity need use .setFlags method
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
                else if(x1 >  x2){
                    cc= EventList.class;
                    Intent i = new Intent(context, cc);
                    i.putExtra("user",userTemp);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
                break;
        }
        return false;
    }
}