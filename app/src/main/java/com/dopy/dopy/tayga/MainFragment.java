package com.dopy.dopy.tayga;


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

import com.dopy.dopy.tayga.databinding.FragmentMainBinding;
import com.dopy.dopy.tayga.model.BroadcastModel;
import com.dopy.dopy.tayga.model.BroadcastPagerAdapter;
import com.dopy.dopy.tayga.model.BroadcastRcvAdapter;
import com.imbryk.viewPager.LoopViewPager;
import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import yalantis.com.sidemenu.interfaces.ScreenShotable;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements ScreenShotable{

    FragmentMainBinding binding;
    View containerView;
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
        this.containerView = binding.containerMainFragment;
        inputTestData();
        setUpParallaxRecyclerView(view);

    }

    private void setUpParallaxRecyclerView(View view){
        recyclerView=binding.rcvMainFragment;
        toolbar=getActivity().findViewById(R.id.toolbar);
        createAdapter(recyclerView);
    }
//    ParallaxRecyclerView 컨트롤
    private void createAdapter(RecyclerView recyclerView){
        BroadcastRcvAdapter adapter = new BroadcastRcvAdapter(models,getContext());
//        해더 삽입
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        View header= cearteCircleIndicator(recyclerView);
        adapter.setParallaxHeader(header, recyclerView);
        adapter.setOnParallaxScroll(new ParallaxRecyclerAdapter.OnParallaxScroll() {
            @Override
            public void onParallaxScroll(float v, float v1, View view) { // 해더와 툴바를 연결해서 사라지게 한다.
                Log.d("MainFragment","onParallaxScroll:"+toolbar.getBackground().toString());
                Drawable c = toolbar.getBackground();
                c.setAlpha(Math.round(v * 255));
                toolbar.setBackground(c);
            }
        });
        adapter.setData(models);
        recyclerView.setAdapter(adapter);
    }

    //  리사이클러 뷰 헤더에 추가될 뷰 페이저를 만든다.
    private View cearteCircleIndicator(RecyclerView recyclerView){
        View header = LayoutInflater.from(getContext()).inflate(R.layout.header, recyclerView, false);
        LoopViewPager viewpager = (LoopViewPager) header.findViewById(R.id.viewpager);
        CircleIndicator indicator = (CircleIndicator) header.findViewById(R.id.indicator);
        viewpager.setAdapter(new BroadcastPagerAdapter(models));
        indicator.setViewPager(viewpager);
        return header;
    }
    private void inputTestData(){
        models.add(new BroadcastModel());
        models.add(new BroadcastModel());
        models.add(new BroadcastModel());
        models.add(new BroadcastModel());
        models.add(new BroadcastModel());
        models.add(new BroadcastModel());
        models.add(new BroadcastModel());
        models.add(new BroadcastModel());
        models.add(new BroadcastModel());
        models.add(new BroadcastModel());
        models.add(new BroadcastModel());

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
                MainFragment.this.bitmap = bitmap;
            }
        };

        thread.start();*/
    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }
}
