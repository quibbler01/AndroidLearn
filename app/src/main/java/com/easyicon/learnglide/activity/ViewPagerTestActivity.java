package com.easyicon.learnglide.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.easyicon.learnglide.R;
import com.easyicon.learnglide.presenter.adapter.MyFragmentStatePagerAdapter;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerTestActivity extends AppCompatActivity {


    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_test);
        ButterKnife.bind(this);

        initViewPager();

        initTab();
    }

    private void initViewPager() {
        MyFragmentStatePagerAdapter fragmentStatePagerAdapter = new MyFragmentStatePagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        mViewPager.setAdapter(fragmentStatePagerAdapter);
    }

    private void initTab() {
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText("微信").setIcon(R.drawable.layout_1);
        mTabLayout.getTabAt(1).setText("通讯录").setIcon(R.drawable.layout_2);
        mTabLayout.getTabAt(2).setText("发现").setIcon(R.drawable.layout_3);
        mTabLayout.getTabAt(3).setText("我").setIcon(R.drawable.layout_4);
//        mTabLayout.getTabAt(4).setText("设置").setIcon(R.drawable.layout_5);


//        mTabLayout.addTab(mTabLayout.newTab().setText("我的").setIcon(R.drawable.layout_1));
//        mTabLayout.addTab(mTabLayout.newTab().setText("发现").setIcon(R.drawable.layout_2));
//        mTabLayout.addTab(mTabLayout.newTab().setText("圈子").setIcon(R.drawable.layout_3));
//        mTabLayout.addTab(mTabLayout.newTab().setText("消息").setIcon(R.drawable.layout_4));
//        mTabLayout.addTab(mTabLayout.newTab().setText("设置").setIcon(R.drawable.layout_5));

//        mTabLayout.addTab(mTabLayout.newTab().setText("圈子").setIcon(R.drawable.layout_3));
//        mTabLayout.addTab(mTabLayout.newTab().setText("消息").setIcon(R.drawable.layout_4));
//        mTabLayout.addTab(mTabLayout.newTab().setText("设置").setIcon(R.drawable.layout_5));
//        mTabLayout.addTab(mTabLayout.newTab().setText("圈子").setIcon(R.drawable.layout_3));
//        mTabLayout.addTab(mTabLayout.newTab().setText("消息").setIcon(R.drawable.layout_4));
//        mTabLayout.addTab(mTabLayout.newTab().setText("设置").setIcon(R.drawable.layout_5));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.setIcon(R.drawable.setting_icon_1);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int index = mTabLayout.getSelectedTabPosition();
                switch (index) {
                    case 0:
                        tab.setIcon(R.drawable.layout_1);
                        break;
                    case 1:
                        tab.setIcon(R.drawable.layout_2);
                        break;
                    case 2:
                        tab.setIcon(R.drawable.layout_3);
                        break;
                    case 3:
                        tab.setIcon(R.drawable.layout_4);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

}
