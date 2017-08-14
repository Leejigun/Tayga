package com.dopy.dopy.tayga.model.youtube;

import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.dopy.dopy.tayga.databinding.YoutubeCardviewBinding;
import com.dopy.dopy.tayga.model.BaseRcvViewHolder;

/**
 * Created by Dopy on 2017-08-13.
 */

public class YoutubeViewholder extends BaseRcvViewHolder {
    YoutubeCardviewBinding binding;
    YouTubeClickInterface youTubeClickInterface;
    public YoutubeViewholder(View itemView) {
        super(itemView);
        binding = YoutubeCardviewBinding.bind(itemView);
    }

    public YoutubeCardviewBinding getBinding() {
        return binding;
    }

    @Override
    public void bind(Object data) {
        SearchData model = (SearchData) data;
        binding.setYoutubeData(model);
        Glide.with(binding.getRoot()).load(model.snippet.thumbnails.medium.url).into(binding.imvYoutubeCard);
    }
}
