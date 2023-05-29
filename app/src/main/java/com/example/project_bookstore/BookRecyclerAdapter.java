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

public class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.ViewHolder> {
    private Cursor cursor;
    private Listener listener;

    interface Listener {
        void onClick(int _id);
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

    public BookRecyclerAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
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
        cursor.moveToPosition(position);

        //TODO: following values must be fetched from cursor
        int imageId = R.drawable.head_first_image;
        String title = cursor.getString(1);
        String author = cursor.getString(2);

        //set the image of the book
        ImageView bookImageView = cardView.findViewById(R.id.book_image);
        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), imageId);
        bookImageView.setImageDrawable(drawable);

        //set the title of the book
        TextView titleView = cardView.findViewById(R.id.book_title);
        titleView.setText(title);

        //set name of author
        TextView authorView = cardView.findViewById(R.id.book_author);
        authorView.setText(author);

        //set onclick listener to the card
        cardView.setOnClickListener(view -> {
            if (listener != null) {
                listener.onClick(cursor.getInt(0));
            }
        });
    }
}
