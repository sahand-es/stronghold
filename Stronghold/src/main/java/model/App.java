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

    static {
        try {
            socket = new Socket("localhost",8000);
            dataOutputStream = new DataOutputStream (socket.getOutputStream());
            dataInputStream = new DataInputStream (socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
