package com.alienstar.daggerpractice.di;

import com.alienstar.daggerpractice.di.auth.AuthViewModelsModule;
import com.alienstar.daggerpractice.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(modules={AuthViewModelsModule.class})
    abstract AuthActivity contributeAuthActivity();

}
