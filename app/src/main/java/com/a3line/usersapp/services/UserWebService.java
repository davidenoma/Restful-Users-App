package com.a3line.usersapp.services;

import com.a3line.usersapp.models.User;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.Converter.Factory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface UserWebService {
     final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    final String FEED = "users";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    @GET(FEED)
    Call<User[]> users();


}
