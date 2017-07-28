package com.example.admin.bakingapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.admin.bakingapp.RecipeChild.Ingredients.Ingredient;

import java.util.List;

/**
 * Created by Admin on 14-Jul-17.
 */

public class Recipes implements Parcelable{

    private Integer recipeId;
    private String recipeName;

    private List<Ingredient> ingredientList;

    public Recipes() {

    }

    //RecipeName
    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer id) {
        this.recipeId = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String name) {
        this.recipeName = name;
    }

    //Ingredients
    private String ingredientName;
    private String ingredientMeasure;
    private String ingredientQuantity;

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String name) {
        this.ingredientName = name;
    }


    public String getIngredientMeasure() {
        return ingredientMeasure;
    }

    public void setIngredientMeasure(String measure) {
        this.ingredientMeasure = measure;
    }

    public String getIngredientQuantity() {
        return ingredientQuantity;
    }

    public void setIngredientQuantity(String quantity) {
        this.ingredientQuantity = quantity;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList (List<Ingredient> list){
        ingredientList = list;
    }



    protected Recipes(Parcel in) {
        recipeId = in.readInt();
        recipeName = in.readString();

        ingredientName = in.readString();
        ingredientMeasure = in.readString();
        ingredientQuantity = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(recipeId);
        dest.writeString(recipeName);

        dest.writeString(ingredientName);
        dest.writeString(ingredientMeasure);
        dest.writeString(ingredientQuantity);
    }


    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Recipes> CREATOR = new Parcelable.Creator<Recipes>() {
        @Override
        public Recipes createFromParcel(Parcel in) {
            return new Recipes(in);
        }

        @Override
        public Recipes[] newArray(int size) {
            return new Recipes[size];
        }
    };



}
