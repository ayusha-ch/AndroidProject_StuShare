package com.example.stu_share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
import java.util.List;

public class EventOwnedList extends AppCompatActivity {
    private Button btnCancel, btnEdit, btnViewAttend, btnCheckIn, btnLogout, btnHome;
    ListView listView;
    //DBHelper dbHelper=null;
    private User user;
    public static  List<EventCoordinator.Event> evt = new ArrayList<EventCoordinator.Event>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_owned_list);



        btnLogout = findViewById(R.id.btnAlLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenMenuActivity();
            }
        });
        //dbHelper=new DBHelper(this);
       // final SQLiteDatabase db = dbHelper.getReadableDatabase();
  //      String[] args=(String[])getIntent().getSerializableExtra("args");
//        dbHelper.updateEventList(db,dbHelper.getEventCursorOwn(db,args[0]));
        listView = (ListView) findViewById(R.id.listV1);
        downloadJSON("https://f9team1.gblearn.com/stu_share/EventView_Owned_Events.php");
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, evt);
        //listView.setAdapter(arrayAdapter);
        user=(User)getIntent().getSerializableExtra("user");
        Log.d("TAG","OwnedEvent"+user.id);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                EventCoordinator.Event event2=(EventCoordinator.Event) adapter.getItemAtPosition(position);
                Intent intent =new Intent(getBaseContext(), EventOwnDetail.class);
                intent.putExtra("args",event2);
                intent.putExtra("user",user);

                startActivity(intent);

            }
        });
    }


    public void logout(){
        Intent intent =new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void OpenMenuActivity() {
        Intent intent = new Intent(this, EventMenu.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }


    private void downloadJSON(final String urlWebService) {

        class DownloadJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }



    @Override
    protected String doInBackground(Void... voids) {
        try {
            URL url = new URL("https://f9team1.gblearn.com/stu_share/EventsRegistered.php");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept","application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            JSONObject jsonParam = new JSONObject();
            jsonParam.put("userid", user.id);
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
            //loadIntoListView(total.toString().trim(),evt);
            return total.toString().trim();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
    DownloadJSON getJSON = new DownloadJSON();
        getJSON.execute();
                }

//                //
//                private void loadIntoListView(String json) throws JSONException {
//                    JSONArray jsonArray = new JSONArray(json);
//                    List<EventCoordinator.Event> eventL = new ArrayList<EventCoordinator.Event>();
//
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject obj = jsonArray.getJSONObject(i);
//                        EventCoordinator.Event event1 = new EventCoordinator.Event();
//
//                        event1.setId( obj.getString("id"));
//                        event1.setOrgID(obj.getString("organizerId"));
//                        event1.setStatus(obj.getString("status"));
//
//                        event1.setStartDate(obj.getString("startDate"));
//                        event1.setStartTime(obj.getString("startTime"));
//                        event1.setEndDate(obj.getString("endDate"));
//
//                        event1.setEndTime(obj.getString("endTime"));
//                        event1.setEventTitle(obj.getString("title"));
//                        event1.setEventDetail(obj.getString("detail"));
//
//                        eventL.add(event1);
//
//                    }
//                    ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, eventL);
//                    listView.setAdapter(arrayAdapter);
//                }
    //

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        List<EventCoordinator.Event> eventL = new ArrayList<EventCoordinator.Event>();
        //String[] stocks = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            EventCoordinator.Event event1 = new EventCoordinator.Event();

            event1.setId( obj.getString("id"));
            event1.setOrgID(obj.getString("organizerId"));
            event1.setStatus(obj.getString("status"));

            event1.setStartDate(obj.getString("startDate"));
            event1.setStartTime(obj.getString("startTime"));
            event1.setEndDate(obj.getString("endDate"));

            event1.setEndTime(obj.getString("endTime"));
            event1.setEventTitle(obj.getString("title"));
            event1.setEventDetail(obj.getString("detail"));

            eventL.add(event1);
            //userShort[i] = user1.getFirstName() + " " + user1.getLastName();

            // stocks[i] = user1.getFirstName() ;
            //stocks[i] = obj.getString("title") + " " + obj.getString("detail");

        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, eventL);
        listView.setAdapter(arrayAdapter);
    }

    public void sendPost(final List<EventCoordinator.Event>evt) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://f9team1.gblearn.com/stu_share/EventsRegistered.php");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("userid", user.id);
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
                    //loadIntoListView(total.toString().trim(),evt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }
}
