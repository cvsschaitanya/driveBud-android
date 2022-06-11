package com.example.drivebud.data.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.drivebud.data.network.api.ApiModels.HomeData;
import com.example.drivebud.data.network.api.DriveBudApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {

    private MutableLiveData<String> rangeLiveData;
    HomeRepository(){
        rangeLiveData = new MutableLiveData<>();
    }

    private static HomeRepository instance;

    public static HomeRepository getInstance(){
        if(instance==null){
            instance = new HomeRepository();
        }
        return instance;
    }

    public LiveData<String> aquireData(){
        updataData();

        return rangeLiveData;
    }

    public void updataData(){
        DriveBudApi.Service.getService().getHomeData()
                .enqueue(new Callback<HomeData>() {
                    @Override
                    public void onResponse(Call<HomeData> call, Response<HomeData> response) {
                        rangeLiveData.setValue(response.body().range);
                    }

                    @Override
                    public void onFailure(Call<HomeData> call, Throwable t) {

                    }
                });
    }

}
