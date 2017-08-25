package com.dopy.dopy.tayga.model;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.AdItemCardviewBinding;
import com.dopy.dopy.tayga.model.broadcast.BaseRcvViewHolder;
import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.dopy.dopy.tayga.model.game.GameItem;
import com.dopy.dopy.tayga.model.twitch.TwitchStream;
import com.dopy.dopy.tayga.ui.GameDetailPageActivity;
import com.dopy.dopy.tayga.ui.ListOfGameActivity;

import org.parceler.Parcels;

/**
 * Created by Dopy on 2017-08-25.
 */

public class AdvertiseCardViewHolder extends BaseRcvViewHolder {
    private static final String TAG = "AdvertiseCardViewHolder";
    AdItemCardviewBinding binding;

    public AdvertiseCardViewHolder(View itemView) {
        super(itemView);
        binding = AdItemCardviewBinding.bind(itemView);
    }

    @Override
    public void bind(Object data) {
        AdvertiseCard advertiseCard = (AdvertiseCard)data;
        binding.txtAdCardviewTag.setText(advertiseCard.getTag());

        BroadcastModel broadcastModel = advertiseCard.getAdItem();
        String viewType = broadcastModel.getClass().toString();

//        if Twitch Stream
        if(viewType.equals(TwitchStream.class.toString())){
            final TwitchStream twitchStream =(TwitchStream)broadcastModel;
            binding.txtAdCardviewTitle.setText(twitchStream.showTitle());
            binding.txtAdCardviewViewerCount.setText(twitchStream.showViewrToString());

            Glide.with(itemView.getContext())
                    .load(twitchStream.preview.medium)
                    .placeholder(R.drawable.twitch_logo)
                    .error(R.drawable.ic_error_outline_black_24dp)
                    .dontAnimate()
                    .into(binding.imvAdCardview);

            binding.containerAdCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemView.getRootView().getContext().startActivity(new Intent(itemView.getContext(),GameDetailPageActivity.class).putExtra("GameDetailPageActivity",Parcels.wrap(twitchStream)));
                }
            });

//          if Game Item
        }else if(viewType.equals(GameItem.class.toString())){
            final GameItem gameItem = (GameItem)broadcastModel;
            Log.d(TAG,"Game item show"+gameItem.showTitle());
            binding.txtAdCardviewTitle.setText(gameItem.showTitle());
            binding.txtAdCardviewViewerCount.setText(gameItem.showViewrToString());

            Glide.with(itemView.getContext())
                    .load(gameItem.game.box.medium)
                    .placeholder(R.drawable.placeholder_broadcast)
                    .error(R.drawable.ic_error_outline_black_24dp)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .dontAnimate()
                    .into(binding.imvAdCardview);

            binding.containerAdCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemView.getRootView().getContext().startActivity(new Intent(itemView.getContext(),ListOfGameActivity.class).putExtra("ListOfGameActivity", Parcels.wrap(gameItem)));
                }
            });

        }else{
            throw new NullPointerException("뷰 타입이 존재하지 않음");
        }

    }
}
