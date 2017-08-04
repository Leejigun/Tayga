package com.dopy.dopy.tayga.mainActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dopy.dopy.tayga.R;

public class TwitchPopularBroadListFragment extends Fragment {

    public TwitchPopularBroadListFragment() {
        // Required empty public constructor
    }

    public static TwitchPopularBroadListFragment newInstance() {
        TwitchPopularBroadListFragment fragment = new TwitchPopularBroadListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_twitch_popular_broad_list, container, false);
    }
}
