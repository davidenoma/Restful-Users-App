package com.a3line.usersapp.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.a3line.usersapp.MainActivity;
import  com.a3line.usersapp.models.User;

import com.a3line.usersapp.R;

public class RegisterUser extends AppCompatActivity {
    private User mUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
    }

    public void loadCamera(View view) {
    }

    public void createUser(View view) {
       EditText fullName =      findViewById(R.id.text_full_name);
        EditText userName =  findViewById(R.id.text_user_name);
        EditText email =     findViewById(R.id.text_email);
        int id;
        User newUser = new User();
        newUser.setId(MainActivity.mUsers.size() + 1);
        mUser.setName(fullName.getText().toString());
        mUser.setUsername(userName.getText().toString());
        mUser.setEmail(email.getText().toString());

        MainActivity.mUsers.add(newUser);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);




    }
}
