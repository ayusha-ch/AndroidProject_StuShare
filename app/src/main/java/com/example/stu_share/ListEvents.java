package com.example.stu_share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
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
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ListEvents extends AppCompatActivity {
    ListView listView;
    DBHelper dbHelper=null;
    Button btnHome, btnLogout12;
    private User user;
    private static final String REGISTER_URL="http://f9044421.gblearn.com/api/init.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_events);

        downloadJSON("https://f9team1.gblearn.com/stu_share/EventView_Status_Active.php");
//        dbHelper=new DBHelper(this);
//        final SQLiteDatabase db = dbHelper.getReadableDatabase();
//        dbHelper.updateEventList(db,dbHelper.getEventCursorAct(db));
        listView = (ListView) findViewById(R.id.listView);
//        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,EventCoordinator.EVENTS);
//        listView.setAdapter(arrayAdapter);

        user=(User)getIntent().getSerializableExtra("user");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                EventCoordinator.Event event2=(EventCoordinator.Event) adapter.getItemAtPosition(position);
                Intent intent =new Intent(getBaseContext(), EventDetail.class);
                intent.putExtra("args",event2);
                intent.putExtra("user",user);
                startActivity(intent);

            }
        });

        btnHome = findViewById(R.id.btnHome);
        btnLogout12 = findViewById(R.id.btnLogout12);
        btnLogout12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenMenuActivity();
            }
        });

    }

    public void logout(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    public void OpenMenuActivity() {
        Intent intent = new Intent(this, Menu.class);
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
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
                    con.setRequestMethod("POST");
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        DownloadJSON getJSON = new DownloadJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        List<EventCoordinator.Event> eventL = new ArrayList<EventCoordinator.Event>();
        String[] stocks = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            EventCoordinator.Event event1 = new EventCoordinator.Event();

            event1.setId( obj.getString("_id"));
            event1.setOrgID(obj.getString("organizer_id"));
            event1.setStatus(obj.getString("status"));

            event1.setStartDate(obj.getString("start_date"));
            event1.setStartTime(obj.getString("start_time"));
            event1.setEndDate(obj.getString("end_date"));

            event1.setEndTime(obj.getString("end_time"));
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

}
