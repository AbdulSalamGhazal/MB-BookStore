package com.example.project_bookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class ShowBooks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_books);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rv = (RecyclerView) findViewById(R.id.book_recycler);
        Cursor cursor = getCursor();
        BookRecyclerAdapter adapter = new BookRecyclerAdapter(cursor);
        rv.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        rv.setLayoutManager(layoutManager);

        adapter.setListener(book_id -> {
            Intent intent = new Intent(this, BookDetailActivity.class);
            intent.putExtra(BookDetailActivity.EXTRA_BOOK_ID, book_id);
            startActivity(intent);
        });
    }

    private Cursor getCursor() {
        SQLiteDatabase db = new DBs(this).getReadableDatabase();
        Cursor cursor = db.query(DBs.BOOK_TABLE_NAME,
                new String[] {DBs.BOOK_COLUMN_ID, DBs.BOOK_COLUMN_NAME, DBs.BOOK_COLUMN_AUTHOR},
                null, null, null, null, null);
        return cursor;
    }
    private void addBooks(SQLiteDatabase db) {
        for (int i = 0; i < 3; i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBs.BOOK_COLUMN_NAME, "Head First Android Development");
            contentValues.put(DBs.BOOK_COLUMN_AUTHOR, "Dawn Griffi\nDavid Griffi");
            contentValues.put(DBs.BOOK_COLUMN_QTY, 4);
            contentValues.put(DBs.BOOK_COLUMN_PRICE, 100.0);
            try {
                boolean success = db.insertOrThrow(DBs.BOOK_TABLE_NAME, null, contentValues) != -1;
                Log.v("SQLHelper", "inserting values: " + (success? "success":"failed"));
            } catch (SQLException e) {
                Log.v("SQLHelper", "inserting values: failed, throw: " + e.getMessage());
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}