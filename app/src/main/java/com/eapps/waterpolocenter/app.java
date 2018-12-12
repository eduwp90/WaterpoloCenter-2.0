package com.eapps.waterpolocenter;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseUser;

/**
 * Created by Edu Hackintosh on 31/08/2016.
 */
public class app extends Application {

    @Override
    public void onCreate()
    {
        super.onCreate();
        Parse.enableLocalDatastore(this);

        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId(getString(R.string.ParseAppID))
                .clientKey(getString(R.string.ParseClientKey))
                .server(getString(R.string.ParseServerURL))

                .build()
        );
        // ...
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParsePush.subscribeInBackground("Test");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

    }

}
