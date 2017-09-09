package com.laquysoft.recipepuppyapp.models;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by joaobiriba on 08/09/2017.
 */


public class Recipes {

    @Expose
    private List<Recipe> results;

    public List<Recipe> getRecipes() {
        return results;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.results = recipes;
    }


}
