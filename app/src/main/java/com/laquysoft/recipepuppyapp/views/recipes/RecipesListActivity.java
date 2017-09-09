package com.laquysoft.recipepuppyapp.views.recipes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.laquysoft.recipepuppyapp.application.AppController;
import com.laquysoft.recipepuppyapp.views.recipes.dagger.DaggerRecipesComponent;
import com.laquysoft.recipepuppyapp.views.recipes.dagger.RecipesModule;

import java.io.Serializable;

import javax.inject.Inject;

/**
 * Created by joaobiriba on 08/09/2017.
 */

public class RecipesListActivity extends AppCompatActivity {


    @Inject
    RecipesView view;
    @Inject
    RecipesPresenter presenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerRecipesComponent.builder().appComponent(AppController.getNetComponent()).recipesModule(new RecipesModule(this)).build().inject(this);
        setContentView(view.view());
        presenter.onCreate();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

}
