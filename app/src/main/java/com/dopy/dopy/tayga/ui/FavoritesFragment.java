package com.dopy.dopy.tayga.ui;


import android.graphics.Bitmap;
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

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.FragmentFavoritesBinding;
import com.dopy.dopy.tayga.model.FavoritesRcvAdapter;
import com.dopy.dopy.tayga.model.FavorityItem;

import java.util.ArrayList;
import java.util.List;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

import static com.dopy.dopy.tayga.R.id.toolbar;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment implements ScreenShotable {
    FragmentFavoritesBinding binding;

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
        setUpParallaxRecyclerView();
    }

    private void setUpParallaxRecyclerView() {
        List<FavorityItem> models = inputTestData();
        FavoritesRcvAdapter adapter = new FavoritesRcvAdapter(models, getContext());
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

    @Override
    public void takeScreenShot() {
        /*Thread thread = new Thread() {
            @Override
            public void run() {
                Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(),
                        containerView.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                containerView.draw(canvas);
                FavoritesFragment.this.bitmap = bitmap;
            }
        };

        thread.start();*/
    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }
}
