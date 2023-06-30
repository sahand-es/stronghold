package view.shape.profile;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.User;

public class GiuTest extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane p = new Pane();

        p.getChildren().add(new RankNode().gethBox());

        Scene scene = new Scene(p);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
