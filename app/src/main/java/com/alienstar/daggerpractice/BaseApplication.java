package com.alienstar.daggerpractice;

import com.alienstar.daggerpractice.di.AppComponent;
import com.alienstar.daggerpractice.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class BaseApplication extends DaggerApplication {

    @Override
    public AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this). build();
        return appComponent;
    }
}
