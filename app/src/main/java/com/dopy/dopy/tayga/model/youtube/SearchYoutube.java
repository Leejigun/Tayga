package com.dopy.dopy.tayga.model.youtube;

import android.os.AsyncTask;
import android.util.Log;

import com.dopy.dopy.tayga.model.RefreshDoneInterface;
import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.id.list;

/**
 * Created by Dopy on 2017-08-12.
 */

public class SearchYoutube{
    final String BASE_URL = "https://www.googleapis.com/youtube/v3/";
    final String SERVICE_KEY = "AIzaSyAq5tNlmeyHl9iklgX_OOwmFYPlN6acfUw"; //콘솔에서 받아온 서버키를 넣어줍니다

    public SearchYoutube() {
    }

    public void getUtube(String tag, int count, final List<BroadcastModel>models , final RefreshDoneInterface refreshDoneInterface) {
        Log.d("SearchYoutube","call getUtube");
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
                    YoutubeList youtubeList = response.body();
                    Log.d("SearchYoutube", "youtubeList size:" + response.body().getList().size());
                    models.addAll(youtubeList.getList());
                    refreshDoneInterface.refreshDone();
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
    }
}
