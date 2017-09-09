package com.laquysoft.recipepuppyapp.application.builder;

import com.laquysoft.recipepuppyapp.utils.rx.AppRxSchedulers;
import com.laquysoft.recipepuppyapp.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;

/**
 * Created by joaobiriba on 08/09/2017.
 */

@Module
public class RxModule {

    @Provides
    RxSchedulers provideRxSchedulers() {
        return new AppRxSchedulers();
    }
}
