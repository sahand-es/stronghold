package model.network;

import controller.ConnectionController;
import model.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Connection extends Thread {

    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    private User user;
    private ConnectionController controller;
    private static ArrayList<Connection> allConnections = new ArrayList<>();


    public Connection(Socket socket) {
        try {
            this.socket = socket;
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            controller = new ConnectionController(this);
            addConnection(this);
            this.handel();
        } catch (IOException e){
            removeConnection(this);
            System.out.println("connection lost :(");
        }
    }

    private void handel() throws IOException {
        while (true) {
            String data = dataInputStream.readUTF();
            System.out.println(data);
            controller.handle(data);
        }
    }

    public void write(String string) throws IOException {
        dataOutputStream.writeUTF(string);
    }

    private static synchronized void addConnection(Connection connection){
        allConnections.add(connection);
    }

    private static synchronized void removeConnection(Connection connection){
        allConnections.remove(connection);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        System.out.println("connection set for : " + user.getUsername());
        this.user = user;
    }
}
