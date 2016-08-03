package com.qf.cartoon.fanpian.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qf.cartoon.fanpian.R;
import com.qf.cartoon.fanpian.adapter.BigAdapter;
import com.qf.cartoon.fanpian.adapter.SmallViewPager;
import com.qf.cartoon.fanpian.parse.onInterfaceListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CenterFragment extends Fragment {
    SmallViewPager center_viewPager;
    ViewPager small_viewPager;

    MyFragment myFragment;
    RecommendFragment recommendFragment;
    FindFragment findFragment;

    List<Fragment> centerList=new ArrayList<>();
    onInterfaceListener monInterfaceListener;
    public CenterFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_center, container, false);
        center_viewPager= (SmallViewPager) view.findViewById(R.id.center_viewPager);
        small_viewPager= (ViewPager) view.findViewById(R.id.samll_view_pager);
        center_viewPager.setChild_viewpager(small_viewPager);
        monInterfaceListener= (onInterfaceListener) getActivity();
        initFrgament();
        return view;
    }
    private void initFrgament() {
        myFragment=new MyFragment();
        recommendFragment=new RecommendFragment();
        findFragment=new FindFragment();
        centerList.add(myFragment);
        centerList.add(recommendFragment);
        centerList.add(findFragment);

        BigAdapter adapter=new BigAdapter(getFragmentManager(),centerList);
        small_viewPager.setAdapter(adapter);

        small_viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                monInterfaceListener.mInterface(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    public void onClick(int pos){
        switch (pos){
            case 0:
                small_viewPager.setCurrentItem(0);
                break;
            case 1:
                small_viewPager.setCurrentItem(1);
                break;
            case 2:
                small_viewPager.setCurrentItem(2);
                break;
        }
    }

}




