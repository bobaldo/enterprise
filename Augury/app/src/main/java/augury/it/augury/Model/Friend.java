package augury.it.augury.Model;

import java.util.Date;

/**
 * Created by Bobaldo on 30/04/2015.
 */
public class Friend {

    public Friend(){
        //TODO:
    }

    private int id;

    public int getId(){return id;}

    private String firstName;

    public String getFirstName() {
        return firstName;
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
    }
}