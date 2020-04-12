package com.easyicon.learnglide.view;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.easyicon.learnglide.R;

import java.util.Arrays;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {
    private static final String TAG = "QUIBBLER_WIDGET";
    private static final String CLICK_ACTION = "com.easyicon.learnglide.CLICK";

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        String action = intent.getAction();
        Log.e(TAG, "NewAppWidget Action：" + action);
        if (CLICK_ACTION.equals(action)) {
            Toast.makeText(context, "点击Example", Toast.LENGTH_SHORT).show();
//            String widgetText = context.getString(R.string.appwidget_text);
//            // Construct the RemoteViews object
//            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
//            views.setTextViewText(R.id.appwidget_text, widgetText + System.currentTimeMillis());
//
//            // "窗口小部件"点击事件发送的Intent广播
//            Intent intentClick = new Intent(context, NewAppWidget.class);
//            intentClick.setAction(CLICK_ACTION);
////        intentClick.addFlags(FLAG_INCLUDE_STOPPED_PACKAGES);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intentClick, PendingIntent.FLAG_UPDATE_CURRENT);
//            views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);
//
////        context.sendBroadcast(new Intent("com.easyicon.learnglide.CLICK"));
//
//            // Instruct the widget manager to update the widget
//            AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context, NewAppWidget.class), views);
//
        }
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // "窗口小部件"点击事件发送的Intent广播
        Intent intentClick = new Intent(context, NewAppWidget.class);
        intentClick.setAction(CLICK_ACTION);
//        intentClick.addFlags(FLAG_INCLUDE_STOPPED_PACKAGES);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intentClick, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);

//        context.sendBroadcast(new Intent("com.easyicon.learnglide.CLICK"));

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        Log.e(TAG, Arrays.toString(appWidgetIds));
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

