package view.shape.government;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.resource.ResourcesName;

public class ResourceValueNode {
    ResourcesName resource;

    HBox hBox;

    Label value;

    public ResourceValueNode(ResourcesName resource,int amount) {
        this.resource = resource;
        hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        hBox.setStyle("-fx-background-color: DARKGOLDENROD");

        Rectangle image = new Rectangle(38,38);
        image.setFill(new ImagePattern(
                new Image(ResourceValueNode.class.getResource("/images/resources/"+resource.name().toLowerCase()+".png").toExternalForm())
        ));

        value = new Label(String.valueOf(amount));
        value.setStyle("-fx-font: 20 sys");

        hBox.getChildren().addAll(image, value);
    }

    public HBox gethBox() {
        return hBox;
    }

    public ResourcesName getResource() {
        return resource;
    }

    public void setValue(int amount) {
        value.setText(String.valueOf(amount));
    }
}
