package com.easyicon.learnglide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "QUIBBLER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test();
    }

    private void test() {
//        DisplayMetrics dm = getResources().getDisplayMetrics();


        WindowManager wm = (WindowManager) (getSystemService(Context.WINDOW_SERVICE));
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);

        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;

        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;

        int densityDpi = dm.densityDpi;
        wm.getDefaultDisplay().getMetrics(dm);
        int mScreenHeight = dm.heightPixels;
        int mScreenWidth = dm.widthPixels;
        Log.d(TAG, "densityDpi = " + dm.densityDpi);

        Log.d(TAG, "xdpi = " + dm.xdpi + "\tydpi = " + dm.ydpi);
        Log.d(TAG, "heightPixels = " + dm.heightPixels + "\twidthPixels = " + dm.widthPixels + "\tscaledDensity = " + dm.scaledDensity + "\tdensity = " + dm.density);

        //        DisplayMetrics dm = getResources().getDisplayMetrics();
        float density = dm.density;
        Log.e(TAG, "width = " + screenWidth + "px\theight = " + screenHeight + "px");
        Log.e(TAG, "density = " + dm.density);
        Log.e(TAG, "width = " + (screenWidth / density) + "dp\theight = " + (screenHeight / density) + "pd");
    }
}
