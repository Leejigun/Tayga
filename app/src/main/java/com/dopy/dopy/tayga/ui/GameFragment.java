package com.dopy.dopy.tayga.ui;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.FavoritesHeaderBinding;
import com.dopy.dopy.tayga.databinding.FragmentGameBinding;
import com.dopy.dopy.tayga.model.game.GameItem;
import com.dopy.dopy.tayga.model.game.GameRcvAdapter;

import java.util.ArrayList;
import java.util.List;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

import static com.dopy.dopy.tayga.R.id.toolbar;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment implements ScreenShotable {
    FragmentGameBinding binding;

    public GameFragment() {
        // Required empty public constructor
    }
    public static GameFragment newInstance(){
        return new GameFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentGameBinding.bind(view);
        setUpParallaxRecyclerView();
    }

    private void setUpParallaxRecyclerView() {
        List<GameItem> gameItemList = inputGameCategory();
        GameRcvAdapter adapter = new GameRcvAdapter(getContext(),gameItemList);
        binding.rcvGameFragment.setLayoutManager(new GridLayoutManager(getContext(),2));
        View header = LayoutInflater.from(getContext()).inflate(R.layout.favorites_header,binding.rcvGameFragment,false);
        binding.rcvGameFragment.setAdapter(adapter);
    }
    public List<GameItem> inputGameCategory(){
        List<GameItem> gameItems = new ArrayList<>();
        gameItems.add(new GameItem(GameItem.HEARTHSTONE));
        gameItems.add(new GameItem(GameItem.OVERWATCH));
        gameItems.add(new GameItem(GameItem.BATTLE_GROUND));
        gameItems.add(new GameItem(GameItem.LOL));
        gameItems.add(new GameItem(GameItem.HOS));
        gameItems.add(new GameItem(GameItem.DOTA2));
        gameItems.add(new GameItem(GameItem.MINECRAFT));
        gameItems.add(new GameItem(GameItem.WOW));
        return gameItems;
    }

    @Override
    public void takeScreenShot() {

    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }
}
