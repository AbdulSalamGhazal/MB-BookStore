package com.example.project_bookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class ShowBooks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_books);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rv = (RecyclerView) findViewById(R.id.book_recycler);
        BookRecyclerAdapter adapter = new BookRecyclerAdapter(null); //TODO: pass a cursor
        rv.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        rv.setLayoutManager(layoutManager);

        adapter.setListener(position -> {
            Intent intent = new Intent(this, BookDetailActivity.class);
            intent.putExtra(BookDetailActivity.EXTRA_BOOK_ID, position);
            startActivity(intent);
        });
    }
}