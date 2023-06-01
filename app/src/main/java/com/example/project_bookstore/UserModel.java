package com.example.project_bookstore;

public class UserModel {
    int id;
    String name, email, password, phone, gender, role ;
    private String books;

    public UserModel(String name, String email, String password, String phone, String gender) {
        this.id =  (int) (Math.random() * 100000000) ;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.role = "user";

    }

    public UserModel(String name, String email, String password, String phone, String gender, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.role = role;
    }

    public UserModel(int id, String name, String email, String password, String phone, String gender, String role, String books) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.role = role;
        this.books = books;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", role='" + role + '\'' +
                ", books='" + books + '\'' +
                '}';
    }
}
