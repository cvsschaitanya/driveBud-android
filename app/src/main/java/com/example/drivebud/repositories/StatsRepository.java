package com.example.drivebud.repositories;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;

public class StatsRepository {
    HashMap<String, Double> map;
    private static StatsRepository instance;

    public StatsRepository() {
        map = new HashMap<>();
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

    }
}
