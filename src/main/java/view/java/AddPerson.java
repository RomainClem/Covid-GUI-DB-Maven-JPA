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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
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
        List<TextField> tfList = new LinkedList<>();
        Collections.addAll(tfList, tfFName, tfMName, tfLName, tfID, tfPnum, tfEmail);

        //Buttons
        Button btSubmit = new Button("Submit");
        Button btCancel = new Button("Cancel");
        btSubmit.setDisable(true);
        //endregion

        //region TextField handling
        tfFName.setOnKeyReleased( e -> checkTextFields(btSubmit, tfList));
        tfMName.setOnKeyReleased( e -> checkTextFields(btSubmit, tfList));
        tfLName.setOnKeyReleased( e -> checkTextFields(btSubmit, tfList));
        tfID.setOnKeyReleased( e -> checkTextFields(btSubmit, tfList));
        tfPnum.setOnKeyReleased( e -> checkTextFields(btSubmit, tfList));
        tfEmail.setOnKeyReleased( e -> checkTextFields(btSubmit, tfList));
        //endregion

        //region Buttons
        hbRowButtons.getChildren().addAll(btSubmit, btCancel);
        hbRowButtons.setSpacing(10);
        hbRowButtons.setAlignment(Pos.CENTER);

        btSubmit.setOnAction(e -> {
            List<Object> personInfo = new LinkedList<>();

            try {
                String fName = tfFName.getText();
                String mName = tfMName.getText();
                String lName = tfLName.getText();
                int id = Integer.parseInt(tfID.getText());
                String pNum = tfPnum.getText();
                String email = tfEmail.getText();
                Collections.addAll(personInfo, fName, mName, lName, id, pNum, email);
            } catch (Exception ex) {
                //Catching if a incorrect info was inputted, and displaying an Alert
                alError.setHeaderText("Incorrect type in text field!");
                alError.setContentText("(Make sure that ID is a positive integer)");
                alError.show();
            }
            if ((int) personInfo.get(3) < 0) {
                alError.setHeaderText("ID must be a positive integer.");
                alError.setContentText("");
                alError.show();
            }
            else if (ctrl.addPerson(personInfo)) clearAndClose(tfList);
            else {
                alError.setHeaderText("ID already in database");
                alError.setContentText("(Double check ID or update existing Person)");
                alError.show();
            }
        });

        btCancel.setOnAction(e -> clearAndClose(tfList));
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

        initModality(Modality.APPLICATION_MODAL);
        setTitle("Add a Person to the database");
        setScene(new Scene(root));
    }

    public void clearAndClose(List<TextField> tfList){
        tfList.get(0).clear();
        tfList.get(1).clear();
        tfList.get(2).clear();
        tfList.get(3).clear();
        tfList.get(4).clear();
        tfList.get(5).clear();
        close();
    }

    public void checkTextFields(Button btSubmit, List<TextField> tfList){
        //Checking if each TextFields have some text and if it's not just space
        for (TextField t: tfList){
            //A TextField is empty, setting or keeping the button disabled (stop loop)
            if (t.getText() == null || t.getText().trim().isEmpty()){
                btSubmit.setDisable(true);
                break;

                //All TextFields aren't empty, enabling add button
            } else {
                btSubmit.setDisable(false);
            }
        }
    }

}
