package com.laquysoft.recipepuppyapp.views.recipes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laquysoft.recipepuppyapp.R;
import com.laquysoft.recipepuppyapp.models.Recipe;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by joaobiriba on 09/09/2017.
 */


public class RecipesAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

    private final PublishSubject<Integer> itemClicks = PublishSubject.create();
    ArrayList<Recipe> listRecipes = new ArrayList<>();


    public void swapAdapter(ArrayList<Recipe> recipes)
    {
        this.listRecipes.clear();
        this.listRecipes.addAll(recipes);
        notifyDataSetChanged();
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view, itemClicks);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {

        Recipe recipe = listRecipes.get(position);
        holder.bind(recipe);

    }


    @Override
    public int getItemCount() {
        if (listRecipes != null && listRecipes.size() > 0) {
            return listRecipes.size();
        } else {
            return 0;
        }
    }
}
