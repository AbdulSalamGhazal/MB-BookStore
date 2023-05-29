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
import java.util.Locale;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText name = (EditText)findViewById(R.id.name);
        EditText email = (EditText)findViewById(R.id.email);
        EditText password = (EditText)findViewById(R.id.newPassword);
        EditText phone = (EditText) findViewById(R.id.PhoneNumber);
        Button gender = (Button) findViewById(R.id.Gender);


        //Register Button
        MaterialButton regButton = (MaterialButton) findViewById(R.id.RegisterButton);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = name.getText().toString();
                String Email = email.getText().toString();
                String Password = password.getText().toString();
                String Phone = phone.getText().toString();
                String Gender = gender.getText().toString();

                if(userName.isEmpty() || Email.isEmpty() || Password.isEmpty() || Phone.isEmpty() || Gender.isEmpty()){
                    Toast.makeText(Register.this,"PLEASE COMPLETE ALL FIELDS!",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(Register.this,"SUCCESSFULLY!",Toast.LENGTH_LONG).show();

                }
            }
        });



        //Popup Menu
        gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(Register.this,gender);
                popup.getMenuInflater().inflate(R.menu.pop,popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId() == R.id.Male){
                            gender.setText("Male");
                        }
                        if(item.getItemId() == R.id.Female){
                            gender.setText("Female");
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