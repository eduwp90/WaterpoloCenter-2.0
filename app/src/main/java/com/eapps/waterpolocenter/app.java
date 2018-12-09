package com.eapps.waterpolocenter;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
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
                .applicationId("Jbp3tpUJvfm54iaYts9Q8bcmXR7EUMt3WUmgsQCD")
                .clientKey("WWQuoaJupwil4fc1PbogqW1M1jFWJXIE2ul3xi3V")
                .server("https://wpcenter.herokuapp.com/Parse/")

                .build()
        );
        // ...
        ParseInstallation.getCurrentInstallation().saveInBackground();

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

    }

}
