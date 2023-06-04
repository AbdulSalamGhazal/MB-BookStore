package com.example.project_bookstore;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import java.util.List;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText nameView = findViewById(R.id.name);
        EditText emailView = findViewById(R.id.email);
        EditText passwordView = findViewById(R.id.newPassword);
        EditText phoneView = findViewById(R.id.PhoneNumber);
        Button genderView = findViewById(R.id.Gender);


        //Register Button
        MaterialButton regButton = findViewById(R.id.RegisterButton);
        regButton.setOnClickListener(v -> {
            String userName = nameView.getText().toString();
            String userEmail = emailView.getText().toString();
            String password = passwordView.getText().toString();
            String phone = phoneView.getText().toString();
            String gender = genderView.getText().toString();

            if(userName.isEmpty() || userEmail.isEmpty() || password.isEmpty() || phone.isEmpty() || gender.isEmpty()){
                Toast.makeText(Register.this,"PLEASE COMPLETE ALL FIELDS!",Toast.LENGTH_LONG).show();
            }
            else{
                UserModel user = new UserModel(userName, userEmail, password, phone, gender);
                DBs helper = new DBs(Register.this);
                List<String> allEmails = helper.getAllEmails();

//                boolean found = false;
//                for (String email : allEmails){
//                    if (email.equals(userEmail)){
//                        Toast.makeText(Register.this,"THIS EMAIL IS EXIST!",Toast.LENGTH_LONG).show();
//                        found = true;
//                    }
//                }
                if (!allEmails.contains(userEmail)) {
                    helper.addUser(user);
                    Toast.makeText(Register.this, "SUCCESSFULLY!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Register.this, SignIn.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(Register.this,"THIS EMAIL IS EXIST!",Toast.LENGTH_LONG).show();
                }
            }
        });



        //Popup Menu
        genderView.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(Register.this,genderView);
            popup.getMenuInflater().inflate(R.menu.pop,popup.getMenu());
            popup.setOnMenuItemClickListener(item -> {
                if(item.getItemId() == R.id.Male){
                    genderView.setText("Male");
                }
                if(item.getItemId() == R.id.Female){
                    genderView.setText("Female");
                }
                return true;
            });
            popup.show();
          });


        //isNotReg TextView
        TextView isReg = findViewById(R.id.isReg);
        isReg.setOnClickListener(v -> {
            Intent i = new Intent(Register.this, SignIn.class);
            startActivity(i);
        });
    }
}