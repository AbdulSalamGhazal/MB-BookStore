package com.example.project_bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        TextView email = (TextView) findViewById(R.id.email);
        TextView password = (TextView) findViewById(R.id.password);
        TextView isReg = (TextView)findViewById(R.id.isReg);
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
        isReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignIn.this, Register.class);
                startActivity(i);
            }
        });
    }
}