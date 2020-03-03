package com.example.stu_share;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Signup2 extends AppCompatActivity {
    Button btnSign;
    EditText txtPC,txtRY,txtExp;
    private User userReg;
    //    DBHelper dbHelper=null;
    SignUpEmailPass signUpEmailPass;
    Signup signup;
    private static final String REGISTER_URL="https://w0044421.gblearn.com/stu_share/user_account_create.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
        txtPC=findViewById(R.id.txtPC);
        txtRY=findViewById(R.id.txtRY);
        txtExp=findViewById(R.id.txtExp);
        btnSign = findViewById(R.id.btnSign);
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPost();
                logout();
            }
        });
        userReg=(User)getIntent().getSerializableExtra("args");

        Button btnCancel=findViewById(R.id.btnSignup2Cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void sendPost() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://w0044421.gblearn.com/stu_share/user_account_create.php");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("programCode", txtPC.getText().toString());
                    jsonParam.put("registeredYear", txtRY.getText().toString());
                    jsonParam.put("expireYear", txtExp.getText().toString());
                    jsonParam.put("email", userReg.email);
                    jsonParam.put("password", userReg.password);
                    jsonParam.put("firstName", userReg.firstName);
                    jsonParam.put("lastName", userReg.lastName);
                    jsonParam.put("question", userReg.question);
                    jsonParam.put("answer", userReg.answer);



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


    public void logout() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
