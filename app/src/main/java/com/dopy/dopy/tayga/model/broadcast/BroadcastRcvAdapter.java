package com.dopy.dopy.tayga.model.broadcast;

import android.app.Application;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.model.TwitchListContainer;
import com.dopy.dopy.tayga.model.favorites.FavoritesCountViewHolder;
import com.dopy.dopy.tayga.model.favorites.FavoritesGameViewHolder;
import com.dopy.dopy.tayga.model.favorites.FavoritesViewPagerViewHolder;
import com.dopy.dopy.tayga.model.game.GameHeaderViewHolder;
import com.dopy.dopy.tayga.model.game.GameItem;
import com.dopy.dopy.tayga.model.game.GameItemList;
import com.dopy.dopy.tayga.model.game.GameItemListViewHolder;
import com.dopy.dopy.tayga.model.twitch.TwitchStream;
import com.dopy.dopy.tayga.model.twitch.TwitchViewHolder;

import java.util.List;

/**
 * Created by Dopy on 2017-08-08.
 */

public class BroadcastRcvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "BroadcastRcvAdapter";
    final int FAVORITES_COUNT_BOX = 100;
    final int FAVORITES_TWITCH_STREAMER = 102;
    final int FAVORITES_TWITCH_GAME = 103;
    final int TWITCH_STREAM = 201;
    final int GAME_ITEM_LIST=202;
    final int GAME_HEADER =203;


    Context context;
    List<BroadcastModel> models;

    public BroadcastRcvAdapter(List<BroadcastModel> models, Context context) {
        this.models = models;
        this.context = context;
    }

    public void setData(List<BroadcastModel> list) {
        this.models = list;
        notifyDataSetChanged();
    }

    public void restoreData(List<BroadcastModel> list) {
        this.models = list;
        notifyDataSetChanged();
    }


    public List<BroadcastModel> getData() {
        return models;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case FAVORITES_COUNT_BOX:
                return new FavoritesCountViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites_count_box, parent, false), (Application) context);
            case FAVORITES_TWITCH_STREAMER:
                return new FavoritesViewPagerViewHolder(LayoutInflater.from(context).inflate(R.layout.viewpager_streamer_layout, parent, false));
            case FAVORITES_TWITCH_GAME:
                return new FavoritesGameViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_wap_layout, parent, false));
            case TWITCH_STREAM:
                return new TwitchViewHolder(LayoutInflater.from(context).inflate(R.layout.twitch_stream_cardview, parent, false));
            case GAME_HEADER:
                return new GameHeaderViewHolder(LayoutInflater.from(context).inflate(R.layout.game_logo_header,parent,false),(Application)context);
            case GAME_ITEM_LIST:
                return new GameItemListViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_wap_layout,parent,false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        Log.d(TAG, "position => " + position + " viewType=> " + viewType);
        switch (viewType) {
            case FAVORITES_COUNT_BOX:
                ((FavoritesCountViewHolder) holder).bind(models.get(position));
                break;
            case FAVORITES_TWITCH_STREAMER:
                ((FavoritesViewPagerViewHolder) holder).bind(models.get(position));
                break;
            case FAVORITES_TWITCH_GAME:
                ((FavoritesGameViewHolder) holder).bind(models.get(position));
                break;
            case TWITCH_STREAM:
                ((TwitchViewHolder) holder).bind(models.get(position));
                break;
            case GAME_ITEM_LIST:
                ((GameItemListViewHolder)holder).bind(models.get(position));
                break;
            case GAME_HEADER:
                ((GameHeaderViewHolder)holder).bind(models.get(position));
                break;
            default:
                Log.d(TAG, "not matched item type position =>" + position);
                new NullPointerException();
        }
    }

    @Override
    public int getItemViewType(int position) {
        BroadcastModel model = models.get(position);
        String viewType = model.getClass().toString();
        if (viewType.equals(TwitchListContainer.class.toString())) {
            int listType = ((TwitchListContainer) model).getListType();
            if (listType == TwitchListContainer.STREAMER) {
                return FAVORITES_TWITCH_STREAMER; // 즐겨찾기한 스트리머의 채널 리스트를 뷰 페이저 형태로 보여준다
            } else {
                return FAVORITES_TWITCH_GAME; // 즐겨찾기 일수도 있고 그냥 게임일 수도 있지만 그리드 형태로 게임들을 보여준다.
            }
        } else if (viewType.equals(TwitchStream.class.toString())) {
            return TWITCH_STREAM; // 방송을 큰 형태로 보여준다.
        } else if (viewType.equals(GameItem.class.toString())) {
            return GAME_HEADER;
        } else if (viewType.equals(BroadcastModel.class.toString())) {
            return FAVORITES_COUNT_BOX;
        }else if(viewType.equals(GameItemList.class.toString())){
            return GAME_ITEM_LIST;
        }
        return TWITCH_STREAM;
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
