package com.example.stu_share;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.willy.ratingbar.RotationRatingBar;

import org.json.JSONArray;
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
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.stu_share.EventAdapter.eventList;
import static com.example.stu_share.EventDetail.url_update;

public class EventList extends AppCompatActivity {
    ListView listView;
    Boolean likeFlag=false;
    EventAdapter mAdapter;
    Button  btnLogout12;
    public static User user3;
    EditText txtS;
    Spinner spinner;
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.toolbar)
    public Toolbar toolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        user3=(User)getIntent().getSerializableExtra("user");
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
                        Log.i("X2up",String.valueOf(x2));

                        if(x2 - x1>30){
                            Log.i("X2-X11",String.valueOf(x1));
                            Log.i("X2-X12",String.valueOf(x2));
                            Intent i = new Intent(getApplicationContext(), MyProfile.class);
                            i.putExtra("user",user3);
                            //Regular class call activity need use .setFlags method
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(i);
                        }else if(x1 -  x2>30){
                            Log.i("X1-X21",String.valueOf(x1));
                            Log.i("X1-X22",String.valueOf(x2));
                            Intent i = new Intent(getApplicationContext(), EventMyEvents.class);
                            i.putExtra("user",user3);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(i);
                        }
                        break;
                }
                return true;
            }
        });

        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        ButterKnife.bind(this);
        final String url1="https://w0044421.gblearn.com/stu_share/EventView_Status_Active.php";
        toolBar.setTitle(getResources().getString(R.string.Events));
        setSupportActionBar(toolBar);

        DrawerUtil.getDrawer(this,toolBar);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                downloadJSON(url1,user3,"");
                //Toast.makeText(getApplicationContext(), "Works!", Toast.LENGTH_LONG).show();
                // To keep animation for 4 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        // Stop animation (This will be after 3 seconds)
                        swipeLayout.setRefreshing(false);
                    }
                }, 2000); // Delay in millis
            }
        });
        txtS=findViewById(R.id.txtSearch);
        spinner=findViewById(R.id.spinner1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = spinner.getSelectedItem().toString();
                String url="https://w0044421.gblearn.com/stu_share/EventView_Sort.php";
                downloadJSON(url,user3,text);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                String text = "createdAt";
                String url="https://w0044421.gblearn.com/stu_share/EventView_Sort.php";
                downloadJSON(url,user3,text);
            }
        });


        downloadJSON(url1,user3,"");
        listView = findViewById(R.id.listview);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        txtS.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String url="https://w0044421.gblearn.com/stu_share/Event_Search.php";
                downloadJSON(url,user3,s.toString());
            }
        });
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        OpenMenuActivity();
                        break;
                    case R.id.action_message:
                        Intent intent = new Intent(getBaseContext(), MessageList.class);
                        intent.putExtra("user",user3);
                        startActivity(intent);
                        break;
                    case R.id.action_myevents:
                        openMyEventsActivity();
                        break;

//                    case R.id.action_profile:
//                        Intent i= new Intent(getBaseContext(),MyProfile.class);
//                        i.putExtra("user",user3);
//                        startActivity(i);
//                        break;
                }
                return false;
            }
        });

        Log.d("MYMENU","my menu user ID"+user3.id);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                EventCoordinator.Event event2=(EventCoordinator.Event) adapter.getItemAtPosition(position);
                Intent intent =new Intent(getBaseContext(), EventDetail.class);
                intent.putExtra("args",event2);
                intent.putExtra("user",user3);
                startActivity(intent);

            }
        });
        registerForContextMenu(listView);
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
        menu.setHeaderTitle("Select The Action");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getItemId()==R.id.join){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int position = (int) info.id;
            Toast.makeText(getApplicationContext(),"You have successfully registered this event!",Toast.LENGTH_SHORT).show();
            EventDetail abc=new EventDetail();
            abc.update(url_update,user3,mAdapter.getItem(position));
        }
        else if(item.getItemId()==R.id.like){
            ImageView likeClick= (ImageView)findViewById(R.id.imgLike);
            likeClick.setImageResource(R.drawable.ic_thumbs_up_red);
            Toast.makeText(getApplicationContext(),"Liked",Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId()==R.id.favourite){
            Toast.makeText(getApplicationContext(),"Faved!",Toast.LENGTH_SHORT).show();
        }else{
            return false;
        }
        return true;
    }
    public void likeClick(View v)
    {
        ConstraintLayout vwParentRow = (ConstraintLayout) v.getParent();
        ImageView likeClick= (ImageView) vwParentRow.getChildAt(3);
        String url="https://w0044421.gblearn.com/stu_share/Event_Like.php";
        EventCoordinator.Event event2=eventList.get((Integer)likeClick.getTag());
        updateLike(url,user3,event2,likeFlag);
        likeFlag=!likeFlag;
        String url1="https://w0044421.gblearn.com/stu_share/EventView_Status_Active.php";
        downloadJSON(url1,user3,"");
    }

    public void updateLike(final String urlWebService, final User user6, final EventCoordinator.Event event4,final Boolean flag) {

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
                    jsonParam.put("user_id", user6.id);
                    jsonParam.put("event_id", event4.id);
                    jsonParam.put("flag",flag);
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
                    final StringBuilder total = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        total.append(line).append('\n');
                    }
                    Log.d("TAG", "Server Response is(updateLike): " + total.toString() + ": ");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //rotationRatingBar.setRating(Float.parseFloat(total.toString()));
                            //imageCheck.setVisibility(View.GONE);
                            String url2="https://w0044421.gblearn.com/stu_share/EventView_Status_Active.php";
                            downloadJSON(url2,user3,"");
                        }
                    });
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
    public void logout(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user",user3);
        startActivity(intent);
    }

    public void openMyEventsActivity(){
        Intent intent =new Intent(this, EventMyEvents.class);
        intent.putExtra("user",user3);
        startActivity(intent);
    }

    public void OpenMenuActivity() {
        Intent intent = new Intent(this, EventMenu.class);
        intent.putExtra("user",user3);
        startActivity(intent);
    }

    private void downloadJSON(final String urlWebService,final User user4,final String keywords) {
        Log.i("KEYWORDS","Keyword is: "+keywords);

        class DownloadJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
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
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("keyword", keywords);
                    jsonParam.put("user_id", user4.id);
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
            event1.setOrgEmail(obj.getString("orgEmail"));
            event1.setEventCode(obj.getString("eventCode"));
            event1.setStatus(obj.getString("status"));
            event1.setStartDate(obj.getString("startDate"));
            event1.setStartTime(obj.getString("startTime"));
            event1.setEndDate(obj.getString("endDate"));
            event1.setEndTime(obj.getString("endTime"));
            event1.setEventTitle(obj.getString("title"));
            event1.setEventDetail(obj.getString("detail"));
            event1.setmImageDrawable((obj.getString("imagePath")));
            event1.setRating(Float.parseFloat(obj.getString("rating")));
            event1.setLiked(Integer.parseInt(obj.getString("isLike")));
            event1.setLikeCount(Integer.parseInt(obj.getString("sum")));
            eventL.add(event1);
        }
        mAdapter = new EventAdapter(this, eventL);
        listView.setAdapter(mAdapter);
    }

}
