package com.dopy.dopy.tayga.model.twitch;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.GameDetailHeaderBinding;
import com.dopy.dopy.tayga.model.broadcast.BaseRcvViewHolder;

/**
 * Created by Dopy on 2017-08-15.
 */

public class GameDetailHeaderViewHolder extends BaseRcvViewHolder {
    GameDetailHeaderBinding binding;

    public GameDetailHeaderViewHolder(View itemView) {
        super(itemView);
        binding = GameDetailHeaderBinding.bind(itemView);
    }

    @Override
    public void bind(final Object data) {
        String type = data.getClass().toString();

        if (TwitchStream.class.toString().equals(type)) {
            final TwitchStream twitchStream = (TwitchStream) data;
            binding.setModel(twitchStream);
            Glide.with(itemView.getContext())
                    .load(twitchStream.channel.logo)
                    .placeholder(R.drawable.placeholder_broadcast)
                    .error(R.drawable.ic_error_outline_black_24dp)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .crossFade()
                    .into(binding.imvGameDetailLogo);
            Glide.with(itemView.getContext())
                    .load(twitchStream.preview.medium)
                    .placeholder(R.drawable.placeholder_broadcast)
                    .error(R.drawable.ic_error_outline_black_24dp)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .crossFade()
                    .into(binding.imvStreamIamge);
            binding.btnPrayButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isPackageInstalled("tv.twitch.android.app", itemView.getContext())) {
                        //installed twitch app
                        String url = ("twitch://stream/"+ (twitchStream.channel.name)+"/");
                        Log.d("GameDetailHeader","url -> "+url);
                        Intent intent = view.getContext().getPackageManager().getLaunchIntentForPackage("tv.twitch.android.app");
                        intent.setData(Uri.parse(url));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        view.getContext().startActivity(intent);
                    } else {
                        String url = "market://details?id=" + "tv.twitch.android.app";
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        itemView.getContext().startActivity(i);
                    }
                }
            });
        } else {//not a twitch app

        }
    }

    private boolean isPackageInstalled(String packagename, Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
