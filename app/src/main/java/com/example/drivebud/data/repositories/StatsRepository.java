package com.example.drivebud.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.drivebud.data.network.api.DriveBudApi;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StatsRepository {
    HashMap<String, Double> map;
    private static StatsRepository instance;
    private DriveBudApi service;

    public StatsRepository() {
        map = new HashMap<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://drivebud.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(DriveBudApi.class);

    }

    public static StatsRepository getInstance() {
        if (instance == null)
            instance = new StatsRepository();
        return instance;
    }

    public MutableLiveData<HashMap<String, Double>> getStats(){
        setData();

        MutableLiveData<HashMap<String, Double>> stats = new MutableLiveData<>();
        stats.setValue(map);

        return stats;
    }

    void setData() {
        map.put("KMs/unit", 6.0);
        map.put("Total KM", 250.0);
        map.put("Average Speed", 45.0);
        map.put("Harsh Acceleration", 3.0);
        map.put("Harsh Breaking Event", 4.0);
        map.put("Driving Score", 7.5);


//        Call<List<Stat>> call = service.getStats();
//
//        call.enqueue(new Callback<List<Stat>>() {
//            @Override
//            public void onResponse(Call<List<Stat>> call, Response<List<Stat>> response) {
//                List<Stat> results = response.body();
//                for(Stat S:results){
//                    map.put(S.statName, S.statValue);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Stat>> call, Throwable t) {
//
//            }
//        });


    }
}
