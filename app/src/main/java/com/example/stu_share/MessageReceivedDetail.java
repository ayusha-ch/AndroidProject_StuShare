package com.example.stu_share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.stu_share.MessageList.arrayAdapter;

public class MessageReceivedDetail extends AppCompatActivity {
    Button btnReply, btnDelete, btnCancel;
    TextView msgT,msgB;
    MessageCoordinator.Message message1;
    private User user1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_received_detail);
        user1=(User)getIntent().getSerializableExtra("user") ;
        Log.i("MSGRDDetail",user1.email+"email!!!");
        message1=(MessageCoordinator.Message)getIntent().getSerializableExtra("message");
        msgT=findViewById(R.id.textMsgSubject);
        msgB=findViewById(R.id.textMessageBody);
        msgT.setText(message1.title);
        msgB.setText(message1.detail);
        btnReply = findViewById(R.id.btnReply);
        final MessageCoordinator.Message message=new MessageCoordinator.Message();
        message.id=message1.id;
        message.title="Re: "+message1.title;
        message.detail="In you previous message, you mentioned:\n"+message1.detail+"\n";
        message.receiver_email=message1.sender_email;
        message.sender_email=user1.email;
        btnReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MessageCreate.class);
                intent.putExtra("message",message);
                intent.putExtra("user",user1);
                startActivity(intent);
            }
        });
        btnCancel = findViewById(R.id.btnMsg_CancelReply);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MessageList.class);
                intent.putExtra("user",user1);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        btnDelete=findViewById(R.id.btnMsg_Delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMessage(message1.id);
            }
        });
    }
    public void deleteMessage(Integer id) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://w0044421.gblearn.com/stu_share/MessageDelete.php");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("messageID", message1.id);

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
                    runOnUiThread(new Runnable() {
                        public void run()
                        {

                            Intent intent=new Intent();
                            intent.putExtra("user",user1);
                            setResult(RESULT_OK,intent);
                            finish();
                        }});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }
}
