package com.example.stu_share;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpEmailPass extends AppCompatActivity {
    Button btnNext;
    EditText txtEm,txtRegPswd,txtRegpswd2;
    TextView test,txtWarning;
    public User userReg=null;
    String password="";
    String email ="";
    String url="https://w0044421.gblearn.com/stu_share/UserCheck.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_email_pass);
        btnNext = findViewById(R.id.btnNext);
        txtEm=findViewById(R.id.txtRegEm2);
        txtRegPswd=findViewById(R.id.txtRegPswd);
        txtRegpswd2=findViewById(R.id.txtRegPswdConf);
        final String[] pwdInput = {""};
        txtWarning=findViewById(R.id.txtRegPwdWarning);
        txtRegPswd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(!isValidPassword(s.toString())){
                    txtWarning.setText("Password doesn't meet requirement!");
                    txtWarning.setTextColor(Color.RED);
                }else{
                    txtWarning.setText("Password OK");
                    txtWarning.setTextColor(Color.GREEN);
                }
            }
        });
        Boolean flag=false;
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 email = txtEm.getText().toString();
                password = txtRegPswd.getText().toString();
                userReg=new User();
                userReg.setEmail(email);
                userReg.setPassword(password);
                downloadJSON(url,email);

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
    private void downloadJSON(final String urlWebService,final String email) {
        class DownloadJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.i("GETUSERCOUNT",s);

                if(!checkEmailDomain(email)){
                    Toast.makeText(getBaseContext(), "It's not George Brown email! ",
                            Toast.LENGTH_LONG).show();
                }else if(Integer.parseInt(s)!=0){
                    Toast.makeText(getBaseContext(), "User exists ",
                            Toast.LENGTH_LONG).show()
                    ;
                    txtWarning.setText("User already exists!");
                    txtWarning.setTextColor(Color.RED);
                }
                else if(!(password.equals(txtRegpswd2.getText().toString()))){
                    txtWarning.setText("Password doesn't match!");
                    txtWarning.setTextColor(Color.RED);
                }
                else if(!isValidPassword(txtRegPswd.getText().toString())){
                    txtWarning.setText("Password doesn't meet requirements!");
                    txtWarning.setTextColor(Color.RED);
                }
                else{
                    Intent intent=new Intent(getBaseContext(),Signup.class);
                    intent.putExtra("args",userReg);
                    startActivity(intent);
                }
            }


                @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    con.setRequestProperty("Accept","application/json");
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("email", email);
                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(con.getOutputStream());
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    os.writeBytes(jsonParam.toString());
                    os.flush();
                    os.close();
                    con.connect();
                    Log.i("STATUS", String.valueOf(con.getResponseCode()));
                    Log.i("MSG" , con.getResponseMessage());
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    DataInputStream is=new DataInputStream(con.getInputStream());
                    StringBuilder total = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null)
                    {
                        total.append(line).append('\n');
                    }
                    Log.d("TAG", "Server Response is: " + total.toString() + ": " );
                    //loadIntoListView(total.toString().trim(),evt);
                    return total.toString().trim();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
                }
        }
        DownloadJSON getJSON = new DownloadJSON();
        getJSON.execute();

    }

    public boolean checkEmailDomain(String em){
        String[] result=null;
        result=em.split("@");
        if(result.length==2 && result[1].toLowerCase().equals("georgebrown.ca")){
            return true;
        }return false;
    }
    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{8,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }


}
