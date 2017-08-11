package com.dopy.dopy.tayga;


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
import android.widget.Toast;

import com.dopy.dopy.tayga.databinding.FragmentMainBinding;
import com.dopy.dopy.tayga.model.BroadcastModel;
import com.dopy.dopy.tayga.model.BroadcastPagerAdapter;
import com.dopy.dopy.tayga.model.BroadcastRcvAdapter;
import com.github.pedrovgs.DraggableListener;
import com.imbryk.viewPager.LoopViewPager;
import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import yalantis.com.sidemenu.interfaces.ScreenShotable;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements ScreenShotable {

    private static final int DELAY_MILLIS = 10;

    FragmentMainBinding binding;
    View containerView;
    RecyclerView recyclerView;
    BroadcastRcvAdapter adapter;
    Toolbar toolbar;
    List<BroadcastModel> models;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
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
        binding = FragmentMainBinding.bind(view);
        this.containerView = binding.containerMainFragment;
        models = inputTestData();
        setUpParallaxRecyclerView(view);

        initializeDraggableView();
        addClickListenerAtRecyclerView();
        hookListeners();
        binding.gameDetailViewHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "state: " + binding.gameDetailViewHeader.getVisibility(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setUpParallaxRecyclerView(View view) {
        recyclerView = binding.rcvMainFragment;
        toolbar = getActivity().findViewById(R.id.toolbar);
        createAdapter(recyclerView);
    }

    //    ParallaxRecyclerView 컨트롤
    private void createAdapter(RecyclerView recyclerView) {
        adapter = new BroadcastRcvAdapter(models, getContext());
//        해더 삽입
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        View header = cearteCircleIndicator(recyclerView);
        adapter.setParallaxHeader(header, recyclerView);
        adapter.setOnParallaxScroll(new ParallaxRecyclerAdapter.OnParallaxScroll() {
            @Override
            public void onParallaxScroll(float v, float v1, View view) { // 해더와 툴바를 연결해서 사라지게 한다.
                Log.d("MainFragment", "onParallaxScroll:" + toolbar.getBackground().toString());
                Drawable c = toolbar.getBackground();
                c.setAlpha(Math.round(v * 255));
                toolbar.setBackground(c);
            }
        });
//        adapter.setData(models);
        recyclerView.setAdapter(adapter);
    }

    //  리사이클러 뷰 헤더에 추가될 뷰 페이저를 만든다.
    private View cearteCircleIndicator(RecyclerView recyclerView) {
        View header = LayoutInflater.from(getContext()).inflate(R.layout.header, recyclerView, false);
        LoopViewPager viewpager = (LoopViewPager) header.findViewById(R.id.viewpager);
        CircleIndicator indicator = (CircleIndicator) header.findViewById(R.id.indicator);
        List<BroadcastModel> advisorModels = inputTestData();
        viewpager.setAdapter(new BroadcastPagerAdapter(advisorModels));
        indicator.setViewPager(viewpager);
        return header;
    }

    private List<BroadcastModel> inputTestData() {
        List<BroadcastModel> models = new ArrayList<>();
        models.add(new BroadcastModel("하스스톤 신규 확장팩 얼어붙은 왕좌의 기사들"));
        models.add(new BroadcastModel("켠김에 왕까지 배틀그라운드 딩셉션과 함께"));
        models.add(new BroadcastModel("오버워치 첼린저 미로의 고급 시계 교육방송"));
        models.add(new BroadcastModel("하스스톤 신규 확장팩 얼어붙은 왕좌의 기사들"));
        models.add(new BroadcastModel("켠김에 왕까지 배틀그라운드 딩셉션과 함께"));
        models.add(new BroadcastModel("오버워치 첼린저 미로의 고급 시계 교육방송"));
        models.add(new BroadcastModel("하스스톤 신규 확장팩 얼어붙은 왕좌의 기사들"));
        models.add(new BroadcastModel("켠김에 왕까지 배틀그라운드 딩셉션과 함께"));
        models.add(new BroadcastModel("오버워치 첼린저 미로의 고급 시계 교육방송"));
        models.add(new BroadcastModel("하스스톤 신규 확장팩 얼어붙은 왕좌의 기사들"));
        models.add(new BroadcastModel("켠김에 왕까지 배틀그라운드 딩셉션과 함께"));
        return models;
    }

    /**
     * Initialize DraggableView.
     */
    private void initializeDraggableView() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.gameDetailViewMainFragment.setVisibility(View.GONE);
                binding.gameDetailViewMainFragment.closeToRight();
            }
        }, DELAY_MILLIS);
    }

    /**
     * Initialize GridView with some injected data and configure OnItemClickListener.
     */
    private void addClickListenerAtRecyclerView() {
        adapter.setOnClickEvent(new ParallaxRecyclerAdapter.OnClickEvent() {
            @Override
            public void onClick(View view, int i) {
                Log.d("MainFragment","addClickListenerAtRecyclerView: adapter().getData().size()->"+adapter.getData().size());
                BroadcastModel model = adapter.getData().get(i);
                Log.d("MainFragment", "on Clicked : " + model.getTitle()+" position : "+i);
                switch (i % 3) {
                    case 0:
                        Log.d("MainFragment", "glide load image position :" + i);
                        Picasso.with(getContext())
                                .load(R.drawable.gamesnapshot)
                                .into(binding.gameDetailViewHeader);
                        break;
                    case 1:
                        Log.d("MainFragment", "glide load image position :" + i);
                        Picasso.with(getContext())
                                .load(R.drawable.gamenapshot2)
                                .into(binding.gameDetailViewHeader);
                        break;
                    case 2:
                        Log.d("MainFragment", "glide load image position :" + i);
                        Picasso.with(getContext())
                                .load(R.drawable.gamenapshot3)
                                .placeholder(R.drawable.taygaicon)
                                .into(binding.gameDetailViewHeader);
                        break;
                }
                renderGameDetailBody(model);
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
            @Override
            public void onMaximized() {
                Log.d("MainFragment", "call onMaximized");
                toolbar.setVisibility(View.GONE);
            }

            @Override
            public void onMinimized() {
                Log.d("MainFragment", "call onMinimized");
                toolbar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onClosedToLeft() {
                Log.d("MainFragment", "call onClosedToLeft");
                binding.gameDetailViewBody.fullScroll(0);
            }

            @Override
            public void onClosedToRight() {
                Log.d("MainFragment", "call onClosedToRight");
                binding.gameDetailViewBody.fullScroll(0);
            }
        });
    }

    /**
     * Configure a view as episodes ListView header with the name of the tv show and the season.
     */
    private void renderGameDetailBody(BroadcastModel data) {
//        여기서 Buttom view를 조작
        String title = data.getTitle();
        binding.gameDetailTitle.setText(title);
        if (data.getFavorites() == 1) {
            binding.imgFavorites.setImageResource(R.drawable.ic_star_black_24dp);
        }
//        연관 동영상 추가
        List<BroadcastModel> youtubeModles = inputTestData();
        BroadcastRcvAdapter youtubeAdapter = new BroadcastRcvAdapter(youtubeModles, getContext());
        binding.rcvRelactiveBroad.setLayoutManager(new LinearLayoutManager(getContext()));
        youtubeAdapter.setOnClickEvent(new ParallaxRecyclerAdapter.OnClickEvent() {
            @Override
            public void onClick(View view, int i) {
                Toast.makeText(getContext(), "Clicked YouTube", Toast.LENGTH_LONG).show();
            }
        });
//        youtubeAdapter.setData(youtubeModles);
        View header = LayoutInflater.from(getContext()).inflate(R.layout.video_clip_header, recyclerView, false);
        youtubeAdapter.setParallaxHeader(header,binding.rcvRelactiveBroad);
        binding.rcvRelactiveBroad.setAdapter(youtubeAdapter);
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
