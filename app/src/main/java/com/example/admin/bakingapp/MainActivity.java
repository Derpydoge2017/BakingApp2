package com.example.admin.bakingapp;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.example.admin.bakingapp.Data.RecipeContract;
import com.example.admin.bakingapp.Recipe.Recipe;
import com.example.admin.bakingapp.Recipe.RecipeAdapter;
import com.example.admin.bakingapp.Recipe.RecipeJSONData;
import com.example.admin.bakingapp.RecipeChild.RecipeChild;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.RecipeAdapterOnClickHandler {

    private RecyclerView mRecyclerView;
    private RecipeAdapter mRecipeAdapter;

    private String recipeName;
    private String ingredientName;
    private String ingredientMeasurement;
    private String ingredientQuantity;

    String RECIPE_BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    private ArrayList<Recipes> mRecipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * Using findViewById, we get a reference to our RecyclerView from xml. This allows us to
         * do things like set the adapter of the RecyclerView and toggle the visibility.
         */
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_numbers);

        /*
         * LinearLayoutManager can support HORIZONTAL or VERTICAL orientations. The reverse layout
         * parameter is useful mostly for HORIZONTAL layouts that should reverse for right to left
         * languages.
         */
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, numberOfColumns());

        mRecyclerView.setLayoutManager(gridLayoutManager);

        /*
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         */

        mRecipeAdapter = new RecipeAdapter(this, (RecipeAdapter.RecipeAdapterOnClickHandler) this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mRecipeAdapter);

        loadRecipeData();

    }

    private int numberOfColumns() {
        int nColumns = 1;
        return nColumns;
    }

    /**
     * This method will load the recipe
     */
    private void loadRecipeData() {
        new RecipeQueryTask().execute();

        showRecipeDataView();
    }


    /**
     * This method is overridden by our MainActivity class in order to handle RecyclerView item
     * clicks.
     *
     * @param recipeDetails The recipe that was clicked
     */
    @Override
    public void onClick(Recipe recipeDetails) {
        Context context = this;
        Class destinationClass = RecipeChild.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        intentToStartDetailActivity.putExtra(Intent.EXTRA_TITLE, recipeDetails);
        startActivity(intentToStartDetailActivity);
    }

    private void showRecipeDataView() {
        /* Make sure the recipe data is visible */
        mRecyclerView.setVisibility(View.VISIBLE);
    }


    public class RecipeQueryTask extends AsyncTask<String, Void, ArrayList<Recipe>> {

        @Override
        protected ArrayList doInBackground(String... params) {
            URL recipeSearchUrl = NetworkUtils.buildUrl(RECIPE_BASE_URL);
            try {

                String jsonRecipeResponse = NetworkUtils
                        .getResponseFromHttpUrl(recipeSearchUrl);

                ArrayList simpleJsonRecipeData = RecipeJSONData
                        .getRecipeDataStringsFromJson(MainActivity.this, jsonRecipeResponse);

                ArrayList simpleJsonAllData = RecipeJSON
                        .getRecipeDataStringsFromJson(MainActivity.this, jsonRecipeResponse);

                mRecipes = simpleJsonAllData;

                return simpleJsonRecipeData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Recipe> recipeData){

            mRecipeAdapter.setRecipeData(recipeData);

            for (Recipes recipe : mRecipes) {
                recipeName = recipe.getRecipeName();
                ingredientName = recipe.getIngredientName();
                ingredientMeasurement = recipe.getIngredientMeasure();
                ingredientQuantity = recipe.getIngredientQuantity();
            }

            // Create new empty ContentValues object
            ContentValues contentValues = new ContentValues();
            contentValues.put(RecipeContract.RecipeEntry.COLUMN_RECIPE_NAME, recipeName);
            contentValues.put(RecipeContract.RecipeEntry.COLUMN_RECIPE_NAME_INGREDIENT, ingredientName);
            contentValues.put(RecipeContract.RecipeEntry.COLUMN_RECIPE_MEASUREMENT_INGREDIENT, ingredientMeasurement);
            contentValues.put(RecipeContract.RecipeEntry.COLUMN_RECIPE_QUANTITY_INGREDIENT, ingredientQuantity);
            // Insert the content values via a ContentResolver
            getContentResolver().insert(RecipeContract.RecipeEntry.CONTENT_URI, contentValues);

        }

    }
}

