package com.laquysoft.recipepuppyapp.views.recipes;

import android.util.Log;

import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;
import com.jakewharton.rxbinding2.support.v7.widget.SearchViewQueryTextEvent;
import com.laquysoft.recipepuppyapp.models.Recipe;
import com.laquysoft.recipepuppyapp.utils.UiUtils;
import com.laquysoft.recipepuppyapp.utils.rx.RxSchedulers;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import timber.log.Timber;

/**
 * Created by joaobiriba on 08/09/2017.
 */

public class RecipesPresenter {
    RecipesView view;
    RecipesModel model;
    RxSchedulers rxSchedulers;
    CompositeDisposable subscriptions;
    ArrayList<Recipe> recipes = new ArrayList<>();

    public RecipesPresenter(RxSchedulers schedulers, RecipesModel model, RecipesView view, CompositeDisposable sub) {
        this.rxSchedulers = schedulers;
        this.view = view;
        this.model = model;
        this.subscriptions = sub;
    }

    public void onCreate() {
        subscriptions.add(getRecipesList());
    }

    public void onDestroy() {
        subscriptions.clear();
    }

    private Disposable getRecipesList(String name) {

        return model.isNetworkAvailable().doOnNext(networkAvailable -> {
            if (!networkAvailable) {
                Log.d("no conn", "no connection");
            }
        }).
                filter(isNetworkAvailable -> true).
                flatMap(isAvailable -> model.provideListRecipes(name, 1)).
                subscribeOn(rxSchedulers.internet()).
                observeOn(rxSchedulers.androidThread()).subscribe(recips -> {
                    Log.d(" loaded ", " recipes ");
                    view.swapAdapter((ArrayList<Recipe>) recips.getRecipes());
                    recipes = (ArrayList<Recipe>) recips.getRecipes();
                    Log.d(" loaded ", "num " + recipes.size());
                }, throwable -> {
                    UiUtils.handleThrowable(throwable);
                }
        );

    }

    private Disposable getRecipesListWithPagination(final String name, final int page) {

        return model.isNetworkAvailable().doOnNext(networkAvailable -> {
            if (!networkAvailable) {
                Log.d("no conn", "no connection");
            }
        }).
                filter(isNetworkAvailable -> true)
                .flatMap(isAvailable -> model.provideListRecipes(name, page)).
                        //retrieve until 20 recipes
                        subscribeOn(rxSchedulers.internet()).
                        observeOn(rxSchedulers.androidThread()).subscribe(recips -> {
                            Log.d(" loaded ", " recipes ");
                            Log.d(" loaded ", "num " + recipes.size());
                            if ( recipes.size() < 20 ) {
                                recipes.addAll(recips.getRecipes());
                                view.swapAdapter(recipes);
                                if (recips.getRecipes().size() < 10) {
                                } else {
                                    getRecipesListWithPagination(name, page+1);
                                }
                            } else {
                                recipes.clear();
                            }
                        }, throwable -> {
                            UiUtils.handleThrowable(throwable);
                        }
                );
    }

    private Disposable getRecipesList() {
        return RxSearchView.queryTextChangeEvents(view.getSearchBar())
                .debounce(400, TimeUnit.MILLISECONDS) // default Scheduler is Computation
                .filter(changes -> !changes.queryText().toString().isEmpty())
                .observeOn(rxSchedulers.androidThread())
                .subscribeWith(getSearchObserver());
    }


    private DisposableObserver<SearchViewQueryTextEvent> getSearchObserver() {
        return new DisposableObserver<SearchViewQueryTextEvent>() {
            @Override
            public void onComplete() {
                Timber.d("onComplete");
            }

            @Override
            public void onError(Throwable e) {
                Timber.e(e, "onError");
            }

            @Override
            public void onNext(SearchViewQueryTextEvent onSearchViewQueryTextEvent) {
                Timber.d("Searching for %s", onSearchViewQueryTextEvent.queryText().toString());
                //getRecipesList(onSearchViewQueryTextEvent.queryText().toString());
                getRecipesListWithPagination(onSearchViewQueryTextEvent.queryText().toString(), 1);
            }
        };
    }
}
