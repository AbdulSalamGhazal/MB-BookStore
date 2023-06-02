package com.example.project_bookstore;

public class BookModel {
    int id,qty;
    String name, desc, author;
    double price;

    public BookModel(int id, String name, String desc, String author, int qty,  double price) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.author = author;
        this.qty = qty;
        this.price = price;
    }

    public BookModel(String name, String desc, String author,int qty,  double price) {
        this.id = (int) (Math.random() * 100000000);
        this.name = name;
        this.desc = desc;
        this.author = author;
        this.qty = qty;
        this.price = price;
    }

    @Override
    public String toString() {
        return "BookModel{" +
                "id=" + id +
                ", qty=" + qty +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}
