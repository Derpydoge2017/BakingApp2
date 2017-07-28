package com.example.admin.bakingapp.RecipeChild.Ingredients;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.bakingapp.Data.RecipeContract;
import com.example.admin.bakingapp.R;

import java.util.ArrayList;

/**
 * {@link IngredientAdapter} exposes a list of ingredients to a
 * {@link android.support.v7.widget.RecyclerView}
 */
public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientAdapterViewHolder> {

    private ArrayList mIngredientData;

    /**
     * Creates a IngredientAdapter.
     */

    public IngredientAdapter() {
    }

    public class IngredientAdapterViewHolder extends RecyclerView.ViewHolder {
        public TextView mIngredientName;
        public TextView mIngredientMeasure;
        public TextView mIngredientQuantity;

        public IngredientAdapterViewHolder(View view) {
            super(view);
            mIngredientName = (TextView) view.findViewById(R.id.ingredient_name);
            mIngredientMeasure = (TextView) view.findViewById(R.id.ingredient_measure);
            mIngredientQuantity = (TextView) view.findViewById(R.id.ingredient_quantity);
        }
    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param viewGroup The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item (which ours doesn't) you
     *                  can use this viewType integer to provide a different layout. See
     *                  {@link android.support.v7.widget.RecyclerView.Adapter#getItemViewType(int)}
     *                  for more details.
     * @return A new IngredientAdapterViewHolder that holds the View for each list item
     */
    @Override
    public IngredientAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.ingredient_list_layout;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new IngredientAdapterViewHolder(view);
    }



    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the ingredient
     * details for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param ingredientAdapterViewHolder The ViewHolder which should be updated to represent the
     *                                  contents of the item at the given position in the data set.
     * @param position                  The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(IngredientAdapterViewHolder ingredientAdapterViewHolder, int position) {

        Ingredient ingredientDetails = (Ingredient) mIngredientData.get(position);
        String name = ingredientDetails.getIngredientName();
        String measure = ingredientDetails.getIngredientMeasure();
        Double quantity = ingredientDetails.getIngredientQuantity();

        ingredientAdapterViewHolder.mIngredientName.setText(name);
        ingredientAdapterViewHolder.mIngredientMeasure.setText(measure);
        ingredientAdapterViewHolder.mIngredientQuantity.setText(String.valueOf(quantity));

    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our forecast
     */
    @Override
    public int getItemCount() {
        if (null == mIngredientData) return 0;
        return mIngredientData.size();
    }

    /**
     * This method is used to set the ingreidents on a IngredientAdapter if we've already
     * created one. This is handy when we get new data from the web but don't want to create a
     * new IngredientAdapter to display it.
     *
     * @param ingredientData The new weather data to be displayed.
     */
    public void setIngredientData(ArrayList ingredientData) {
        mIngredientData = ingredientData;
        notifyDataSetChanged();
    }

}