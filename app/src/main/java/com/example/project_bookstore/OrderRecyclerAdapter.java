package com.example.project_bookstore;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.ViewHolder> {
    private List<OrderModel> orders;
    private BookRecyclerAdapter.Listener listener;

    interface Listener {
        void onClick(int book_id);
    }

    public void setListener(BookRecyclerAdapter.Listener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }

    public OrderRecyclerAdapter(List<OrderModel> orders) {
        this.orders = orders;
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    @NonNull
    @Override
    public OrderRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_book, parent, false);
        return new OrderRecyclerAdapter.ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderRecyclerAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView orderIdView, userIdView, userNameView, priceView, statusView;
        OrderModel order = orders.get(position);

        orderIdView = cardView.findViewById(R.id.orderIdTextView);
        orderIdView.setText(order.id);

        userIdView = cardView.findViewById(R.id.userIdTextView);
        userIdView.setText("ID#: " + order.userId);

        userNameView = cardView.findViewById(R.id.userNameTextView);
        userNameView.setText("Name: " + order.userName);

        priceView = cardView.findViewById(R.id.totalTextView);
        priceView.setText("Total Price: " + order.total);

        statusView = cardView.findViewById(R.id.statusTextView);
        statusView.setText(order.status);

        //set onclick listener to the card
        cardView.setOnClickListener(view -> {
            if (listener != null) {
                listener.onClick(order.id);
            }
        });
    }

}
