package com.example.stu_share;
import android.os.AsyncTask;
import android.util.Log;

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
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.security.auth.Subject;

public class Utility {
    static String fromGmail="shy0105030229@gmail.com";
    static String pswdGmail="Oakwood123";
    static String subject="Thank you for registration";
    static String msg="Please check your code: ";
    public static String generateCode(int n)
    {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

    public static void send(final String from, final String password, final String to, final String sub, final String msg,final String code){
        class DownloadJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    Properties props = new Properties();
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.socketFactory.port", "465");
                    props.put("mail.smtp.socketFactory.class",
                            "javax.net.ssl.SSLSocketFactory");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.port", "465");
                    //get Session
                    Session session = Session.getDefaultInstance(props,
                            new javax.mail.Authenticator() {
                                protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(from,password);
                                }
                            });

                    Message message = new MimeMessage(session);
                    message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
                    message.setSubject(sub);
                    message.setContent("<h3>"+msg+code+"</h3><hr>Or click the URL below:<br><a href=\"http://w0044421.gblearn.com/stu_share/user_account_activation.php?token="+code+"\">\"Click Here\"</a>","text/html");
                    //send message
                    Transport.send(message);
                    System.out.println("message sent successfully");
                    return null;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        DownloadJSON getJSON = new DownloadJSON();
        getJSON.execute();


    }
}
