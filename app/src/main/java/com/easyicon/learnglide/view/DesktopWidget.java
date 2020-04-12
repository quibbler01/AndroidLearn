package com.easyicon.learnglide.view;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.easyicon.learnglide.R;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * ProjectName:    LearnGlide
 * Package:        com.easyicon.learnglide.view
 * ClassName:      DesktopWidget
 * Description:
 * Author:         61444
 * CreateDate:     2020/4/10 22:42
 */
public class DesktopWidget extends AppWidgetProvider {
    private static final String TAG = "QUIBBLER_DesktopWidget";
    private static final String CLICK_ACTION = "com.easyicon.learnglide.desktopWidget.CLICK";

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        String action = intent.getAction();
        Log.e(TAG, "Action：" + action);
        if (CLICK_ACTION.equals(action)) {
            Toast toast = Toast.makeText(context, null, Toast.LENGTH_SHORT);
            toast.setText("后台Toast");
            toast.show();
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.desktop_view);

            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy HH:mm:ss", Locale.CHINESE);

            remoteViews.setTextViewText(R.id.desktop_text, "当前时间:" + simpleDateFormat.format(date));

            // "窗口小部件"点击事件发送的Intent广播
            Intent intentClick = new Intent(context, DesktopWidget.class);//显示意图
            intentClick.setAction(CLICK_ACTION);
            //intentClick.setPackage(context.getPackageName());//隐式意图必须设置Package，实际测试发现，如果使用隐式意图，在应用被杀掉时不响应广播
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intentClick, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.desktop_text, pendingIntent);

            AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context, DesktopWidget.class), remoteViews);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.e(TAG, Arrays.toString(appWidgetIds));
        for (int appWidgetId : appWidgetIds) {
            onWidgetUpdate(context, appWidgetManager, appWidgetId);
        }
    }

    private void onWidgetUpdate(Context context, AppWidgetManager appWidgetManager, int appWidgetID) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.desktop_view);


        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy HH:mm:ss", Locale.CHINESE);
        remoteViews.setTextViewText(R.id.desktop_text, "当前时间:" + simpleDateFormat.format(date));

        // "窗口小部件"点击事件发送的Intent广播
        Intent intentClick = new Intent(context, DesktopWidget.class);//显示意图
        intentClick.setAction(CLICK_ACTION);
        //intentClick.setPackage(context.getPackageName());//隐式意图必须设置Package，实际测试发现，如果使用隐式意图，在应用被杀掉时不响应广播
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intentClick, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.desktop_text, pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetID, remoteViews);
    }

    @Override
    public void onEnabled(Context context) {

    }

    @Override
    public void onDisabled(Context context) {

    }
}
