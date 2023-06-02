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
        EditText emailView = (EditText) findViewById(R.id.email);
        EditText passwordView = (EditText) findViewById(R.id.password);
        MaterialButton loginButton = (MaterialButton) findViewById(R.id.loginbutton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailView.getText().toString();
                String password = passwordView.getText().toString();
                // search for matching email, if none found toast "Email/Password incorrect"
                // if found fetch password and compare it, if not match "Email/Password incorrect"
                DBs helper = new DBs(SignIn.this);
                UserModel user = helper.getUserInfoByEmail(email);
                if(email.isEmpty() || password.isEmpty()){
                    Toast.makeText(SignIn.this,"PLEASE COMPLETE ALL FIELDS!",Toast.LENGTH_LONG).show();
                }
                else {
                    if (helper.getUserInfoByEmail(email) != null && password.equals(user.password)) {
                        userId = user.id;
                        userEmail = user.email;
                        Toast.makeText(SignIn.this, "LOGIN SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignIn.this, ShowBooksActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(SignIn.this, "Email Or Password Is Wrong!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        });


        //isNotReg TextView
        TextView isNotReg = (TextView)findViewById(R.id.isNotReg);
        isNotReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignIn.this, Register.class);
                startActivity(i);
            }
        });
    }
}