package com.example.project_bookstore;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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

        // Find the views by their ids
        toolbar = findViewById(R.id.toolbar);
        bookImageView = findViewById(R.id.book_image);
        bookTitleView = findViewById(R.id.book_title);
        bookAuthorView = findViewById(R.id.book_author);
        bookDescriptionView = findViewById(R.id.book_description);
        bookPriceView = findViewById(R.id.book_price);
        quantityInput = findViewById(R.id.quantity_input);
        addToCartButton = findViewById(R.id.add_to_cart_button);

        Cursor cursor = getCursor();
        cursor.moveToFirst();
        // Set the toolbar as the action bar
        setSupportActionBar(toolbar);

        // Set the toolbar title to 'Book Details'
        getSupportActionBar().setTitle("Book Details");

        // Set the content of the views
        bookImageView.setImageResource(R.drawable.head_first_image); // Replace with your own image resource
        bookTitleView.setText(cursor.getString(0)); // Replace with your own title
        bookAuthorView.setText(cursor.getString(1)); // Replace with your own author
//        bookDescriptionView.setText(" Here you are trying to learn something, while " +
//                "here your brain is, doing you a favor by making sure the learning doesn’t stick. Your " +
//                "brain’s thinking, “Better leave room for more important things, like which wild " +
//                "animals to avoid and whether naked snowboarding is a bad idea.” So how do you " +
//                "trick your brain into thinking that your life depends on knowing how to develop " +
//                "Android apps?"); // Replace with your own description
        bookPriceView.setText("$" + cursor.getDouble(2)); // Replace with your own price

        // Set a click listener for the button
        addToCartButton.setOnClickListener(v -> {
            // Get the inputted quantity
            int quantity = Integer.parseInt(quantityInput.getText().toString());
            // Add the item to the cart with the quantity
            addToCart(bookTitleView.getText().toString(), quantity);
        });
    }

    private void addToCart(String title, int quantity) {
        // TODO: Implement your logic for adding an item to the cart
    }

    private Cursor getCursor() {
        Intent intent = getIntent();
        int bookId = intent.getExtras().getInt(EXTRA_BOOK_ID);
        SQLiteDatabase db = new DBsBook(this).getReadableDatabase();
        Cursor cursor = db.query(DBsBook.TABLE_NAME,
                new String[]{DBsBook.COLUMN_NAME, DBsBook.COLUMN_AUTHOR, DBsBook.COLUMN_PRICE},
                "_id = ?",
                new String[]{String.valueOf(bookId)},
                null, null, null);
        return cursor;
    }
}
