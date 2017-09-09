package com.laquysoft.recipepuppyapp.views.recipes.dagger;

import com.laquysoft.recipepuppyapp.application.builder.AppComponent;
import com.laquysoft.recipepuppyapp.views.recipes.RecipesListActivity;

import dagger.Component;

/**
 * Created by joaobiriba on 09/09/2017.
 */

@RecipesScope
@Component(dependencies = {AppComponent.class} , modules = {RecipesModule.class})
public interface RecipesComponent {

    void inject (RecipesListActivity recipesListActivity);
}
