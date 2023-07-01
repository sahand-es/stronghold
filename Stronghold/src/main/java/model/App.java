package model;

import javafx.stage.Screen;
import javafx.stage.Stage;
import model.chat.Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class App {
    public static Stage stage;

    private static Socket socket;
    private static DataInputStream dataInputStream;
    private static DataOutputStream dataOutputStream;
    private static User currentUser;

    public static double centerX = Screen.getPrimary().getVisualBounds().getWidth() / 2;
    public static double centerY = Screen.getPrimary().getVisualBounds().getHeight() / 2;

    private static ArrayList<Chat> chats;

    public static void writeToServer(String data) throws IOException {
        dataOutputStream.writeUTF(data);
    }

    public static String readFromServer() throws IOException {
        return dataInputStream.readUTF();
    }

    public static void setSocket(Socket socket) throws IOException {
        App.socket = socket;

        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        App.currentUser = currentUser;
    }
}
