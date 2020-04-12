package com.easyicon.learnglide.presenter.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * ProjectName:    LearnGlide
 * Package:        com.easyicon.learnglide.presenter.adapter
 * ClassName:      MyViewPagerAdapter
 * Description:
 * Author:         61444
 * CreateDate:     2020/2/29 20:39
 */
public class MyViewPagerAdapter extends PagerAdapter {
    private List<View> mViews;

    public MyViewPagerAdapter(List<View> mViews) {
        this.mViews = mViews;
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(mViews.get(position));
        return mViews.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mViews.get(position));
    }
}
