package com.example.stu_share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BookCurrentDetail extends AppCompatActivity {

    private EditText title, author, edition, isbn, details, price;
    private Button home,logout,terminate,update;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_current_detail);
        user=(User)getIntent().getSerializableExtra("user");
        title=findViewById(R.id.txtTitle);
        author=findViewById(R.id.txtAuthor);
        edition=findViewById(R.id.txtEdition);
        isbn=findViewById(R.id.txtISBN);
        details=findViewById(R.id.txtDetails);
        price=findViewById(R.id.txtPrice);

        terminate=findViewById(R.id.btnTerminate);



        final BookCoordinator.Book book=(BookCoordinator.Book)getIntent().getSerializableExtra("book");
        final int position1=(int)getIntent().getSerializableExtra("position1");
        terminate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookCoordinator.BOOK_LIST.remove(book);
                BookCoordinator.BOOK_PAST_LIST.add(book);
                Toast.makeText(BookCurrentDetail.this,"Your car share has been terminated.",Toast.LENGTH_LONG).show();
            }
        });
        home=findViewById(R.id.btnHome);
        logout=findViewById(R.id.btnAlLogout);

        update=findViewById(R.id.btnUpdate);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getBaseContext(),"Your information has been updated!",Toast.LENGTH_LONG);
                Intent i=new Intent(getBaseContext(),BookMenu.class);
                i.putExtra("user",user);
                startActivity(i);
            }
        });

        title.setText(book.title);
        author.setText(book.author);
        edition.setText(book.edition);
        isbn.setText(book.isbn);

        details.setText(book.details);
        price.setText(book.price);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),MainMenu.class);
                i.putExtra("user",user);
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

    }
}
