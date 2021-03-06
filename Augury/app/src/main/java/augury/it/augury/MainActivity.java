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

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.io.File;
import java.util.ArrayList;

import augury.it.augury.Facebook.ManageFacebook;
import augury.it.augury.Model.Friend;
import augury.it.augury.Storage.ManageParse;

public class MainActivity extends ListActivity implements AdapterView.OnItemClickListener {
    private ListView listFriends;
    private Context context;
    Friend amico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = this;
        listFriends = (ListView) findViewById(android.R.id.list);
        listFriends.setOnItemClickListener(this);

        //TODO: capire a cosa serve, nel caso camcellarlo
        File dirFiles = context.getFilesDir();
        for (String strFile : dirFiles.list())
        {
            System.out.println("---"+strFile);
        }

        try {
            ParseUser user = ParseUser.getCurrentUser();
            if (user != null) {
                if (ManageParse.getIsLoad(user)) {
                    ManageParse.getFriends(user, listFriends, context);
                } else {
                    loginAndLoadFriends();
                }
            } else {
                loginAndLoadFriends();
            }
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Errore", Toast.LENGTH_SHORT).show();
        }
    }

    public void loginAndLoadFriends() {
        ArrayList<String> permission = new ArrayList<String>();
        permission.add("public_profile");
        ParseFacebookUtils.logInWithReadPermissionsInBackground(this, permission, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException err) {
                if (user == null) {
                    Log.d("MyApp", "LogIn. The user cancelled the Facebook login.");
                    Toast.makeText(getApplicationContext(), "Errore nella creazione dell'utente - contattare il centro servizio", Toast.LENGTH_SHORT).show();
                } else if (user.isNew()) {
                    ManageParse.saveUserInfo(user);
                    ManageFacebook.saveFriends(user, context);
                    ManageParse.getFriends(user, listFriends, context);
                    ManageParse.setIsLoad(user, true);
                    Log.d("MyApp", "LogIn. User logged in through Facebook!");
                    Log.d("SERVIZIO", "sei entrato nel ManageParse.getFriends()");
                } else {
                    ManageParse.saveUserInfo(user);
                    ManageFacebook.saveFriends(user, context);
                    ManageParse.getFriends(user, listFriends, context);
                    ManageParse.setIsLoad(user, true);
                    Log.d("MyApp", "LogIn. User logged in through Facebook!");
                    Log.d("SERVIZIO", "sei entrato nel ManageParse.getFriends()-2");
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        amico = (Friend) listFriends.getItemAtPosition(position);
        Log.d("listAmico", amico.getFirstname());
        Log.d("listAmico", amico.getLastname());

        Bundle b = amico.toBundle();
        Intent i = new Intent(this, DettaglioFriend.class);
        i.putExtra("nome_amico", amico.getFirstname());
        i.putExtra("cognome_amico", amico.getLastname());
        i.putExtra("image_amico", amico.getImageLocal());
        startActivity(i);
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