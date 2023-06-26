package view.shape;

import controller.GameControl;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.environment.buildings.Building;
import model.map.MapTile;
import utility.DataManager;
import view.GameViewController;

import java.io.IOException;


public class BuildingNode extends Rectangle {
    Building building;
    private VBox detailBox;
    private ProgressBar pb;

    public BuildingNode(Building building) {
        super(MapTile.TILE_WIDTH, (MapTile.TILE_HEIGHT * 2));
        this.building = building;

        // TODO: 6/8/2023 towers background 
        Image image = new Image(building.getName().getImagePath());

        double scale = image.getHeight()/image.getWidth();
        System.out.println(scale);
        this.setHeight(MapTile.TILE_WIDTH * scale);


        this.setFill(new ImagePattern(image));


        try {
            detailBox = FXMLLoader.load((this.getClass().getResource(DataManager.BUILDING_DETAIL_BOX)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        setDetailBox();
        setOnRightClick(this);

//        this.setFill(Color.BLACK);
    }

    private void setOnRightClick(BuildingNode buildingNode) {
        buildingNode.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                    showDetailsBox();
                }
                if (mouseEvent.isPrimaryButtonDown()) {
                    GameControl.selectBuildingByClick(building);
                }
            }
        });

        buildingNode.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                GameViewController.removeNode(detailBox);
            }
        });
    }

    private void setDetailBox() {
        pb = (ProgressBar) detailBox.getChildren().get(0);
        Text kind = (Text) detailBox.getChildren().get(2);
        kind.setText(building.getName().getName());
        Text type = (Text) detailBox.getChildren().get(3);
        type.setText("Type:    " + building.getName().kind);
        Text damage = (Text) detailBox.getChildren().get(1);
        damage.setText(building.getBlock().toString());
        HBox hBox = (HBox) detailBox.getChildren().get(4);
        ImageView soldierState = (ImageView) hBox.getChildren().get(0);

        pb.setProgress(1);
    }

    private void showDetailsBox() {
        pb.setProgress((double) building.getHp() / building.getInitialHp());

        GameViewController.addNode(detailBox,0, 0);
    }


}
