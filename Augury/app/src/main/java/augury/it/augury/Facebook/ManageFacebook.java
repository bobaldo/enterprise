package augury.it.augury.Facebook;

import android.content.Context;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import augury.it.augury.Storage.ManageParse;


/**
 * Created by Bobaldo on 30/04/2015.
 */
public class ManageFacebook {
    public static void saveFriends(final ParseUser user, final Context context) {
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
                                ArrayList<ParseObject> friendList= new ArrayList<ParseObject>();

                                for (int i = 0; i < 10; i++) {
                                    try {
                                        JSONObject obj = jsonArray.getJSONObject(i);

                                        ParseObject friendIns = ManageParse.insert(obj, user);
                                        friendList.add(friendIns);

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
        }
    }
}