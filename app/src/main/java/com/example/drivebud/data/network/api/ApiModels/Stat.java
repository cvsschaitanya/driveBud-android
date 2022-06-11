package com.example.drivebud.data.network.api.ApiModels;

import java.io.Serializable;

public class Stat implements Serializable {
    public String statName;
    public Double statValue;

    public Stat(String statName, Double statValue) {
        this.statName = statName;
        this.statValue = statValue;
    }

    public String getStatName() {
        return statName;
    }

    public void setStatName(String statName) {
        this.statName = statName;
    }

    public Double getStatValue() {
        return statValue;
    }

    public void setStatValue(Double statValue) {
        this.statValue = statValue;
    }
}
