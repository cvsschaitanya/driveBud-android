package com.example.drivebud.ui.home;

import androidx.lifecycle.LiveData;

interface AquireListener{
    void onStarted();
    void onSuccess(LiveData<String> rangeLiveData);
    void onFailure();
}
