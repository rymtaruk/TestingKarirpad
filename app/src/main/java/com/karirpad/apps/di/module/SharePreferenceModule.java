package com.karirpad.apps.di.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created By reynard on 7/4/21.
 */

@Module
public abstract class SharePreferenceModule {

    @Provides
    static SharedPreferences provideSharedPreferences(Application applicationContext){
        return PreferenceManager.getDefaultSharedPreferences(applicationContext);
    }
}
