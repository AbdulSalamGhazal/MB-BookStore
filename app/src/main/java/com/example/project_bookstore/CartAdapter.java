package com.example.project_bookstore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.BookViewHolder> {

    // Declare the array list of books and the context
    private List<BookModel> books;
    private Context context;

    // Define a constructor to initialize the fields
    public CartAdapter(List<BookModel> books, Context context) {
        this.books = books;
        this.context = context;
    }

    // Define a view holder class that extends RecyclerView.ViewHolder
    public static class BookViewHolder extends RecyclerView.ViewHolder {

        // Declare the views for the book item layout
        private TextView bookNameTextView;
        private TextView bookPriceTextView;
        protected ImageButton removeButton;
        private Context context;

        // Define a constructor to initialize the views
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            bookNameTextView = itemView.findViewById(R.id.bookNameTextView);
            bookPriceTextView = itemView.findViewById(R.id.bookPriceTextView);
            removeButton = itemView.findViewById(R.id.removeButton);
        }
    }

    // Override the onCreateViewHolder method to inflate the book item layout and return a view holder
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        context = view.getContext();
        return new BookViewHolder(view);
    }

    // Override the onBindViewHolder method to set the data to the views and handle the click events
    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // Get the current book from the array list
        BookModel currentBook = books.get(position);

        // Set the book name and price to the text views
        holder.bookNameTextView.setText(currentBook.name);
        holder.bookPriceTextView.setText(String.format("$%.2f", currentBook.price));

        // Set a click listener to the remove button
        holder.removeButton.setOnClickListener(v -> {
            //remove book from database
            new DBs(context).deleteBookFromUser(SignIn.userId, currentBook.id);
            // Remove the book from the array list
            books.remove(position);

            // Notify the adapter that the data set has changed
            notifyDataSetChanged();

            // Update the total price in the main activity
            ((CartActivity) context).updateTotal(currentBook.price);
        });
    }

    // Override the getItemCount method to return the size of the array list
    @Override
    public int getItemCount() {
        return books.size();
    }
}
