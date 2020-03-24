package com.example.stu_share;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminBookDetail extends AppCompatActivity {
    private TextView txtTitle,txtAuthor,txtEdition,txtISBN,txtDetails;
    private Button btnHome,btnLogout,terminate,btnContact1;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        user=(User)getIntent().getSerializableExtra("user");
        btnLogout = findViewById(R.id.btnAlLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        btnContact1=findViewById(R.id.btnAlContactUs2);
        btnContact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(), MessageCreate.class);
                i.putExtra("user",user);
                i.putExtra("id","admin");
                startActivity(i);

            }
        });
        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),AdminDashboard.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });
        terminate=findViewById(R.id.btnBookTerminate);
        terminate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminBookDetail.this,"Your posting has been terminated!",Toast.LENGTH_LONG).show();
                Intent i=new Intent(getBaseContext(), AdminDashboard.class);
                i.putExtra("user",user);
                startActivity(i);
            }
        });

        txtTitle=findViewById(R.id.txtTitle);
        txtAuthor=findViewById(R.id.txtAuthor);
        txtEdition=findViewById(R.id.txtEdition);
        txtISBN=findViewById(R.id.txtISBN);
        //txtDetails=findViewById(R.id.txtDetails);

        BookCoordinator.Book book=(BookCoordinator.Book)getIntent().getSerializableExtra("book");
        txtTitle.setText(book.title);
        txtAuthor.setText(book.author);
        txtEdition.setText(book.edition);
        txtISBN.setText(book.isbn);
        txtDetails.setText(book.details);

    }
}
