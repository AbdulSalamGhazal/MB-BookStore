package com.example.project_bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText nameView = (EditText)findViewById(R.id.name);
        EditText emailView = (EditText)findViewById(R.id.email);
        EditText passwordView = (EditText)findViewById(R.id.newPassword);
        EditText phoneView = (EditText) findViewById(R.id.PhoneNumber);
        Button genderView = (Button) findViewById(R.id.Gender);


        //Register Button
        MaterialButton regButton = (MaterialButton) findViewById(R.id.RegisterButton);
        regButton.setOnClickListener(v -> {
            String userName = nameView.getText().toString();
            String email = emailView.getText().toString();
            String password = passwordView.getText().toString();
            String phone = phoneView.getText().toString();
            String gender = genderView.getText().toString();

            if(userName.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty() || gender.isEmpty()){
                Toast.makeText(Register.this,"PLEASE COMPLETE ALL FIELDS!",Toast.LENGTH_LONG).show();
            }
            else{
                UserModel user = new UserModel(userName, email, password, phone, gender);
                DBs helper = new DBs(Register.this);
                List<String> allEmails = helper.getAllEmails();
                for (String Email : allEmails){
                    if (Email.equals(email)){
                        Toast.makeText(Register.this,"THIS EMAIL IS EXIST!",Toast.LENGTH_LONG).show();
                    }
                    else {
                        helper.addUser(user);
                        Toast.makeText(Register.this, "SUCCESSFULLY!", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(Register.this, SignIn.class);
                        startActivity(i);
                    }
                }
            }
        });



        //Popup Menu
        genderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(Register.this,genderView);
                popup.getMenuInflater().inflate(R.menu.pop,popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId() == R.id.Male){
                            genderView.setText("Male");
                        }
                        if(item.getItemId() == R.id.Female){
                            genderView.setText("Female");
                        }
                        return true;
                    }
                });
                popup.show();
              }
        });


        //isNotReg TextView
        TextView isReg = (TextView)findViewById(R.id.isReg);
        isReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, SignIn.class);
                startActivity(i);
            }
        });
    }
}