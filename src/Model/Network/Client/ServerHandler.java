package Model.Network.Client;

import Model.Network.Packet.Packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerHandler implements Runnable {
    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private Client client;

    public ServerHandler(Socket socket, ObjectInputStream objectInputStream,
                         ObjectOutputStream objectOutputStream, Client client) {
        this.socket = socket;
        this.objectInputStream = objectInputStream;
        this.objectOutputStream = objectOutputStream;
        this.client = client;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Packet packet = (Packet) objectInputStream.readObject();
            } catch (IOException e) {
                client.disconnect();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public void sendPacket(Packet packet) {
        try {
            objectOutputStream.writeObject(packet);
        } catch (IOException e) {
            client.disconnect();
        }
    }
}
