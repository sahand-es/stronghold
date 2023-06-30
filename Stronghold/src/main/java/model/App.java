package model;

import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class App {
    public static Stage stage;

    private static Socket socket;

    private static DataInputStream dataInputStream;
    private static DataOutputStream dataOutputStream;

    private static User currentUser;

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
