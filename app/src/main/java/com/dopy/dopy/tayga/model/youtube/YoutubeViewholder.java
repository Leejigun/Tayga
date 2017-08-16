package com.dopy.dopy.tayga.model.youtube;

import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.YoutubeCardviewBinding;
import com.dopy.dopy.tayga.model.BaseRcvViewHolder;


/**
 * Created by Dopy on 2017-08-13.
 */

public class YoutubeViewholder extends BaseRcvViewHolder{
    YoutubeCardviewBinding binding;
    SetOnclickYoutubePlay youTubeClickInterface;
    public YoutubeViewholder(View itemView) {
        super(itemView);
        binding = YoutubeCardviewBinding.bind(itemView);
    }

    public YoutubeCardviewBinding getBinding() {
        return binding;
    }

    @Override
    public void bind(final Object data) {
        SearchData model = (SearchData) data;
        binding.setYoutubeData(model);
        Glide.with(itemView.getContext())
                .load(model.snippet.thumbnails.medium.url)
                .placeholder(R.drawable.placeholder_broadcast)
                .error(R.drawable.ic_error_outline_black_24dp)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .crossFade()
                .into(binding.imvYoutubeCard);
    }
}
