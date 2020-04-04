package com.example.stu_share;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyProfileEdit extends AppCompatActivity {
    ImageView buttonImg;
    @BindView(R.id.toolbar)
    public Toolbar toolBar;
    Button btnSubmit;
    TextView editFn,editLn,editQ,editA;
    private User user;
    private final String urlWebService="https://w0044421.gblearn.com/stu_share/user_update.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        user=(User)getIntent().getSerializableExtra("user");
        setContentView(R.layout.activity_profile_edit);

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
                        intent.putExtra("user",user);
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

        editFn=findViewById(R.id.editFirstName);
        editFn.setText(user.firstName);
        editLn=findViewById(R.id.editLastName);
        editLn.setText(user.lastName);
        editQ=findViewById(R.id.editQuestion);
        editQ.setText(user.question);
        editA=findViewById(R.id.editAnswer);
        editA.setText(user.answer);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.role.equals("admin")){
                    user.setFirstName(editFn.getText().toString());
                    user.setLastName(editLn.getText().toString());
                    user.setQuestion(editQ.getText().toString());
                    user.setAnswer(editA.getText().toString());
                    Toast.makeText(getBaseContext(), "Profile has been updated",
                            Toast.LENGTH_LONG).show();
                    updateUser(user,urlWebService);
                    Intent intent = new Intent(getBaseContext(), MyProfile.class);
                    intent.putExtra("user",user);
                    startActivity(intent);
                }else{

                    user.setFirstName(editFn.getText().toString());
                    user.setLastName(editLn.getText().toString());
                    user.setQuestion(editQ.getText().toString());
                    user.setAnswer(editA.getText().toString());
                    Toast.makeText(getBaseContext(), "Profile has been updated",
                            Toast.LENGTH_LONG).show();
                    updateUser(user,urlWebService);
                    Intent intent = new Intent(getBaseContext(), EventMenu.class);
                    intent.putExtra("user",user);
                    startActivity(intent);
                }

            }
        });

        ButterKnife.bind(this);
        toolBar.setTitle(getResources().getString(R.string.Events));
        setSupportActionBar(toolBar);
        buttonImg = findViewById(R.id.buttonImg) ;
        buttonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        DrawerUtil.getDrawer(this,toolBar);

    }


    private void updateUser(final User user, final String urlWebService) {
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
                    jsonParam.put("userId", user.id);
                    jsonParam.put("firstname", user.firstName);
                    jsonParam.put("lastname", user.lastName);
                    jsonParam.put("question", user.question);
                    jsonParam.put("answer", user.answer);

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

                    StringBuilder total = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        total.append(line).append('\n');
                    }
                    Log.d("TAG", "Server Response is: " + total.toString() + ": ");
                } catch (UnsupportedEncodingException | JSONException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }});
        thread.start();
    }
    public void OpenMenuActivity() {
        if(user.role.equals("admin")){
            Intent intent = new Intent(this, AdminEventList.class);
            intent.putExtra("user",user);
            startActivity(intent);
        }else{Intent intent = new Intent(this, MyProfile.class);
            intent.putExtra("user",user);
            startActivity(intent);}
    }

    public void logout(){
        Intent intent =new Intent(this, MainActivity.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    public void openMyEventsActivity(){
        Intent intent =new Intent(this, EventMyEvents.class);
        intent.putExtra("user",user);
        Log.d("TAG","Menu to MyEvent"+user.id);
        startActivity(intent);
    }
}
