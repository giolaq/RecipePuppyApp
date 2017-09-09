package com.laquysoft.recipepuppyapp.views.recipes;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.laquysoft.recipepuppyapp.R;
import com.laquysoft.recipepuppyapp.models.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by joaobiriba on 09/09/2017.
 */

public class RecipeViewHolder extends RecyclerView.ViewHolder {

    View view;

    @BindView(R.id.item_recipe_image)
    ImageView thumbnail;
    @BindView(R.id.item_recipe_title)
    TextView recipeTitle;


    public RecipeViewHolder(View itemView, PublishSubject<Integer> clickSubject) {
        super(itemView);
        this.view = itemView;
        ButterKnife.bind(this, view);
        view.setOnClickListener(v -> clickSubject.onNext(getAdapterPosition())
        );
    }

    void bind(Recipe recipe) {
        Glide.with(view.getContext()).load(recipe.getThumbnail()).into(thumbnail);

        recipeTitle.setText(TextUtils.isEmpty(recipe.getTitle()) ? "Anonymous Recipe" : recipe.getTitle());

    }

}
