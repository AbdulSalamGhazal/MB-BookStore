package com.example.project_bookstore;

import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.ViewHolder> {
    private List<BookModel> books;
    private Listener listener;

    interface Listener {
        void onClick(int book_id);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }

    public BookRecyclerAdapter(List<BookModel> books) {
        this.books = books;
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_book, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        BookModel book = books.get(position);

        //set the image of the book
        ImageView bookImageView = cardView.findViewById(R.id.book_image);
        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), book.image);
        bookImageView.setImageDrawable(drawable);

        //set the title of the book
        TextView titleView = cardView.findViewById(R.id.book_title);
        titleView.setText(book.name);

        //set name of author
        TextView authorView = cardView.findViewById(R.id.book_author);
        authorView.setText(book.author);

        //set onclick listener to the card
        cardView.setOnClickListener(view -> {
            if (listener != null) {
                listener.onClick(book.id);
            }
        });
    }
}
