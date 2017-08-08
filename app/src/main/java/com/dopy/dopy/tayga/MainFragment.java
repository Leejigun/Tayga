package com.dopy.dopy.tayga;


import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dopy.dopy.tayga.databinding.FragmentMainBinding;
import com.dopy.dopy.tayga.model.BroadcastModel;
import com.dopy.dopy.tayga.model.BroadcastPagerAdapter;
import com.dopy.dopy.tayga.model.BroadcastRcvAdapter;
import com.poliveira.parallaxrecyclerview.HeaderLayoutManagerFixed;
import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import yalantis.com.sidemenu.interfaces.ScreenShotable;

import static android.R.id.content;
import static android.R.id.input;



/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements ScreenShotable{
    public static final String CLOSE = "Close";
    public static final String BUILDING = "Building";
    public static final String BOOK = "Book";

    FragmentMainBinding binding;
    RecyclerView recyclerView;
    Toolbar toolbar;
    List<BroadcastModel> models =new ArrayList<>();
    public MainFragment() {
        // Required empty public constructor
    }
    public static MainFragment newInstance(){
        return new MainFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding=FragmentMainBinding.bind(view);
        inputTestData();

        recyclerView=view.findViewById(R.id.rcvMainFragment);
        toolbar=getActivity().findViewById(R.id.toolbar);
        createAdapter(recyclerView);
    }

    private void setUpHeader(View view){
        ViewPager viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        viewpager.setAdapter(new BroadcastPagerAdapter(models));
        indicator.setViewPager(viewpager);
    }
//    ParallaxRecyclerView 컨트롤
    private void createAdapter(RecyclerView recyclerView){
        BroadcastRcvAdapter adapter = new BroadcastRcvAdapter(models,getContext());
//        해더 삽입
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        View header = LayoutInflater.from(getContext()).inflate(R.layout.header, recyclerView, false);
        setUpHeader(header);
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
        models.add(new BroadcastModel(102));
        models.add(new BroadcastModel(102));
        models.add(new BroadcastModel(102));
        models.add(new BroadcastModel(102));
        models.add(new BroadcastModel(102));
        models.add(new BroadcastModel(102));
    }

    @Override
    public void takeScreenShot() {

    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }
}
