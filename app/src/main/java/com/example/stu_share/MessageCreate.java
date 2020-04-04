package com.example.stu_share;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
import java.security.PrivateKey;

public class MessageCreate extends AppCompatActivity {
    Button home,logout, btnSendMessage;
    TextView txtTitle, txtDetails;
    private static final String TAG = "Create";
    private User userReg;
    private static String length;
    private MessageCoordinator.Message message;

    private static final String REGISTER_URL="https://w0044421.gblearn.com/stu_share/createMessage.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        userReg=(User)getIntent().getSerializableExtra("user");
        message=(MessageCoordinator.Message) getIntent().getSerializableExtra("message");
        txtTitle = findViewById(R.id.textMsgSubject);

        txtTitle.setText(message.title);
        txtDetails = findViewById(R.id.textMessageBody);
        txtDetails.setText(message.detail);
        btnSendMessage = findViewById(R.id.btnReply);
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtTitle.length() < 4  || txtTitle.length() > 100){
                    Toast.makeText(getApplicationContext(),"Message title length doesn't meet requirement!", Toast.LENGTH_LONG).show();
                }
                else if(txtDetails.length() < 15  || txtTitle.length() >  100){
                    Toast.makeText(getApplicationContext(),"Message detail length doesn't meet requirement!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Message sent successfully!",Toast.LENGTH_LONG).show();
                    sendPost();
                    Intent intent = new Intent();
                    intent.putExtra("user",userReg);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
        Button btnCancel = findViewById(R.id.btnMsg_Delete1);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("user",userReg);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
    public void sendPost() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String messageCode=Utility.generateCode(6);
                    URL url = new URL("https://w0044421.gblearn.com/stu_share/createMessage.php");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("title", txtTitle.getText().toString());
                    jsonParam.put("details", txtDetails.getText().toString());
                    jsonParam.put("email", userReg.email);
                    jsonParam.put("activationCode",MessageCreate.length);
                    jsonParam.put("messageCode", messageCode);
                    jsonParam.put("receiver", message.receiver_email);


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
