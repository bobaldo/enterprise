package augury.it.augury;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import augury.it.augury.Model.Friend;
import augury.it.augury.Utility.DownloadImageTask;

/**
 * Created by innocenzo on 11/05/15.
 */
public class FriendArrayAdapter extends ArrayAdapter<Friend> {

    private Context context;
    Friend amico;
    private List<Friend> objects;
    //private List<Friend> friendList;

    public FriendArrayAdapter(Context context, int resource, List<Friend> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        amico = getItem(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.lista_friend, null);

        ImageView image = (ImageView) view.findViewById(R.id.icona);
        //Log.d("App PD", amico.getImageUrl());

        FileInputStream imgFile = null;
        try {
            if(amico.getImageLocal() != null) {
                imgFile = context.openFileInput(amico.getImageLocal());
                if (imgFile != null) {
                    Bitmap myBitmap = BitmapFactory.decodeStream(imgFile);
                    image.setImageBitmap(myBitmap);
                } else {
                    new DownloadImageTask(context, image).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, amico);
                    image.setImageResource(R.drawable.paperone);
                }
            }
            else{
                new DownloadImageTask(context, image).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, amico);
                image.setImageResource(R.drawable.paperone);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //image.setImageResource(new URL(amico.));

        TextView tv = (TextView) view.findViewById(R.id.nomeFriend);
        tv.setText(amico.getFirstname());

        TextView tvCognome = (TextView) view.findViewById(R.id.cognomeFriend);
        tvCognome.setText(amico.getLastname());

        return view;

    }
}
