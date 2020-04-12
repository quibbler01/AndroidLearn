package com.easyicon.learnglide.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.easyicon.learnglide.R;
import com.easyicon.learnglide.util.Rom;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Package:        com.easyicon.learnglide.activity
 * ClassName:      BaseGlideActivity
 * Description:    使用ButterKnife库
 * Author:         61444
 * CreateDate:     2020/2/22 20:26
 * Version:        1.0
 */
public class BaseGlideActivity extends AppCompatActivity {
    private static final String TAG = "BaseGlideActivity";

    @BindView(R.id.text_view)
    TextView mTextView;

    @BindView(R.id.button)
    Button mButton;

    private String mUrl = "http://g1.ykimg.com/0521000E5C1731E68B2F531AC20391D5";

    @BindView(R.id.image)
    ImageView mImageView;

    @BindString(R.string.app_name)
    String mAppName;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate " + System.currentTimeMillis());
        super.onCreate(savedInstanceState);
        Window window = getWindow();
//        window.setBackgroundDrawable(null);
//        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        WindowManager.LayoutParams lp = window.getAttributes();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        } else {

        }
//        window.setAttributes(lp);

        mHandler.obtainMessage(1, "").sendToTarget();

        setContentView(R.layout.activity_base_glide);

        ButterKnife.bind(this);

        Log.d(TAG, "@BindString(R.string.app_name) " + mAppName);
//        initView();

        useGlide();

        Log.e(Rom.TAG, Rom.getProp("ro.vivo.os.version"));
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume " + System.currentTimeMillis());
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

//    private void initView() {
//        mImageView = findViewById(R.id.image);
//    }

    private void useGlide() {
        Glide.with(this)
                .load(mUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(mImageView);
    }

    private void testHandler() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 1000);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background);
        bitmap.recycle();
        InputStream inputStream = new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };

    }

    public static void closeResorce(Closeable resource) {
        if (null != resource) {
            try {
                resource.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @OnClick({R.id.image, R.id.text_view, R.id.button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image:
                Toast.makeText(this, "ButterKnife点击图片", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button:
                mButton.setText("ButterKnife点击按钮");
                break;
        }
    }
}
