package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.resource.ResourcesName;
import view.shape.ResourceNode;

import java.awt.*;
import java.net.URL;

public class MarketViewController extends Application {
    public ScrollPane scrollPane;

    private BorderPane borderPane;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        borderPane = FXMLLoader.load(new URL(
                MarketViewController.class.getResource("/fxml/market-menu.fxml").toExternalForm()
        ));
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize(){
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setPannable(true);

        scrollPane.setContent(new ResourceNode(ResourcesName.APPLE,5,3,100 , 20).getStackPane());

    }
}
