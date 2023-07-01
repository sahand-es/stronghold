package view.shape.profile;

import com.google.gson.Gson;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.App;
import model.Database;
import model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class RankScroll {
    private ScrollPane scrollPane;
    private VBox vBox;

    public RankScroll() {

        vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        ArrayList<User> users = getUsersFromServer();
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User o2, User o1) {
                if (o1.getScore() == o2.getScore()){
                    return o1.getHighScore() - o2.getHighScore();
                }
                return o1.getScore() - o2.getScore();
            }
        });

        for (User user : users) {
            vBox.getChildren().add(new RankNode(user).gethBox());
        }

        scrollPane = new ScrollPane();
        scrollPane.setPrefSize(400,600);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setPannable(true);
        scrollPane.setContent(vBox);
    }

    public static ArrayList<User> getUsersFromServer() {
        HashMap<String,String> data = new HashMap<>();
        data.put("command","getAllUsers");
        String dataStr = new Gson().toJson(data);
        try {
            App.writeToServer(dataStr);
            dataStr = App.readFromServer();
            ArrayList<User> users = new ArrayList<>();
            while (!dataStr.equals("EOF")){
                User user = new Gson().fromJson(dataStr, User.class);
                users.add(user);
                dataStr = App.readFromServer();
            }
            return users;
        } catch (Exception e){
            return null;
        }
    }

    public ScrollPane getScrollPane() {
        return scrollPane;
    }


}
