package view.fxmlcontroller;

import controller.GameControl;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.map.Block;
import model.units.enums.UnitName;
import view.GameViewController;


public class DropUnitBox {
    public HBox hBox;
    public ScrollPane scPane;

    @FXML
    private void initialize() {
        scPane.setBackground(new Background(new BackgroundFill(Color.DARKGOLDENROD,
                new CornerRadii(0), new Insets(0))));
        hBox.setBackground(new Background(new BackgroundFill(Color.DARKGOLDENROD,
                new CornerRadii(0), new Insets(0))));
        for (UnitName unit : UnitName.values()) {
            VBox unitBox = new VBox();
            ImageView imageView = new ImageView(new Image(unit.getImagePath()));
            Text name = new Text(unit.getName());
            imageView.setFitHeight(60);
            imageView.setFitWidth(60);

            imageView.addEventFilter(
                    MouseEvent.MOUSE_PRESSED,
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            Block selected = GameViewController.getMapPane().getSelectedTiles().get(0).getBlock();
                            GameControl.dropUnit(selected.getX(), selected.getY(), unit.getName(), 1);
                        }
                    }
            );

            name.setTextAlignment(TextAlignment.CENTER);
            name.setWrappingWidth(78);
            unitBox.setAlignment(Pos.CENTER);

            unitBox.getChildren().addAll(imageView, name);
            hBox.getChildren().add(unitBox);
        }
    }
}
