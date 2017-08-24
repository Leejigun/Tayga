package com.dopy.dopy.tayga.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.ActivityMainBinding;
import com.dopy.dopy.tayga.databinding.AppBarMainBinding;
import com.dopy.dopy.tayga.databinding.ContentMainBinding;
import com.dopy.dopy.tayga.databinding.NavHeaderMainBinding;
import com.dopy.dopy.tayga.model.MyApplication;
import com.dopy.dopy.tayga.model.User;
import com.dopy.dopy.tayga.ui.auth.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/*
* */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private static final String TAG = "MainActivity";
    ActivityMainBinding activityBinding;
    AppBarMainBinding appbarBinding;
    ContentMainBinding contentBinding;
    NavHeaderMainBinding navHeaderMainBinding;
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authStateListener;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dbRef;
    MyApplication myApplication;

    boolean backKeyPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity", "oncreate");
        super.onCreate(savedInstanceState);
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        appbarBinding = activityBinding.appBarMain;
        contentBinding = appbarBinding.contentMain;
        setSupportActionBar(appbarBinding.toolbar);
        auth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user==null){
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                }
            }
        };
        user = auth.getCurrentUser();
        auth.addAuthStateListener(authStateListener);
        firebaseDatabase = FirebaseDatabase.getInstance();
        dbRef = firebaseDatabase.getReference();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, activityBinding.drawerLayout, appbarBinding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        activityBinding.drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        activityBinding.navView.setNavigationItemSelectedListener(this);
        navHeaderMainBinding = NavHeaderMainBinding.bind(activityBinding.navView.getHeaderView(0));
        getUserInfo();
        getSupportFragmentManager().beginTransaction()
                .replace(contentBinding.mainFrame.getId(), MainFragment.newInstance())
                .commit();
    }

    private void getUserInfo() {
        myApplication = (MyApplication) getApplication();
        User userInfo = new User(user.getUid(), user.getDisplayName(), user.getEmail(), user.getPhotoUrl().toString());
        myApplication.setUser(userInfo);
        updateUI(userInfo);
    }

    private void updateUI(User currentUser) {
        if (currentUser.getDisplayname() != null) {
            navHeaderMainBinding.profileDisplayName.setText(currentUser.getDisplayname());
        }
        if (currentUser.getEmail() != null) {
            navHeaderMainBinding.profileEmail.setText(currentUser.getEmail());
        }
        if (currentUser.getPotoUri() != null) {
            Glide.with(this)
                    .load(Uri.parse(currentUser.getPotoUri()))
                    .placeholder(R.drawable.ic_assignment_ind_black_24dp)
                    .error(R.drawable.ic_assignment_ind_black_24dp)
                    .crossFade()
                    .into(navHeaderMainBinding.profileImage);
        }
        navHeaderMainBinding.containerFavritesStarCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(contentBinding.mainFrame.getId(), FavoritesFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
                activityBinding.drawerLayout.closeDrawers();
            }
        });
        setUpFavoritesAndStartCount(currentUser);
    }

    private void setUpFavoritesAndStartCount(final User currentUser){
        dbRef.child("Favorites").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int streamercount = (int)dataSnapshot.child("Streamer").child(currentUser.getUserID()).getChildrenCount();
                int gamecount = (int)dataSnapshot.child("Game").child(currentUser.getUserID()).getChildrenCount();
                int totalcount = (streamercount+gamecount);
                navHeaderMainBinding.txtFavoritesCount.setText(Integer.toString(totalcount));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        dbRef.child("Like").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int streamerCount = (int)dataSnapshot.child("Streamer").child(currentUser.getUserID()).getChildrenCount();
                navHeaderMainBinding.txtStarCount.setText(Integer.toString(streamerCount));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_main) {
            getSupportFragmentManager().beginTransaction()
                    .replace(contentBinding.mainFrame.getId(), MainFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.menu_popular) {
            getSupportFragmentManager().beginTransaction()
                    .replace(contentBinding.mainFrame.getId(), StreamListFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.menu_game) {
            getSupportFragmentManager().beginTransaction()
                    .replace(contentBinding.mainFrame.getId(), GameFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.menu_favorites) {
            getSupportFragmentManager().beginTransaction()
                    .replace(contentBinding.mainFrame.getId(), FavoritesFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.menu_profile_logout) {
            auth.signOut();
            startActivity(new Intent(this,LoginActivity.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (activityBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            activityBinding.drawerLayout.closeDrawer(GravityCompat.START);
        } else if (backKeyPressed) {
            finish();
        } else {
            Toast.makeText(this, "뒤로가기를 한번 더 누르시면 앱을 종료합니다.", Toast.LENGTH_LONG).show();
            backKeyPressed = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    backKeyPressed = false;
                }
            }, 3 * 1000);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        auth.removeAuthStateListener(authStateListener);
    }
}
