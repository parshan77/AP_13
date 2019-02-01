package Model.Network.Client;

import Model.Network.Packet.Packet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerHandler implements Runnable {
    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;


    @Override
    public void run() {
        while (true) {
            // TODO: 2/1/2019 javab haro begir
        }
    }

    public void sendPacket(Packet packet) {

    }
}
