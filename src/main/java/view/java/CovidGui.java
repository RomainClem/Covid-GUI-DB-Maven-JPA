package view.java;

import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CovidGui extends Application {

    @Override
    public void start(Stage stage){

        BorderPane root = new BorderPane();

        //region MenuBar
        MenuBar menuGui = new MenuCovidGui();
        root.setTop(menuGui);
        //endregion

        //region TabPane
        TabPane tP = new TabPane();
        tP.setSide(Side.LEFT);

        PersonTab tabPerson = new PersonTab();
        ContactTab tabContact = new ContactTab();
        tP.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tP.getTabs().addAll(tabPerson, tabContact);
        root.setCenter(tP);
        //endregion
        
        //region Stage settings
        stage.setTitle("Covid application");
        //Giving it a nice color
        root.setStyle("-fx-base:AZURE");

        stage.setScene(new Scene(root));
        stage.show();
        //endregion
    }

    public static void main(String[] args) {
        launch(args);
    }

}