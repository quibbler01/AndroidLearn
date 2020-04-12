package com.easyicon.learnglide.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.easyicon.learnglide.R;
import com.easyicon.learnglide.presenter.UserManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondActivity extends AppCompatActivity {

    @BindView(R.id.second_view)
    LinearLayout secondView;
    @BindView(R.id.button)
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置contentFeature,可使用切换动画
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//        Transition explode = TransitionInflater.from(this).inflateTransition(R.transition.explode);
//        getWindow().setEnterTransition(explode);

        setContentView(R.layout.activity_second);

        getWindow().setStatusBarColor(getColor(android.R.color.holo_blue_bright));

        ButterKnife.bind(this);

        mButton.setText(String.valueOf(UserManager.sUerId));
    }

}
