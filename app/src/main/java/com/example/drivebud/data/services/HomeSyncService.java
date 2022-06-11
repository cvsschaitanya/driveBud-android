package com.example.drivebud.data.services;

import static android.app.Service.START_STICKY;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.drivebud.data.repositories.HomeRepository;

public class HomeSyncService extends Service {

    private Handler handler;
    public static final long POLL_INTERVAL = 10;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                HomeRepository.getInstance().updataData();
                handler.postDelayed(this, POLL_INTERVAL);
            }
        });

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        stopSelf();
        super.onDestroy();
    }
}
