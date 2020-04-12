package com.easyicon.learnglide.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.easyicon.learnglide.R;
import com.easyicon.learnglide.presenter.UserManager;
import com.easyicon.learnglide.util.MD5;
import com.easyicon.learnglide.util.MD5Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirstActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button mButton;
    @BindView(R.id.first_view)
    LinearLayout firstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        getWindow().setStatusBarColor(getColor(android.R.color.holo_orange_light));
        ButterKnife.bind(this);

        mButton.setText(String.valueOf(UserManager.sUerId));
        UserManager.sUerId = 3;

        testCache();
    }

    @OnClick({R.id.button, R.id.first_view})
    public void onViewClicked() {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, mButton, "quibbler").toBundle());
        Log.d("QUIBBLER", "onViewClicked");
    }

    private void testCache() {
        int cacheSize = 4 * /*1024 **/ 1024; // 4MiB
        LruCache<Integer, String> lruCache = new LruCache<Integer, String>(cacheSize) {
            @Override
            protected int sizeOf(Integer key, String value) {
                return value.getBytes().length;
            }
        };

//        Bitmap bitmap;
//        bitmap.getByteCount();
        String str = "sadjaskldjsakldlkasjdsfkahsdfjklsadfjkshgkljsdh";

        for (int i = 0; i < 5; ++i) {
            lruCache.put(i, str.substring(0, i + 1));
        }
//        Log.d("QUIBBLER", "" + Runtime.getRuntime().maxMemory() / 1024);
        Log.d("QUIBBLER", "size\t" + lruCache.size());
        Log.d("QUIBBLER", "maxSize\t" + lruCache.maxSize());


        LruCache<String, Bitmap> bitmapCache = new LruCache<String, Bitmap>(cacheSize) {
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
        Log.d("QUIBBLER", MD5.getMD5("关于MD5加密"));
        Log.d("QUIBBLER", MD5Util.toMD5("关于MD5加密"));

//        String str1 = null;
//        str1.getBytes();
//        startActivity(new Intent());

    }

}
