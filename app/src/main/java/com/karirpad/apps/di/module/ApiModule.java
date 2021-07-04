package com.karirpad.apps.di.module;

import com.karirpad.apps.manager.api.IApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created By reynard on 7/3/21.
 */
@Module(includes = ViewModelModule.class)
public class ApiModule {

    @Provides
    @Singleton
    Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("https://run.mocky.io")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    IApi provideIApi(Retrofit retrofit){
        return retrofit.create(IApi.class);
    }
}
