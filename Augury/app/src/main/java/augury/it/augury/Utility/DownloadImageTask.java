package augury.it.augury.Utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import augury.it.augury.Model.Friend;

/**
 * Created by michael on 07/06/15.
 */
public class DownloadImageTask extends AsyncTask<Friend, Void, Bitmap> {

    private final Context context;
    private final WeakReference<ImageView> imageViewReference;

    public DownloadImageTask(Context context, ImageView imageView)
    {

        this.context = context;
        imageViewReference = new WeakReference<ImageView>(imageView);
    }


    @Override
    protected Bitmap doInBackground(Friend... obj) {

        return download_Image(obj[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {

        if (imageViewReference != null) {
            ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }

    }


    private Bitmap download_Image(Friend obj) {

        FileOutputStream outputStream;
       // ParseObject obj;
        String filename;
        Bitmap myBitmap = null;

        for(int i=0; i<1; i++) {
                    try {

                    //obj = list.get(i);
                    System.out.println("Inside saving photo");
                    filename = obj.getParseId() + ".jpg";

                    InputStream is = new URL(obj.getImageUrl()).openStream();
                    myBitmap = BitmapFactory.decodeStream(is);
                    outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);

                    // Writing the bitmap to the output stream
                    myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    outputStream.close();

                    ParseQuery<ParseObject> query = ParseQuery.getQuery(Constants.FRIEND);
                    ParseObject friend = query.get(obj.getParseId());
                    if (friend != null) {
                        friend.put("imageLocal", friend.getObjectId() + ".jpg");
                        friend.save();
                        obj.setImageLocal(friend.getObjectId() + ".jpg");
                    } else {
                        System.out.println("Error wtiting image path on parse!!!!!!!!!!");

                    }

                        } catch (FileNotFoundException e1) {
                            filename = "";

                        } catch (Exception e) {
                            System.out.println("------------------"+e);

                        }
                }

        return myBitmap;
    }
}
