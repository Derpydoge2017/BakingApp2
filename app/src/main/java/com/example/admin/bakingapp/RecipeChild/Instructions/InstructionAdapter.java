package com.example.admin.bakingapp.RecipeChild.Instructions;


import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.bakingapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * {@link InstructionAdapter} exposes a list of instructions to a
 * {@link android.support.v7.widget.RecyclerView}
 */
public class InstructionAdapter extends RecyclerView.Adapter<InstructionAdapter.InstructionAdapterViewHolder> {

    private ArrayList mInstructionData;

    /*
     * An on-click handler that we've defined to make it easy for an Activity to interface with
     * our RecyclerView
     */
    private final InstructionAdapterOnClickHandler mClickHandler;

    /**
     * The interface that receives onClick messages.
     */
    public interface InstructionAdapterOnClickHandler {
        void onClick(Instruction instruction);
    }

    /**
     * Creates a InstructionAdapter.
     *
     * @param clickHandler The on-click handler for this adapter. This single handler is called
     *                     when an item is clicked.
     */
    private  Context context;

    public InstructionAdapter(Context clickHandler, InstructionAdapterOnClickHandler instructionAdapterOnClickHandler) {
        context = clickHandler;
        mClickHandler = instructionAdapterOnClickHandler;
    }

    public class InstructionAdapterViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        public TextView mShortDescription;
        public ImageView mThumbnail;

        public InstructionAdapterViewHolder(View view) {
            super(view);
            mShortDescription = (TextView) view.findViewById(R.id.instruction_short);
            mThumbnail = (ImageView) view.findViewById(R.id.instruction_image);

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
            Instruction instructionDetail = (Instruction) mInstructionData.get(adapterPosition);
            mClickHandler.onClick(instructionDetail);

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
     * @return A new InstructionAdapterViewHolder that holds the View for each list item
     */
    @Override
    public InstructionAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.instruction_list_layout;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new InstructionAdapterViewHolder(view);
    }



    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the instruction
     * details for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param instructionAdapterViewHolder The ViewHolder which should be updated to represent the
     *                                  contents of the item at the given position in the data set.
     * @param position                  The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(InstructionAdapterViewHolder instructionAdapterViewHolder, int position) {

        Instruction instructionDetail = (Instruction) mInstructionData.get(position);
        String shortDescription = instructionDetail.getShortDescription();
        String thumbnailURL = instructionDetail.getThumbnailURL();

        instructionAdapterViewHolder.mShortDescription.setText(shortDescription);

        if (thumbnailURL.length() == 0){

            instructionAdapterViewHolder.mThumbnail.setImageResource(0);
            instructionAdapterViewHolder.mThumbnail.setVisibility(View.INVISIBLE);

        } else {

            Picasso.with(context).load(thumbnailURL).into(instructionAdapterViewHolder.mThumbnail);
            instructionAdapterViewHolder.mThumbnail.setVisibility(View.VISIBLE);

        }

    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our forecast
     */
    @Override
    public int getItemCount() {
        if (null == mInstructionData) return 0;
        return mInstructionData.size();
    }

    /**
     * This method is used to set the instructions on a InstructionAdapter if we've already
     * created one. This is handy when we get new data from the web but don't want to create a
     * new InstructionAdapter to display it.
     *
     * @param instructionData The new weather data to be displayed.
     */
    public void setInstructionData(ArrayList instructionData) {
        mInstructionData = instructionData;
        notifyDataSetChanged();
    }

}