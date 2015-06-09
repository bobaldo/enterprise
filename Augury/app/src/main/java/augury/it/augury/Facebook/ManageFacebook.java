package augury.it.augury.Facebook;

import android.content.Context;
import android.os.AsyncTask;
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
import augury.it.augury.Utility.DownloadImageTask;


/**
 * Created by Bobaldo on 30/04/2015.
 */
public class ManageFacebook {
    public void autenticate(String username, String Password) {
    }

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

                                for (int i = 0; i < 4; i++) {
                                    try {
                                        JSONObject obj = jsonArray.getJSONObject(i);

                                        ParseObject friendIns = ManageParse.insert(obj, user);
                                        friendList.add(friendIns);

                                        //new DownloadImageTask(context).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, friendIns);
                                        System.out.println("Inserito friends numero " + i);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                new DownloadImageTask(context).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, friendList);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
            req.setParameters(parameters);
            req.executeAsync();
            //ManageParse.setIsLoad(user, true);
        }
    }

    /*private static String savePhotoUser(final JSONObject obj,final Context context) {

        Bitmap bmp;

        FileOutputStream outputStream = null;
        String filename = "";
        try {
            try{
                System.out.println("Inside saving photo");
                filename = obj.get("first_name").toString() + '_' + obj.get("last_name").toString() + '_' + System.currentTimeMillis() + ".jpg";
                URL url = new URL("http://res.cloudinary.com/demo/image/upload/q_90/happy_dog.jpg");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);

                outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);

                // Writing the bitmap to the output stream
                myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.close();

            } catch (FileNotFoundException e1) {
                filename = "";
            }
        } catch (Exception e) {
            filename = "";
        }
        return filename;
    }*/
}