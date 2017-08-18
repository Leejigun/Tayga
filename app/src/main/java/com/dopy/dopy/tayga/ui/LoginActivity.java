package com.dopy.dopy.tayga.ui;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.dopy.dopy.tayga.R;
import com.dopy.dopy.tayga.databinding.ActivityMainBinding;
import com.dopy.dopy.tayga.model.User;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends Activity {
    private final String FRIEBASE_AUTH = "firebase auth";

    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;
    ActivityMainBinding binding;
    GoogleSignInOptions gso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        User user = getUserInfo();
        if(user==null){
            
        }
    }

    private void setUpEmailAuth() {
//        로그인 리스너 생성
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(FRIEBASE_AUTH, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Log.d(FRIEBASE_AUTH, "onAuthStateChanged:signed_out");
                }
            }
        };
    }
    private void setUpGoogleAuthSetUp(){
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private void createUserWithEmailAndPassword(String email, String password){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(FRIEBASE_AUTH,"createUserWithEmail:onComplete : "+task.isSuccessful());

                        if(!task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,R.string.auth_failed,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void signinWithEmailAndPassword(String email,String password){
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(FRIEBASE_AUTH,"signInWithEmail:onComplete:" + task.isSuccessful());

                        if(!task.isSuccessful()){
                            Log.w(FRIEBASE_AUTH, "signInWithEmail:failed", task.getException());
                            Toast.makeText(LoginActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private User getUserInfo(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUri = user.getPhotoUrl();
            String uid = user.getUid();
            User currentUser = new User(name,email,photoUri,uid);
            return currentUser;
        }
        Log.d(FRIEBASE_AUTH,"failed getUserInfo");
        return null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener!=null){
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }


}
