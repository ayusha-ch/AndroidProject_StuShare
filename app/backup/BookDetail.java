package com.example.stu_share;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BookDetail extends AppCompatActivity {

    private TextView txtTitle,txtAuthor,txtEdition,txtISBN,txtDetails;
    private Button btnHome,btnLogout,btnEmail,message,btnContact1;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        user=(User)getIntent().getSerializableExtra("user");
        Button btnLogout = findViewById(R.id.btnAlLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        Button btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),MainMenu.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        Button btnEmail = findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),EmailActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        message=findViewById(R.id.btnMessage);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(), MessageCreate.class);
                startActivity(i);
            }
        });
        btnContact1=findViewById(R.id.btnContact);
        btnContact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(), MessageCreate.class);
                MessageCoordinator.Message message=new MessageCoordinator.Message();
                message.title="Re: About your events post:" +event.eventTitle;
                message.detail="I just saw your post regarding:\n" +event.toString()+"\n\n\n"+user2.firstName+" "+user2.lastName;
                message.sender_email=user2.email;
                message.receiver_email="david@georgebrown.ca";
                i.putExtra("user",user2);
                i.putExtra("id","admin");
                i.putExtra("message",message);
                startActivity(i);

            }
        });
        txtTitle=findViewById(R.id.txtTitle);
        txtAuthor=findViewById(R.id.txtAuthor);
        txtEdition=findViewById(R.id.txtEdition);
        txtISBN=findViewById(R.id.txtISBN);
        txtDetails=findViewById(R.id.txtDetails);

        BookCoordinator.Book book=(BookCoordinator.Book)getIntent().getSerializableExtra("book");
        txtTitle.setText(book.title);
        txtAuthor.setText(book.author);
        txtEdition.setText(book.edition);
        txtISBN.setText(book.isbn);
//        txtDetails.setText(book.details);

    }
}
