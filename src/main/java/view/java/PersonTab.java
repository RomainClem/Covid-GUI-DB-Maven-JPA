package view.java;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import controller.java.Controller;
import javafx.scene.layout.HBox;
import model.java.Person;

public class PersonTab extends Tab {

    public PersonTab(){

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

        //Buttons
        Button btAdd = new Button("Add");
        Button btDelete = new Button("Delete");
        Button btUpdate = new Button("Edit");

        //GridPane, Controller and misc.
        Controller ctrl = new Controller();
        GridPane root = new GridPane();
        TableView<Person> tbPerson = new TableView<>();
        HBox hbRowButtons = new HBox();
        //endregion

        //region TableView
        tbPerson.setPlaceholder(new Label("No data in database."));

        TableColumn<Person, Integer> colId = new TableColumn<>("ID");
        TableColumn<Person, String> colFName = new TableColumn<>("First Name");
        TableColumn<Person, String> colLName = new TableColumn<>("Last Name");

        colId.setMaxWidth(30);
        colFName.setMaxWidth(100);
        colLName.setMaxWidth(100);

        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colFName.setCellValueFactory(new PropertyValueFactory<>("fName"));
        colLName.setCellValueFactory(new PropertyValueFactory<>("lName"));

        // Row handling event
        tbPerson.setRowFactory(tv -> {
            TableRow<Person> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    Person rowData = row.getItem();
                    System.out.println("Double click on: " + rowData.getName());
                }
            });
            return row;
        });

        tbPerson.getColumns().addAll(colId, colFName, colLName);
        //endregion


        //region Buttons
        hbRowButtons.getChildren().addAll(btAdd,btUpdate, btDelete);
        hbRowButtons.setSpacing(10);

        btAdd.setOnAction(e -> {

        });
        //endregion

        //region GridPane settings
        root.setPadding(new Insets(10));
        root.setHgap(25);
        root.setVgap(1);
        //endregion

        //region GridPane layout
        //Setting up the cells where each elements are meant to be
        root.setAlignment(Pos.CENTER);

        root.add(tbPerson, 0, 0, 1, 14);
        GridPane.setHalignment(tbPerson, HPos.CENTER);

        root.add(lbFName, 1, 0);
        root.add(tfFName, 1, 1);

        root.add(lbMName, 1, 2);
        root.add(tfMName, 1, 3);

        root.add(lbLName, 1, 4);
        root.add(tfLName, 1, 5);

        root.add(lbId, 1, 6);
        root.add(tfID, 1, 7);

        root.add(lbPnum, 1, 8);
        root.add(tfPnum, 1, 9);

        root.add(lbEmail, 1, 10);
        root.add(tfEmail, 1, 11);

        root.add(hbRowButtons, 1, 13, 2, 1);
        //endregion

        setContent(root);
    }
}
