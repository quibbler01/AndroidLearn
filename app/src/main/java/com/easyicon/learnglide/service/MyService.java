package com.easyicon.learnglide.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.DisplayMetrics;

import com.easyicon.learnglide.service.impl.BookManagerImpl;

public class MyService extends Service {
    private BookManagerImpl bookManagerImpl = new BookManagerImpl();

    public MyService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bookManagerImpl.isServiceAlive.set(false);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return bookManagerImpl;
    }

    private void getPx() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
    }
}
