package com.example.admin.bakingapp.Widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.admin.bakingapp.Data.RecipeContract;
import com.example.admin.bakingapp.R;
import com.example.admin.bakingapp.Recipe.Recipe;
import com.example.admin.bakingapp.RecipeChild.Ingredients.Ingredient;
import com.example.admin.bakingapp.Recipes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Admin on 11-Jul-17.
 */

public class WidgetRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    private List<Recipes> mRecipes;

    private String ingredientName;
    private String ingredientMeasure;
    private String ingredientQuantity;

    Intent intent = new Intent();

    public WidgetRemoteViewFactory(Context applicationContext) {
        mContext = applicationContext;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

        new DataQueryFavourite().execute(RecipeContract.RecipeEntry.CONTENT_URI);

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.widget_list_layout);

        final Recipes currentRecipe = mRecipes.get(position);
        RemoteViews titleView = new RemoteViews(mContext.getPackageName(), R.layout.baking_app_widget);
        String recipeTitle = currentRecipe.getRecipeName();
        titleView.setTextViewText(R.id.widget_title, recipeTitle);


        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //AsyncTask that makes database queries for movie data
    private class DataQueryFavourite extends AsyncTask<Uri, Void, List<Recipes>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected List<Recipes> doInBackground(Uri... uris) {

            Cursor data = mContext.getContentResolver().query(uris[0], null, null, null, null);
            int recipeNameIndex = data.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_RECIPE_NAME);
            int ingredientNameIndex = data.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_RECIPE_NAME_INGREDIENT);
            int ingredientMeasureIndex = data.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_RECIPE_MEASUREMENT_INGREDIENT);
            int ingredientQuantityIndex = data.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_RECIPE_QUANTITY_INGREDIENT);

            mRecipes.clear();

            while(data.moveToNext()) {
                String recipeName = data.getString(recipeNameIndex);
                String recipeIngredientName = data.getString(ingredientNameIndex);
                String recipeIngredientMeasure = data.getString(ingredientMeasureIndex);
                String recipeIngredientQuantity = data.getString(ingredientQuantityIndex);

                Recipes recipe = new Recipes();

                recipe.setRecipeName(recipeName);
                recipe.setIngredientName(recipeIngredientName);
                recipe.setIngredientMeasure(recipeIngredientMeasure);
                recipe.setIngredientQuantity(recipeIngredientQuantity);

                mRecipes.add(recipe);
            }

            return mRecipes;

        }

        @Override
        protected void onPostExecute(List<Recipes> recipes) {


            for (Recipes recipe : mRecipes){

                RemoteViews ingRemoteViews = new RemoteViews(mContext.getPackageName(), R.layout.widget_list_layout);
                ingredientName = recipe.getIngredientName();
                ingredientMeasure = recipe.getIngredientMeasure();
                ingredientQuantity = recipe.getIngredientQuantity();

                ingRemoteViews.setTextViewText(R.id.widget_IngredientName, ingredientName);
                ingRemoteViews.setTextViewText(R.id.widget_IngredientMeasurement, ingredientMeasure);
                ingRemoteViews.setTextViewText(R.id.widget_IngredientQuantity, ingredientQuantity);

            }

            super.onPostExecute(recipes);

        }
    }

}
