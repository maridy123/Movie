package com.qf.cartoon.fanpian.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qf.cartoon.fanpian.R;
import com.qf.cartoon.fanpian.adapter.BigAdapter;
import com.qf.cartoon.fanpian.fragment.CenterFragment;
import com.qf.cartoon.fanpian.fragment.SearchFragment;
import com.qf.cartoon.fanpian.fragment.UserFragment;
import com.qf.cartoon.fanpian.parse.onInterfaceListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements onInterfaceListener {

    //    @BindView(R.id.big_viewpager)
    ViewPager big_viewpager;
    RelativeLayout relativelayout;
    List<Fragment> fragmentlist = new ArrayList<>();
    CenterFragment centerFragment;
    TextView my_text_view;
    TextView recommend_text_view;
    TextView find_text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        big_viewpager = (ViewPager) findViewById(R.id.big_viewpager);
        my_text_view = (TextView) findViewById(R.id.my_text_view);
        recommend_text_view = (TextView) findViewById(R.id.recommend_text_view);
        find_text_view = (TextView) findViewById(R.id.find_text_view);
        relativelayout= (RelativeLayout) findViewById(R.id.relativelayout);

        my_text_view.setTextColor(Color.RED);
        initFragment();
    }

    private void initFragment() {
        fragmentlist.add(new UserFragment());
        centerFragment = new CenterFragment();
        fragmentlist.add(centerFragment);
        fragmentlist.add(new SearchFragment());
        BigAdapter adapter = new BigAdapter(getSupportFragmentManager(), fragmentlist);
        big_viewpager.setAdapter(adapter);
        big_viewpager.setCurrentItem(1);
        big_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 1) {
                    relativelayout.setAlpha(1 - positionOffset);
                }else if(position==0){
                    relativelayout.setAlpha(positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        my_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                centerFragment.onClick(0);
//                Intent intent =new Intent();
//                intent.setAction("ok");
//                sendBroadcast(intent);
            }
        });

        recommend_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                centerFragment.onClick(1);
            }
        });

        find_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                centerFragment.onClick(2);
            }
        });
    }

    @Override
    public void mInterface(int pos) {
        switch (pos) {
            case 0:
                my_text_view.setTextColor(Color.RED);
                recommend_text_view.setTextColor(Color.GRAY);
                find_text_view.setTextColor(Color.GRAY);
                break;
            case 1:
                my_text_view.setTextColor(Color.GRAY);
                recommend_text_view.setTextColor(Color.RED);
                find_text_view.setTextColor(Color.GRAY);
                break;
            case 2:
                my_text_view.setTextColor(Color.GRAY);
                recommend_text_view.setTextColor(Color.GRAY);
                find_text_view.setTextColor(Color.RED);
                break;
        }
    }

}
