package com.dopy.dopy.tayga;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.bumptech.glide.Glide;
import com.dopy.dopy.tayga.databinding.FragmentMainBinding;
import com.dopy.dopy.tayga.model.BroadcastModel;
import com.dopy.dopy.tayga.model.BroadcastPagerAdapter;
import com.dopy.dopy.tayga.model.BroadcastRcvAdapter;
import com.github.pedrovgs.DraggableListener;
import com.github.pedrovgs.DraggablePanel;
import com.github.pedrovgs.DraggableView;
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

    private static final int DELAY_MILLIS = 10;

    FragmentMainBinding binding;
    View containerView;
    RecyclerView recyclerView;
    BroadcastRcvAdapter adapter;
    Toolbar toolbar;
    List<BroadcastModel> models =new ArrayList<>();
    BroadcastModel selectedModel;

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

        initializeDraggableView();
        addClickListenerAtRecyclerView();
        hookListeners();
    }

    private void setUpParallaxRecyclerView(View view){
        recyclerView=binding.rcvMainFragment;
        toolbar=getActivity().findViewById(R.id.toolbar);
        createAdapter(recyclerView);
    }
//    ParallaxRecyclerView 컨트롤
    private void createAdapter(RecyclerView recyclerView){
       adapter = new BroadcastRcvAdapter(models,getContext());
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
    /**
     * Initialize DraggableView.
     */
    private void initializeDraggableView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override public void run() {
                binding.gameDetailViewMainFragment.setVisibility(View.GONE);
                binding.gameDetailViewMainFragment.closeToRight();
            }
        }, DELAY_MILLIS);
    }
    /**
     * Initialize GridView with some injected data and configure OnItemClickListener.
     */
    private void addClickListenerAtRecyclerView (){
        adapter.setOnClickEvent(new ParallaxRecyclerAdapter.OnClickEvent() {
            @Override
            public void onClick(View view, int i) {
                Log.d("MainFragment","on Clicked : "+i+" item");
                selectedModel=models.get(i);
                switch (i%3){
                    case 0:
                        Glide.with(getActivity()).load(R.drawable.gamesnapshot).into(binding.gameDetailViewHeader);
                        break;
                    case 1:
                        Glide.with(getActivity()).load(R.drawable.gamenapshot2).into(binding.gameDetailViewHeader);
                        break;
                    case 2:
                        Glide.with(getActivity()).load(R.drawable.gamenapshot3).into(binding.gameDetailViewHeader);
                        break;
                }
                renderGameDetailBody(selectedModel);
                binding.gameDetailViewMainFragment.setVisibility(View.VISIBLE);
                binding.gameDetailViewMainFragment.maximize();
            }
        });
    }
    /**
     * Hook DraggableListener to draggableView to modify action bar title with the tv show
     * information.
     */
    private void hookListeners() {
        binding.gameDetailViewMainFragment.setDraggableListener(new DraggableListener() {
            @Override public void onMaximized() {
                Log.d("MainFragment","call onMaximized");
            }

            @Override public void onMinimized() {
                Log.d("MainFragment","call onMinimized");
            }

            @Override public void onClosedToLeft() {
                Log.d("MainFragment","call onClosedToLeft");
            }

            @Override public void onClosedToRight() {
                Log.d("MainFragment","call onClosedToRight");
            }
        });
    }
    /**
     * Configure a view as episodes ListView header with the name of the tv show and the season.
     */
    private void renderGameDetailBody(BroadcastModel model){
//        여기서 아이템에 대해서 클릭 리스너도 달고 조작


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
