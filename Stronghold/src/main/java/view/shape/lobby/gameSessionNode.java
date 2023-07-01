package view.shape.lobby;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.User;

public class gameSessionNode {
    private User admin;
    private HBox mainNode;
    private Label usersLabel;
    private Label joinedNumberLabel;
    private Button Join;
    private Button leave;

    public gameSessionNode(User admin) {
        this.admin = admin;
        mainNode  = new HBox();
        mainNode.setAlignment(Pos.CENTER);
        mainNode.setSpacing(100);
        mainNode.setPrefSize(600,70);
        mainNode.setStyle("-fx-background-color: #263f73");




    }


}
