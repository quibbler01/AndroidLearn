package com.easyicon.learnglide.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String action = intent.getAction();
        if (null != action && action.equals("com.easyicon.learnglide.CLICK")) {
            Log.e("QUIBBLER_WIDGET", "MyReceiver收到：" + action);
        }
    }
}
