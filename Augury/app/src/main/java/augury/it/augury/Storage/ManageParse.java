package augury.it.augury.Storage;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import augury.it.augury.FriendArrayAdapter;
import augury.it.augury.Model.Friend;
import augury.it.augury.R;
import augury.it.augury.Utility.Constants;

/**
 * Created by Bobaldo on 30/04/2015.
 */
public class ManageParse {
    public void autenticate(String username, String password) {
    }

    public static void insert(JSONObject obj, ParseUser user) throws JSONException {
        ParseObject fri = new ParseObject(Constants.FRIEND);
        fri.put(Constants.FIRSTNAME, obj.get("first_name"));
        fri.put(Constants.LASTNAME, obj.get("last_name"));
        fri.put(Constants.IMAGEURL, obj.getJSONObject("picture").getJSONObject("data").getString("url"));
        fri.put(Constants.ISDELETE, false);
        fri.put(Constants.USER, user);
        fri.saveInBackground();
    }

    public void insert(Friend friend) {
        ParseObject testObject = new ParseObject(Constants.FRIEND);
        testObject.put(Constants.FIRSTNAME, friend.getFirstname());
        testObject.put(Constants.LASTNAME, friend.getLastname());
        testObject.put(Constants.ISDELETE, friend.getIsDelete());
        testObject.put(Constants.IDFACEBOOK, friend.getIdFacebook());
        testObject.put(Constants.IMAGEURL, friend.getImageUrl());
        testObject.saveEventually();
    }

    public void update(Friend friend) {
    }

    public void delete(int id) {
    }

    public Friend get(int id) {
        return null;
    }

    public List<Friend> get() {
        return null;
    }

    public static void getFriends(final ParseUser user, final ListView listView, final Context context) {
        ParseQuery<ParseObject> queryuser = ParseQuery.getQuery(Constants.FRIEND);
        queryuser.whereEqualTo(Constants.USER, user);
        queryuser.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(final List<ParseObject> userList, ParseException e) {
                new AsyncTask<Void, Void, List<Friend>>() {
                    @Override
                    protected List<Friend> doInBackground(Void... voids) {
                        List<Friend> ret = new ArrayList<Friend>();

                        final int size = userList.size();
                        for (int i = 0; i < size; i++) {
                            Friend f = new Friend();
                            ParseObject fri = userList.get(i);
                            f.setFirstname(fri.getString(Constants.FIRSTNAME));
                            f.setLastname(fri.getString(Constants.LASTNAME));
                            f.setIsDelete(fri.getBoolean(Constants.ISDELETE));
                            f.setImageUrl(fri.getString(Constants.IMAGEURL));
                            ret.add(f);
                        }
                        return ret;
                    }

                    @Override
                    protected void onPostExecute(List<Friend> cursor) {
                        try {
                            FriendArrayAdapter adapter = new FriendArrayAdapter(context, R.layout.lista_friend, cursor);
                            listView.setAdapter(adapter);
                        } catch (NullPointerException e) {
                        }
                    }
                }.execute();

            }
        });
    }

    public static void saveUserInfo(final ParseUser userParse) {
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

    public static boolean getIsLoad(ParseUser user) {
        boolean ret = false;
        try {
            ret = user.getBoolean("isLoad");
        } catch (Exception ex) {
        }
        return ret;
    }

    public static void setIsLoad(ParseUser user, boolean valore) {
        user.put("isLoad", valore);
        user.saveInBackground();
    }
}