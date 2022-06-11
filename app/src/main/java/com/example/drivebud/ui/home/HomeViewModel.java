package com.example.drivebud.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.drivebud.data.repositories.HomeRepository;

public class HomeViewModel extends ViewModel {

    String range;

    AquireListener aquireListener;

    void aquireData(){
        aquireListener.onStarted();

        LiveData<String> rangeLiveData = HomeRepository.getInstance().aquireData();
        aquireListener.onSuccess(rangeLiveData);
    }


}