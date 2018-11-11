package com.example.asus.weektwo1;


import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.asus.weektwo1.fragment.FragmentMyData;
import com.example.asus.weektwo1.fragment.FragmentMyName;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mVpFragment;
    private TextView mTxtMyData;
    private TextView mTxtMyName;
    private List<Fragment> mFragmentList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        初始化控件
        mVpFragment = findViewById(R.id.vp_fragment);//vp
        mTxtMyData = findViewById(R.id.txt_mydata);//我的数据
        mTxtMyName = findViewById(R.id.txt_myname);//我的名片

        //设置底部按钮默认背景色
        mTxtMyData.setBackgroundColor(Color.GRAY);
        mTxtMyName.setBackgroundColor(Color.WHITE);
        setVPFragmentQ();
//点击底部我的数据  和  我的名片的点击
        mTxtMyData.setOnClickListener(this);
        mTxtMyName.setOnClickListener(this);

        //3.1 为vp设置适配器
        mVpFragment.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        });

        //3.2 为vp设置改变事件
        mVpFragment.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //3.2.1 创建一个当底部按钮改变时,按钮底部背景色同步改变的方法
                setColorChanger(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setVPFragmentQ() {
        mFragmentList = new ArrayList<>();
        //初始化Fragment
        FragmentMyData fragmentMyData = new FragmentMyData();
        FragmentMyName fragmentMyName = new FragmentMyName();
        //将fragment加入到list
        mFragmentList.add(fragmentMyData);
        mFragmentList.add(fragmentMyName);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txt_mydata://我的数据
                mVpFragment.setCurrentItem(0);
                setColorChanger(0);
                break;

            case R.id.txt_myname://我的名片
                mVpFragment.setCurrentItem(1);
                setColorChanger(1);
                break;
        }
    }
    private void setColorChanger(int position) {
        mTxtMyData.setBackgroundColor(position==0? Color.GRAY:Color.WHITE);
        mTxtMyName.setBackgroundColor(position==1? Color.GRAY:Color.WHITE);
    }
}
