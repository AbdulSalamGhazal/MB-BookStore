package com.example.project_bookstore;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    // Declare the views and variables
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView totalTextView;
    private Button purchaseButton;

    // Declare an array list of books
    private List<BookModel> books;

    // Declare a variable to store the total price
    private double totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Initialize the views and variables
        recyclerView = findViewById(R.id.recyclerView);
        totalTextView = findViewById(R.id.totalTextView);
        purchaseButton = findViewById(R.id.purchaseButton);

        // Initialize the array list of books with some sample data
        DBs helper = new DBs(this);
        books = helper.getUserBooks(SignIn.userId);

        // Initialize the total price to zero
        totalPrice = 0.0;

        // Calculate the total price by looping through the books array list
        for (BookModel book : books) {
            totalPrice += book.price;
        }

        // Set the total text view to display the total price with two decimal places
        totalTextView.setText(String.format("Total: $%.2f", totalPrice));

        // Initialize the layout manager and set it to the recycler view
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Initialize the adapter and set it to the recycler view
        adapter = new CartAdapter(books, this);
        recyclerView.setAdapter(adapter);

        purchaseButton.setOnClickListener(v -> {
            if (books.isEmpty()) {
                Toast.makeText(CartActivity.this,"Cart is Empty",Toast.LENGTH_SHORT).show();
            }
            else {
                helper.make_order(new OrderModel(SignIn.userId, totalPrice));
                helper.deleteAllBooksFromUser(SignIn.userId);
                books.clear();
                adapter.notifyDataSetChanged();
            }
        });
    }

    // A method to update the total price when a book is removed from the cart
    public void updateTotal(double price) {
        // Subtract the price from the total price
        totalPrice -= price;

        // Set the total text view to display the updated total price with two decimal places
        totalTextView.setText(String.format("Total: $%.2f", totalPrice));
    }
}
