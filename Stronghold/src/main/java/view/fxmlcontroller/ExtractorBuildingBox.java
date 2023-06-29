package view.fxmlcontroller;

import controller.GameControl;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.environment.buildings.ResourceExtractorBuilding;
import model.resource.ResourcesName;

public class ExtractorBuildingBox {
    public Text nameText;
    public ImageView buildingImage;
    public Slider slider;
    public Pane pane;
    public HBox resourcesHbox;

    @FXML
    private void initialize() {
        pane.setBackground(new Background(new BackgroundFill(Color.DARKGOLDENROD,
                new CornerRadii(10), new Insets(0))));
        if (!(GameControl.getSelectedBuilding() instanceof ResourceExtractorBuilding))
            return;
        else {
            for (ResourcesName resourcesName :
                    ((ResourceExtractorBuilding) GameControl.getSelectedBuilding()).getExtractedResource()) {
                ImageView resourceImage = new ImageView(new Image("file:src/main/resources/images/resources/"
                        +resourcesName.name().toLowerCase()+".png"));
                resourceImage.setFitHeight(50);
                resourceImage.setFitWidth(50);

                resourcesHbox.getChildren().add(resourceImage);
            }
            buildingImage.setImage(new Image(GameControl.getSelectedBuilding().getName().getImagePath()));
            nameText.setText(GameControl.getSelectedBuilding().getName().getName());
        }
    }

    public void setRate(MouseEvent mouseEvent) {
        if (GameControl.getSelectedBuilding() instanceof ResourceExtractorBuilding)
            ((ResourceExtractorBuilding) GameControl.getSelectedBuilding()).setRate((int) slider.getValue());
    }
}
