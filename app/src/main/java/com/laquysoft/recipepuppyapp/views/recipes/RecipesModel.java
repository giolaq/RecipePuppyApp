package com.laquysoft.recipepuppyapp.views.recipes;

import com.laquysoft.recipepuppyapp.api.RecipePuppyApi;
import com.laquysoft.recipepuppyapp.models.Recipe;
import com.laquysoft.recipepuppyapp.models.Recipes;
import com.laquysoft.recipepuppyapp.utils.NetworkUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by joaobiriba on 08/09/2017.
 */

public class RecipesModel {
    RecipesListActivity context;
    RecipePuppyApi api;

    public RecipesModel(RecipesListActivity context, RecipePuppyApi api) {
        this.api = api;
        this.context = context;
    }


    Observable<Recipes> provideListRecipes(final String query, final int page) {
        return api.getRecipes(query, page);
    }

//    Observable<List<Recipe>> providesNRecipes(final String query, final int page) {
//
//        return provideListRecipes(query, page).concatMap(new Function<Recipes, ObservableSource<? extends Recipes>>() {
//            @Override
//            public ObservableSource<? extends Recipes> apply(@NonNull Recipes recipes) throws Exception {
//                // Terminal case.
//                if (recipes.getRecipes().isEmpty()) {
//                    return Observable.just(recipes);
//                }
//                return Observable.just(recipes)
//                        .concatWith(providesNRecipes(query, page + 1));
//            }
//        });
//
//    }

    Observable<Boolean> isNetworkAvailable() {
        return NetworkUtils.isNetworkAvailableObservable(context);
    }

}
