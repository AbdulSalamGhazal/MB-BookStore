package com.example.project_bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import kotlin.experimental.BitwiseOperationsKt;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
        DBs db = new DBs(this);
        BookModel book1 = new BookModel("12 Rules","a book that discuss the rules of life","jordan",15,29);
        BookModel book2 = new BookModel("atomic habit","how to mantain you habits","david",100,9.99);
        UserModel user1 = new UserModel("ahmad","ahmad@gmail.com","passAhmad","9665913943","male");
        UserModel user2 = new UserModel("tala","tgirl@hotmail.com","qwerty","9615423434","female");
        db.addUser(user2);
        OrderModel order = new OrderModel(user2.id , 300);
        db.make_order(order);
        System.out.println(db.getAllOrders());

        // Sign in button
        Button signin = (Button) findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SignIn.class);
                startActivity(i);
            }
        });


        //Signup
        Button signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Register.class);
                startActivity(i);
            }
        });
    }
}