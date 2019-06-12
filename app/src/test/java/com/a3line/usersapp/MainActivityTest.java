package com.a3line.usersapp;

import com.a3line.usersapp.models.User;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.junit.Test;
import com.a3line.usersapp.models.User;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class MainActivityTest {

    @Test
    public void okHttpRequest() {

        OkHttpClient client = new OkHttpClient();
        String url;
        Request request = new Request.Builder().url("https://jsonplaceholder.typicode.com/users").build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();User user[] = null;
        try {
           user = gson.fromJson(response.body().string(), User[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
       List<User> userList = Arrays.asList(user);
        System.out.println(userList.get(0).toString());
        assertEquals("username", userList.get(0).getUsername(), "Bret");


    }


}