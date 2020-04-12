package com.easyicon.learnglide;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Process;
import android.util.DisplayMetrics;
import android.util.Log;

import com.easyicon.learnglide.util.Constant;
import com.easyicon.learnglide.util.Utils;

import java.util.Locale;

/**
 * ProjectName:    LearnGlide
 * Package:        com.easyicon.learnglide
 * ClassName:      MyApplication
 * Description:
 * Author:         61444
 * CreateDate:     2020/3/10 23:14
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        String processName = Utils.getProcessName(getApplicationContext(), Process.myPid());
        Log.d(Constant.TAG, "new application start,process name " + processName);
    }

    private void changeLanguage() {
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        config.setLocale(Locale.JAPAN);
        resources.updateConfiguration(config, dm);
    }
}
