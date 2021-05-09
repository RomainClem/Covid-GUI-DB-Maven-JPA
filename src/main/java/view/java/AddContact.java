package view.java;

import controller.java.Controller;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.java.Contact;
import model.java.Person;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AddContact extends Stage {
    ComboBox<Person> cbBoxContact1, cbBoxContact2;
    DatePicker datePicker;
    TextField tfTime;

    public  AddContact(){
        //region Attributes
        //Labels
        Label lbTitle = new Label("Record close contact");
        Label lbContact1 = new Label("Contact 1");
        Label lbContact2 = new Label("Contact 2");
        Label lbDate = new Label("Choose a date");
        Label lbTime = new Label("Insert time");

        //Buttons
        Button btRecord = new Button("Submit");
        Button btCancel = new Button("Cancel");
        Button btRefresh = new Button("Refresh");

        //Misc.
        GridPane root = new GridPane();
        tfTime = new TextField();
        tfTime.setPromptText("hh:mm");
        Controller ctrl = new Controller();
        datePicker = new DatePicker();
        HBox hbRowButtons = new HBox();
        Alert alError = new Alert(Alert.AlertType.ERROR);
        //endregion

        //region ComboBox init.
        cbBoxContact1 = new ComboBox<>();
        cbBoxContact2 = new ComboBox<>();

        cbBoxContact1.setItems(FXCollections.observableArrayList(ctrl.loadPerson()));
        cbBoxContact2.setItems(FXCollections.observableArrayList(ctrl.loadPerson()));
        //endregion

        //region Buttons
        hbRowButtons.getChildren().addAll(btRecord, btRefresh, btCancel);
        hbRowButtons.setSpacing(10);
        hbRowButtons.setAlignment(Pos.CENTER);

        btRecord.setOnAction(e -> {
            try {
                LocalDate lD = datePicker.getValue();
                LocalTime lT = LocalTime.parse(tfTime.getText());
                LocalDateTime lDT = LocalDateTime.of(lD, lT);

                Person person1 = cbBoxContact1.getValue();
                Person person2 = cbBoxContact2.getValue();

                // Checking if both Contacts are the same
                if (!person1.equals(person2)){
                    ctrl.addContact(new Contact(person1.getId(), person2.getId(), lDT));
                    clearAndClose();
                } else {
                    alError.setHeaderText("Please select two different contacts.");
                    alError.show();
                }

            } catch (Exception ex) {
                //Catching if a incorrect info was inputted, and displaying an Alert
                alError.setHeaderText("Incorrect type in text field!");
                alError.show();
            }

        });

        btRefresh.setOnAction(e -> {
            cbBoxContact1.setItems(FXCollections.observableArrayList(ctrl.loadPerson()));
            cbBoxContact2.setItems(FXCollections.observableArrayList(ctrl.loadPerson()));
        });

        btCancel.setOnAction(e -> clearAndClose());
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

        root.add(lbContact1, 0, 1);
        root.add(cbBoxContact1, 0, 2);

        root.add(lbContact2, 1, 1);
        root.add(cbBoxContact2, 1, 2);

        root.add(lbDate, 0, 4);
        root.add(datePicker, 0, 5);

        root.add(lbTime, 1, 4);
        root.add(tfTime, 1, 5);

        root.add(hbRowButtons, 0, 7,2, 1);
        //endregion

        initModality(Modality.APPLICATION_MODAL);
        setTitle("Add a Contact to the database");
        setScene(new Scene(root));
    }

    public void clearAndClose(){
        cbBoxContact1.setValue(null);
        cbBoxContact2.setValue(null);
        datePicker.setValue(null);
        tfTime.clear();
        close();
    }
}
