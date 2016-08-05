package com.qf.cartoon.fanpian.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.qf.cartoon.fanpian.R;
import com.qf.cartoon.fanpian.activity.OnlineActivity;
import com.qf.cartoon.fanpian.activity.ShortActivity;
import com.qf.cartoon.fanpian.adapter.RecommendPagerAdapter;
import com.qf.cartoon.fanpian.bean.RecommendEntity;
import com.qf.cartoon.fanpian.javaInterface.RecommendInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends Fragment {

    List<RecommendEntity.DataBean.ExplorerbannerlistBean> urls;
    ViewPager mViewPager;
    RecommendPagerAdapter mAdapter;
    List<View> mListView;


    public RecommendFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Fresco.initialize(getContext());
        View rootView = inflater.inflate(R.layout.fragment_recommend,container,false);
         mViewPager = (ViewPager) rootView.findViewById(R.id.viewpager_recomend);
        initUI();
        getData();
        return rootView;
    }


    private void initUI(){
        mListView = new ArrayList<>();
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View item = inflater.inflate(R.layout.recommend_item_viewpager,null);
        mListView.add(item);

    }

    private void getData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://morguo.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RecommendInterface recommendInterface = retrofit.create(RecommendInterface.class);

        Call<RecommendEntity> call = recommendInterface.getData();
        call.enqueue(new Callback<RecommendEntity>() {
            @Override
            public void onResponse(Call<RecommendEntity> call, Response<RecommendEntity> response) {
                RecommendEntity entity = response.body();

                urls = entity.getData().getExplorerbannerlist();
                mAdapter = new RecommendPagerAdapter(urls,getActivity());
                mViewPager.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<RecommendEntity> call, Throwable t) {

            }
        });
    }

    private void deal(){
        switch (getView().getId()){
            case R.id.first:
                Intent intent = new Intent(getActivity(), OnlineActivity.class);
                startActivity(intent);
                break;
            case R.id.second:
                Intent intent1 = new Intent(getActivity(), ShortActivity.class);
                startActivity(intent1);
                break;
            case R.id.thrid:
//                Intent intent2 = new Intent(getActivity(),AlbumActivity)
                break;
            case R.id.fourth:
                break;
        }
    }
}
