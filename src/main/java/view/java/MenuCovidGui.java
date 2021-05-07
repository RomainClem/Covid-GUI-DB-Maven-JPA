package view.java;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;


public class MenuCovidGui extends MenuBar {

    public MenuCovidGui(){

        //region Menu Item
        MenuItem mIAddContact = new MenuItem("Add Person");

        Stage stageAddContact = new AddPerson();
//        stageAddContact.initModality(Modality.WINDOW_MODAL);

        mIAddContact.setOnAction(e -> {
            stageAddContact.show();
        });
        //endregion


        //region Menu
        Menu mPerson = new Menu("Person");
        mPerson.getItems().addAll(mIAddContact);

        Menu mContact = new Menu("Contact");

        getMenus().addAll(mPerson, mContact);
        //endregion

        //region Event Handling

        //endregion
    }

}
