package model.chat;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import utility.DataManager;

import java.io.IOException;

public class MessageNode extends Pane{
    private Pane pane;
    private Text text;
    private Text sentTime;
    private Text username;
    private ImageView reaction;
    private ImageView avatar;
    private ImageView seen;
    private Message message;

    public MessageNode(Message message) {
        this.message = message;
        try {
            pane = FXMLLoader.load(this.getClass().getResource(DataManager.MESSAGE_NODE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.getChildren().add(pane);
        avatar = (ImageView) pane.getChildren().get(0);
        text = (Text) pane.getChildren().get(1);
        sentTime = (Text) pane.getChildren().get(2);
        seen = (ImageView) pane.getChildren().get(3);
        reaction = (ImageView) pane.getChildren().get(4);
        username = (Text) pane.getChildren().get(5);

        set();
    }

    public Pane getPane() {
        return pane;
    }

    private void set() {
        setAvatar();
        setReaction();
        setText();
        setSentTime();
        setUsername();
        setSeen();
    }

    private void setAvatar() {
        double radius = avatar.getFitHeight()/2;
        Circle clip = new Circle(radius);
        clip.setCenterY(avatar.getY() + radius);
        clip.setCenterX(avatar.getX() + radius);
        avatar.setClip(clip);
        avatar.setImage(new Image(message.getUserAvatarPath()));
    }

    private void setText() {
        text.setText(message.getMessage());
    }

    private void setSentTime() {
        sentTime.setText(message.getSendingTimeString());
    }

    private void setReaction() {
        if (message.getReactionPath() != null) {
            reaction.setImage(new Image(message.getReactionPath()));
        }
    }

    private void setSeen() {
        seen.setImage(new Image(message.getStatus().getImagePath()));
    }

    private void setUsername() {
        username.setText(message.getUsername());
    }

    private void update() {
        setReaction();
        setText();
    }
}
