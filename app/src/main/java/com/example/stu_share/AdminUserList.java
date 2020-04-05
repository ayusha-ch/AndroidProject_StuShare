package com.example.stu_share;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdminUserList extends AppCompatActivity {
    Button btnLogout, btnHome;
    UserAdapter mAdapter;
    ListView listView;
    private static User user1;
    TextView txt;
    @BindView(R.id.toolbar)
    public Toolbar toolBar;
    SwipeRefreshLayout swipeLayout;
    public static List<User> userList;
    private String url1="https://w0044421.gblearn.com/stu_share/read_all_users.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_list);
        user1=(User)getIntent().getSerializableExtra("user");
        ButterKnife.bind(this);
        toolBar.setTitle("Users List");
        setSupportActionBar(toolBar);
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
                        Log.i("X2UP",x2+"||"+x1);
                        if(x2 - x1>150){
                            Intent i = new Intent(getApplicationContext(), AdminEventList.class);
                            i.putExtra("user",user1);
                            //Regular class call activity need use .setFlags method
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(i);
                        }else if(x1 -  x2>150){
                            Log.i("X1-X21",String.valueOf(x1));
                            Log.i("X1-X22",String.valueOf(x2));
                            Intent i = new Intent(getApplicationContext(), AdminMessageList.class);
                            i.putExtra("user",user1);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(i);
                        }
                        break;
                }
                return true;
            }
        });
        AdminDrawerUtil.getDrawer(this,toolBar);
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                downloadJSON(url1);
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
        BottomNavigationView navigation = findViewById(R.id.include6);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_adminEventList:
                        Intent intent = new Intent(getBaseContext(), AdminEventList.class);
                        intent.putExtra("user",user1);
                        startActivity(intent);
                        break;
                    case R.id.action_message:
                        Intent intent1 = new Intent(getBaseContext(), AdminMessageList.class);
                        intent1.putExtra("user",user1);
                        startActivity(intent1);
                        break;
                    case R.id.action_adminUserList:
                        Intent intent2 = new Intent(getBaseContext(), AdminUserList.class);
                        intent2.putExtra("user",user1);
                        startActivity(intent2);
                        break;

                    case R.id.action_profile:
                        Intent i= new Intent(getBaseContext(),MyProfile.class);
                        i.putExtra("user",user1);
                        startActivity(i);
                        break;
                }
                return false;
            }
        });

        listView = (ListView) findViewById(R.id.listUser);
        downloadJSON(url1);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                User user2=(User) adapter.getItemAtPosition(position);
                Intent intent =new Intent(getBaseContext(), AdUserDetail.class);
                intent.putExtra("args",user2);
                intent.putExtra("user",user1);
                startActivity(intent);

            }
        });
    }


    public void logout(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user",user1);
        startActivity(intent);
    }
    public void home(){
        Intent intent = new Intent(this, AdminEventList.class);
        intent.putExtra("user",user1);
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
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
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
        ArrayList<User> userL = new ArrayList<User>();
        String[] stocks = new String[jsonArray.length()];
        String[] userShort = new String[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            User user1=new User();

            //please keep adding all the information to user1 objects!!!!!!!!
            user1.setId( obj.getString("id"));
            user1.setEmail( obj.getString("email"));
            user1.setPassword(obj.getString("password"));
            user1.setFirstName( obj.getString("firstName"));
            user1.setLastName(obj.getString("lastName"));
            user1.setCollegeCode( obj.getString("collegeCode"));
            user1.setProgramCode( obj.getString("programCode"));
            user1.setRegisterYear(obj.getString("registeredYear"));
            user1.setExpireYear( obj.getString("expireYear"));
            user1.setStatus( obj.getString("status"));
            user1.setQuestion(obj.getString("question"));
            user1.setAnswer( obj.getString("answer"));
            user1.setRole( obj.getString("role"));
            user1.setmImageDrawable((obj.getString("imagePath")));
            userL.add(user1);
            userShort[i] = user1.getFirstName() + " " + user1.getLastName();
            stocks[i] = user1.getFirstName() ;

        }

        mAdapter = new UserAdapter(this, userL);
        listView.setAdapter(mAdapter);


    }
}

