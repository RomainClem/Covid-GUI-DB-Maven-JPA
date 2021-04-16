package view.java;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage){

        //region Attributes initialisation
        //Creating the TabPane and GUI tabs
        //region Attributes
        TabPane root = new TabPane();

        Tab tabPerson = new PersonTab(stage);

        root.getTabs().addAll(tabPerson);
        //endregion

        //region TabPane
        root.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        //endregion

        //region Stage settings
        stage.setTitle("Covid application");
        //Giving it a nice color
        root.setStyle("-fx-base:OLIVE");
        stage.setScene(new Scene(root));
        stage.show();
        //endregion
    }

    public static void main(String[] args) {
        launch(args);
    }

}