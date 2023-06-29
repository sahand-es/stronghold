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
import model.environment.buildings.UnitMakerBuilding;
import model.map.Block;
import model.units.enums.UnitName;
import view.GameViewController;

public class CreateUnitBox {
    public HBox hBox;
    public Pane pane;
    public ImageView buildingImage;
    public Text nameText;
    public ScrollPane scPane;
    private UnitName selectedUnit = UnitName.ASSASSIN;

    @FXML
    private void initialize() {
        pane.setBackground(new Background(new BackgroundFill(Color.DARKGOLDENROD,
                new CornerRadii(10), new Insets(0))));
        hBox.setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND,
                new CornerRadii(0), new Insets(0))));
        scPane.setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND,
                new CornerRadii(0), new Insets(0))));
        if (!GameControl.getSelectedBuilding().getGovernment().equals(GameControl.getCurrentGovernment()))
            return;
        if (GameControl.getSelectedBuilding() instanceof UnitMakerBuilding) {
            buildingImage.setImage(new Image(GameControl.getSelectedBuilding().getName().getImagePath()));
            nameText.setText(GameControl.getSelectedBuilding().getName().getName());
            for (UnitName unit : UnitName.getUnits(GameControl.getSelectedBuilding().getName())) {
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
                                selectedUnit = unit;
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

    public void createUnit(MouseEvent mouseEvent) {
        System.out.println(GameControl.createUnit(selectedUnit.getName(), 1).message());
    }
}
