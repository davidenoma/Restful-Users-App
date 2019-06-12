package com.a3line.usersapp.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.a3line.usersapp.models.User;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;


public class UserWebServiceImpl extends IntentService {
    public static final String TAG = "UserService";
    public static final String SERVICE_MESSAGE = "userServiceMessage";
    public static final String SERVICE_PAYLOAD = "userServicePayload";

    public UserWebServiceImpl(){
        super("UserService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        UserWebService webService =
                UserWebService.retrofit.create(UserWebService.class);
        Call<User[]> call = webService.users();

        User[] users;

        try {

                users = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(TAG, "onHandleIntent: " + e.getMessage());
            return;
        }
        Intent messageIntent = new Intent(SERVICE_MESSAGE);
        messageIntent.putExtra(SERVICE_PAYLOAD,users);
        LocalBroadcastManager manager =
                LocalBroadcastManager.getInstance(getApplicationContext());
        manager.sendBroadcast(messageIntent);
    }
}
