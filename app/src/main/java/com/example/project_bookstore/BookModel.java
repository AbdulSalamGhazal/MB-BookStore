package com.example.project_bookstore;

public class BookModel {
    int id;
    String name, desc, author;
    int image;
    double price;
    int  qty;




    public BookModel(int id, String name, String desc, String author, int image, double price, int qty) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.author = author;
        this.image = image;
        this.price = price;
        this.qty = qty;
    }

    public BookModel(String name, String desc, String author, int image, double price, int qty) {
        this.id = (int) (Math.random() * 100000000);
        this.name = name;
        this.desc = desc;
        this.author = author;
        this.image = image;
        this.price = price;
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "BookModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", author='" + author + '\'' +
                ", image=" + image +
                ", price=" + price +
                ", qty=" + qty +
                '}';
    }
}
