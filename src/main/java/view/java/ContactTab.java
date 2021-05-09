package view.java;

import controller.java.Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.java.Contact;
import model.java.Person;
import java.time.LocalDateTime;
import java.util.List;

public class ContactTab extends Tab {
    TextField tfId, tfPersonDetails, tfPContactDetails;
    Controller ctrl;
    Alert alError;
    Button btDelete;
    TableView<Contact> tbContact;
    DatePicker datePicker;

    public ContactTab( ){

        //region Attributes initialisation
        //Labels
        Label lbId = new Label("Person Id");
        Label lbDate = new Label("Contacts after date");

        //TextField
        tfId = new TextField();
        tfPersonDetails = new TextField();
        tfPersonDetails.setPromptText("Person details");
        tfPersonDetails.setEditable(false);

        tfPContactDetails = new TextField();
        tfPContactDetails.setPromptText("Person details");
        tfPContactDetails.setEditable(false);

        //Buttons
        Button btSearch = new Button("Search");
        btDelete = new Button("Delete");
        btDelete.setDisable(true);

        //GridPane, Controller and misc.
        ctrl = new Controller();
        GridPane root = new GridPane();
        tbContact = new TableView<>();
        alError = new Alert(Alert.AlertType.ERROR);
        datePicker = new DatePicker();
        //endregion

        //region TableView
        tbContact.setPlaceholder(new Label("Search for contacts."));

        TableColumn<Contact, String> colDateTime = new TableColumn<>("Date/Time");
        TableColumn<Contact, String> colId = new TableColumn<>("ID");
        TableColumn<Contact, String> colFName = new TableColumn<>("First Name");
        TableColumn<Contact, String> colLName = new TableColumn<>("Last Name");

        colId.setMaxWidth(30);
        colFName.setMaxWidth(100);
        colLName.setMaxWidth(100);
        colDateTime.setMinWidth(105);

        colDateTime.setCellValueFactory(new PropertyValueFactory<>("dateContact"));

        colId.setCellValueFactory(e -> {
            int id = Integer.parseInt(tfId.getText());
            int idQuery;
            if(id == e.getValue().getPersonId1()) {
                idQuery = e.getValue().getPersonId2();
            } else idQuery = e.getValue().getPersonId1();
            return new SimpleStringProperty(Integer.toString(idQuery));
        });

        colFName.setCellValueFactory(e -> {
            int id = Integer.parseInt(tfId.getText());
            int idQuery;
            if(id == e.getValue().getPersonId1()) {
                idQuery = e.getValue().getPersonId2();
            } else idQuery = e.getValue().getPersonId1();
            return new SimpleStringProperty(ctrl.findPerson(idQuery).getFName());
        });

        colLName.setCellValueFactory(e -> {
            int id = Integer.parseInt(tfId.getText());
            int idQuery;
            if(id == e.getValue().getPersonId1()) {
                idQuery = e.getValue().getPersonId2();
            } else idQuery = e.getValue().getPersonId1();
            return new SimpleStringProperty(ctrl.findPerson(idQuery).getLName());
        });

        colId.setCellValueFactory(e -> {
            int id = Integer.parseInt(tfId.getText());
            int idQuery;
            if(id == e.getValue().getPersonId1()) {
                idQuery = e.getValue().getPersonId2();
            } else idQuery = e.getValue().getPersonId1();
            return new SimpleStringProperty(Integer.toString(ctrl.findPerson(idQuery).getId()));
        });

        // Row handling event
        tbContact.setRowFactory(tv -> {
            TableRow<Contact> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    Contact contact = row.getItem();
                    int id = Integer.parseInt(tfId.getText());
                    int idQuery;
                    if(id == contact.getPersonId1()) {
                        idQuery = contact.getPersonId2();
                    } else idQuery = contact.getPersonId1();
                    tfPContactDetails.setText(ctrl.findPerson(idQuery).print());
                    btDelete.setDisable(false);
                }
            });
            return row;
        });

        tbContact.getColumns().addAll(colDateTime, colId, colFName, colLName);
        tbContact.setMaxHeight(200);
        tbContact.setPlaceholder(new Label("Search or add a contact to selected Person."));
        //endregion

        //region Buttons w/event handling
        btSearch.setOnAction(e -> {
            clearAndSearch();
        });

        btDelete.setOnAction(e -> {
            Contact selectedContact = tbContact.getSelectionModel().getSelectedItem();
            ctrl.deleteContact(selectedContact.getId());
            clearAndSearch();
            tfPContactDetails.clear();
        });
        //endregion

        //region GridPane settings
        root.setPadding(new Insets(10));
        root.setHgap(20);
        root.setVgap(5);
        //endregion

        //region GridPane layout
        //Setting up the cells where each elements are meant to be
        root.setAlignment(Pos.CENTER);

        root.add(lbId,0,0);
        root.add(tfId, 0,1);

        root.add(lbDate, 1,0);
        root.add(datePicker, 1,1);

        root.add(tfPersonDetails, 0, 2,2,1);

        root.add(btSearch,0,3);
        root.add(btDelete,1,3);

        root.add(tbContact, 0, 5, 2, 1);

        root.add(tfPContactDetails, 0, 6,2,1);

        //endregion

        setText("Contact");
        setContent(root);
    }

    public void clearAndSearch(){
        int id = -1;
        tbContact.getItems().clear();
        tfPContactDetails.clear();
        try {
            id = Integer.parseInt(tfId.getText());
        } catch (Exception ex){
            alError.setHeaderText("Input a positive ID.");
            alError.setContentText("");
            alError.show();
        }

        if (id > 0) {
            Person person = ctrl.findPerson(id);
            if (person == null) {
                alError.setHeaderText("No Person with ID: " + id + " in the database!" );
                alError.setContentText("(Please refer to the Person table)");
                alError.show();
                tfPersonDetails.setText("No person with id: "+ id);
            } else {
                List<Contact> contactList = ctrl.loadContact();
                int finalId = id;
                contactList.removeIf(c -> c.getPersonId1() != finalId && c.getPersonId2() != finalId);

                if (datePicker.getValue() != null){
                    LocalDateTime lDT = datePicker.getValue().atTime(0,0);
                    contactList.removeIf(c -> c.getDateContact().isBefore(lDT));
                }

                btDelete.setDisable(true);
                tbContact.setItems(FXCollections.observableArrayList(contactList));
                tfPersonDetails.setText(person.print());
            }
        } else {
            alError.setHeaderText("Input a positive ID.");
            alError.setContentText("");
            alError.show();
        }
    }
}
