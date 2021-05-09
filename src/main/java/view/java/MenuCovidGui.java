package view.java;

import controller.java.Controller;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import model.java.Name;
import model.java.Person;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class MenuCovidGui extends MenuBar {

    public MenuCovidGui(){
        Controller ctrl = new Controller();

        //region Menu Item
        MenuItem mIAddPerson = new MenuItem("Add Person");
        MenuItem mIAddPersonLoop = new MenuItem("out of memory");
        MenuItem mIAddContact = new MenuItem("Add Contact");
        MenuItem miSave = new MenuItem("Save to file");

        AddPerson stageAddPerson = new AddPerson();
        AddContact stageAddContact = new AddContact();

        mIAddPerson.setOnAction(e -> stageAddPerson.show());
        mIAddContact.setOnAction(e -> stageAddContact.show());
        mIAddPersonLoop.setOnAction(e -> {
            ArrayList<Person> list = new ArrayList<>();
            int i = 0;
            while (true){
                Person person = new Person(new Name(Integer.MAX_VALUE,"MAKE IT CRASH MAKE IT CRASH ",
                        "MAKE IT CRASH MAKE IT CRASH MAKE IT CRASH MAKE IT CRASH",
                        "MAKE IT CRASH MAKE IT CRASH MAKE IT CRASH MAKE IT CRASH"),
                        "MAKE IT CRASH MAKE IT CRASH MAKE IT CRASH MAKE IT CRASH ",
                        "MAKE IT CRASH MAKE IT CRASH MAKE IT CRASH MAKE IT CRASH ", Integer.MAX_VALUE);
                list.add(person);
            }
        });
        miSave.setOnAction(e -> ctrl.save());
        //endregion

        //region Menu
        Menu mPerson = new Menu("Person");
        mPerson.getItems().addAll(mIAddPerson, mIAddPersonLoop);

        Menu mContact = new Menu("Contact");
        mContact.getItems().addAll(mIAddContact);

        Menu mSave = new Menu("Save");
        mSave.getItems().addAll(miSave);


        getMenus().addAll(mPerson, mContact, mSave);
        //endregion
    }

}
