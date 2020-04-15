package com.easyicon.learnglide.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * ProjectName:    LearnGlide
 * Package:        com.easyicon.learnglide.view
 * ClassName:      MyDrawable
 * Description:
 * Author:         61444
 * CreateDate:     2020/4/14 23:57
 */
public class MyDrawable extends Drawable {
    Paint mPaint;

    public MyDrawable() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
    }

    public MyDrawable(int color) {
        mPaint = new Paint();
        mPaint.setColor(color);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        final Rect r = getBounds();
        float cx = r.exactCenterX();
        float cy = r.exactCenterY();
        canvas.drawCircle(cx, cy, Math.min(cx, cy), mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
        invalidateSelf();
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
