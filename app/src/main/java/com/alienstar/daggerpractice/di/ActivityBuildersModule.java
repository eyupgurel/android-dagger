package com.alienstar.daggerpractice.di;

import com.alienstar.daggerpractice.di.auth.AuthModule;
import com.alienstar.daggerpractice.di.auth.AuthViewModelsModule;
import com.alienstar.daggerpractice.ui.auth.AuthActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = ActivityBuildersModule.AuthActivitySubcomponent.class)
//@Module
public abstract class ActivityBuildersModule {
   //@ContributesAndroidInjector(modules={AuthModule.class, AuthViewModelsModule.class})
   // abstract AuthActivity contributeAuthActivity();
   @Binds
    @IntoMap
    @ClassKey(AuthActivity.class)
    abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
            AuthActivitySubcomponent.Factory builder);

    @Subcomponent(modules = {AuthModule.class, AuthViewModelsModule.class})
    public interface AuthActivitySubcomponent extends AndroidInjector<AuthActivity> {
        @Subcomponent.Factory
        interface Factory extends AndroidInjector.Factory<AuthActivity> {}
    }
}
