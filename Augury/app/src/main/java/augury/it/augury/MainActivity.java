package augury.it.augury;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ListActivity implements AdapterView.OnItemClickListener {

    ListView lista;

    ArrayList<String> amici = new ArrayList<String>();

    public void loadData() {
        amici.add("Pippo");
        amici.add("Paperino");
        amici.add("Topolino");
        amici.add("QuiQuoQua");
        amici.add("Paperone");

     }








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(android.R.id.list);
        amici.add("Pippo");
        amici.add("Paperino");
        amici.add("Topolino");
        amici.add("QuiQuoQua");
        amici.add("Paperone");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.lista_friend, amici);
        setListAdapter(adapter);

        lista.setOnItemClickListener(this);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.v("ecco la posizione ", Integer.toString(position));
        Intent intent = new Intent(this, dettaglioFriend.class);
        intent.putExtra("POSIZIONE", position);
        startActivity(intent);
    }
}
