package com.easyicon.learnglide.activity;

import android.animation.ValueAnimator;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.easyicon.learnglide.R;
import com.easyicon.learnglide.ui.ScrollTextView;
import com.easyicon.learnglide.util.MyReceiver;
import com.easyicon.learnglide.view.CircleView;
import com.easyicon.learnglide.view.MyView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewActivity extends AppCompatActivity {


    @BindView(R.id.text)
    ScrollTextView mText;
    @BindView(R.id.my_view)
    MyView mMyView;
    @BindView(R.id.circle_view)
    CircleView mCircleView;

    private VelocityTracker mVelocityTracker;

    private GestureDetector mGestureDetector;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private BroadcastReceiver mBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        ButterKnife.bind(this);
        mBroadcastReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.easyicon.learnglide.CLICK");
//        registerReceiver(mBroadcastReceiver, intentFilter);

//        testScroll();

//        useAnimToScrollView();

//        resetLayoutParams();

        animator();

        mGestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });

        mGestureDetector = new GestureDetector(this, new Listener());
    }

    GestureDetector.OnGestureListener mOnGestureListener = new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    };

    @OnClick(R.id.text)
    public void onTextClicked() {
    }

    @OnClick(R.id.my_view)
    public void onMyViewClicked() {
    }

    @OnClick(R.id.circle_view)
    public void onCircleViewClicked() {
        Toast.makeText(this, "前台弹出Toast", Toast.LENGTH_SHORT).show();
    }

    private class Listener implements GestureDetector.OnGestureListener, GestureDetector.OnContextClickListener {
        @Override
        public boolean onContextClick(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    }


    private void testScroll() {
        mText.postDelayed(new Runnable() {
            @Override
            public void run() {
                mText.smoothScrollTo(200, 200);
            }
        }, 1000);
    }

    private void useAnimToScrollView() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_scroll_view);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mText.startAnimation(animation);
            }
        }, 1000);
    }

    private void animator() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1).setDuration(2000);
        //即便这里设置了回弹，但是值始终是正的，后面增长始终为正。
        valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = (float) animation.getAnimatedFraction();
                Log.d("QUIBBLER.fraction", "fraction = " + fraction);
                mText.scrollBy(0, 0 + (int) (5 * fraction));
            }
        });
        valueAnimator.start();
    }

    private void resetLayoutParams() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mText.getLayoutParams();
                params.width += 100;
                params.leftMargin += 100;
                mText.setLayoutParams(params);
                mText.requestLayout();
            }
        }, 1000);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        mVelocityTracker = VelocityTracker.obtain();
        mVelocityTracker.addMovement(event);
        mVelocityTracker.computeCurrentVelocity(1000);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_MOVE:
                int X = (int) mVelocityTracker.getXVelocity();
                int Y = (int) mVelocityTracker.getYVelocity();
                Log.e("QUIBBLER", String.format(Locale.getDefault(), "X滑动速度:%5d，Y滑动速度:%5d", X, Y));
//                mText.setText(String.format(Locale.getDefault(), "X滑动速度:%5d，Y滑动速度:%5d", X, Y));
                break;
//                if (null != mVelocityTracker) {
//                    mVelocityTracker.clear();
//                    mVelocityTracker.recycle();
//                    mVelocityTracker = null;
//                }
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
//        unregisterReceiver(mBroadcastReceiver);
    }

    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            return false;
        }
    };
}
