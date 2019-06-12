package com.a3line.usersapp.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.a3line.usersapp.models.User;
import com.a3line.usersapp.utils.HttpHelper;
import com.a3line.usersapp.utils.RequestPackage;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class UserServiceOkHttp extends IntentService {
    public static final String TAG = "UserService";
    public static final String MY_SERVICE_MESSAGE = "serviceMessage";
    public static final String MY_SERVICE_PAYLOAD = "servicePayload";
    public static final String REQUEST_PACKAGE = "requestPackage";

    public UserServiceOkHttp() {
        super("UserService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        OkHttpClient client = new OkHttpClient();
        String url;
        Request request = new Request.Builder().url("https://jsonplaceholder.typicode.com/users").build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        User users[] = null;
        try {
            users = gson.fromJson(response.body().string(), User[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }





        Intent messageIntent = new Intent(MY_SERVICE_MESSAGE);
        messageIntent.putExtra(MY_SERVICE_PAYLOAD, users);
        LocalBroadcastManager manager =
                LocalBroadcastManager.getInstance(getApplicationContext());
        manager.sendBroadcast(messageIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }


}
