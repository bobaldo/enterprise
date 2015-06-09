package augury.it.augury.Model;

import android.os.Bundle;

/**
 * Created by Bobaldo on 08/05/2015.
 */
public class Friend {

    public static final String NOME_AMICO = "nomeAmico";
    public static final String COGNOME_AMICO = "cognomeAmico";
    public static final String IMAGE_AMICO = "imageAmico";

    public Friend() {
    }

    //for test
    //TODO: delete
    public Friend(String firstName, String lastName) {
        this.firstname = firstName;
        this.lastname = lastName;
    }

    private int id;

    //COSTRUTTORE PER IL BUNDLE
    public Friend(Bundle b) {
        if (b != null) {
            this.firstname = b.getString(NOME_AMICO);
            this.lastname = b.getString(COGNOME_AMICO);
            this.imageUrl = b.getString(IMAGE_AMICO);
        }
    }

    public int getId() {
        return id;
    }

    private String lastname;

    public String getLastname() {
        return lastname;
    }

    private String firstname;

    public String getFirstname() {
        return firstname;
    }

    private boolean isDelete;

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    private String idFacebook;

    public String getIdFacebook() {
        return idFacebook;
    }

    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    private String imageLocal;

    public void setImageLocal(String imageLocal) {
        this.imageLocal = imageLocal;
    }

    public String getImageLocal() {
        return imageLocal;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public Bundle toBundle() {
        Bundle b = new Bundle();
        b.putString(NOME_AMICO, this.getFirstname());
        b.putString(COGNOME_AMICO, this.getLastname());
        return b;

    }

}