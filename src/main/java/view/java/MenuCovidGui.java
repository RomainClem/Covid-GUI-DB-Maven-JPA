package view.java;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;


public class MenuCovidGui extends MenuBar {

    public MenuCovidGui(){
        Menu mPerson = new Menu("Person");
        Menu mContact = new Menu("Contact");
        getMenus().addAll(mPerson, mContact);
    }

}
