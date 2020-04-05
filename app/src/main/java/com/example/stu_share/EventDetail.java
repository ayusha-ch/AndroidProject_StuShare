package com.example.stu_share;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.willy.ratingbar.BaseRatingBar;
import com.willy.ratingbar.RotationRatingBar;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventDetail extends AppCompatActivity {

    private Button  btnJoin,btnContact1,btnMsg,btnAddCalendar;

    ImageView buttonImg;
    @BindView(R.id.toolbar)
    public Toolbar toolBar;


    private TextView txtEvtTitle, txtEvtDetail, txtStDate, txtStTime, txtEndTime, txtEndDate,txtEventC;
    private  User user2;
    private ImageView shareImage,imageCheck;
    private RotationRatingBar rotationRatingBar;
    public static String url_update="https://w0044421.gblearn.com/stu_share/EventReg.php";
    public static String url_update_rate="https://w0044421.gblearn.com/stu_share/Event_Rate.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        ButterKnife.bind(this);
        toolBar.setTitle(getResources().getString(R.string.Events));
        setSupportActionBar(toolBar);
        buttonImg = findViewById(R.id.buttonImg) ;
        buttonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCreateActivity();
            }
        });

        DrawerUtil.getDrawer(this,toolBar);

        user2=(User)getIntent().getSerializableExtra("user");
        final EventCoordinator.Event event = (EventCoordinator.Event) getIntent().getSerializableExtra("args");
        btnMsg=findViewById(R.id.btnMessage);
        btnMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MessageCreate.class);
                MessageCoordinator.Message message=new MessageCoordinator.Message();
                message.title="Re: About your events post:" +event.eventTitle;
                message.detail="I just saw your post regarding:\n" +event.toString()+"\n\n\n"+user2.firstName+" "+user2.lastName;
                message.receiver_email=event.orgEmail;
                i.putExtra("message",message);
                i.putExtra("user",user2);
                startActivityForResult(i,2);
            }
        });
        BottomNavigationView navigation = findViewById(R.id.include);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        OpenMenuActivity();
                        break;
                    case R.id.action_message:
                        Intent intent = new Intent(getBaseContext(), MessageList.class);
                        intent.putExtra("user",user2);
                        startActivity(intent);
                        break;
                    case R.id.action_myevents:
                        openMyEventsActivity();
                        break;

                    case R.id.action_profile:
                        Intent i= new Intent(getBaseContext(),MyProfile.class);
                        i.putExtra("user",user2);
                        startActivity(i);
                        break;
                }
                return false;
            }
        });
        btnAddCalendar=findViewById(R.id.btnCal);
        btnAddCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    addEvent(event);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        imageCheck=findViewById(R.id.imgChecked);
        txtEventC=findViewById(R.id.txtEventCode);
        txtEventC.setText(event.eventCode);
        shareImage=findViewById(R.id.shareImage1);
        rotationRatingBar=findViewById(R.id.ratingBarDetail);
        rotationRatingBar.setRating(event.rating);
        rotationRatingBar.setOnRatingChangeListener(new BaseRatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(BaseRatingBar ratingBar, float rating, boolean fromUser) {
                imageCheck.setVisibility(View.VISIBLE);
            }
        });
        imageCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRating(url_update_rate,user2,event,rotationRatingBar.getRating());
                Toast.makeText(getApplicationContext(),"Rate event successfully with "+rotationRatingBar.getRating()+" !",Toast.LENGTH_LONG).show();
            }
        });

        btnJoin = findViewById(R.id.btnJoin);
        txtEvtTitle = findViewById(R.id.txtEventTitle);
        txtEvtDetail = findViewById(R.id.txtEvtDetail);
        txtStDate = findViewById(R.id.txtStDate);
        txtStTime = findViewById(R.id.txtStTime);
        txtEndDate = findViewById(R.id.txtEndDate);
        txtEndTime = findViewById(R.id.txtEndTime);
        user2=(User)getIntent().getSerializableExtra("user");
        txtEvtTitle.setText(event.eventTitle);
        txtEvtDetail.setText(event.eventDetail);
        txtStTime.setText(event.startTime);
        txtStDate.setText(event.startDate);
        txtEndDate.setText(event.endDate);
        txtEndTime.setText(event.endTime);
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "you have successfully joined the event",
                        Toast.LENGTH_LONG).show();
                update(url_update,user2,event);

            }
        });
        Button btnEmail = findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title="Re: About your posting: "+event.eventTitle;
                String message="Hi, I just saw it in your posting : "+ event.eventDetail+"\n\n\n\n\n\n"+user2.firstName+" "+user2.lastName+"\n"+user2.email;
                String recipientList = event.orgEmail;
                String[] recipients = recipientList.split(",");
                String subject = title;
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, message);
                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent, "Choose an email client"));

            }
        });

        btnContact1=findViewById(R.id.btnContact);
        btnContact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(), MessageCreate.class);
                MessageCoordinator.Message message=new MessageCoordinator.Message();
                message.title="Re: About your events post:" +event.eventTitle;
                message.detail="I just saw your post regarding:\n" +event.toString()+"\n\n\n"+user2.firstName+" "+user2.lastName;
                message.sender_email=user2.id;
                message.receiver_email="2";
                i.putExtra("user",user2);
                i.putExtra("id","admin");
                i.putExtra("message",message);
                startActivity(i);
            }
        });
        shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/html");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                share.putExtra(Intent.EXTRA_SUBJECT, user2.firstName+" "+user2.lastName+" Has share an Event: "+event.eventTitle);
                share.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml(
                (new StringBuilder()
                        .append("<p style=“color:blue;”><b>Exciting events are recommended to you!</b></p>")
                        .append("<small><p>"+event.toString()+"</p></small>")).toString()));
                startActivity(Intent.createChooser(share, "Share link!"));
            }
        });
    }
    public void addEvent(EventCoordinator.Event event) throws ParseException {

        String stDate = event.startDate+" "+event.startTime;
        String edDate=event.endDate+" "+event.endTime;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm");
        Date startDate = sdf.parse(stDate);
        Date endDate = sdf.parse(edDate);
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, event.eventTitle)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startDate.getTime())
                .putExtra(CalendarContract.Events.DESCRIPTION, event.eventDetail)
                .putExtra(Intent.EXTRA_EMAIL, event.orgEmail)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endDate.getTime());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    public void updateRating(final String urlWebService, final User user, final EventCoordinator.Event event1,final Float rate) {

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
                    jsonParam.put("user_id", user.id);
                    jsonParam.put("event_id", event1.id);
                    jsonParam.put("rating",rate);
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
                    Log.d("TAG", "Server Response is: " + total.toString() + ": ");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            rotationRatingBar.setRating(Float.parseFloat(total.toString()));
                            imageCheck.setVisibility(View.GONE);
                        }
                    });
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
    public void update(final String urlWebService, final User user, final EventCoordinator.Event event1) {

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
                    jsonParam.put("eventId", event1.id);

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


                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }
    public void logout(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void OpenMenuActivity() {
        Intent intent = new Intent(this, EventList.class);
        intent.putExtra("user",user2);
        startActivity(intent);
    }
    public void openMyEventsActivity(){
        Intent intent =new Intent(this, EventMyEvents.class);
        intent.putExtra("user",user2);
        Log.d("TAG","Menu to MyEvent"+user2.id);
        startActivity(intent);
    }

    public void OpenCreateActivity() {
        Intent intent = new Intent(this, EventCreateDescription.class);
        intent.putExtra("user",user2);
        startActivity(intent);
    }
}



