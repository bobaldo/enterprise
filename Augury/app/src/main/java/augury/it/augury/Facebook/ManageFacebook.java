package augury.it.augury.Facebook;

import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import augury.it.augury.Storage.ManageParse;


/**
 * Created by Bobaldo on 30/04/2015.
 */
public class ManageFacebook {
    public void autenticate(String username, String Password) {
    }

    public static void saveFriends(final ParseUser user) {
        if (!ManageParse.getIsLoad(user)) {
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,first_name,last_name,picture");
            parameters.putString("limit", "5000");

            GraphRequest req = new GraphRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/me/taggable_friends",
                    null,
                    HttpMethod.GET,
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            try {
                                JSONObject jsonObj = response.getJSONObject();
                                JSONArray jsonArray = jsonObj.getJSONArray("data");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    try {
                                        JSONObject obj = jsonArray.getJSONObject(i);
                                        ManageParse.insert(obj, user);
                                        System.out.println("Inserito friends numero " + i);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
            req.setParameters(parameters);
            req.executeAsync();
            ManageParse.setIsLoad(user, true);
        }
    }
}