package com.example.admin.bakingapp;

import android.content.Context;

import com.example.admin.bakingapp.Recipe.Recipe;
import com.example.admin.bakingapp.RecipeChild.Ingredients.Ingredient;
import com.example.admin.bakingapp.RecipeChild.Instructions.Instruction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Admin on 14-Jul-17.
 */

public class RecipeJSON {

    /**
     * @param recipeJsonStr JSON response from server
     * @return Array of Strings describing recipe data
     * @throws JSONException If JSON data cannot be properly parsed
     */
    public static ArrayList<Recipes> getRecipeDataStringsFromJson(Context context, String recipeJsonStr)
            throws JSONException {

        /* Main information information needed to display */
        final String RECIPE_TITLE = "name";
        final String RECIPE_ID = "id";

        /* Recipe information */
        final String INGREDIENT_LIST = "ingredients";
        /* Main information information needed to display */
        final String INGREDIENT_NAME = "ingredient";
        final String INGREDIENT_MEASURE = "measure";
        final String INGREDIENT_QUANTITY = "quantity";


        JSONArray recipeJSON = new JSONArray(recipeJsonStr);

        ArrayList<Recipes> recipeList = new ArrayList<Recipes>();

        for (int i = 0; i < recipeJSON.length(); i++) {

            Recipes recipe = new Recipes();

            Ingredient ingredient = new Ingredient();

            /* These are the values that will be collected */
            String recipeTitle;
            int recipeId;

            /* Get the JSON object for the recipe */
            JSONObject recipeObject = recipeJSON.getJSONObject(i);

            JSONArray ingredientArray = recipeObject.getJSONArray(INGREDIENT_LIST);

            for (int x = 0; x < ingredientArray.length(); x++) {

                /* These are the values that will be collected */
                String ingredientName;
                String ingredientMeasure;
                String ingredientQuantity;

                /* Get the JSON object for the movie */
                JSONObject ingredientObject = ingredientArray.getJSONObject(x);

                // Extract relevant JSON fields
                ingredientName = ingredientObject.getString(INGREDIENT_NAME);
                ingredientMeasure = ingredientObject.getString(INGREDIENT_MEASURE);
                ingredientQuantity = ingredientObject.getString(INGREDIENT_QUANTITY);

                recipe.setIngredientName(ingredientName);
                recipe.setIngredientMeasure(ingredientMeasure);
                recipe.setIngredientQuantity(ingredientQuantity);

            }


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
