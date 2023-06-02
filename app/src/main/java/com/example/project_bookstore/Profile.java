package com.example.project_bookstore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Set info
        DBs helper = new DBs(Profile.this);
        UserModel user = helper.getUserInfoByEmail(SignIn.userEmail);
        TextView name = (TextView) findViewById(R.id.NameBox);
        TextView email = (TextView) findViewById(R.id.EmailBox);
        TextView phone = (TextView) findViewById(R.id.PhoneBox);
        TextView gender = (TextView) findViewById(R.id.GenderBox);
        name.setText(user.name);
        email.setText(user.email);
        phone.setText(user.phone);
        gender.setText(user.gender);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //logout
        Button logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this);
            builder.setTitle("Logout");
            builder.setMessage("Are you sure you want to logout ? ");
            builder.setPositiveButton("Yes", (dialog, which) -> {
                Intent i = new Intent(Profile.this,SignIn.class);
                startActivity(i);
            });
            builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
            builder.show();
        });



    }
}