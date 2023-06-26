package view.fxmlcontroller;

import controller.GameControl;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import view.shape.map.MapTile;
import model.map.Texture;

import java.util.ArrayList;

public class ChangeTextureBox {
    public HBox firstRow;
    public HBox secondRow;
    public HBox thirdRow;
    public HBox forthRow;
    public VBox fatherOfHs;

    @FXML
    private void initialize() {
        ArrayList<HBox> hBoxes = new ArrayList<>();
        for (Node hBox : fatherOfHs.getChildren()) {
            hBoxes.add((HBox) hBox);
        }

        int i = 0;
        int j = 0;

        for (HBox hBox : hBoxes) {
            for (Node node : hBox.getChildren()) {
                if (node instanceof MapTile) {
                    MapTile tile = (MapTile) node;
                    int finalI = i;
                    tile.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            GameControl.setTexture(Texture.values()[finalI]);
                        }
                    });
                    Image image = new Image(Texture.values()[i].getImagePath());
                    tile.setFill(new ImagePattern(image));
                    i++;
                }
                if (node instanceof Text) {
                    Text text = (Text) node;
                    text.setText(Texture.values()[j].getName());
                    j++;
                }
            }
        }
    }
}
