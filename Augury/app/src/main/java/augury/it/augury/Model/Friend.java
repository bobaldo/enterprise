package augury.it.augury.Model;

import android.os.Bundle;

import java.util.Date;

import augury.it.augury.Utility.Constants;

/**
 * Created by Bobaldo on 08/05/2015.
 */
public class Friend {

    //for test
    //TODO: delete
    public Friend(String firstName, String lastName) {
        this.firstname = firstName;
        this.lastname = lastName;
    }

    public Friend(String firstname, String lastname, Date birthdayDate, String idFacebook,String imageUrl, Boolean autoSend) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdayDate = birthdayDate;
        this.idFacebook = idFacebook;
        this.imageUrl = imageUrl;
        this.autoSend = autoSend;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    //creato da un bundle
    public Friend(Bundle b) {
        if (b != null) {
            this.firstname = b.getString(Constants.FIRSTNAME);

        }
    }

    //Pacchetto dati da trasferire tra le varie activity
    public Bundle toBundle() {
        Bundle b = new Bundle();
        b.putString(Constants.FIRSTNAME, this.firstname);
        return b;
    }

    private int id;

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
    }
}