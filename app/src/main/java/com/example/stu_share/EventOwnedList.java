package com.example.stu_share;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
import java.util.List;

public class EventOwnedList extends AppCompatActivity {
    private Button btnCancel, btnEdit, btnViewAttend, btnCheckIn, btnLogout, btnHome;
    ListView listView;
    EventAdapter mAdapter;
    //DBHelper dbHelper=null;
    private User user;
    public static  List<EventCoordinator.Event> evt = new ArrayList<EventCoordinator.Event>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_owned_list);
        user=(User)getIntent().getSerializableExtra("user");
        listView = (ListView) findViewById(R.id.listV1);
        downloadJSON("https://w0044421.gblearn.com/stu_share/EventView_Owned_Events.php");
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
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.include5);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        OpenMenuActivity();
                        break;
                    case R.id.action_message:
                        Intent intent = new Intent(getBaseContext(), MessageList.class);
//              intent.putExtra("args", userReg);
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
    }

    @Override
    public void onResume(){
        super.onResume();
        downloadJSON("https://w0044421.gblearn.com/stu_share/EventView_Owned_Events.php");
    }
    public void logout(){
        Intent intent =new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void openMyEventsActivity(){
        Intent intent =new Intent(this, EventMyEvents.class);
        intent.putExtra("user",user);
        Log.d("TAG","Menu to MyEvent"+user.id);
        startActivity(intent);
    }
    public void OpenMenuActivity() {
        Intent intent = new Intent(this, EventList.class);
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
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }



    @Override
    protected String doInBackground(Void... voids) {
        try {
            URL url = new URL("https://w0044421.gblearn.com/stu_share/EventView_Owned_Events.php");
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



    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        ArrayList<EventCoordinator.Event> eventL = new ArrayList<EventCoordinator.Event>();
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
            event1.setmImageDrawable((obj.getString("imagePath")));
            Log.i("OWNEDEVENT",event1.toString());
            eventL.add(event1);
        }
        mAdapter = new EventAdapter(this, eventL);
        listView.setAdapter(mAdapter);
    }
}
