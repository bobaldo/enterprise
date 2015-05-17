package augury.it.augury;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import augury.it.augury.Facebook.ManageFacebook;
import augury.it.augury.Model.Friend;
import augury.it.augury.Storage.ManageParse;

public class MainActivity extends ListActivity implements AdapterView.OnItemClickListener {
    private ManageFacebook manageFacebook = new ManageFacebook();
    private ManageParse manageParse = new ManageParse();
    private ListView lista;
    private Context context;
    private List<Friend> listaAmici;
    private FriendArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = this;
        Toast.makeText(getApplicationContext(), "PD", Toast.LENGTH_LONG).show();

        //manageParse.insert(new Friend("davide", "patrizi"));

        lista = (ListView) findViewById(android.R.id.list);

        /*
        //TODO: eliminare
        listaAmici = manageFacebook.getFriends();
        adapter = new FriendArrayAdapter(context, R.layout.lista_friend, listaAmici);
        setListAdapter(adapter);
        */

        lista.setOnItemClickListener(this);

        ArrayList<String> permission = new ArrayList<String>();
        permission.add("public_profile");

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

        ParseFacebookUtils.logInWithReadPermissionsInBackground(this, permission, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException err) {
                if (user == null) {
                    Log.d("MyApp", "LogIn. The user cancelled the Facebook login.");
                    Toast.makeText(getApplicationContext(), "Errore", Toast.LENGTH_SHORT).show();

                    //Toast toast = Toast.makeText(getApplicationContext(), "LogIn. The user cancelled the Facebook login.", Toast.LENGTH_SHORT);
                } else if (user.isNew()) {
                    listaAmici = manageFacebook.getFriends(manageParse);

                    adapter = new FriendArrayAdapter(context, R.layout.lista_friend, listaAmici);
                    setListAdapter(adapter);
                    Log.d("MyApp", "LogIn. User signed up and logged in through Facebook!");
                    Toast.makeText(getApplicationContext(), "ciao", Toast.LENGTH_SHORT).show();
                    //Toast toast = Toast.makeText(getApplicationContext(), "LogIn. User signed up and logged in through Facebook!", Toast.LENGTH_SHORT);
                } else {
                    saveUserInfo(user);
                    manageFacebook.getAllFriends();
                    listaAmici = manageFacebook.getFriends();
                    adapter = new FriendArrayAdapter(context, R.layout.lista_friend, listaAmici);
                    setListAdapter(adapter);
                    Log.d("MyApp", "LogIn. User logged in through Facebook!");
                    Toast.makeText(getApplicationContext(), "ciao", Toast.LENGTH_SHORT).show();
                    //Toast toast = Toast.makeText(getApplicationContext(), "LogIn. User logged in through Facebook!", Toast.LENGTH_SHORT);
                }
            }
        });
    }

    private void saveUserInfo(final ParseUser userParse) {

        GraphRequestAsyncTask request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject user, GraphResponse response) {
                if (user != null) {
                    userParse.put("idFacebook", user.optString("id"));
                    userParse.put("nameFacebook", user.optString("name"));
                    userParse.saveEventually();
                }
            }
        }).executeAsync();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//        Bundle b = amico.toBundle();


        Log.v("ecco la posizione ", Integer.toString(position));
        Intent intent = new Intent(this, DettaglioFriend.class);
        // intent.putExtra(FRIEND_BUNDLE, b);
        startActivity(intent);
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