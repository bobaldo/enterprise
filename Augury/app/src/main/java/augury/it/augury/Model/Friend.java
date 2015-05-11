package augury.it.augury.Model;

import android.os.Bundle;

/**
 * Created by Bobaldo on 08/05/2015.
 */
public class Friend {

    private String firstName;
    public static final String FIRSTNAME = "firstName";

    public Friend(String firstName) {
        this.firstName = firstName;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //creato da un bundle
    public Friend(Bundle b) {
        if(b != null) {
            this.firstName = b.getString(FIRSTNAME);

        }
    }

    //Pacchetto dati da trasferire tra le varie activity
    public Bundle toBundle() {
        Bundle b = new Bundle();
        b.putString(FIRSTNAME, this.FIRSTNAME);

        return b;
    }

    /*private int id;

    public int getId() {
        return id;
    }





    private String lastName;

    public String getLastName() {
        return lastName;
    }

    private Date birthdayDate;

    public Date getBirthdayDate() {
        return birthdayDate;
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

    private boolean autoSend;

    public Boolean getAutoSend() {
        return autoSend;
    }

    public void setAutoSend(Boolean autoSend) {
        this.autoSend = autoSend;
    }*/
}
