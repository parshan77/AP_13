package Model.Network.Server;

import Exceptions.NotFoundException;
import Interfaces.Storable;
import Model.Network.Packet.FriendRequest;
import Model.Network.Packet.PacketType;
import Model.Network.Packet.Packet;
import Model.Products.Product;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;


public class Server {
    private int port;
    private Bazaar bazaar;
    private HashMap<Integer, Socket> clientsSockets = new HashMap<>();
    private HashMap<Integer, ClientHandler> clientHandlers = new HashMap<>();
    private HashMap<ClientHandler, Thread> clientHandlerThreads = new HashMap<>();

    public Server(int port) {
        this.port = port;
    }

    public void setup() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            return;
            // TODO: 1/31/2019 kei in exception ro mide?! -> vaghti masalan portesh estefade shode bashe
        }
        new Thread(new Runnable() {
            int clientsCount = 0;

            @Override
            public void run() {
                try {
                    Socket socket = serverSocket.accept();
                    clientsCount++;
                    clientsSockets.put(clientsCount, socket);
                    ClientHandler clientHandler =
                            new ClientHandler(getThis(), socket, socket.getInputStream(), socket.getOutputStream());
                    clientHandlers.put(clientsCount, clientHandler);
                    Thread clientHandlerThread = new Thread(clientHandler);
                    clientHandlerThread.start();
                } catch (IOException e) {
                    e.printStackTrace();
                    // TODO: 1/31/2019 kei exception mide?
                }
            }
        }).start();
    }

    public void sellToServer(ClientHandler senderClientHandler, Storable storable){
        if (storable instanceof Product)
            bazaar.add(storable);
    }

    public void buyFromServer(ClientHandler senderClientHandler, String itemName) {
        Packet packet = new Packet(PacketType.buyFromServer);
        try {
            Storable item = bazaar.get(itemName);
            packet.setBuyedProduct(item);
            packet.setWasProductAvailable(true);
        } catch (NotFoundException e) {
            packet.setWasProductAvailable(false);
        }
        senderClientHandler.sendPacket(packet);
    }

    public void donateToServer(ClientHandler senderClientHandler, ArrayList<Storable> storables) {
        bazaar.addAll(storables);
    }

    public void sendPVMessage(ClientHandler senderClientHandler, String receiverUsername, String message) {
        ClientHandler receiverClientHandler;
        try {
            receiverClientHandler = getClientHandler(receiverUsername);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return;
            // TODO: 1/31/2019 in  exception rokh nemide chon ruye profile yaru click karde va taraf hatman vojud dare
        }
        Packet pvMessagePacket = new Packet(PacketType.sendPvMessage);
        pvMessagePacket.setPvMessage(message);
        receiverClientHandler.sendPacket(pvMessagePacket);
    }

    public void sendFriendRequest(ClientHandler senderClientHandler, String receiverUsername) {
        ClientHandler receiverClientHandler = null;
        try {
            receiverClientHandler = getClientHandler(receiverUsername);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return;
            // TODO: 1/31/2019 in  exception rokh nemide chon ruye profile yaru click karde va taraf hatman vojud dare
        }
        Packet friendRequestPacket = new Packet(PacketType.friendRequest);
        FriendRequest friendRequest = new FriendRequest(senderClientHandler.getUsername(), receiverUsername);
        friendRequestPacket.setFriendRequest(friendRequest);
        receiverClientHandler.sendPacket(friendRequestPacket);
    }

    public void sendPublicMessage(ClientHandler senderClientHandler, String receiverUsername, String message) {
        ClientHandler receiverClientHandler = null;
        try {
            receiverClientHandler = getClientHandler(receiverUsername);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return;
            // TODO: 1/31/2019 in  exception rokh nemide chon ruye profile yaru click karde va taraf hatman vojud dare
        }
        Packet publicMessagePacket = new Packet(PacketType.publicMessage);
        publicMessagePacket.setPublicMessage(message);
        for (ClientHandler clientHandler : clientHandlers.values())
            clientHandler.sendPacket(publicMessagePacket);
    }

    public void isUsernameValid(ClientHandler senderClientHandler, String username) {
        Packet usernameCheckingPacket = new Packet(PacketType.checkUsernameValidation);
        usernameCheckingPacket.setUsernameValid(true);
        for (ClientHandler clientHandler : clientHandlers.values())
            if (clientHandler.getUsername().equals(username))
                usernameCheckingPacket.setUsernameValid(false);
        senderClientHandler.sendPacket(usernameCheckingPacket);
    }

    public void getOnlinePlayersList(ClientHandler senderClientHandler) {
        ArrayList<String> onlines = new ArrayList<>();
        for (ClientHandler clientHandler : clientHandlers.values()) {
            onlines.add(clientHandler.getUsername());
        }
        Packet packet = new Packet(PacketType.getOnlinePlayers);
        packet.setOnlinePlayers(onlines);
        senderClientHandler.sendPacket(packet);
    }

    public void disconnect(ClientHandler clientHandler) {
        // TODO: 1/31/2019
    }

    private ClientHandler getClientHandler(String clientUsername) throws NotFoundException {
        for (ClientHandler clientHandler : clientHandlers.values())
            if (clientHandler.getUsername().equals(clientUsername))
                return clientHandler;
        throw new NotFoundException();
    }

    private Server getThis() {
        return this;
    }
}
