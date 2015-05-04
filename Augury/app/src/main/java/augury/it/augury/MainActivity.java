package augury.it.augury;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private Context context;
    CallbackManager callbackManager;
    private LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);
    }

    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();

            Toast.makeText(context, "Collegato a Facebook!", Toast.LENGTH_SHORT).show();

            //shareButton.setVisibility(View.VISIBLE);

            /*ShareLinkContent shareLinkContent = new ShareLinkContent.Builder()
                    .setContentTitle("Nuova Piada Condivisa!")
                    .setContentDescription("Ecco la mia nuova piada: ")
                    .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
                    .build();*/


           /* shareDialog = new ShareDialog(getActivity());
            if(shareDialog.canShow(ShareLinkContent.class)) {
                ShareLinkContent shareLinkContent = new ShareLinkContent.Builder()
                        .setContentTitle("Nuova Piada Condivisa!")
                        .setContentDescription("Ecco la mia nuova piada: ")
                        .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
                        .build();
                shareDialog.show(shareLinkContent);
            }*/

            //shareButton.setShareContent(shareLinkContent);
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                callbackManager = CallbackManager.Factory.create();
                loginButton.setReadPermissions("user_friends");
                loginButton.registerCallback(callbackManager, mCallback);
                break;
        }
    }
}