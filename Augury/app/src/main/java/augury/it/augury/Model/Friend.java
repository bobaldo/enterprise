package augury.it.augury.Model;

/**
 * Created by Bobaldo on 08/05/2015.
 */
public class Friend {
    public Friend() {
    }

    //for test
    //TODO: delete
    public Friend(String firstName, String lastName) {
        this.firstname = firstName;
        this.lastname = lastName;
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

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}