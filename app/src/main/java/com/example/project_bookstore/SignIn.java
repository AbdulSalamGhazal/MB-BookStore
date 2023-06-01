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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);



        //Login Button
        EditText email = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.password);
        MaterialButton loginButton = (MaterialButton) findViewById(R.id.loginbutton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString();
                String Password = password.getText().toString();

                // search for matching email, if none found toast "Email/Password incorrect"
                // if found fetch password and compare it, if not match "Email/Password incorrect"
                if(Email.equals("admin") && Password.equals("admin")){
                    //TODO: assign a value to user ID
                    Toast.makeText(SignIn.this,"LOGIN SUCCESSFULLY",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignIn.this, ShowBooksActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(SignIn.this,"LOGIN FAILED!",Toast.LENGTH_SHORT).show();
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