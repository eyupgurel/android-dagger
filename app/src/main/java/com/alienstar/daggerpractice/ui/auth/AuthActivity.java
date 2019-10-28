package com.alienstar.daggerpractice.ui.auth;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.lifecycle.ViewModelProviders;

import com.alienstar.daggerpractice.R;
import com.alienstar.daggerpractice.viewmodels.ViewModelProviderFactory;
import com.bumptech.glide.RequestManager;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity {
    private static final String TAG = "AuthActivity";

    private AuthViewModel authViewModel;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Inject
    Drawable logo;
    @Inject
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        authViewModel = ViewModelProviders.of(this,viewModelProviderFactory).get(AuthViewModel.class);
        setLogo();
    }

    private void setLogo(){
        requestManager.load(logo).into((ImageView)findViewById(R.id.login_logo));
    }


}
