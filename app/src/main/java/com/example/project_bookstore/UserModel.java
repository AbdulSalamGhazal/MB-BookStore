package com.example.project_bookstore;

public class UserModel {
    int id;
    String name, email, password, phone, gender;
    private String books;

    public UserModel(String name, String email, String password, String phone, String gender) {
        this.id =  (int) (Math.random() * 100000000) ;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.gender = gender;

    }
    public UserModel(String email){
        this.email=email;
    }

    public UserModel(int id, String name, String email, String password, String phone, String gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.gender = gender;

    }

    public UserModel(int id, String name, String email, String password, String phone, String gender, String books) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
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
                ", books='" + books + '\'' +
                '}';
    }
}
