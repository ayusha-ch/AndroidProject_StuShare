package com.example.stu_share;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class BookMenu extends AppCompatActivity {

    private Button btnMyBooks,btnViewBooks,btnCreateBook,btnMessage,btnHome,btnLogout;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_menu);
        user=(User)getIntent().getSerializableExtra("user");
        btnMyBooks = findViewById(R.id.btnMyBooks);
        btnMyBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),BookMyMenu.class);
                i.putExtra("user",user);
                startActivity(i);
            }
        });


        btnViewBooks = findViewById(R.id.viewBooks);
        btnViewBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),BookList.class);
                i.putExtra("user",user);
                startActivity(i);
            }
        });


        btnCreateBook = findViewById(R.id.createBooks);
        btnCreateBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),BookCreate.class);
                i.putExtra("user",user);
                startActivity(i);
            }
        });
        btnMessage = findViewById(R.id.messageCentre);
        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),MessageList.class);
                i.putExtra("user",user);
                startActivity(i);
            }
        });

        btnHome=findViewById(R.id.home);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),MainMenu.class);
                i.putExtra("user",user);
                startActivity(i);
            }
        });

        btnLogout=findViewById(R.id.logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),MainActivity.class);
                i.putExtra("user",user);
                startActivity(i);
            }
        });

    }
}
