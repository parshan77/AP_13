package Model.Network.Server;

import Model.Network.Packet.Packet;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    private String username;
    private String name;
    private int numberOfTrades = 0;
    private int moneyInBazaar = 0;

    public ClientHandler(Server server, Socket socket, InputStream inputStream, OutputStream outputStream) {
        this.socket = socket;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        try {
            objectInputStream = new ObjectInputStream(inputStream);
            objectOutputStream = new ObjectOutputStream(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                objectInputStream.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendPacket(Packet packet) {
        // TODO: 1/31/2019
        try {
            objectOutputStream.writeObject(packet);
        } catch (IOException e) {
            e.printStackTrace();
            // TODO: 2/1/2019 handle kon
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void changeMoneyInBazaar(int amount) {
        moneyInBazaar += amount;
    }

    public int getMoneyInBazaar() {
        return moneyInBazaar;
    }

    public void addToTradesCount() {
        numberOfTrades++;
    }

    public int getNumberOfTrades() {
        return numberOfTrades;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
