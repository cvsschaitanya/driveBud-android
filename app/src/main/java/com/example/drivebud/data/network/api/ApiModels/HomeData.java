package com.example.drivebud.data.network.api.ApiModels;

public class HomeData {
    public String battery, range;

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public HomeData(String battery, String range) {
        this.battery = battery;
        this.range = range;
    }
}
