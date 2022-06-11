package com.example.drivebud.data.network.api;

import com.example.drivebud.data.network.api.ApiModels.HomeData;
import com.example.drivebud.data.network.api.ApiModels.Stat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface DriveBudApi {

    @GET("home")
    Call<HomeData> getHomeData();

    @GET("stats")
    Call<List<Stat>> getStats();


    class Service{
        static DriveBudApi instance = null;
        public static DriveBudApi getService(){
            if(instance==null){
                instance = new Retrofit.Builder()
                        .baseUrl("https://drivebud.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(DriveBudApi.class);

            }
            return instance;
        }
    }
}

