package view.java;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import controller.java.Controller;
import javafx.scene.layout.HBox;
import model.java.Person;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PersonTab extends Tab {
    TextField tfFName,tfMName,tfLName,tfID,tfPNum,tfEmail;
    TableView<Person> tbPerson;
    Controller ctrl;
    Button btUpdate;

    public PersonTab(){

        //region Attributes initialisation
        //Labels
        Label lbFName = new Label("First name");
        Label lbMName = new Label("Middle name");
        Label lbLName = new Label("Last name");
        Label lbId = new Label("Id");
        Label lbPNum = new Label("Phone number");
        Label lbEmail = new Label("Email");

        //TextFields
        tfFName = new TextField();
        tfMName = new TextField();
        tfLName = new TextField();
        tfID = new TextField();
        tfID.setDisable(true);
        tfPNum = new TextField();
        tfEmail = new TextField();

        //Buttons
        Button btDelete = new Button("Delete");
        btUpdate = new Button("Edit");
        Button btRefresh = new Button();

        //GridPane, Controller and misc.
        ctrl = new Controller();
        GridPane root = new GridPane();
        tbPerson = new TableView<>();
        HBox hbRowButtons = new HBox();
        Alert alError = new Alert(Alert.AlertType.ERROR);
        Alert alSuccess = new Alert(Alert.AlertType.INFORMATION);
        //endregion

        //region Image + refresh button
        ImageView imgVRefresh = new ImageView(getClass().getClassLoader().getResource("img/refresh.png").toExternalForm());

        imgVRefresh.setFitHeight(25);
        imgVRefresh.setPreserveRatio(true);

        btRefresh.setPrefSize(25,25);
        btRefresh.setGraphic(imgVRefresh);
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
                    Person peronRow = row.getItem();
                    tfFName.setText(peronRow.getFName());
                    tfMName.setText(peronRow.getMName());
                    tfLName.setText(peronRow.getLName());
                    tfID.setText(Integer.toString(peronRow.getId()));
                    tfPNum.setText(peronRow.getPhone());
                    tfEmail.setText(peronRow.getEmail());
                    btUpdate.setDisable(false);
                }
            });
            return row;
        });

        tbPerson.getColumns().addAll(colId, colFName, colLName);
        tbPerson.setMaxHeight(400);
        tbPerson.setPlaceholder(new Label("Refresh or Add Person to the database."));
        //endregion

        //region Buttons w/event handling
        hbRowButtons.getChildren().addAll(btUpdate, btDelete);
        hbRowButtons.setSpacing(10);
        hbRowButtons.setAlignment(Pos.BASELINE_CENTER);

        btUpdate.setDisable(true);
        btUpdate.setOnAction(e -> {
            List<Object> personInfo = new LinkedList<>();
            try {
                String fName = tfFName.getText();
                String mName = tfMName.getText();
                String lName = tfLName.getText();
                int id = Integer.parseInt(tfID.getText());
                String pNum = tfPNum.getText();
                String email = tfEmail.getText();
                Collections.addAll(personInfo, fName, mName, lName, id, pNum, email);
            } catch (Exception ex) {
                //Catching incorrect info, and displaying an Alert
                alError.setHeaderText("Incorrect type in text field!");
                alError.setContentText("(Make sure that ID is a positive integer)");
                alError.show();
            }
            if ((int) personInfo.get(3) < 0) {
                alError.setHeaderText("ID must be a positive integer.");
                alError.setContentText("");
                alError.show();
            } else {
                ctrl.updatePerson(personInfo);
                alSuccess.setHeaderText("Person updated.");
                alSuccess.show();
                clearAndLoad();
            }
        });

        btDelete.setOnAction(e ->{
            int id = -1;
            try {
                id = Integer.parseInt(tfID.getText());
            } catch (Exception ex){
                alError.setHeaderText("Select a person to delete first.");
                alError.setContentText("");
                alError.show();
            }

            if (id > 0) ctrl.deletePerson(id);
            clearAndLoad();
        });

        btRefresh.setOnAction(e -> tbPerson.setItems(FXCollections.observableArrayList(ctrl.loadPerson())));
        //endregion

        //region GridPane settings
        root.setPadding(new Insets(10));
        root.setHgap(15);
        root.setVgap(1);
        //endregion

        //region GridPane layout
        //Setting up the cells where each elements are meant to be
        root.setAlignment(Pos.CENTER);

        root.add(tbPerson, 0, 0, 1, 15);
        GridPane.setHalignment(tbPerson, HPos.CENTER);

        root.add(lbFName, 1, 0);
        root.add(tfFName, 1, 1);

        root.add(lbMName, 1, 2);
        root.add(tfMName, 1, 3);

        root.add(lbLName, 1, 4);
        root.add(tfLName, 1, 5);

        root.add(lbId, 1, 6);
        root.add(tfID, 1, 7);

        root.add(lbPNum, 1, 8);
        root.add(tfPNum, 1, 9);

        root.add(lbEmail, 1, 10);
        root.add(tfEmail, 1, 11);

        root.add(hbRowButtons, 1, 13);
        GridPane.setMargin(hbRowButtons, new Insets(20,0 ,0 ,0));

        root.add(btRefresh,1, 14);
        GridPane.setHalignment(btRefresh, HPos.CENTER);
        //endregion

        setText("Person");
        setContent(root);
    }

    public void clearAndLoad() {
        tfFName.clear();
        tfMName.clear();
        tfLName.clear();
        tfID.clear();
        tfPNum.clear();
        tfEmail.clear();
        tbPerson.setItems(FXCollections.observableArrayList(ctrl.loadPerson()));
        btUpdate.setDisable(true);
    }

}
