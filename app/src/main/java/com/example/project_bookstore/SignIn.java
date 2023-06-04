package com.example.project_bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class SignIn extends AppCompatActivity {
    public static int userId;
    public static String userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);



        //Login Button
        EditText emailView = findViewById(R.id.email);
        EditText passwordView = findViewById(R.id.password);
        MaterialButton loginButton = findViewById(R.id.loginbutton);
        loginButton.setOnClickListener(v -> {
            String email = emailView.getText().toString();
            String password = passwordView.getText().toString();

            DBs helper = new DBs(SignIn.this);
            UserModel admin1 = new UserModel("admin","admin@gmail.com","123456789","0541895742","Male","admin");
            helper.addUser(admin1);
            UserModel user = helper.getUserInfoByEmail(email);
            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(SignIn.this,"PLEASE COMPLETE ALL FIELDS!",Toast.LENGTH_LONG).show();
            }
            else {
                if (helper.getUserInfoByEmail(email) != null && password.equals(user.password)) {
                    if(user.role.equals("user")){
                        userId = user.id;
                        userEmail = user.email;
                        Toast.makeText(SignIn.this, "LOGIN SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignIn.this, ShowBooksActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(SignIn.this, "LOGIN SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignIn.this, OrderDetailActivity.class);
                        startActivity(intent);
                    }
                }else {
                    Toast.makeText(SignIn.this, "Email Or Password Is Wrong!", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        //isNotReg TextView
        TextView isNotReg = findViewById(R.id.isNotReg);
        isNotReg.setOnClickListener(v -> {
            Intent i = new Intent(SignIn.this, Register.class);
            startActivity(i);
        });
    }
}