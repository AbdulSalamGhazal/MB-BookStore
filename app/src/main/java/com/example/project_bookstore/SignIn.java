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
                if(email.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    Toast.makeText(SignIn.this,"LOGIN SUCCESSFUL",Toast.LENGTH_SHORT).show();
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