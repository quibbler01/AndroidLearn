package com.easyicon.learnglide.util;

import android.content.Context;

/**
 * ProjectName:    LearnGlide
 * Package:        com.easyicon.learnglide.util
 * ClassName:      ThreadInfoUtil
 * Description:
 * Author:         61444
 * CreateDate:     2020/3/21 22:01
 */
public class ThreadInfoUtil {

    public static void getMainThread(Context context) {
        android.os.Process.myUid();
        android.os.Process.myPid();
        android.os.Process.myTid();

        Thread.currentThread().getId();
        context.getApplicationContext().getMainLooper().getThread().getId();


    }

}
