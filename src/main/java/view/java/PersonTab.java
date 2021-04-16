package view.java;


import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.java.Contact;
import controller.java.Controller;

public class PersonTab extends Tab {
    private TableView<Contact> tbContact;

    //Initializing an Optional result ButtonType for the Exit Alert
    public PersonTab(Stage stage){

        //region Attributes initialisation
        //Labels
        Label lbTitle = new Label("Add Contact");
        Label lbFname = new Label("First name");
        Label lbMname = new Label("Middle name");
        Label lbLname = new Label("Last name");
        Label lbId = new Label("Id");
        Label lbPnum = new Label("Phone number");
        Label lbEmail = new Label("Email");

        //TextFields
        TextField tfFname = new TextField();
        TextField tfMname = new TextField();
        TextField tfLname = new TextField();
        TextField tfPnum = new TextField();
        TextField tfID = new TextField();
        TextField tfEmail = new TextField();

        //Buttons
        Button btAdd = new Button("Add");

        //GridPane, Controller
        //region Attributes
        Controller ctrl = new Controller();
        GridPane root = new GridPane();

        //endregion

        //region GridPane settings
        root.setPadding(new Insets(10));
        root.setHgap(25);
        root.setVgap(15);
        //endregion

        //region GridPane layout
        //Setting up the cells where each elements are meant to be
        root.setAlignment(Pos.CENTER);

        root.add(lbTitle, 0, 0, 2, 1);
        GridPane.setHalignment(lbTitle, HPos.CENTER);

        root.add(lbFname, 0, 1);
        root.add(tfFname, 0, 2);

        root.add(lbMname, 0, 3);
        root.add(tfMname, 0, 4);

        root.add(lbLname, 0, 5);
        root.add(tfLname, 0, 6);

        //endregion
        setContent(root);
    }
}
