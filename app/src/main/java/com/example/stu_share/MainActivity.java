package com.example.stu_share;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

import static com.example.stu_share.CarCoordinator.CAR_LIST;
import static com.example.stu_share.CarCoordinator.CAR_PAST_LIST;
import static com.example.stu_share.RoomCoordinator.ROOM_LIST;
import static com.example.stu_share.RoomCoordinator.ROOM_PAST_LIST;
import static com.example.stu_share.BookCoordinator.BOOK_LIST;
import static com.example.stu_share.BookCoordinator.BOOK_PAST_LIST;


import static com.example.stu_share.MessageCordinator.MESSAGE_LIST;

public class MainActivity extends AppCompatActivity {
    private Button btnCreateAcc, btnLogin, btnFgtPswd;
    private EditText txtEm, txtPswd;
    private TextView txtErr;
    private User user=new User();
    private String userString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreateAcc = findViewById(R.id.btnReg);
        btnFgtPswd = findViewById(R.id.btnResetPassword);
        txtEm = findViewById(R.id.txtRegEm);
        txtPswd = findViewById(R.id.txtPswd);

        final String txtEmail = txtEm.getText().toString();
        final String txtPassword = txtPswd.getText().toString();
        final String txtE = txtEm.getText().toString();
        final String txtP = txtPswd.getText().toString();
        txtErr = findViewById(R.id.txtWrong);
        user=(User)getIntent().getSerializableExtra("user");

        btnFgtPswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ResetWithEmail.class);
                startActivity(intent);
            }
        });
        txtErr = findViewById(R.id.txtWrong);
        btnCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignupActivity();
            }
        });
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user=sendPost();
            }
        });
    }

    public void openSignupActivity() {
        Intent intent = new Intent(this, SignUpEmailPass.class);
        startActivity(intent);
    }
    public User sendPost() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://w0044421.gblearn.com/stu_share/user_login.php");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("email", txtEm.getText().toString());

                    user=(User) getIntent().getSerializableExtra("user");

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

                    final User user1=jsonToUser(total.toString().trim(),user);
                    runOnUiThread(new Runnable() {
                    public void run() {
                        if (user1 == null) {
                            txtErr.setText("No such a user exists!");
                        } else if (!txtPswd.getText().toString().equals(user1.password)) {
                            txtErr.setText("Wrong password provided!");
                        }
                        else if(user1.role.equals("admin")){
                            Intent i=new Intent(getBaseContext(), AdminEventList.class);
                            createCars();
                            createMessageList();
                            createRooms();
                            createBooks();
                            i.putExtra("user",user1);
                            startActivity(i);

                        }  else if(user1.role.equals("alumni")){
                            Intent i=new Intent(getBaseContext(), AlumnaiDashboard.class);
                            createMessageList();
                            createBooks();
                            i.putExtra("user",user1);
                            startActivity(i);
                        }
                        else{
                            Intent i=new Intent(getBaseContext(), EventList.class);
                            createCars();
                            createRooms();
                            createMessageList();
                            createBooks();
                            i.putExtra("user",user1);
                            startActivity(i);
                        }

                    }});
            user=user1;
                    conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
        });
        thread.start();



        return user;
    }
    private void createCars() {
        CarCoordinator.Car car1=new CarCoordinator.Car("1","Dharam KC","A brand new Jeep checrokee for rent!","2019-12-25",
                "Jeep","Cherokee","2019","0","Casa Loma Campus",
                "Nice to have ,greate deal for GBC studeents, 50% discount with valid student ID","$40","active","2019-11-23 18:04:00");
        CarCoordinator.Car car2=new CarCoordinator.Car("2","Harmanpreet Kaur","Looking for ride share from Vancouver to Toronto","2019-12-20",
                "Tesla","Model X","2020","10","Vancouver City Hall",
                "Electric SUV with blazing fast speed, environmental friendly","$20","active","2019-11-23 18:04:00");
        CarCoordinator.Car car3=new CarCoordinator.Car("3","Yu Shi","7 sets caravan for rent","2019-12-25",
                "Dodge","Caravan","2016","80,000","At your choice",
                "7 seats caravan good for car pool","$120","active","2019-11-23 18:04:00");
        CarCoordinator.Car car4=new CarCoordinator.Car("1","Ayusha","Looking for lunxury ride ,please contact","2019-12-23",
                "Any","Any","2019","0","St James Campus",
                "Looking for a ride to GTA,Please contact ayusha@georgebrown.ca","$10","active","2019-11-23 18:04:00");


        CarCoordinator.Car pastcar1=new CarCoordinator.Car("1","Dharam KC","(Past)A brand new Jeep checrokee for rent!","2018-12-25",
                "Jeep","Cherokee","2019","0","Casa Loma Campus",
                "Nice to have ,greate deal for GBC studeents, 50% discount with valid student ID","$40","active","2018-11-23 18:04:00");
        CarCoordinator.Car pastcar2=new CarCoordinator.Car("2","Harmanpreet Kaur","(Past)Looking for ride share from Vancouver to Toronto","2018-12-20",
                "Tesla","Model X","2020","10","Vancouver City Hall",
                "Electric SUV with blazing fast speed, environmental friendly","$20","active","2018-11-23 18:04:00");
        CarCoordinator.Car pastcar3=new CarCoordinator.Car("3","Yu Shi","(Past)7 sets caravan for rente","2018-12-25",
                "Dodge","Caravan","2016","80,000","At your choice",
                "7 seats caravan good for car pool","$120","active","2018-11-23 18:04:00");
        CarCoordinator.Car pastcar4=new CarCoordinator.Car("1","Ayusha","(Past)Looking for lunxury ride ,please contact","2018-12-23",
                "Any","Any","2019","0","St James Campus",
                "Looking for a ride to GTA,Please contact ayusha@georgebrown.ca","$10","active","2018-11-23 18:04:00");
        CAR_LIST.add(car1);
        CAR_LIST.add(car2);
        CAR_LIST.add(car3);
        CAR_LIST.add(car4);
        CAR_PAST_LIST.add(pastcar1);
        CAR_PAST_LIST.add(pastcar2);
        CAR_PAST_LIST.add(pastcar3);
        CAR_PAST_LIST.add(pastcar4);
    }

    private void createRooms() {
        RoomCoordinator.Room room1 =new RoomCoordinator.Room("1", "Yi Shi", "Renting a room in two bedroom basement", "2019-12-20", "2",
                "10","Basement","offering", "yes","spadiana",
                "Looking for a roommate to share a room in two bedroom basement", "1000", "active","2019-11-23 18:04:00");

        RoomCoordinator.Room room2 =new RoomCoordinator.Room("2", "Dharam KC", "Want a room in the three bedroom apartment", "2019-12-25", "3",
                "12","Apartment","wanting", "yes","yonge and bloor",
                "Want a roommate to share a room in two bedroom apartment", "800", "active","2019-11-20 10:04:00");

        RoomCoordinator.Room room3 =new RoomCoordinator.Room("3", "Ayusha", "Looking for a roommate to share room ", "2019-12-1", "1",
                "0","Basement","offering", "yes","scarborough",
                "Looking for a roommate to share a room in two bedroom apartment", "1000", "active","2019-11-22 16:04:00");

        RoomCoordinator.Room room4 =new RoomCoordinator.Room("4", "Harmanpreet Kaur", "Looking for the roommate in the mississauga", "2019-12-20", "2",
                "10","Basement","offering", "yes","mississauga",
                "Looking for a roommate to share a room in one bedroom basement", "1000", "active","2019-11-23 18:04:00");


        RoomCoordinator.Room pastroom1 =new RoomCoordinator.Room("1", "Yi Shi", "(Past)Renting a room", "2019-12-20", "2",
                "10","Basement","offering", "yes","spadiana",
                "Looking for a roommate to share a room in two bedroom basement", "1000", "active","2019-11-23 18:04:00");

        RoomCoordinator.Room pastroom2 =new RoomCoordinator.Room("2", "Dharam KC", "(Past)Want a room", "2019-12-25", "3",
                "12","Apartment","wanting", "yes","yonge and bloor",
                "Want a roommate to share a room in two bedroom apartment", "800", "active","2019-11-20 10:04:00");

        RoomCoordinator.Room pastroom3 =new RoomCoordinator.Room("3", "Ayusha", "(Past)Renting a room", "2019-12-1", "1",
                "0","Basement","offering", "yes","scarborough",
                "Looking for a roommate to share a room in two bedroom apartment", "1000", "active","2019-11-22 16:04:00");

        RoomCoordinator.Room pastroom4 =new RoomCoordinator.Room("4", "Harmanpreet Kaur", "(Past)Renting a room", "2019-12-20", "2",
                "10","Basement","offering", "yes","mississauga",
                "Looking for a roommate to share a room in one bedroom basement", "1000", "active","2019-11-23 18:04:00");

        ROOM_LIST.add(room1);
        ROOM_LIST.add(room2);
        ROOM_LIST.add(room3);
        ROOM_LIST.add(room4);

        ROOM_PAST_LIST.add(pastroom1);
        ROOM_PAST_LIST.add(pastroom2);
        ROOM_PAST_LIST.add(pastroom3);
        ROOM_PAST_LIST.add(pastroom4);

    }

    private void createBooks(){
        BookCoordinator.Book book1 = new BookCoordinator.Book("1", "Harman", "Code Complete",
                "Steve McConnell", "2","0735619670",
                "This book is brand new available for buying", "52","2019-11-23 18:04:10","active");

        BookCoordinator.Book book2 = new BookCoordinator.Book("2", "Ayusha", "Clean Code: A Handbook of Agile Software Craftsmanship",
                "Robert C. Martin ", "1"," 9780132350884",
                "This book is in mint condition and looks like new for rent for 10 months","$10", "2019-11-24 16:02:30","active");

        BookCoordinator.Book book3 = new BookCoordinator.Book("3", "Dharam", "Java: The Complete Reference, Tenth Edition",
                "Herbert Schildt", "10","0735619670",
                "I am looking for this book. If anyone has this book please contact ", "$20","2019-11-29 14:04:00","active");
        BookCoordinator.Book book4 = new BookCoordinator.Book("4", "David", "Book for python course",
                "McConnell", "2","0735619670",
                "This book is brand new. I have it available for rest for 4 months for 10 per month", "$10 ","2019-11-28 15:03:00","active");

        BookCoordinator.Book pastbook1 = new BookCoordinator.Book("1", "Harman", "(Past)Code Complete",
                "Steve McConnell", "2","0735619670",
                "This book is brand new available for buying", "$52","2019-11-23 18:04:10","active");

        BookCoordinator.Book pastbook2 = new BookCoordinator.Book("2", "Ayusha", "(Past)Clean Code: A Handbook of Agile Software Craftsmanship",
                "Robert C. Martin ", "1"," 9780132350884",
                "This book is in mint condition and looks like new for rent for 10 months", "$10","2019-11-24 16:02:30","active");

        BookCoordinator.Book pastbook3 = new BookCoordinator.Book("3", "Dharam", "(Past)Java: The Complete Reference, Tenth Edition",
                "Herbert Schildt", "10","0735619670",
                "I am looking for this book. If anyone has this book please contact ", "$20","2019-11-29 14:04:00","active");

        BookCoordinator.Book pastbook4 = new BookCoordinator.Book("4", "David", "(Past)Book for python course",
                "McConnell", "2","0735619670",
                "This book is brand new. I have it available for rest for 4 months for 10 per month","$10 ", "2019-11-28 15:03:00","active");

        BOOK_LIST.add(book1);
        BOOK_LIST.add(book2);
        BOOK_LIST.add(book3);
        BOOK_LIST.add(book4);


        BOOK_PAST_LIST.add(pastbook1);
        BOOK_PAST_LIST.add(pastbook2);
        BOOK_PAST_LIST.add(pastbook3);
        BOOK_PAST_LIST.add(pastbook4);


    }




    private void createMessageList() {
        MessageCordinator.Message message1=new MessageCordinator.Message(1,"Android Programming Book for Sell");
        MessageCordinator.Message message2=new MessageCordinator.Message(2,"Come Learn Resume Writing!");
        MessageCordinator.Message message3=new MessageCordinator.Message(3,"Ride-share Available from Scarborough!");
        MessageCordinator.Message message4=new MessageCordinator.Message(4,"Looking for Rental Room Share?");
        MESSAGE_LIST.add(message1);
        MESSAGE_LIST.add(message2);
        MESSAGE_LIST.add(message3);
        MESSAGE_LIST.add(message4);
    }
    private User jsonToUser(String json, User user) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
             user = new User();

            user.setId( obj.getString("id"));
            user.setEmail(obj.getString("email"));
            user.setPassword(obj.getString("password"));
            user.setFirstName(obj.getString("firstName"));
            user.setLastName(obj.getString("lastName"));
            user.setCollegeCode(obj.getString("collegeCode"));
            user.setProgramCode(obj.getString("programCode"));
            user.setRegisterYear(obj.getString("registeredYear"));
            user.setExpireYear(obj.getString("expireYear"));
            user.setStatus(obj.getString("status"));
            user.setQuestion(obj.getString("question"));
            user.setAnswer(obj.getString("answer"));
            user.setRole(obj.getString("role"));

        }
        return user;
    }
   }



