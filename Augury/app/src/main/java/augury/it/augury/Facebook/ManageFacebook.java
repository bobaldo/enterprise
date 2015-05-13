package augury.it.augury.Facebook;

import android.app.Application;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphRequestBatch;
import com.facebook.GraphResponse;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import augury.it.augury.Model.Friend;
import augury.it.augury.Storage.ManageParse;


/**
 * Created by Bobaldo on 30/04/2015.
 */
public class ManageFacebook {
    public void autenticate(String username, String Password) {
    }

    private List<Friend> friends = new ArrayList<>();
    //private ManageFacebook manageFacebook;

    public List<Friend> getFriends() {
        friends.add(new Friend("Pippo", "Pippo"));
        friends.add(new Friend("Paperino", "Pippo"));
        friends.add(new Friend("Topolino", "Pippo"));
        friends.add(new Friend("QuiQuoQua", "Pippo"));
        friends.add(new Friend("Paperone", "Pippo"));
        return friends;
    }

    public void addAllFriends(final ManageParse manageParse){
        //GraphRequestAsyncTask request = GraphRequest.newMyFriendsRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONArrayCallback() {

        Bundle params = new Bundle();
        params.putString("fields", "id,first_name,birthday");



                    GraphRequest g = GraphRequest.newMyFriendsRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONArrayCallback() {
                        @Override
                        public void onCompleted(
                                JSONArray jsonArray,
                                GraphResponse response) {

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject obj = null;
                                try {
                                    obj = jsonArray.getJSONObject(i);
                                    manageParse.insert(new Friend(obj.getString("first_name"), obj.getString("last_name")));
                                    obj.getString("id");
                                    obj.getString("birthday");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    });


        g.setParameters(params);
        GraphRequestBatch batch = new GraphRequestBatch(g);
            batch.addCallback(new GraphRequestBatch.Callback() {
                @Override
                public void onBatchCompleted(GraphRequestBatch graphRequests) {
                    //Toast.makeText(, "Add all friends on cloud!", Toast.LENGTH_SHORT).show();
                }
            });

            batch.executeAsync();



            /*@Override
            public void onCompleted(JSONArray jsonArray, GraphResponse response) {
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject obj = null;
                    try {
                        obj = jsonArray.getJSONObject(i);
                        manageParse.insert(new Friend(obj.getString("first_name"), obj.getString("last_name")));
                        obj.getString("id");
                        obj.getString("birthday");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).executeAsync();*/

        /*Bundle parameters = new Bundle();
        parameters.putString("fields", "id,first_name,last_name, birthday");
        request.setParameters(parameters);
        request.executeAsync();*/
    }
}