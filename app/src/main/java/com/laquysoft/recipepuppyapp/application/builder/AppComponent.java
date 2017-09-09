package com.laquysoft.recipepuppyapp.application.builder;

import android.content.Context;

import com.laquysoft.recipepuppyapp.api.RecipePuppyApi;
import com.laquysoft.recipepuppyapp.utils.rx.RxSchedulers;

import dagger.Component;

/**
 * Created by joaobiriba on 08/09/2017.
 */

@AppScope
@Component(modules = {NetworkModule.class, AppContextModule.class, RxModule.class, RecipePuppyApiServiceModule.class})
public interface AppComponent {


    Context getAppContext();

    RxSchedulers rxSchedulers();


    RecipePuppyApi apiService();


    // void inject(MainActivity activity);
    //Retrofit provideNetworkApi();

}
