package com.bowtaps.crowdcontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.bowtaps.crowdcontrol.model.ParseUserModel;
import com.bowtaps.crowdcontrol.model.ParseUserProfileModel;
import com.parse.ParseUser;

/*
 *  This Activity is the first thing launched by the app.
 *  It determines if the user is logged in and either launches
 *  the {@Link SignupActivity} or launches the {@Link GroupJoinActivity}
 *
 *  @see SignupActivity
 *  @see GroupJoinActivity
 */
public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    Button mButtonCreateAccount;
    ParseUserProfileModel mParseUserProfileModel = new ParseUserProfileModel(CrowdControlApplication.aProfile);
    ParseUserModel mParseUserModel = new ParseUserModel(CrowdControlApplication.aUser);
    ParseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mParseUserModel.getCurrentUser();
        mUser = CrowdControlApplication.aUser;

        // Get handles to buttons
        mButtonCreateAccount = (Button) findViewById(R.id.buttonToCreate);

        //Declare button clicks
        mButtonCreateAccount.setOnClickListener(this);

        //This Determines if a user is logged in from a previous run
        if(mUser != null && mUser.getObjectId() != null){
            //TODO: This functionality is implying that the GroupJoinActivity is the "Home" page for a logged in user.
            mParseUserModel.updateUser();
            //mParseUserProfileModel.initializeFromUser(CrowdControlApplication.aUser);
            //CrowdControlApplication.aProfile = mUser.getParseObject("CCUser");

            launchGroupJoinActivity();
            finish();

        }
        else{
            launchLoginActivity();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        // Handles clicks onn items in view
        // in this case, either the facebook button or the create account button

        switch (view.getId()) {
            case R.id.buttonToCreate:
                onCreateAccountButtonClick((Button) view);
                break;

            default:
                // Sorry, you're outta luck
                break;
        }
    }

    /**
     * Handles clicks on the buttonToCreate button. Simply launches the {@link SignupActivity}.
     *
     * @param button  The button object that was clicked.
     * @see           SignupActivity
     */
    private void onCreateAccountButtonClick(Button button) {
        launchLoginActivity();
    }

    /**
     * Launches the {@link SignupActivity}.
     *
     * @see SignupActivity
     */
    private void launchLoginActivity() {
        Intent myIntent = new Intent(this, SignupActivity.class);
        this.startActivity(myIntent);
    }


    /**
     * Launches the {@link GroupJoinActivity}.
     *
     * @see GroupJoinActivity
     */
    private void launchGroupJoinActivity() {
        Intent myIntent = new Intent(this,GroupJoinActivity.class);
        this.startActivity(myIntent);
    }

}
