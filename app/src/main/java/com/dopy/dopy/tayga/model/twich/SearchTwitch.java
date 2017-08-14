package com.dopy.dopy.tayga.model.twich;

import android.util.Log;

import com.dopy.dopy.tayga.model.recommanded.RecommandedPagerAdapter;
import com.dopy.dopy.tayga.model.broadcast.BroadcastRcvAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dopy on 2017-08-14.
 */

public class SearchTwitch {
    final String BASE_URL = "https://api.twitch.tv/kraken/streams/";


    public SearchTwitch () {
    }

    public void getTwitch(int offset, final BroadcastRcvAdapter adapter) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TwitchService service = retrofit.create(TwitchService.class);
        try {
            Call<TwitchStreamList> videoList = (Call<TwitchStreamList>) service.searchStreamList("ko", 10, offset);
            videoList.enqueue(new Callback<TwitchStreamList>() {
                @Override
                public void onResponse(Call<TwitchStreamList> call, Response<TwitchStreamList> response) {
                    int statusCode = response.code();
                    Log.d("SearchTwitch", "statusCode :" + Integer.toString(statusCode));
                    Log.d("SearchTwitch", "response.body() :" + response.body().getList());
                    TwitchStreamList datas = response.body();
                    Log.d("SearchTwitch", datas.getList().size() + " 개의 데이터가 들어왔습니다.");
                    Log.d("SearchTwitch", datas.getList().get(0).channel.status);
                    adapter.getData().addAll(datas.getList());
//                   adapter.setData(datas.getList());
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<TwitchStreamList> call, Throwable t) {
                    Log.d("SearchTwitch", "데이터를 불러오는데 실패 했습니다.");
                    Log.d("SearchTwitch", "call:" + call.request());
                    Log.d("SearchTwitch", "Throwable:" + t.getMessage());
                }
            });
        } catch (NullPointerException e) {
            Log.d("SearchTwitch", e.getMessage());
        } catch (Exception e) {
            Log.d("SearchTwitch", e.getMessage());
        }
    }

    public void getRecommandList(int offset, final RecommandedPagerAdapter adapter) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TwitchService service = retrofit.create(TwitchService.class);
        try {
            Call<TwitchStreamList> videoList = (Call<TwitchStreamList>) service.searchStreamList("ko", 5, offset);
            videoList.enqueue(new Callback<TwitchStreamList>() {
                @Override
                public void onResponse(Call<TwitchStreamList> call, Response<TwitchStreamList> response) {
                    int statusCode = response.code();
                    Log.d("SearchTwitch", "statusCode :" + Integer.toString(statusCode));
                    Log.d("SearchTwitch", "response.body() :" + response.body());
                    TwitchStreamList datas = response.body();
                    Log.d("SearchTwitch", datas.getList().size() + " 개의 데이터가 들어왔습니다.");
                    Log.d("SearchTwitch", datas.getList().get(0).channel.status);
                    adapter.setData(datas.getList());
                }

                @Override
                public void onFailure(Call<TwitchStreamList> call, Throwable t) {
                    Log.d("SearchTwitch", "데이터를 불러오는데 실패 했습니다.");
                    Log.d("SearchTwitch", "call:" + call.request());
                    Log.d("SearchTwitch", "Throwable:" + t.getMessage());
                }
            });
        } catch (NullPointerException e) {
            Log.d("SearchTwitch", e.getMessage());
        } catch (Exception e) {
            Log.d("SearchTwitch", e.getMessage());
        }
    }
}
