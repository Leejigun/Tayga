package com.dopy.dopy.tayga.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.widget.PullRefreshLayout;
import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.FragmentFavoritesBinding;
import com.dopy.dopy.tayga.model.ContainerRefresh;
import com.dopy.dopy.tayga.model.MyApplication;
import com.dopy.dopy.tayga.model.User;
import com.dopy.dopy.tayga.model.broadcast.BroadcastModel;
import com.dopy.dopy.tayga.model.broadcast.BroadcastRcvAdapter;
import com.dopy.dopy.tayga.model.game.GameItem;
import com.dopy.dopy.tayga.model.twitch.TwitchStream;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment {
    FragmentFavoritesBinding binding;
    List<BroadcastModel> models;
    ContainerRefresh containerRefresh;
    User user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    BroadcastRcvAdapter adapter;

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
        containerRefresh = new ContainerRefresh(binding.rotateFavoritesloading, binding.refreshLayoutFavorites);
        models = new ArrayList<>();
        setUpFirebase();
        setUpParallaxRecyclerView();
    }

    private void setUpFirebase() {
        MyApplication myApplication = (MyApplication) getActivity().getApplication();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        user = myApplication.getUser();
    }

    private void setUpParallaxRecyclerView() {
        containerRefresh.pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFavoritesList();
            }
        });
        refreshFavoritesList();
        adapter = new BroadcastRcvAdapter(models, getActivity().getApplication(), containerRefresh);
        binding.rcvFavoritesFragment.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvFavoritesFragment.setAdapter(adapter);
    }

    private void refreshFavoritesList() {
        databaseReference.child("Favorites").child("Streamer").child(user.getUserID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Log.d("FavoritesFragment","Streamer=>"+((TwitchStream)ds.getValue(TwitchStream.class)).showTitle());
                    models.add(ds.getValue(TwitchStream.class));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("Favorites").child("Game").child(user.getUserID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Log.d("FavoritesFragment","Game ==>"+((GameItem)ds.getValue(GameItem.class)).showTitle());
                    models.add(ds.getValue(GameItem.class));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        containerRefresh.stopLoading();
    }

}
