package com.example.stu_share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static com.example.stu_share.EventList.user3;

public class AdminUserList extends AppCompatActivity {
    Button btnLogout, btnHome;
    UserAdapter mAdapter;
    ListView listView;
    private static User user1;
    TextView txt;

    public static List<User> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_list);

        listView = (ListView) findViewById(R.id.listUser);
        downloadJSON("https://w0044421.gblearn.com/stu_share/read_all_users.php");
        btnLogout = findViewById(R.id.btnAlLogout);
        user1=(User)getIntent().getSerializableExtra("user");
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
                home();
            }
        });

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
    public boolean onTouchEvent(MotionEvent touchEvent){
        return onTouchEvent(touchEvent,getApplicationContext());
    }
    public static float x1,x2,y1,y2;

    //To allow swipe left or right gesure
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
                if(x1 < x2){
                    Intent i = new Intent(context, AdminMessageList.class);
                    i.putExtra("user",user1);
                    //Regular class call activity need use .setFlags method
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }else if(x1 >  x2){
                    Intent i = new Intent(context, AdminEventList.class);
                    i.putExtra("user",user1);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
                break;
        }
        return false;
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

