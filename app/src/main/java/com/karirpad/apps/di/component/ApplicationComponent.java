package com.karirpad.apps.di.component;

import android.app.Application;

import com.karirpad.apps.di.module.ActivityBindingModule;
import com.karirpad.apps.di.module.ApiModule;
import com.karirpad.apps.di.module.ContextModule;
import com.karirpad.apps.di.module.RoomModule;
import com.karirpad.apps.di.module.SharePreferenceModule;
import com.karirpad.apps.di.module.ViewModelModule;
import com.karirpad.apps.di.utils.Injector;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created By reynard on 7/3/21.
 */

@Singleton
@Component(modules = {ActivityBindingModule.class, RoomModule.class, ApiModule.class, AndroidInjectionModule.class, ViewModelModule.class, ContextModule.class, SharePreferenceModule.class})
public interface ApplicationComponent {
    void inject(Injector injector);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        ApplicationComponent build();
    }
}
