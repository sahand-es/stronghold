package model.network;

import model.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection extends Thread {

    Socket socket;
    final DataInputStream dataInputStream;
    final DataOutputStream dataOutputStream;

    User user;


    public Connection(Socket socket) throws IOException, InterruptedException {
        this.socket = socket;
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        this.handel();
    }

    private void handel() throws IOException {
        while (true) {
            String data = dataInputStream.readUTF();
            System.out.println(data);
            dataOutputStream.writeUTF("got your message");
        }
    }
}
