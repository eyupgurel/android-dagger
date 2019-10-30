package com.alienstar.daggerpractice.ui.auth;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.alienstar.daggerpractice.R;
import com.alienstar.daggerpractice.models.User;
import com.alienstar.daggerpractice.viewmodels.ViewModelProviderFactory;
import com.bumptech.glide.RequestManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity {
    private static final String TAG = "AuthActivity";

    private AuthViewModel authViewModel;

    @BindView(R.id.user_id_input) EditText userId;
    @BindView(R.id.login_button) Button loginButton;
    @BindView(R.id.progress_bar) ProgressBar progressBar;

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
        ButterKnife.bind(this);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(userId.getText().toString()))
                    authViewModel.authenticateWithId(Integer.parseInt( userId.getText().toString()));
            }
        });
        authViewModel = ViewModelProviders.of(this,viewModelProviderFactory).get(AuthViewModel.class);
        setLogo();
        subscribeObservers();
    }

    private void subscribeObservers(){
        authViewModel.observeUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource!=null){
                    switch(userAuthResource.status){
                        case LOADING:
                            showProgressBar(true);
                            break;
                        case AUTHENTICATED :
                            Log.d(TAG, "onChanged:  LOGIN SUCCESS : " +  userAuthResource.data.getEmail());
                            showProgressBar(false);
                            break;
                            
                        case ERROR:
                            Toast.makeText(AuthActivity.this,
                                    userAuthResource.message + "\nDid you enter digit between 1 and 10?", Toast.LENGTH_SHORT).show();
                            showProgressBar(false);
                            break;

                        case  NOT_AUTHENTICATED:
                            showProgressBar(false);
                            break;

                        default:
                            throw new IllegalArgumentException(userAuthResource.toString());
                        }
                    }
                }
        });
    }

    private void showProgressBar(boolean isVisible){
          progressBar.setVisibility(isVisible ? View.VISIBLE  : View.GONE);
    }

    private void setLogo(){
        requestManager.load(logo).into((ImageView)findViewById(R.id.login_logo));
    }
}
