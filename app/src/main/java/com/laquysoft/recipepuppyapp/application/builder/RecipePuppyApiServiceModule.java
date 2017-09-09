package com.laquysoft.recipepuppyapp.application.builder;

import com.laquysoft.recipepuppyapp.api.RecipePuppyApi;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by joaobiriba on 08/09/2017.
 */

@Module
public class RecipePuppyApiServiceModule {

    private static final String BASE_URL = "http://www.recipepuppy.com/";
    @AppScope
    @Provides
    RecipePuppyApi provideApiService(OkHttpClient client, GsonConverterFactory gson, RxJava2CallAdapterFactory rxAdapter)
    {
        Retrofit retrofit =   new Retrofit.Builder().client(client)
                .baseUrl(BASE_URL).addConverterFactory(gson).
                        addCallAdapterFactory(rxAdapter).build();

        return  retrofit.create(RecipePuppyApi.class);
    }


}
