package com.example.stu_share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.stu_share.BookCoordinator.BOOK_LIST;

public class BookCreate extends AppCompatActivity {
    private EditText txtTitle, txtAuthor, txtEdition, txtISBN, txtDetails, txtPrice;
    private Button btnHome, btnLogout, btnSubmit;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_create);
        user=(User)getIntent().getSerializableExtra("user");
        txtTitle=findViewById(R.id.txtTitle);
        txtAuthor=findViewById(R.id.txtAuthor);
        txtEdition=findViewById(R.id.txtEdition);
        txtISBN=findViewById(R.id.txtISBN);
        txtDetails=findViewById(R.id.txtDetails);
        txtPrice = findViewById(R.id.txtPrice2);

        btnHome=findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getBaseContext(),MainMenu.class);
                i.putExtra("user",user);
                startActivity(i);
            }
        });

        btnSubmit=findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookCoordinator.Book temp=new BookCoordinator.Book("1","1",
                        txtTitle.getText().toString(),txtAuthor.getText().toString(),
                        txtEdition.getText().toString(),txtISBN.getText().toString(),
                        txtDetails.getText().toString(), txtPrice.getText().toString(), "today", "active");
                BOOK_LIST.add(temp);
                Toast.makeText(BookCreate.this,
                        "You car share information has been recorded!",
                        Toast.LENGTH_SHORT).show();
                Intent i =new Intent(getBaseContext(),BookMenu.class);
                i.putExtra("user",user);
                startActivity(i);
            }
        });
        btnLogout=findViewById(R.id.btnAlLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}
