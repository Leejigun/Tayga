package com.dopy.dopy.tayga.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.widget.PullRefreshLayout;
import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.FragmentFavoritesBinding;
import com.dopy.dopy.tayga.model.FavoritesRcvAdapter;
import com.dopy.dopy.tayga.model.FavorityItem;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment{
    FragmentFavoritesBinding binding;
    ContainerRefresh containerRefresh;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentFavoritesBinding.bind(view);
        containerRefresh=new ContainerRefresh(binding.rotateFavoritesloading,binding.refreshLayoutFavorites);
        setUpParallaxRecyclerView();
    }

    private void setUpParallaxRecyclerView() {
        List<FavorityItem> models = inputTestData();
        containerRefresh.pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
        FavoritesRcvAdapter adapter = new FavoritesRcvAdapter(models, getContext(),containerRefresh);
        binding.rcvFavoritesFragment.setLayoutManager(new LinearLayoutManager(getContext()));
        View header = LayoutInflater.from(getContext()).inflate(R.layout.favorites_header, binding.rcvFavoritesFragment, false);
        binding.rcvFavoritesFragment.setAdapter(adapter);
    }

    private List<FavorityItem> inputTestData() {
        List<FavorityItem> models = new ArrayList<>();
        models.add(new FavorityItem());
        models.add(new FavorityItem());
        models.add(new FavorityItem());
        models.add(new FavorityItem());
        models.add(new FavorityItem());
        models.add(new FavorityItem());
        models.add(new FavorityItem());
        models.add(new FavorityItem());
        models.add(new FavorityItem());
        models.add(new FavorityItem());
        return models;
    }
}
