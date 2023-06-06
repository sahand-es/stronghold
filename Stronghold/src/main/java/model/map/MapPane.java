package model.map;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Game;
import model.society.Government;
import model.society.enums.Colors;
import model.units.Person;
import model.units.enums.UnitName;
import view.shape.PersonNode;

import javax.swing.*;

public class MapPane extends Pane {
    private static double WIDTH;
    private static double HEIGHT;
    Map map;
    Group allTiles;
    Group allPersons;
    Text detailsText = new Text();
    private double scale = 1;
    private final double MAX_SCALE = 2.2;
    private final double MIN_SCALE = 0.8;


    public MapPane(Map map) {
        this.map = map;
        WIDTH = map.getWidth();
        HEIGHT = map.getHeight();
        int tilesCount = (int) (WIDTH * HEIGHT);
        allTiles = new Group();

        for (int i = 0; i < tilesCount; i++) {
            int x = (int) (i / WIDTH);
            int y = (int) (i % WIDTH);

            MapTile tile = tileInit(x, y);
            setTileHover(tile);
            setTileDetailShow(tile);


            allTiles.getChildren().add(tile);
        }
        this.getChildren().add(allTiles);
//        this.getChildren().add(allPersons);
        setDragMove();
    }

    private void setDragMove() {
        MapPane mapPane = this;
        final Delta dragDelta = new Delta();
        mapPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // record a delta distance for the drag and drop operation.
                dragDelta.x = mapPane.getLayoutX() - mouseEvent.getSceneX();
                dragDelta.y = mapPane.getLayoutY() - mouseEvent.getSceneY();
                if (mouseEvent.isPrimaryButtonDown())
                    mapPane.setCursor(Cursor.MOVE);
            }
        });
        mapPane.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mapPane.setCursor(Cursor.HAND);
            }
        });
        mapPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.isPrimaryButtonDown()) {
                    mapPane.setLayoutX(mouseEvent.getSceneX() + dragDelta.x);
                    mapPane.setLayoutY(mouseEvent.getSceneY() + dragDelta.y);
                }
            }
        });
        mapPane.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mapPane.setCursor(Cursor.HAND);
            }
        });

        mapPane.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent scrollEvent) {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100));
                scaleTransition.setNode(mapPane);
                if (scrollEvent.getDeltaY() < 0)
                    scale -= 0.05;
                else
                    scale += 0.05;
                if (scale <= MIN_SCALE) {
                    scale = MIN_SCALE;
                } else if (scale >= MAX_SCALE) {
                    scale = MAX_SCALE;
                }

                scaleTransition.setToX(scale);
                scaleTransition.setToY(scale);
                scaleTransition.play();
            }
        });
    }

    private void showDetailsText(MapTile tile) {
        detailsText.setText(tile.getBlock().showDetails());
        detailsText.setLayoutX(tile.getLayoutX() + 25);
        detailsText.setLayoutY(tile.getLayoutY() - 18);

        this.getChildren().add(detailsText);
    }

    private void hideDetailsText(MapTile tile) {
        this.getChildren().remove(detailsText);
    }

    private MapTile tileInit(int x, int y) {
        MapTile tile = new MapTile();

        tile.setBlock(map.getBlockByXY(x, y));
        tile.setLayoutX(MapTile.TILE_WIDTH / 2 * (x - y));
        tile.setLayoutY(MapTile.TILE_WIDTH / 4 * (x + y));
        tile.setFill(new ImagePattern(new Image(tile.getBlock().getTexture().getImagePath())));
        map.getBlockByXY(x, y).setTile(tile);

        return tile;
    }

    private void setTileHover(MapTile tile) {
        tile.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            if (newValue) {
                FadeTransition ft = new FadeTransition(Duration.millis(100), tile);
                ft.setFromValue(1);
                ft.setFromValue(0.8);
                ft.play();
            } else {
                FadeTransition ft = new FadeTransition(Duration.millis(100), tile);
                ft.setFromValue(0.8);
                ft.setFromValue(1);
                ft.play();
            }
        });
    }

    private void setTileDetailShow(MapTile tile) {
        tile.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                    showDetailsText(tile);
                }
            }
        });

        tile.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                hideDetailsText(tile);
            }
        });
    }
}

class Delta {
    double x, y;
}
