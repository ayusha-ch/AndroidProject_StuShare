package com.example.stu_share;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.util.Objects.isNull;

public class EventCreate extends AppCompatActivity {
    ImageView buttonImg;
    @BindView(R.id.toolbar)
    public Toolbar toolBar;
    TimePickerDialog timePickerSt,timePickerEnd;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;
    private static final String TAG = "Create";
    EventCreateDescription createDescription;
    private TextView txtStDate;
    private TextView txtEndDate;

    private TextView startDisplayDate;
    private DatePickerDialog.OnDateSetListener startDateSetListener;
    private TextView endDisplayDate;
    private DatePickerDialog.OnDateSetListener endDateSetListener;
    private EventCoordinator.Event event1;
    EditText txtStTime,txtEndTime;
    private User user;
    Button btnCreate;
    //    DBHelper dbHelper = null;
    private static final String REGISTER_URL="https://w0044421.gblearn.com/stu_share/create_event.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        ButterKnife.bind(this);
        toolBar.setTitle(getResources().getString(R.string.EventCreate));
        setSupportActionBar(toolBar);
        buttonImg = findViewById(R.id.buttonImg) ;
        buttonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCreateActivity();
            }
        });

        DrawerUtil.getDrawer(this,toolBar);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
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

                    case R.id.action_profile:
                        Intent i= new Intent(getBaseContext(),MyProfile.class);
                        i.putExtra("user",user);
                        startActivity(i);
                        break;
                }
                return false;
            }
        });

        startDisplayDate = findViewById(R.id.txtStDate);
        endDisplayDate = findViewById(R.id.txtEndDate);

        txtStDate=findViewById(R.id.txtStDate);
        txtStTime=findViewById(R.id.txtStTime);
        txtStTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);
                timePickerSt = new TimePickerDialog(EventCreate.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        txtStTime.setText(String.format("%02d:%02d", hourOfDay, minutes) );
                    }
                }, currentHour, currentMinute, false);

                timePickerSt.show();
            }
        });
        txtEndTime=findViewById(R.id.txtEndTime);
        txtEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerEnd = new TimePickerDialog(EventCreate.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {

                        txtEndTime.setText(String.format("%02d:%02d", hourOfDay, minutes) );
                    }
                }, currentHour, currentMinute, false);

                timePickerEnd.show();
            }
        });
        txtEndDate= findViewById(R.id.txtEndDate);

        startDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(EventCreate.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog,
                        startDateSetListener, year, month, day);
                dialog.getDatePicker().setMinDate(System.currentTimeMillis());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        startDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                view.setMinDate(System.currentTimeMillis());
                String date = ""+year +month +dayOfMonth;
                //startDisplayDate.setText(date);
                endDisplayDate.setText(date);
        }};

        endDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(EventCreate.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog,
                        endDateSetListener, year, month, day);
                dialog.getDatePicker().setMinDate(System.currentTimeMillis());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        endDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date =""+year +month +dayOfMonth;
                endDisplayDate.setText(date);
            }
        };

        user=(User)getIntent().getSerializableExtra("user");
        final Calendar myCalendar = Calendar.getInstance();
        final Calendar myCalendar1 = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            final long today = System.currentTimeMillis() - 1000;

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                view.setMinDate(System.currentTimeMillis());
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();

            }
            private void updateLabel() {
                String myFormat = "yyyyMMdd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.CANADA);
                txtEndDate.setText(sdf.format(myCalendar.getTime()));
            }
        };

        txtEndDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog( EventCreate.this,date, myCalendar1
                        .get(Calendar.YEAR), myCalendar1.get(Calendar.MONTH),
                        myCalendar1.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
            final long today = System.currentTimeMillis() - 1000;
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub

                view.setMinDate(System.currentTimeMillis());
                myCalendar1.set(Calendar.YEAR, year);
                myCalendar1.set(Calendar.MONTH, monthOfYear);
                myCalendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();

            }
            private void updateLabel() {
                String myFormat = "yyyyMMdd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.CANADA);
                txtStDate.setText(sdf.format(myCalendar1.getTime()));
                txtEndDate.setText(sdf.format(myCalendar.getTime()));
            };

        };
         event1=(EventCoordinator.Event)getIntent().getSerializableExtra("event");
         Log.i("EVENTINFOCREATE",event1.toString());
         txtStDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog( EventCreate.this,date2, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnCreate=findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            Date date = new Date();
            String today=dateFormat.format(date);

            @Override
            public void onClick(View v) {
                try {
                    if(dateFormat.parse(txtEndDate.getText().toString()).before(dateFormat.parse(today))||dateFormat.parse(txtStDate.getText().toString()).before(dateFormat.parse(today))){
                        Toast.makeText(getApplicationContext(),"Sorry the date is ealier than today, Please correct!",Toast.LENGTH_LONG).show();
                    }else if(txtEndDate.getText().toString().matches("")
                            ||txtStDate.getText().toString().matches("")
                            ||txtEndTime.getText().toString().matches("")
                            ||txtStTime.getText().toString().matches("")){
                        Toast.makeText(getApplicationContext(),"Some required fields are still empty!",Toast.LENGTH_LONG).show();
                    }else if(dateFormat.parse(txtEndDate.getText().toString()).before(dateFormat.parse(txtStDate.getText().toString()))){
                        Toast.makeText(getApplicationContext(),"End date can't be earlier than start date!",Toast.LENGTH_LONG).show();
                    } else{
                        sendPost();
                        OpenMenuActivity();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public void sendPost() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String eventC=Utility.generateCode(6);
                    URL url = new URL("https://w0044421.gblearn.com/stu_share/create_event.php");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("eventTitle", event1.eventTitle);
                    jsonParam.put("organizerId", user.id);
                    jsonParam.put("eventDetail",event1.eventDetail);
                    Log.i("EVENTINFOCREATEPOST",event1.toString());
                    jsonParam.put("endTime", txtEndTime.getText().toString());
                    jsonParam.put("startTime", txtStTime.getText().toString());
                    jsonParam.put("endDate", txtEndDate.getText().toString());
                    jsonParam.put("startDate", txtStDate.getText().toString());
                    jsonParam.put("eventCode", eventC);
                    jsonParam.put("orgEmail", user.getEmail());
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
                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void OpenMenuActivity(){
        Intent intent = new Intent(this, EventList.class);
      intent.putExtra("user",user);
        startActivity(intent);
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

    public void OpenEventList() {
        Intent intent = new Intent(this, EventList.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    public void OpenCreateActivity() {
        Intent intent = new Intent(this, EventCreateDescription.class);
        intent.putExtra("user",user);
        startActivity(intent); }

}
