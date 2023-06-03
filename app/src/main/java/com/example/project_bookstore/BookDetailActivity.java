package com.example.project_bookstore;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class BookDetailActivity extends AppCompatActivity {
    public final static String EXTRA_BOOK_ID = "bookId";
    private Toolbar toolbar;
    private ImageView bookImageView;
    private TextView bookTitleView;
    private TextView bookAuthorView;
    private TextView bookDescriptionView;
    private TextView bookPriceView;
    private EditText quantityInput;
    private Button addToCartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Find the views by their ids
        bookImageView = findViewById(R.id.book_image);
        bookTitleView = findViewById(R.id.book_title);
        bookAuthorView = findViewById(R.id.book_author);
        bookDescriptionView = findViewById(R.id.book_description);
        bookPriceView = findViewById(R.id.book_price);
        addToCartButton = findViewById(R.id.add_to_cart_button);

        int bookId = getIntent().getExtras().getInt(EXTRA_BOOK_ID);
        DBs helper = new DBs(this);
        BookModel book = helper.getBookById(String.valueOf(bookId));

        // Set the toolbar title to 'Book Details'
        getSupportActionBar().setTitle("Book Details");

        // Set the content of the views
        bookImageView.setImageResource(R.drawable.rules_for_life); // TODO: Replace with your own image resource
        bookTitleView.setText(book.name); // Replace with your own title
        bookAuthorView.setText(book.author); // Replace with your own author
//        bookDescriptionView.setText(book.);

        bookPriceView.setText("$" + book.price); // Replace with your own price

        // Set a click listener for the button
        addToCartButton.setOnClickListener(v -> helper.addBookToUser(SignIn.userId, book.id));
    }

}
