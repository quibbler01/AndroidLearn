package com.easyicon.learnglide.presenter.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.easyicon.learnglide.ui.fragment.BlankFragment;
import com.easyicon.learnglide.ui.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName:    LearnGlide
 * Package:        com.easyicon.learnglide.presenter.adapter
 * ClassName:      MyFragmentStatePagerAdapter
 * Description:
 * Author:         61444
 * CreateDate:     2020/2/29 21:36
 */
public class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments;

    public MyFragmentStatePagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        init();
    }

    private void init() {
        mFragments = new ArrayList<>();
        mFragments.add(new MyFragment());
        mFragments.add(new BlankFragment());
        mFragments.add(new MyFragment());
        mFragments.add(new BlankFragment());
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
