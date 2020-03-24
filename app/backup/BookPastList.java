package com.example.stu_share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import static com.example.stu_share.BookCoordinator.BOOK_PAST_LIST;

public class BookPastList extends AppCompatActivity {

    private ListView listBook;
    private Button home, logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_past_list);

        listBook=findViewById(R.id.listPastBook);
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,BOOK_PAST_LIST);
        listBook.setAdapter(arrayAdapter);
        listBook.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position1,
                                    long arg3)
            {
                arrayAdapter.notifyDataSetChanged();
                BookCoordinator.Book book =(BookCoordinator.Book) adapter.getItemAtPosition(position1);
                Intent intent =new Intent(getBaseContext(), BookPastDetail.class);
                intent.putExtra("book",book);
                intent.putExtra("position1",position1);
                startActivity(intent);
            }
        });

        home=findViewById(R.id.btnHome6);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),BookMenu.class);
                startActivity(i);
            }
        });
        logout=findViewById(R.id.btnLogout12);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}
