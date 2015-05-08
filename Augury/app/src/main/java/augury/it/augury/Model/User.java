package augury.it.augury.Model;

/**
 * Created by Bobaldo on 01/05/2015.
 */
public class User {
    private int id;

    public int getId() {
        return id;
    }

    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    private String lastName;

    public String getLastName() {
        return lastName;
    }

    private boolean isFacebook;

    public Boolean getIsFacebook() {
        return isFacebook;
    }

    public void setIsFacebook(Boolean isFacebook) {
        this.isFacebook = isFacebook;
    }
}