package com.example.admin.bakingapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.SystemClock;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.admin.bakingapp.RecipeChild.RecipeChild;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNot.not;


/**
 * Created by Admin on 16-Jul-17.
 */

@RunWith(AndroidJUnit4.class)
public class RecipeChildIntentEspressoTest {

    private final String PACKAGE_NAME = "com.example.admin.bakingapp.RecipeChild.RecipeChild";

    @Rule
    public IntentsTestRule<RecipeChild> mActivityRule =
            new IntentsTestRule<>(RecipeChild.class);

    @Before
    public void stubAllExternalIntents() {
        // By default Espresso Intents does not stub any Intents. Stubbing needs to be setup before
        // every test run. In this case all external Intents will be blocked.
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }


    @Test
    public void clickRecipeSteps_opensStepsActivity() {

        SystemClock.sleep(1000);

        // Build a result to return when a particular activity is launched.
        Intent intent = new Intent();
        intent.putExtra(RecipeChild.STEP_DETAILS, 2);
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, intent);

        // Set up result stubbing when an intent sent to "details" is seen.
        intending(toPackage(PACKAGE_NAME)).respondWith(result);

        // User action that results in step details activity being launched.
        onView(withId(R.id.instruction_rv)).perform(click());
    }

}
