package augury.it.augury;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseInstallation;

/**
 * Created by mursi_000 on 02/05/2015.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "IxxqhUgzaiMY86iRjUQUhqtO3z0BiIRLX2NgrDaJ", "YyZqabhX8tdIZ7IsnAIfNt4KjtaVWZET6tlXr6WO");

        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParseFacebookUtils.initialize(getApplicationContext());

    }
}