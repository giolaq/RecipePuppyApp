package com.laquysoft.recipepuppyapp.views.recipes.dagger;

import com.laquysoft.recipepuppyapp.api.RecipePuppyApi;
import com.laquysoft.recipepuppyapp.utils.rx.RxSchedulers;
import com.laquysoft.recipepuppyapp.views.recipes.RecipesListActivity;
import com.laquysoft.recipepuppyapp.views.recipes.RecipesModel;
import com.laquysoft.recipepuppyapp.views.recipes.RecipesPresenter;
import com.laquysoft.recipepuppyapp.views.recipes.RecipesView;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by joaobiriba on 09/09/2017.
 */

@Module
public class RecipesModule {

    RecipesListActivity recipesListActivity;

    public RecipesModule(RecipesListActivity context) {
        this.recipesListActivity = context;
    }



    @RecipesScope
    @Provides
    RecipesView provideView() {
        return new RecipesView(recipesListActivity);
    }

    @RecipesScope
    @Provides
    RecipesPresenter providePresenter(RxSchedulers schedulers, RecipesView view, RecipesModel model) {
        CompositeDisposable subscriptions = new CompositeDisposable();
        return new RecipesPresenter(schedulers, model, view, subscriptions);
    }



    @RecipesScope
    @Provides
    RecipesListActivity provideContext() {
        return recipesListActivity;
    }
    @RecipesScope
    @Provides
    RecipesModel provideModel(RecipePuppyApi api) {
        return new RecipesModel(recipesListActivity, api);
    }
}

