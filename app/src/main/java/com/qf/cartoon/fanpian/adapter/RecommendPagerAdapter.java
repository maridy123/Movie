package com.qf.cartoon.fanpian.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qf.cartoon.fanpian.R;
import com.qf.cartoon.fanpian.bean.RecommendEntity;

import java.util.List;
import java.util.zip.Inflater;

import retrofit2.Retrofit;

/**
 * Created by HP on 2016/8/4.
 */
public class RecommendPagerAdapter extends PagerAdapter {

    private List<View> viewList;
    private List<RecommendEntity.DataBean.ExplorerbannerlistBean> urls;
    private Context context;

    public RecommendPagerAdapter( List<RecommendEntity.DataBean.ExplorerbannerlistBean> urls, Context context) {

        this.urls = urls;
        this.context = context;
        Fresco.initialize(context);
    }

    @Override
    public int getCount() {

        return urls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.recommend_item_viewpager, null);
        SimpleDraweeView imageView = (SimpleDraweeView) view.findViewById(R.id.image_item_recommend);
        imageView.setImageURI("http://morguo.com/"+urls.get(position).getImage());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView(((View) object));
    }
}
