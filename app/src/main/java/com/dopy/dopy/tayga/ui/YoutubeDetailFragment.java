package com.dopy.dopy.tayga.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.FragmentYoutubeDetailBinding;
import com.dopy.dopy.tayga.model.youtube.SearchData;

/**
 * A simple {@link Fragment} subclass.
 */
public class YoutubeDetailFragment extends Fragment {
    FragmentYoutubeDetailBinding binding;
    SearchData data;

    public YoutubeDetailFragment() {
    }

    public YoutubeDetailFragment(SearchData data) {
        this.data=data;
    }
    public static  YoutubeDetailFragment newInstance(SearchData data){
        return new YoutubeDetailFragment(data);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_youtube_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding =FragmentYoutubeDetailBinding.bind(view);
        binding.setYouTubeData(data);
        binding.notifyChange();
    }

    public void setDetailData(SearchData data){
        binding.setYouTubeData(data);
        binding.notifyChange();
    }
}
