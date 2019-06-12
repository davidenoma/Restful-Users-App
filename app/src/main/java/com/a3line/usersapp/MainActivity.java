package com.a3line.usersapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.a3line.usersapp.models.User;
import com.a3line.usersapp.presenter.UserRecyclerAdapter;
import com.a3line.usersapp.services.UserServiceOkHttp;
import com.a3line.usersapp.utils.NetworkHelper;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<User> mUsers;
    RecyclerView mRecyclerView;
    UserRecyclerAdapter mUserRecyclerAdapter;
    boolean networkOk;

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

             User[] users = (User[])intent.getParcelableArrayExtra(UserServiceOkHttp.MY_SERVICE_PAYLOAD);
            Toast.makeText(MainActivity.this,
                    "Received " + users.length + " items from service",
                    Toast.LENGTH_SHORT).show();
            mUsers = Arrays.asList(users);

            displayData();
        }
    };



    private void displayData() {

        if (mUsers != null) {
            mUserRecyclerAdapter = new UserRecyclerAdapter(this, mUsers);
            mRecyclerView = findViewById(R.id.user_content_list);
            mRecyclerView.setAdapter(mUserRecyclerAdapter);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        networkOk = NetworkHelper.hasNetworkAccess(this);
        if(networkOk){
            Intent intent = new Intent(this, UserServiceOkHttp.class);
            startService(intent);

        }else {
            Toast.makeText(this, "Network not available", Toast.LENGTH_SHORT).show();
        }
        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(mBroadcastReceiver,
                        new IntentFilter(UserServiceOkHttp.MY_SERVICE_MESSAGE));

       requestData();

    }

    private void requestData() {
        Intent intent = new Intent(this, UserServiceOkHttp.class);
        startService(intent);

    }

}
