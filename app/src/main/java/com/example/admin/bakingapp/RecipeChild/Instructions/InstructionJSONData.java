package com.example.admin.bakingapp.RecipeChild.Instructions;


import android.content.Context;

import com.example.admin.bakingapp.RecipeChild.Ingredients.Ingredient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public final class InstructionJSONData {
    /**
     * @param instructionJsonStr JSON response from server
     * @return Array of Strings describing ingredient data
     * @throws JSONException If JSON data cannot be properly parsed
     */
    public static ArrayList<Instruction> getInstructionDataStringsFromJson(Context context, String instructionJsonStr)
            throws JSONException {

        /* Instruction information */
        final String INSTRUCTION_LIST = "steps";

        /* Main information information needed to display */
        final String INSTRUCTION_SHORT_DESCRIPTION = "shortDescription";
        final String INSTRUCTION_LONG_DESCRIPTION = "description";
        final String INSTRUCTION_ID = "id";
        final String INSTRUCTION_VIDEO = "videoURL";
        final String INSTRUCTION_IMAGE = "thumbnailURL";

        JSONArray recipeArray = new JSONArray(instructionJsonStr);

        ArrayList<Instruction> instructionList = new ArrayList<Instruction>();

        for (int i = 0; i < recipeArray.length(); i++) {

            JSONObject recipeJSON = recipeArray.getJSONObject(i);

            JSONArray instructionArray = recipeJSON.getJSONArray(INSTRUCTION_LIST);

            for (int x = 0; x < instructionArray.length(); x++) {

                Instruction instruction = new Instruction();

                /* These are the values that will be collected */
                String shortDescription;
                String longDescription;
                Integer id;
                String videoUrl;
                String thumbnailURL;

                /* Get the JSON object for the movie */
                JSONObject instructionObject = instructionArray.getJSONObject(x);

                // Extract relevant JSON fields
                shortDescription = instructionObject.getString(INSTRUCTION_SHORT_DESCRIPTION);
                longDescription = instructionObject.getString(INSTRUCTION_LONG_DESCRIPTION);
                id = instructionObject.getInt(INSTRUCTION_ID);
                videoUrl = instructionObject.getString(INSTRUCTION_VIDEO);
                thumbnailURL = instructionObject.getString(INSTRUCTION_IMAGE);

                instruction.setShortDescription(shortDescription);
                instruction.setLongDescription(longDescription);
                instruction.setInstructionId(id);
                instruction.setVideoURL(videoUrl);
                instruction.setThumbnailURL(thumbnailURL);


                instructionList.add(instruction);


            }



        }

        return instructionList;

    }
}

