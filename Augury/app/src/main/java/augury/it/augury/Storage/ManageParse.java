package augury.it.augury.Storage;

import com.parse.ParseObject;

import java.util.List;

import augury.it.augury.Model.Friend;
import augury.it.augury.Utility.Constants;

/**
 * Created by Bobaldo on 30/04/2015.
 */
public class ManageParse {
    public void autenticate(String username, String password) {
    }

    public void insert(Friend friend) {
        ParseObject testObject = new ParseObject(Constants.FRIEND);
        testObject.put(Constants.FIRSTNAME, friend.getFirstname());
        testObject.put(Constants.LASTNAME, friend.getLastname());
        testObject.put(Constants.BIRTHDATE, friend.getBirthdayDate());
        testObject.put(Constants.ISDELETE, friend.getIsDelete());
        testObject.put(Constants.IDFACEBOOK, friend.getIdFacebook());
        testObject.put(Constants.IMAGEURL, friend.getImageUrl());
        testObject.put(Constants.AUTOSEND, friend.getAutoSend());
        testObject.saveEventually();
    }

    public void update(Friend friend) {
    }

    public void delete(int id) {
    }

    public Friend get(int id) {
        return null;
    }

    public List<Friend> get() {
        return null;
    }
}
