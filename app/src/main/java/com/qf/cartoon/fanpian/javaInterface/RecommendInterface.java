package com.qf.cartoon.fanpian.javaInterface;

import com.qf.cartoon.fanpian.bean.RecommendEntity;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by HP on 2016/8/4.
 */
public interface RecommendInterface {
    @GET(value = "forum.php?mod=movieexplorer&v=3&androidflag=1&page=1&pdateline=")
    Call<RecommendEntity> getData();
}
