package com.example.stu_share;

import android.content.Intent;
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

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpEmailPass extends AppCompatActivity {
    Button btnNext;
    EditText txtEm,txtRegPswd,txtRegpswd2;
    TextView test;
    public User userReg=null;
//    DBHelper dbHelper=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_email_pass);
        btnNext = findViewById(R.id.btnNext);
        txtEm=findViewById(R.id.txtRegEm2);
        txtRegPswd=findViewById(R.id.txtRegPswd);
        txtRegpswd2=findViewById(R.id.txtRegPswdConf);
        final String[] pwdInput = {""};
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
                    Toast.makeText(getApplicationContext(),"Password doesn't meet requirements!",Toast.LENGTH_SHORT).show();
                    }
            }
        });
//        downloadJSON("https://w0044421.gblearn.com/stu_share/user_email_pass.php");
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
                else if(!isValidPassword(txtRegPswd.getText().toString())){
                    Toast.makeText(getBaseContext(), "Password doesn't meet requirements! ",
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
//              intent.putExtra("args", userReg);
                startActivity(intent);
            }
        });
    }
//    private void downloadJSON(final String urlWebService) {
//
//        class DownloadJSON extends AsyncTask<Void, Void, String> {
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//            }
//
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
//                try {
//                    loadIntoListView(s);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }


    //            @Override
//            protected String doInBackground(Void... voids) {
//                try {
//                    URL url = new URL(urlWebService);
//                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
//                    con.setRequestMethod("POST");
//                    StringBuilder sb = new StringBuilder();
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                    String json;
//                    while ((json = bufferedReader.readLine()) != null) {
//                        sb.append(json + "\n");
//                    }
//                    return sb.toString().trim();
//                } catch (Exception e) {
//                    return null;
//                }
//            }
//        }
//        DownloadJSON getJSON = new DownloadJSON();
//        getJSON.execute();
//    }

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
