package augury.it.augury;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(), "Ciaooooooo", Toast.LENGTH_LONG).show();
      /*  ParseObject testObject = new ParseObject("Friend");
        testObject.put("firstname", "stefano");
        testObject.put("lastname", "musaico");
        testObject.saveEventually();*/
        ArrayList<String> permission = new ArrayList<String>();
        permission.add("friends_birthday");
       /* ParseFacebookUtils.logInInBackground(AccessToken.getCurrentAccessToken(), new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    // Hooray! The user is logged in.
                } else {
                    // Signup failed. Look at the ParseException to see what happened.
                }
            }
        });*/
      /* ParseFacebookUtils.logInWithReadPermissionsInBackground(this, permission, new LogInCallback() {
           @Override
           public void done(ParseUser user, ParseException err) {
               if (user == null) {
                   Log.d("MyApp", "Uh oh. The user cancelled the Facebook login.");
               } else if (user.isNew()) {
                   Log.d("MyApp", "User signed up and logged in through Facebook!");
               } else {
                   Log.d("MyApp", "User logged in through Facebook!");
               }
           }
       });*/

        ParseFacebookUtils.logInWithReadPermissionsInBackground(this, Arrays.asList("public_profile"), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException err) {
                if (user == null) {
                    Log.d("MyApp", "LogIn. The user cancelled the Facebook login.");

                    Toast.makeText(getApplicationContext(), "Errore", Toast.LENGTH_SHORT).show();

                    //Toast toast = Toast.makeText(getApplicationContext(), "LogIn. The user cancelled the Facebook login.", Toast.LENGTH_SHORT);
                } else if (user.isNew()) {
                    Log.d("MyApp", "LogIn. User signed up and logged in through Facebook!");
                    Toast.makeText(getApplicationContext(), "ciao", Toast.LENGTH_SHORT).show();
                    //Toast toast = Toast.makeText(getApplicationContext(), "LogIn. User signed up and logged in through Facebook!", Toast.LENGTH_SHORT);
                } else {
                    Log.d("MyApp", "LogIn. User logged in through Facebook!");
                    Toast.makeText(getApplicationContext(),"ciao", Toast.LENGTH_SHORT).show();
                    //Toast toast = Toast.makeText(getApplicationContext(), "LogIn. User logged in through Facebook!", Toast.LENGTH_SHORT);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}