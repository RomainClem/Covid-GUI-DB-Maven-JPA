package view.java;

import controller.java.Controller;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

public class AddPerson extends Stage {

    public AddPerson(){
        //region Attributes initialisation

        //Labels
        Label lbTitle = new Label("Add Contact");
        Label lbFName = new Label("First name");
        Label lbMName = new Label("Middle name");
        Label lbLName = new Label("Last name");
        Label lbId = new Label("Id");
        Label lbPnum = new Label("Phone number");
        Label lbEmail = new Label("Email");

        //TextFields
        TextField tfFName = new TextField();
        TextField tfMName = new TextField();
        TextField tfLName = new TextField();
        TextField tfID = new TextField();
        TextField tfPnum = new TextField();
        TextField tfEmail = new TextField();

        //GridPane, Controller and misc.
        Controller ctrl = new Controller();
        GridPane root = new GridPane();
        HBox hbRowButtons = new HBox();
        Alert alError = new Alert(Alert.AlertType.ERROR);

        //Buttons
        Button btSubmit = new Button("Submit");
        Button btCancel = new Button("Cancel");
        //endregion

        //region Buttons
        hbRowButtons.getChildren().addAll(btSubmit, btCancel);
        hbRowButtons.setSpacing(10);
        hbRowButtons.setAlignment(Pos.CENTER);

        btSubmit.setOnAction(e -> {
            try {
                String fName = tfFName.getText();
                String mName = tfMName.getText();
                String lName = tfLName.getText();
                int id = Integer.parseInt(tfID.getText());
                String pNum = tfPnum.getText();
                String email = tfEmail.getText();
                List<Object> personInfo = new LinkedList<>(){{
                    add(fName); add(mName); add(lName); add(id); add(pNum); add(email);
                }};
                ctrl.addPerson(personInfo);
            } catch (Exception ex) {
                //Catching if a incorrect info was inputted, and displaying an Alert
                alError.setHeaderText("Incorrect type in text field!");
                alError.show();
            }
            close();
        });
        btCancel.setOnAction(e -> {
            close();
        });
        //endregion

        //region GridPane settings
        root.setPadding(new Insets(10));
        root.setHgap(25);
        root.setVgap(10);
        //endregion

        //region GridPane layout
        //Setting up the cells where each elements are meant to be
        root.setAlignment(Pos.CENTER);

        root.add(lbTitle, 0, 0, 3,1);
        GridPane.setHalignment(lbTitle, HPos.CENTER);

        root.add(lbFName, 0, 1);
        root.add(tfFName, 0, 2);

        root.add(lbMName, 1, 1);
        root.add(tfMName, 1, 2);

        root.add(lbLName, 2, 1);
        root.add(tfLName, 2, 2);

        root.add(lbId, 0, 4);
        root.add(tfID, 0, 5);

        root.add(lbPnum, 1, 4);
        root.add(tfPnum, 1, 5);

        root.add(lbEmail, 2, 4);
        root.add(tfEmail, 2, 5);

        root.add(hbRowButtons, 0, 7,3, 1);
        //endregion

        setScene(new Scene(root));
    }

}
