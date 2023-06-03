package com.example.project_bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

import kotlin.experimental.BitwiseOperationsKt;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
        DBs db = new DBs(this);
        db.create_books();
        System.out.println(db.getAllBooks());

//        UserModel user1 = new UserModel("ahmad","ahmad@gmail.com","passAhmad","9665913943","male");
//        db.addUser(user1);
//        OrderModel order = new OrderModel(user1.id , 300);
//        db.make_order(order);
//        System.out.println(db.getAllOrders());
//        System.out.println(db.getOrderById(order.id));

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