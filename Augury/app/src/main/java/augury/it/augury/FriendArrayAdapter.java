package augury.it.augury;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import augury.it.augury.Model.Friend;

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
        //image.loadUrl(amico.getImageUrl());
        //image.loadUrl("http://www.mercuriomessenger.com/images/emoticons/icona036.png");

        //ImageView image = (ImageView) view.findViewById(R.id.icona);
        image.setImageResource(R.drawable.paperone);
        //image.setImageResource(new URL(amico.));

        TextView tv = (TextView) view.findViewById(R.id.nomeFriend);
        tv.setText(amico.getFirstname());

        TextView tvCognome = (TextView) view.findViewById(R.id.cognomeFriend);
        tvCognome.setText(amico.getLastname());




        return view;


    }
}
