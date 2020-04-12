package com.easyicon.learnglide.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Placeholder;

import com.easyicon.learnglide.R;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConstraintLayoutActivity extends AppCompatActivity {
    private static final String TAG = "TAG_ConstraintLayout";
    @BindView(R.id.place_holder)
    Placeholder placeHolder;
    @BindView(R.id.text_view)
    TextView textView;
    private int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_constrant_layout);
        ButterKnife.bind(this);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged");
    }

    @OnClick(R.id.text_view)
    public void onViewClicked() {
//        placeHolder.setContentId(R.id.text_view);
        Intent intent = new Intent(this, FirstActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("data", "第" + (i++) + "次启动，timeStamp " + System.currentTimeMillis());
//        Log.d("QUIBBLER", "i = " + i);
        startActivity(intent);
    }

    private void testInput() {
        try {
            InputStream inputStream = null;
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            InputStreamReader reader1 = new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1);
            InputStreamReader reader2 = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        } catch (Exception e) {

        }
    }

}
