package com.laquysoft.recipepuppyapp.views.recipes;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.laquysoft.recipepuppyapp.R;
import com.laquysoft.recipepuppyapp.models.Recipe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

/**
 * Created by joaobiriba on 08/09/2017.
 */

public class RecipesView {
    @BindView(R.id.activity_recipes_list_recycleview)
    RecyclerView list;

    @BindView(R.id.search_bar)
    SearchView searchBar;

    View view;

    RecipesAdapter adapter;

    public RecipesView(RecipesListActivity context ) {
        FrameLayout parent = new FrameLayout(context);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(context).inflate(R.layout.activity_recipes_list, parent, true);
        ButterKnife.bind(this, view);

        adapter = new RecipesAdapter();

        list.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        list.setLayoutManager(mLayoutManager);


    }

    public SearchView getSearchBar() {
        return searchBar;
    }

    public View view() {
        return view;
    }

    public void swapAdapter(ArrayList<Recipe> recipes)
    {
        adapter.swapAdapter(recipes);
    }

}
