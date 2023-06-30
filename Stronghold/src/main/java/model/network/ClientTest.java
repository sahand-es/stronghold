package model.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientTest {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",8000);
        DataOutputStream dataOutputStream = new DataOutputStream (socket.getOutputStream());
        DataInputStream dataInputStream = new DataInputStream (socket.getInputStream());
        Scanner scanner = new Scanner(System.in);
        dataOutputStream.writeUTF("1");
        System.out.println(dataInputStream.readUTF());

        while (true){
            String message = scanner.nextLine();
            dataOutputStream.writeUTF(message);
            System.out.println(dataInputStream.readUTF());
        }
    }
}
