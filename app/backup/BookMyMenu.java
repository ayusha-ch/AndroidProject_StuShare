package com.example.stu_share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BookMyMenu extends AppCompatActivity {
    private Button home,logout,viewCurrent,viewPast,create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_my_menu);


        home=findViewById(R.id.btnHome);
        logout=findViewById(R.id.btnAlLogout);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),BookMenu.class);
                startActivity(i);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);
            }
        });
        viewCurrent=findViewById(R.id.btnViewCurrentBook);
        viewCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(),BookCurrentList.class);
                startActivity(i);
            }
        });
        viewPast=findViewById(R.id.btnViewPastBook);
        viewPast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(),BookPastList.class);
                startActivity(i);
            }
        });
        create=findViewById(R.id.createBook);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(),BookCreate.class);
                startActivity(i);
            }
        });

    }
}
