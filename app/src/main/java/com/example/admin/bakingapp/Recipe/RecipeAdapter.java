package com.example.admin.bakingapp.Recipe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.bakingapp.R;

import java.util.ArrayList;


/**
 * {@link RecipeAdapter} exposes a list of weather forecasts to a
 * {@link android.support.v7.widget.RecyclerView}
 */
public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeAdapterViewHolder> {

    private ArrayList mRecipeData;

    private final RecipeAdapterOnClickHandler mClickHandler;

    /**
     * The interface that receives onClick messages.
     */
    public interface RecipeAdapterOnClickHandler {
        void onClick(Recipe movieForDay);
    }


    /**
     * Creates a RecipeAdapter.
     *
     * @param clickHandler The on-click handler for this adapter. This single handler is called
     *                     when an item is clicked.
     */
    private Context context;

    public RecipeAdapter(Context clickHandler ,RecipeAdapterOnClickHandler recipeAdapterOnClickHandler) {
        context = clickHandler;
        mClickHandler = recipeAdapterOnClickHandler;
    }

    public class RecipeAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mRecipeName;
        //public TextView mMovieReleaseDate;
        //public TextView mMovieRate;
        //public ImageView mMoviePosterView;

        public RecipeAdapterViewHolder(View view) {
            super(view);
            mRecipeName = (TextView) view.findViewById(R.id.recipe_name);
            //mMovieReleaseDate = (TextView) view.findViewById(R.id.tv_movie_releaseDate);
            //mMovieRate = (TextView) view.findViewById(R.id.tv_movie_Rate);
            //mMoviePosterView = (ImageView) view.findViewById(R.id.tv_movie_poster);
            view.setOnClickListener(this);
        }

        /**
         * This gets called by the child views during a click.
         *
         * @param v The View that was clicked
         */

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Recipe recipeDetails = (Recipe) mRecipeData.get(adapterPosition);
            String name = recipeDetails.getRecipeName();
            mClickHandler.onClick(recipeDetails);

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
     * @return A new MovieAdapterViewHolder that holds the View for each list item
     */
    @Override
    public RecipeAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.recipe_list_layout;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new RecipeAdapterViewHolder(view);
    }



    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the movie
     * details for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param recipeAdapterViewHolder The ViewHolder which should be updated to represent the
     *                                  contents of the item at the given position in the data set.
     * @param position                  The position of the item within the adapter's data set.
     */


    @Override
    public void onBindViewHolder(RecipeAdapterViewHolder recipeAdapterViewHolder, int position) {

        Recipe recipeDetails = (Recipe) mRecipeData.get(position);
        /*
        String releaseDate = movieDetails.getMovieReleaseDate();
        String rate = movieDetails.getMovieRate();
        String posterPath = movieDetails.getMoviePosterPath();
        String completedPosterPath = basePictureURL + posterPath;
        */

        String recipeName = recipeDetails.getRecipeName();
        recipeAdapterViewHolder.mRecipeName.setText(recipeName);

    }



    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our forecast
     */
    @Override
    public int getItemCount() {
        if (null == mRecipeData) return 0;
        return mRecipeData.size();
    }

    /**
     * This method is used to set the recipes on a RecipeAdapter if we've already
     * created one. This is handy when we get new data from the web but don't want to create a
     * new RecipeAdapter to display it.
     *
     * @param recipeData The new weather data to be displayed.
     */
    public void setRecipeData(ArrayList recipeData) {
        mRecipeData = recipeData;
        notifyDataSetChanged();
    }

}