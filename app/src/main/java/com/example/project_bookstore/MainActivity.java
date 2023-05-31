package com.example.project_bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DBs db = new DBs(MainActivity.this);
//        BookModel myBook = new BookModel("12 rules","jord",10,1.9);
//        db.addBook(myBook);
        UserModel myUser = new UserModel("Khaled","kh@gmail.com","dfask2123","050023401423","male");
        db.addUser(myUser);
//        db.addBookToUser(myUser.id, myBook.id);
//        System.out.println(db.getAllUsers());
//        db.addBookToUser(myUser.id, myBook.id);
//        System.out.println(db.getAllUsers());
//        System.out.println(db.getUserBooks(myUser.id));

        UserModel myUser2 = new UserModel("ahmed","ahmed@gmail.com","dfask2123","050023401423","male");
        db.addUser(myUser2);
        System.out.println(db.getAllUsers());

        System.out.println(db.getAllEmails());

        System.out.println(db.getUserInfoByEmail("dafdsfds"));
        System.out.println(db.getUserInfoByEmail("kh@gmail.com"));

        // Sign up button
        Button signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Register.class);
                startActivity(i);
            }
        });


        // Sign in button
        Button signin = (Button) findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SignIn.class);
                startActivity(i);
            }
        });

    }
}