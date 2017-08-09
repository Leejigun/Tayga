package com.dopy.dopy.tayga;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dopy.dopy.tayga.databinding.FragmentFavoritesBinding;
import com.dopy.dopy.tayga.model.BroadcastModel;
import com.dopy.dopy.tayga.model.BroadcastPagerAdapter;
import com.dopy.dopy.tayga.model.BroadcastRcvAdapter;
import com.dopy.dopy.tayga.model.FavoritesRcvAdapter;
import com.dopy.dopy.tayga.model.FavorityItem;
import com.imbryk.viewPager.LoopViewPager;
import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import yalantis.com.sidemenu.interfaces.ScreenShotable;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment implements ScreenShotable {
    FragmentFavoritesBinding binding;
    RecyclerView recyclerView;
    Toolbar toolbar;
    List<FavorityItem> models =new ArrayList<>();

    public FavoritesFragment() {
        // Required empty public constructor
    }
    public static FavoritesFragment newInstance(){return new FavoritesFragment();}
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
        inputTestData();

        recyclerView=view.findViewById(R.id.rcvFavoritesFragment);
        toolbar=getActivity().findViewById(R.id.toolbar);
        createAdapter(recyclerView);
    }
    private void createAdapter(RecyclerView recyclerView){
        FavoritesRcvAdapter adapter = new FavoritesRcvAdapter(models,getContext());
//        해더 삽입
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        View header = LayoutInflater.from(getContext()).inflate(R.layout.favorites_header, recyclerView, false);
        adapter.setParallaxHeader(header, recyclerView);
        adapter.setOnParallaxScroll(new ParallaxRecyclerAdapter.OnParallaxScroll() {
            @Override
            public void onParallaxScroll(float v, float v1, View view) {
                Log.d("MainFragment","onParallaxScroll:"+toolbar.getBackground().toString());
                Drawable c = toolbar.getBackground();
                c.setAlpha(Math.round(v * 255));
                toolbar.setBackground(c);
            }
        });
        adapter.setData(models);
        recyclerView.setAdapter(adapter);
    }
    private void inputTestData(){
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
    }

    @Override
    public void takeScreenShot() {
    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }
}
