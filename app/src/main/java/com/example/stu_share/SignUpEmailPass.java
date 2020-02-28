package com.example.stu_share;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SignUpEmailPass extends AppCompatActivity {
    Button btnNext;
    EditText txtEm,txtRegPswd,txtRegpswd2;
    public User userReg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_email_pass);
        btnNext = findViewById(R.id.btnNext);
        txtEm=findViewById(R.id.txtRegEm2);
        txtRegPswd=findViewById(R.id.txtRegPswd);
        txtRegpswd2=findViewById(R.id.txtRegPswdConf);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEm.getText().toString();
                String password = txtRegPswd.getText().toString();
                userReg=new User();
                userReg.setEmail(email);
                userReg.setPassword(password);
                if(!checkEmailDomain(email)){
                    Toast.makeText(getBaseContext(), "It's not George Brown email! ",
                            Toast.LENGTH_LONG).show();
                }
                else if(!(password.equals(txtRegpswd2.getText().toString()))){
                    Toast.makeText(getBaseContext(), "Password doesn't match! ",
                            Toast.LENGTH_LONG).show()
                    ;
                }
                else{
                    Intent intent=new Intent(getBaseContext(),Signup.class);
                    intent.putExtra("args",userReg);
                    startActivity(intent);
                }

            }
        });
        Button btnCancel = findViewById(R.id.btnEmailPassCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean checkEmailDomain(String em){
        String[] result=null;
        result=em.split("@");
        if(result.length==2 && result[1].toLowerCase().equals("georgebrown.ca")){
            return true;
        }return false;
    }

}
