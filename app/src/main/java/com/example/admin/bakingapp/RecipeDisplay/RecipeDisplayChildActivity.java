package com.example.admin.bakingapp.RecipeDisplay;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.bakingapp.Data.RecipeContract;
import com.example.admin.bakingapp.R;
import com.example.admin.bakingapp.RecipeChild.Instructions.Instruction;

/**
 * Created by Admin on 11-Jul-17.
 */

public class RecipeDisplayChildActivity extends AppCompatActivity {

    private Instruction mInstruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_display);
        if (getIntent() != null) {
            //Get EXTRA from intent and attach to Fragment as Argument
            mInstruction = getIntent().getParcelableExtra("android.intent.extra.TITLE");

            Bundle args = new Bundle();

            args.putParcelable("ARGUMENTS", mInstruction);

            RecipeDisplayChildFragment displayChildFragment = new RecipeDisplayChildFragment();
            displayChildFragment.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.recipeDisplayContainer, displayChildFragment).commit();

        }
    }

}
