package com.example.admin.bakingapp.RecipeChild;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;

import com.example.admin.bakingapp.NetworkUtils;
import com.example.admin.bakingapp.R;
import com.example.admin.bakingapp.Recipe.Recipe;
import com.example.admin.bakingapp.RecipeChild.Ingredients.Ingredient;
import com.example.admin.bakingapp.RecipeChild.Ingredients.IngredientAdapter;
import com.example.admin.bakingapp.RecipeChild.Ingredients.IngredientJSONData;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Admin on 12-Jul-17.
 */

public class RecipeChild extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // Flag determines if this is a one or two pane layout
    private boolean isTwoPane = false;

    public static final String STEP_DETAILS = "step_details";

    String RECIPE_BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    private Context context;

    private Recipe mRecipe;

    private RecyclerView mIngredientRV;

    private IngredientAdapter mIngredientAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        // Call this to determine which layout we are in (tablet or phone)
        determinePaneLayout();
    }

    private void loadIngredientData() {
        new IngredientQueryTask().execute();
        showIngredientDataView();
    }

    public class IngredientQueryTask extends AsyncTask<String, Void, ArrayList<Ingredient>> {

        @Override
        protected ArrayList doInBackground(String... params) {
            URL recipeSearchUrl = NetworkUtils.buildUrl(RECIPE_BASE_URL);
            try {
                String jsonRecipeResponse = NetworkUtils
                        .getResponseFromHttpUrl(recipeSearchUrl);

                ArrayList simpleJsonIngredientData = IngredientJSONData
                        .getIngredientDataStringsFromJson(context, jsonRecipeResponse);


                return simpleJsonIngredientData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Ingredient> ingredientData) {

            mIngredientAdapter.setIngredientData(ingredientData);

        }

    }

    private void showIngredientDataView() {
        /* Make sure the recipe data is visible */
        mIngredientRV.setVisibility(View.VISIBLE);

    }



    private void determinePaneLayout() {

        FrameLayout fragmentItemDetail = (FrameLayout) findViewById(R.id.fragmentDetail);

        //Get EXTRA from intent and attach to Fragment as Argument
        mRecipe = getIntent().getParcelableExtra("android.intent.extra.TITLE");
        Bundle args = new Bundle();
        args.putParcelable("ARGUMENTS", mRecipe);

        // If there is a second pane for details
        if (fragmentItemDetail != null) {
            isTwoPane = true;
        }

        if (isTwoPane){

            Context context = this;
            mIngredientRV = (RecyclerView) findViewById(R.id.ingredient_rv);
            GridLayoutManager gridIngredientManager = new GridLayoutManager(context, 1);
            mIngredientRV.setLayoutManager(gridIngredientManager);
            mIngredientAdapter = new IngredientAdapter();
            mIngredientRV.setAdapter(mIngredientAdapter);
            loadIngredientData();

            RecipeChildFragmentTablet tabletChildFragment = new RecipeChildFragmentTablet();
            tabletChildFragment.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentInstructionList, tabletChildFragment).commit();



        } else {

            RecipeChildFragment recipeChildFragment = new RecipeChildFragment();
            recipeChildFragment.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.recipeChildContainer, recipeChildFragment).commit();

        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
