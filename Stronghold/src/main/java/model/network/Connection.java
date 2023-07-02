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

        this.socket = socket;
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        controller = new ConnectionController(this);
        addConnection(this);
    }

    @Override
    public void run() {
        try {
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

    public void writeToAll(String data,ArrayList<String> usernames) throws IOException {
        if (user != null){
            usernames.remove(user.getUsername());
        }

        for (String username : usernames) {
            Connection connection = getConnectionByUsername(username);
            if (connection != null)
                connection.write(data);
        }
    }

    private static synchronized void addConnection(Connection connection){
        allConnections.add(connection);
    }

    private static synchronized void removeConnection(Connection connection){
        allConnections.remove(connection);
    }

    private static Connection getConnectionByUsername(String username){
        for (Connection connection : allConnections) {
            if (connection.user != null && connection.user.getUsername().equals(username))
                return connection;
        }
        return null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        System.out.println("connection set for : " + user.getUsername());
        this.user = user;
    }
}
