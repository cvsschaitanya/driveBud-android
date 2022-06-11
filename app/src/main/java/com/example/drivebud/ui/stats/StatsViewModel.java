package com.example.drivebud.ui.stats;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.drivebud.data.repositories.StatsRepository;

import java.util.HashMap;

public class StatsViewModel extends ViewModel {

    private MutableLiveData<HashMap<String, Double>> stats_data;

    public StatsViewModel() {
//        this.stats_data = new MutableLiveData<>();
    }

    public LiveData<HashMap<String, Double>> getStats() {
        loadStats();
        return stats_data;
    }

    public void loadStats() {
        if(stats_data==null){
            this.stats_data = StatsRepository.getInstance().getStats();
        }
    }
}