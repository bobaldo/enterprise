package augury.it.augury;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;

import augury.it.augury.Model.Friend;

public class DettaglioFriend extends ActionBarActivity {

    String nomeAmico;
    String cognomeAmico;
    String imageAmico;
    String ris;

    Friend amico;

    TextView nomeRisultato;
    TextView cognomeRisultato;
    ImageView immagineRisultato;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettaglio_friend);

        nomeRisultato = (TextView) findViewById(R.id.nomeRisultato);
        cognomeRisultato = (TextView) findViewById(R.id.cognomeRisultato);
        immagineRisultato = (ImageView) findViewById(R.id.immagineFriend);
        i = getIntent();
        Log.d("nome_amico", i.getStringExtra("nome_amico"));
        nomeAmico = i.getStringExtra("nome_amico");
        cognomeAmico = i.getStringExtra("cognome_amico");
        imageAmico = i.getStringExtra("image_amico");

        FileInputStream imgFile = null;
        try {
            if (imageAmico != null) {
                imgFile = this.openFileInput(imageAmico);
                if (imgFile != null) {
                    Bitmap myBitmap = BitmapFactory.decodeStream(imgFile);
                    immagineRisultato.setImageBitmap(myBitmap);
                }
            } else {
                immagineRisultato.setImageResource(R.drawable.paperone);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        badName();
    }

    public void badName() {

        //nomeAmico = amico.getFirstname();
        //cognomeAmico = amico.getLastname();

        //Log.d("cognomeAmico", cognomeAmico.toString());

        String firstNameChar = new String();
        String secondNameChar = new String();
        firstNameChar = nomeAmico.substring(0, 1);
        secondNameChar = cognomeAmico.substring(0, 1);

        Log.d("LETTERA_NOME: ", firstNameChar);
        Log.d("LETTERA_COGNOME: ", secondNameChar);

        switch (firstNameChar.toLowerCase()) {
            case "a":
                nomeAmico = "La Cattiva";
                break;
            case "b":
                nomeAmico = "Il Matto";
                break;
            case "c":
                nomeAmico = "Il Grande";
                break;
            case "d":
                nomeAmico = "Il Pericoloso";
                break;
            case "e":
                nomeAmico = "Capitan";
                break;
            case "f":
                nomeAmico = "L'Ombroso";
                break;
            case "g":
                nomeAmico = "Professor";
                break;
            case "h":
                nomeAmico = "Dottor";
                break;
            case "i":
                nomeAmico = "Fantasma";
                break;
            case "l":
                nomeAmico = "Il Brutale";
                break;
            case "m":
                nomeAmico = "L'inafferrabile";
                break;
            case "n":
                nomeAmico = "Il Vil";
                break;
            case "o":
                nomeAmico = "L'Oscuro";
                break;
            case "p":
                nomeAmico = "Il Pazzo";
                break;
            case "q":
                nomeAmico = "l'Iron";
                break;
            case "r":
                nomeAmico = "Il Velenoso";
                break;
            case "s":
                nomeAmico = "Il Brutale";
                break;
            case "t":
                nomeAmico = "Il Sanguinario";
                break;
            case "u":
                nomeAmico = "The Dark";
                break;
            case "v":
                nomeAmico = "Il Brutto";
                break;
            case "z":
                nomeAmico = "Il Rancido";
                break;
        }

        switch (secondNameChar.toLowerCase()) {
            case "a":
                cognomeAmico = "Ombra";
                break;
            case "b":
                cognomeAmico = "Cavaliere";
                break;
            case "c":
                cognomeAmico = "Tarantola";
                break;
            case "d":
                cognomeAmico = "Teschio";
                break;
            case "e":
                cognomeAmico = "MasterMind";
                break;
            case "f":
                cognomeAmico = "Wizard";
                break;
            case "g":
                cognomeAmico = "Ninja";
                break;
            case "h":
                cognomeAmico = "Diavolo";
                break;
            case "i":
                cognomeAmico = "Freak";
                break;
            case "l":
                cognomeAmico = "Bestia";
                break;
            case "m":
                cognomeAmico = "Criminale";
                break;
            case "n":
                cognomeAmico = "Master";
                break;
            case "o":
                cognomeAmico = "Signore";
                break;
            case "p":
                cognomeAmico = "Bambino";
                break;
            case "q":
                cognomeAmico = "Cadavere";
                break;
            case "r":
                cognomeAmico = "Cacciatore";
                break;
            case "s":
                cognomeAmico = "Ragno";
                break;
            case "t":
                cognomeAmico = "Creatura";
                break;
            case "u":
                cognomeAmico = "Lupo Mannaro";
                break;
            case "v":
                cognomeAmico = "Vampiro";
                break;
            case "z":
                cognomeAmico = "Mutante";
                break;
        }

        ris = nomeAmico + " " + cognomeAmico;
        nomeRisultato.setText(nomeAmico);
        cognomeRisultato.setText(cognomeAmico);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dettaglio_friend, menu);
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
}