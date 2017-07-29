package com.example.admin.bakingapp.RecipeChild;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.bakingapp.NetworkUtils;
import com.example.admin.bakingapp.R;
import com.example.admin.bakingapp.RecipeChild.Instructions.Instruction;
import com.example.admin.bakingapp.RecipeChild.Instructions.InstructionAdapter;
import com.example.admin.bakingapp.RecipeChild.Instructions.InstructionJSONData;
import com.example.admin.bakingapp.RecipeDisplay.RecipeDisplayChildActivity;
import com.example.admin.bakingapp.RecipeDisplay.RecipeDisplayChildFragment;

import java.net.URL;
import java.util.ArrayList;

public class RecipeChildFragmentTablet extends Fragment implements InstructionAdapter.InstructionAdapterOnClickHandler {

    private static final String SHARE = " #RecipeApp";

    String RECIPE_BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    private Context context;

    private InstructionAdapter mInstructionAdapter;
    private RecyclerView mInstructionRV;

    public RecipeChildFragmentTablet() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_child, container, false);
        context = getActivity();

        mInstructionRV = (RecyclerView) rootView.findViewById(R.id.instruction_rv);

        GridLayoutManager gridInstructionManager = new GridLayoutManager(context, numberOfColumns());

        mInstructionRV.setLayoutManager(gridInstructionManager);

        mInstructionAdapter = new InstructionAdapter(context, (InstructionAdapter.InstructionAdapterOnClickHandler) this);
        mInstructionRV.setAdapter(mInstructionAdapter);
        loadInstructionData();

        return rootView;

    }

    /**
     * This method will load the instruction
     */
    private void loadInstructionData() {
        new InstructionQueryTask().execute();
        showInstructionDataView();
    }

    private void showInstructionDataView() {
        /* Make sure the recipe data is visible */
        mInstructionRV.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(Instruction instruction) {

        // Replace framelayout with new detail fragment
        RecipeDisplayChildFragment fragmentItem = new RecipeDisplayChildFragment();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentDetail, fragmentItem).commit();



    }

    public class InstructionQueryTask extends AsyncTask<String, Void, ArrayList> {

        @Override
        protected ArrayList doInBackground(String... params) {
            URL recipeSearchUrl = NetworkUtils.buildUrl(RECIPE_BASE_URL);
            try {
                String jsonRecipeResponse = NetworkUtils
                        .getResponseFromHttpUrl(recipeSearchUrl);

                ArrayList simpleJsonInstructionData = InstructionJSONData
                        .getInstructionDataStringsFromJson(context, jsonRecipeResponse);

                return simpleJsonInstructionData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList instructionData) {
            mInstructionAdapter.setInstructionData(instructionData);
        }

    }

    private int numberOfColumns() {
        int nColumns = 1;
        return nColumns;
    }

}

