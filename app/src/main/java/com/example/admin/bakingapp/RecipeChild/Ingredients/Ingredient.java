package com.example.admin.bakingapp.RecipeChild.Ingredients;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Admin on 20-May-17.
 */

public class Ingredient implements Parcelable {

    private String ingredientName;
    private String ingredientMeasure;
    private Double ingredientQuantity;

    public Ingredient() {

    }

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


    public Double getIngredientQuantity() {
        return ingredientQuantity;
    }

    public void setIngredientQuantity(Double quantity) {
        this.ingredientQuantity = quantity;
    }


    protected Ingredient(Parcel in) {
        ingredientName = in.readString();
        ingredientMeasure = in.readString();
        ingredientQuantity = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ingredientName);
        dest.writeString(ingredientMeasure);
        dest.writeDouble(ingredientQuantity);
    }


    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Ingredient> CREATOR = new Parcelable.Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

}

