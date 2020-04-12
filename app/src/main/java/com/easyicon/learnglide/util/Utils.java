package com.easyicon.learnglide.util;

import android.app.ActivityManager;
import android.content.Context;
import android.util.DisplayMetrics;

import java.util.List;

/**
 * ProjectName:    LearnGlide
 * Package:        com.easyicon.learnglide.util
 * ClassName:      Utils
 * Description:
 * Author:         61444
 * CreateDate:     2020/2/25 21:52
 */
public class Utils {
    private void getPx(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
    }

    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }

}
