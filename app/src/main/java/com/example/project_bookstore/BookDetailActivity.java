package com.example.project_bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BookDetailActivity extends AppCompatActivity {
    public final static String EXTRA_BOOK_ID = "bookId";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
    }
}