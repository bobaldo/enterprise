package augury.it.augury.Facebook;

import java.util.ArrayList;
import java.util.List;

import augury.it.augury.Model.Friend;


/**
 * Created by Bobaldo on 30/04/2015.
 */
public class ManageFacebook {
    public void autenticate(String username, String Password) {
    }

    private List<Friend> friends = new ArrayList<>();

    public List<Friend> getFriends() {
        friends.add(new Friend("Pippo", "Pippo"));
        friends.add(new Friend("Paperino", "Pippo"));
        friends.add(new Friend("Topolino", "Pippo"));
        friends.add(new Friend("QuiQuoQua", "Pippo"));
        friends.add(new Friend("Paperone", "Pippo"));
        return friends;
    }
}