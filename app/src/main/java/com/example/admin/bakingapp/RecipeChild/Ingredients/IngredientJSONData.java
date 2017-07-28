package com.example.admin.bakingapp.RecipeChild.Ingredients;


import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Utility functions to handle OpenWeatherMap JSON data.
 */
public final class IngredientJSONData {
    /**
     * @param ingredientJsonStr JSON response from server
     * @return Array of Strings describing ingredient data
     * @throws JSONException If JSON data cannot be properly parsed
     */
    public static ArrayList<Ingredient> getIngredientDataStringsFromJson(Context context, String ingredientJsonStr)
            throws JSONException {

        /* Recipe information */
        final String INGREDIENT_LIST = "ingredients";

        /* Main information information needed to display */
        final String INGREDIENT_NAME = "ingredient";
        final String INGREDIENT_MEASURE = "measure";
        final String INGREDIENT_QUANTITY = "quantity";

        JSONArray recipeArray = new JSONArray(ingredientJsonStr);

        ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();

        for (int i = 0; i < recipeArray.length(); i++) {

            JSONObject recipeJSON = recipeArray.getJSONObject(i);

            JSONArray ingredientArray = recipeJSON.getJSONArray(INGREDIENT_LIST);

            for (int x = 0; x < ingredientArray.length(); x++) {

                Ingredient ingredient = new Ingredient();

                /* These are the values that will be collected */
                String ingredientName;
                String ingredientMeasure;
                Double ingredientQuantity;

                /* Get the JSON object for the movie */
                JSONObject ingredientObject = ingredientArray.getJSONObject(x);

                // Extract relevant JSON fields
                ingredientName = ingredientObject.getString(INGREDIENT_NAME);
                ingredientMeasure = ingredientObject.getString(INGREDIENT_MEASURE);
                ingredientQuantity = ingredientObject.getDouble(INGREDIENT_QUANTITY);

                ingredient.setIngredientName(ingredientName);
                ingredient.setIngredientMeasure(ingredientMeasure);
                ingredient.setIngredientQuantity(ingredientQuantity);

                ingredientList.add(ingredient);


            }

        }

        return ingredientList;

    }
}

