package com.example.admin.bakingapp.Recipe;


import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Utility functions to handle OpenWeatherMap JSON data.
 */
public final class RecipeJSONData {
    /**
     * @param recipeJsonStr JSON response from server
     * @return Array of Strings describing recipe data
     * @throws JSONException If JSON data cannot be properly parsed
     */
    public static ArrayList<Recipe> getRecipeDataStringsFromJson(Context context, String recipeJsonStr)
            throws JSONException {

        /* Main information information needed to display */
        final String RECIPE_TITLE = "name";
        final String RECIPE_ID = "id";

        JSONArray recipeJSON = new JSONArray(recipeJsonStr);

        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();

        for (int i = 0; i < recipeJSON.length(); i++) {

            Recipe recipe = new Recipe();

            /* These are the values that will be collected */
            String recipeTitle;
            int recipeId;

            /* Get the JSON object for the recipe */
            JSONObject recipeObject = recipeJSON.getJSONObject(i);

            // Extract relevant JSON fields
            recipeTitle = recipeObject.getString(RECIPE_TITLE);
            recipeId = recipeObject.getInt(String.valueOf(RECIPE_ID));

            recipe.setRecipeName(recipeTitle);
            recipe.setRecipeId(recipeId);


            recipeList.add(recipe);
        }

        return recipeList;
    }
}

