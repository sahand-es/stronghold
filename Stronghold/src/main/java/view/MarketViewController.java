package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
        borderPane = new BorderPane();
        scrollPane = new ScrollPane();
        borderPane.setCenter(scrollPane);

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setPannable(true);


        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.getChildren().add(new ResourceNode(ResourcesName.APPLE,5,3,100 , 20).getStackPane());
        vBox.getChildren().add(new ResourceNode(ResourcesName.MEAT,4,2,100 , 10).getStackPane());
        vBox.getChildren().add(new ResourceNode(ResourcesName.CHEESE,6,3,100 , 20).getStackPane());
        vBox.getChildren().add(new ResourceNode(ResourcesName.BREAD,5,3,100 , 30).getStackPane());

        scrollPane.setContent(vBox);


        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize(){



    }
}
