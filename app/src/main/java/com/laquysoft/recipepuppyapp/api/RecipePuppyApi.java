package com.laquysoft.recipepuppyapp.api;

import com.laquysoft.recipepuppyapp.models.Recipes;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by joaobiriba on 08/09/2017.
 */

public interface RecipePuppyApi {

    @GET("/api/")
    Observable<Recipes> getRecipes(@Query("q") String recipeName,
                                   @Query("p") int page);

    @GET("/api/")
    Observable<Recipes> getPageAndNext(@Query("q") String recipeName,
                                       @Query("p")int page);

}
