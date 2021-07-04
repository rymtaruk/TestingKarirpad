package com.karirpad.apps.di.module;

import com.karirpad.apps.manager.database.DatabaseManager;
import com.karirpad.apps.manager.database.repository.DatabaseRepository;
import com.karirpad.apps.manager.database.repository.IDatabaseRepository;
import com.karirpad.apps.manager.preferences.ISharePreferences;
import com.karirpad.apps.manager.preferences.SharePreferences;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created By reynard on 7/3/21.
 */
@Module
public abstract class RoomModule {

    @Binds
    @Singleton
    abstract ISharePreferences provideISharePreferences(SharePreferences sharePreferences);

    @Binds
    @Singleton
    abstract IDatabaseRepository provideIDatabaseRepository(DatabaseRepository databaseRepository);
}
