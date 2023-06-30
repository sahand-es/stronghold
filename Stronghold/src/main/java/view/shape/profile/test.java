package view.shape.profile;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class test extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane p = new Pane();
        p.getChildren().add(new RankScroll().getScrollPane());
        Scene s = new Scene(p);
        primaryStage.setScene(s);
        primaryStage.show();
    }
}
