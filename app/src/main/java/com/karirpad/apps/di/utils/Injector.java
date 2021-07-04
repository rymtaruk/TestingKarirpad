package com.karirpad.apps.di.utils;

import android.app.Application;
import android.content.Context;

import com.karirpad.apps.di.component.ApplicationComponent;
import com.karirpad.apps.di.component.DaggerApplicationComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

/**
 * Created By reynard on 7/3/21.
 */
public class Injector implements HasAndroidInjector {
    static Injector instance;
    ApplicationComponent applicationComponent;

    @Inject
    DispatchingAndroidInjector<Object> appInjector;

    private Injector(Application applicationContext) {
        applicationComponent = DaggerApplicationComponent.builder()
                .application(applicationContext)
                .build();
        applicationComponent.inject(this);
    }

    public static void initialize(Application applicationContext) {
        instance = new Injector(applicationContext);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static Injector getInstance(Context context) {
        if (instance == null) {
            initialize((Application) context.getApplicationContext());
        }

        return instance;
    }


    @Override
    public AndroidInjector<Object> androidInjector() {
        return appInjector;
    }
}
