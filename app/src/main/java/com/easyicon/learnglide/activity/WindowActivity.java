package com.easyicon.learnglide.activity;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.easyicon.learnglide.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WindowActivity extends AppCompatActivity {

    @BindView(R.id.create_window)
    Button mCreateWindow;
    @BindView(R.id.dialog_btn)
    Button mDialogBtn;
    //继承自ViewManager
    private WindowManager mWindowManager;

    private Button mCreateWindowButton;
    Button mFloatingButton;

    WindowManager.LayoutParams mLayoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window);
        ButterKnife.bind(this);

        requestPermissions(new String[]{"android.permission.SYSTEM_ALERT_WINDOW"}, 0);
        initView();
    }

    private void initView() {
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int x = (int) event.getX();
                int y = (int) event.getY();
                mLayoutParams.x = rawX;
                mLayoutParams.y = rawY;
                mWindowManager.updateViewLayout(mFloatingButton, mLayoutParams);
                break;
            }
            case MotionEvent.ACTION_UP: {
                break;
            }
            default:
                break;
        }

        return false;
    }

    public void createWindow() {
        int code = checkSelfPermission("android.permission.SYSTEM_ALERT_WINDOW");
        if (code != PackageManager.PERMISSION_GRANTED) {
            Log.e("QUIBBLER", String.valueOf(code));
            requestPermissions(new String[]{"android.permission.SYSTEM_ALERT_WINDOW"}, 0);
//            return;
        }
        mFloatingButton = new Button(this);
        mFloatingButton.setText("Button");
        mFloatingButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int rawX = (int) event.getRawX();
                int rawY = (int) event.getRawY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        break;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        int x = (int) event.getX();
                        int y = (int) event.getY();
                        mLayoutParams.x = rawX;
                        mLayoutParams.y = rawY;
                        mWindowManager.updateViewLayout(mFloatingButton, mLayoutParams);
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        break;
                    }
                    default:
                        break;
                }

                return false;
            }
        });

        mLayoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT,
                0, 0, PixelFormat.TRANSLUCENT);
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        //非常重要的type，没有就会奔溃。WindowManager.LayoutParams.TYPE_SYSTEM_ERROR
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR /*| WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY*/;
        mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        mLayoutParams.x = 100;
        mLayoutParams.y = 300;

        mWindowManager.addView(mFloatingButton, mLayoutParams);
    }

    public void createDialog() {
        Dialog dialog = new Dialog(this.getApplicationContext());
        TextView textView = new TextView(this);
        textView.setText("this is toast");
        dialog.setContentView(textView);
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ERROR ` `);
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        try {
            mWindowManager.removeView(mFloatingButton);
        } catch (IllegalArgumentException e) {
            Log.e("QUIBBLER", e.toString());
        }
        super.onDestroy();
    }

    @OnClick({R.id.create_window, R.id.dialog_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.create_window:
                createWindow();
                break;
            case R.id.dialog_btn:
                createDialog();
                break;
        }
    }
}
