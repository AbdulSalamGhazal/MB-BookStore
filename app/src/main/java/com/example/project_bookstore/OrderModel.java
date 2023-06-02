package com.example.project_bookstore;

import java.util.List;

public class OrderModel {

    int id ,userId;
    String userName;

    double total;
    String payment_method, status;

    public OrderModel(int id, int userId, String userName, double total, String payment_method, String status) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.total = total;
        this.payment_method = payment_method;
        this.status = status;
    }

    public OrderModel(int userId, String userName, double total, String payment_method, String status) {
        this.id = (int) (Math.random() * 100000000);
        this.userId = userId;
        this.userName = userName;
        this.total = total;
        this.payment_method = payment_method;
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", total=" + total +
                ", payment_method='" + payment_method + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
