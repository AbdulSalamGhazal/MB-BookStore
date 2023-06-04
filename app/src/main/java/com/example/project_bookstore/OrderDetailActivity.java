package com.example.project_bookstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {
    public final static String EXTRA_ORDER_ID = "orderId";
    private UserModel user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        // find views
        TextView orderIdView = findViewById(R.id.order_id);
        TextView userIdView = findViewById(R.id.user_id);
        TextView userNameView = findViewById(R.id.user_name);
        TextView userPhoneView = findViewById(R.id.user_phone);
        TextView userEmailView = findViewById(R.id.user_email);
        TextView totalPriceView = findViewById(R.id.total_price);
        RecyclerView recyclerView = findViewById(R.id.book_recycler);

        // get order id from intent
        Intent intent = getIntent();
        int orderId = intent.getExtras().getInt(EXTRA_ORDER_ID);

        // get helper to access database
        DBs helper = new DBs(this);

        // get required data models from helper
        OrderModel order = helper.getOrderById(orderId);
        List<BookModel> books = helper.getUserBooks(order.userId);

        // set text to views
        orderIdView.setText("#" + order.id);
        userIdView.setText(order.userId);
        totalPriceView.setText("Total: " + String.valueOf(order.total));

        // Initialize the layout manager and set it to the recycler view
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Initialize the adapter and set it to the recycler view
        RecyclerView.Adapter adapter = new CartAdapter(books, this);
        recyclerView.setAdapter(adapter);
    }

    class ModifiedCartAdapter extends CartAdapter {

        public ModifiedCartAdapter(List<BookModel> books, Context context) {
            super(books, context);
        }

        @Override
        public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
            super.onBindViewHolder(holder, position);
            holder.removeButton.setVisibility(View.GONE);
        }
    }
}