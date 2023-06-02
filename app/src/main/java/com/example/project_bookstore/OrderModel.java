package com.example.project_bookstore;

import java.util.List;

public class OrderModel {

    int id ,userId;
    String userName;

    double total;
    String status;

    public OrderModel(int id, int userId, String userName, double total, String status) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.total = total;
        this.status = status;
    }

    public OrderModel(int userId, double total) {
        this.id = (int) (Math.random() * 100000000);
        this.userId = userId;
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", total=" + total +
                ", status='" + status + '\'' +
                '}';
    }
}
