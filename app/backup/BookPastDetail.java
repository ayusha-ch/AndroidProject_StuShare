package com.example.stu_share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.stu_share.BookCoordinator.BOOK_LIST;
import static com.example.stu_share.BookCoordinator.BOOK_PAST_LIST;

public class BookPastDetail extends AppCompatActivity {
    private EditText title, author, edition, isbn, price, description;
    private Button home,logout,reactivate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_past_detail);

        title=findViewById(R.id.txtTitle);
        author=findViewById(R.id.txtAuthor);
        edition=findViewById(R.id.txtEdition);
        isbn=findViewById(R.id.txtISBN);
        price=findViewById(R.id.txtPrice);
        description=findViewById(R.id.txtDetails);
        reactivate=findViewById(R.id.buttonAReactivate2);

        final BookCoordinator.Book book=(BookCoordinator.Book) getIntent().getSerializableExtra("book");
        final int position1=(int)getIntent().getSerializableExtra("position1");
        reactivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BookPastDetail.this, "Your book share is now activated!", Toast.LENGTH_SHORT).show();
                BOOK_PAST_LIST.remove(book);
                BOOK_LIST.add(book);

                Intent i=new Intent(getBaseContext(),BookMenu.class);
                startActivity(i);
            }
        });


        home=findViewById(R.id.btnHome);
        logout=findViewById(R.id.b);
        title.setText(book.title);
        author.setText(book.author);
        edition.setText(book.edition);
        isbn.setText(book.isbn);
        price.setText(book.price);
        description.setText(book.details);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),RoomMenu.class);
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
