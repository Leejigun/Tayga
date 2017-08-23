package com.dopy.dopy.tayga.model.twitch;

import android.util.Log;

import com.dopy.dopy.tayga.model.RefreshDoneInterface;
import com.dopy.dopy.tayga.model.TwitchListContainer;
import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.dopy.dopy.tayga.model.game.GameItem;
import com.dopy.dopy.tayga.model.game.GameItemList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.facebook.login.widget.ProfilePictureView.TAG;

/**
 * Created by Dopy on 2017-08-14.
 */

public class SearchTwitch {
    final String BASE_URL = "https://api.twitch.tv/kraken/";


    public SearchTwitch() {
    }

    public void getTwitch(int offset, int count, final List<BroadcastModel> broadcastModels, final RefreshDoneInterface refreshInterface) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TwitchService service = retrofit.create(TwitchService.class);
        try {
            Call<TwitchStreamList> videoList = (Call<TwitchStreamList>) service.searchStreamList("ko", count, offset);
            videoList.enqueue(new Callback<TwitchStreamList>() {
                @Override
                public void onResponse(Call<TwitchStreamList> call, Response<TwitchStreamList> response) {
                    int statusCode = response.code();
                    Log.d("SearchTwitch", "statusCode :" + Integer.toString(statusCode));
                    Log.d("SearchTwitch", "response.body() :" + response.body().getList());
                    TwitchStreamList datas = response.body();
                    Log.d("SearchTwitch", datas.getList().size() + " 개의 데이터가 들어왔습니다.");
                    List<TwitchStream> list = new ArrayList<>();
                    list.addAll(datas.getList());
                    broadcastModels.addAll(list);
                    refreshInterface.refreshDone();
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

    public void getListOfGame(final int offset, final String gameName, final List<BroadcastModel> list, final RefreshDoneInterface refreshDoneInterface) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TwitchService service = retrofit.create(TwitchService.class);
        try {
            final Call<TwitchStreamList> videoList = (Call<TwitchStreamList>) service.searchStreamListOfGame(gameName, 20, offset);
            videoList.enqueue(new Callback<TwitchStreamList>() {
                @Override
                public void onResponse(Call<TwitchStreamList> call, Response<TwitchStreamList> response) {
                    int statusCode = response.code();
                    Log.d("SearchTwitch", "statusCode :" + Integer.toString(statusCode));
                    TwitchStreamList datas = response.body();
                    Log.d("SearchTwitch","이런 이름의 게임 :"+gameName);
                    Log.d("SearchTwitch",videoList.request().toString());
                    Log.d("SearchTwitch", datas.getList().size() + " 개의 데이터가 들어왔습니다.");
                    list.addAll(datas.getList());
                    refreshDoneInterface.refreshDone();
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

    public void getGameList(int offset, int count, final List<BroadcastModel> broadcastModels, final RefreshDoneInterface refreshInterface) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TwitchService service = retrofit.create(TwitchService.class);
        try {
            Call<GameItemList> videoList = (Call<GameItemList>) service.searchGameList(count, offset);
            videoList.enqueue(new Callback<GameItemList>() {
                @Override
                public void onResponse(Call<GameItemList> call, Response<GameItemList> response) {
                    int statusCode = response.code();
                    Log.d("SearchTwitch", "statusCode :" + Integer.toString(statusCode));
                    Log.d("SearchTwitch", "response.body() :" + response.body().getList());
                    GameItemList datas = response.body();
                    Log.d("SearchTwitch", datas.getList().size() + " 개의 데이터가 들어왔습니다.");
                    Log.d("SearchTwitch", datas.getList().get(0).game.name);
                    List<GameItem> list = new ArrayList<>();
                    list.addAll(datas.getList());
                    TwitchListContainer twitchListContainer = new TwitchListContainer();
                    twitchListContainer.setGameItemList(list);
                    twitchListContainer.setListType(TwitchListContainer.GAME);
                    broadcastModels.add(twitchListContainer);
                    refreshInterface.refreshDone();
                }

                @Override
                public void onFailure(Call<GameItemList> call, Throwable t) {
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

    public void getFeaturedStream(int offset, int count, final List<BroadcastModel> broadcastModels, final RefreshDoneInterface refreshInterface) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TwitchService service = retrofit.create(TwitchService.class);
        Call<TwitchStream> videoList = (Call<TwitchStream>) service.serchFeaturedStream(count, offset);
        try {
            videoList.enqueue(new Callback<TwitchStream>() {
                @Override
                public void onResponse(Call<TwitchStream> call, Response<TwitchStream> response) {
                    int statusCode = response.code();
                    Log.d("SearchTwitch", "statusCode :" + Integer.toString(statusCode));
                    Log.d("SearchTwitch", "response.body().showTitle :" + response.body().showTitle());
                    TwitchStream twitchStream = response.body();
                    broadcastModels.add(twitchStream);
                    refreshInterface.refreshDone();
                }

                @Override
                public void onFailure(Call<TwitchStream> call, Throwable t) {

                }
            });
        } catch (NullPointerException e) {
            Log.d("SearchTwitch", e.getMessage());
        } catch (Exception e) {
            Log.d("SearchTwitch", e.getMessage());
        }
    }
}
