package com.example.project_bookstore;

public class BookModel {
    int id,qty;
    String name,author;
    double price;

    public BookModel(String name, String author, int qty, double price) {
        this.qty = qty;
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public BookModel(int id, String name, String author, int qty, double price) {
        this.id = id;
        this.qty = qty;
        this.name = name;
        this.author = author;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}
