package augury.it.augury.Utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import augury.it.augury.Model.Friend;

/**
 * Created by michael on 07/06/15.
 */
public class DownloadImageTask extends AsyncTask<ArrayList<ParseObject>, Void, String> {

    private Context context;

    public DownloadImageTask(Context context)
    {
        this.context = context;
    }


    @Override
    protected String doInBackground(ArrayList<ParseObject>... obj) {

        return download_Image(obj[0]);
    }

    @Override
    protected void onPostExecute(String result) {

        System.out.println("Saved: "+result);

    }


    private String download_Image(ArrayList<ParseObject> list) {
        Bitmap bmp;

        FileOutputStream outputStream = null;
        ParseObject obj;
        String filename = "";
        try {
            try{
                for(int i=0; i<4; i++) {

                    obj = list.get(i);
                    System.out.println("Inside saving photo");
                    filename = obj.getObjectId().toString() + ".jpg";
                    URL url = new URL(obj.get(Constants.IMAGEURL).toString());
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    Bitmap myBitmap = BitmapFactory.decodeStream(input);

                    outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);

                    // Writing the bitmap to the output stream
                    myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    outputStream.close();

                    ParseQuery<ParseObject> query = ParseQuery.getQuery(Constants.FRIEND);
                    ParseObject friend = query.get(obj.getObjectId().toString());
                    if (friend != null) {
                        friend.put("imageLocal", friend.getObjectId().toString() + ".jpg");
                        friend.save();
                    } else {
                        System.out.println("Error wtiting image path on parse!!!!!!!!!!");

                    }
                    /*query.getInBackground(obj.getObjectId().toString(), new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject friend, ParseException e) {
                            if (e == null) {
                                friend.put("imageLocal", friend.getObjectId().toString() + ".jpg");
                                friend.saveInBackground();
                            } else {
                                System.out.println("Error wtiting image path on parse!!!!!!!!!!");
                                System.out.println(e);
                            }
                        }
                    });*/
                }

            } catch (FileNotFoundException e1) {
                filename = "";
            }
        } catch (Exception e) {
            filename = "";
        }

        return filename;
    }
}
