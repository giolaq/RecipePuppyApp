package com.laquysoft.recipepuppyapp.application.builder;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by joaobiriba on 08/09/2017.
 */

@Module
public class AppContextModule {


    private final Context context;

    public AppContextModule(Context aContext) {
        this.context = aContext;
    }

    @AppScope
    @Provides
    Context provideAppContext() {
        return context;
    }

}
