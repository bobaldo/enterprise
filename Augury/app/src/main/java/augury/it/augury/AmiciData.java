package augury.it.augury;

import java.util.ArrayList;
import java.util.List;

import augury.it.augury.Model.Friend;

/**
 * Created by innocenzo on 11/05/15.
 */
public class AmiciData {

    public List<Friend> amici = new ArrayList<>();
    public List<Friend> getFriend() {return amici; }

    public AmiciData() {

        amici.add(new Friend("Pippo"));
        amici.add(new Friend("Paperino"));
        amici.add(new Friend("Topolino"));
        amici.add(new Friend("QuiQuoQua"));
        amici.add(new Friend("Paperone"));
    }
}
