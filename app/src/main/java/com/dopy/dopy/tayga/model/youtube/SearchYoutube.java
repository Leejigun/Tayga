package com.dopy.dopy.tayga.model.youtube;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dopy on 2017-08-12.
 */

public class SearchYoutube{
    final String BASE_URL = "https://www.googleapis.com/youtube/v3/";
    final String SERVICE_KEY = "AIzaSyAzd4t2_efKUvoyzM0X49ckFYGLe9s-IjI"; //콘솔에서 받아온 서버키를 넣어줍니다

    List<SearchData> list;
    YoutubeRcvAdapter adapter;
    public SearchYoutube(List<SearchData> list,YoutubeRcvAdapter adapter) {
        this.list=list;
        this.adapter=adapter;
    }

    public YoutubeList getUtube(String tag, int count) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        YoutubeService service = retrofit.create(YoutubeService.class);
        try {
            Call<YoutubeList> videoList = (Call<YoutubeList>) service.searchVideoList("snippet", tag, SERVICE_KEY, count,"video");
            videoList.enqueue(new Callback<YoutubeList>() {
                @Override
                public void onResponse(Call<YoutubeList> call, Response<YoutubeList> response) {
                    int statusCode = response.code();
                    Log.d("SearchYoutube", "statusCode :" + Integer.toString(statusCode));
                    Log.d("SearchYoutube", "response.body() :" + response.body());
                    YoutubeList datas = response.body();
                    Log.d("SearchYoutube", datas.getList().size() + " 개의 데이터가 들어왔습니다.");
                    Log.d("SearchYoutube", datas.getList().get(0).snippet.title);
                    Log.d(this.getClass().toString(),"before setData");
                    adapter.setData(datas.getList());
                    Log.d(this.getClass().toString(),"before notifyDataSetChanged");
                    adapter.notifyDataSetChanged();
                    Log.d(this.getClass().toString(),"end notifyDataSetChanged");
                }

                @Override
                public void onFailure(Call<YoutubeList> call, Throwable t) {
                    Log.d("SearchYoutube", "데이터를 불러오는데 실패 했습니다.");
                    Log.d("SearchYoutube", "call:" + call.request());
                    Log.d("SearchYoutube", "Throwable:" + t.getMessage());
                }
            });
        }catch (NullPointerException e){
            Log.d("SearchYoutube", e.getMessage());
        }
        catch (Exception e) {
            Log.d("SearchYoutube", e.getMessage());
        }
        return null;
    }
}
