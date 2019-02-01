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
    private ArrayList<Socket> clientsSockets = new ArrayList<>();
    private HashMap<Socket, ClientHandler> clientHandlers = new HashMap<>();
    private HashMap<ClientHandler, Thread> clientHandlerThreads = new HashMap<>();
    private ArrayList<FriendRequest> friendRequests = new ArrayList<>();

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
//            int clientsCount = 0;

            @Override
            public void run() {
                try {
                    Socket socket = serverSocket.accept();
//                    clientsCount++;
                    clientsSockets.add(socket);
                    ClientHandler clientHandler =
                            new ClientHandler(getThis(), socket, socket.getInputStream(), socket.getOutputStream());
                    clientHandlers.put(socket, clientHandler);
                    Thread clientHandlerThread = new Thread(clientHandler);
                    clientHandlerThread.start();
                } catch (IOException e) {
                    e.printStackTrace();
                    // TODO: 1/31/2019 kei exception mide?
                }
            }
        }).start();
    }

    public void handleRequest(ClientHandler clientHandler, Packet packet) {
        switch (packet.getPacketType()) {
            case buyFromServer:
                buyFromServer(clientHandler, packet.getBuyingProductName());
                break;
            case sellToServer:
                sellToServer(clientHandler, packet);
                break;
            case friendRequest:
                sendFriendRequest(clientHandler, packet.getFriendRequest());
                break;
            case publicMessage:
                sendPublicMessage(clientHandler, packet.getPublicMessage());
                break;
            case PVMessage:
                sendPVMessage(clientHandler, packet);
                break;
            case getOnlinePlayers:
                getOnlinePlayersList(clientHandler);
                break;
            case checkUsernameValidation:
                isUsernameValid(clientHandler, packet.getUsernameToBeChecked());
                break;
            case SendNameAndUsername:
                setNameAndUsername(clientHandler, packet.getClientUsername(), packet.getClientName());
                break;
            case donateStorables:
                donateToServer(clientHandler, packet.getDonatedStorables());
                break;

        }
    }

    private void setNameAndUsername(ClientHandler clientHandler, String username, String name) {
        clientHandler.setUsername(username);
        clientHandler.setName(name);
    }

    private void sellToServer(ClientHandler senderClientHandler,Packet packet) {
        ArrayList<Storable> storables = packet.getSoldStorables();
        for (Storable storable : storables) {
            if (storable instanceof Product) {
                bazaar.add(storable);
            }
            senderClientHandler.changeMoneyInBazaar(bazaar.getPrice(storable));
        }
        senderClientHandler.addToTradesCount();
    }

    private void buyFromServer(ClientHandler senderClientHandler, String itemName) {
        Packet packet = new Packet(PacketType.buyFromServer);
        try {
            Storable item = bazaar.get(itemName);
            packet.setBuyedProduct(item);
            senderClientHandler.changeMoneyInBazaar((-1) * bazaar.getPrice(itemName));
            senderClientHandler.addToTradesCount();
            packet.setWasProductAvailable(true);
        } catch (NotFoundException e) {
            packet.setWasProductAvailable(false);
        }
        senderClientHandler.sendPacket(packet);
    }

    public void donateToServer(ClientHandler senderClientHandler, ArrayList<Storable> storables) {
        bazaar.addAll(storables);
    }

    public void sendPVMessage(ClientHandler senderClientHandler, Packet packet) {
        String receiverUsername = packet.getPvMessageReceiverUsername();
        ClientHandler receiverClientHandler;
        try {
            receiverClientHandler = getClientHandler(receiverUsername);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return;
            // TODO: 1/31/2019 in  exception rokh nemide chon ruye profile yaru click karde va taraf hatman vojud dare
        }
        receiverClientHandler.sendPacket(packet);
    }

    public void sendFriendRequest(ClientHandler senderClientHandler, FriendRequest request) {
        ClientHandler receiverClientHandler = null;
        String receiverUsername = request.getReceiverUsername();
        try {
            receiverClientHandler = getClientHandler(receiverUsername);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return;
            // TODO: 1/31/2019 in  exception rokh nemide chon ruye profile yaru click karde va taraf hatman vojud dare
        }
        Packet packet = new Packet(PacketType.friendRequest);
        packet.setFriendRequest(request);
        receiverClientHandler.sendPacket(packet);
        friendRequests.add(request);
    }

    public void sendPublicMessage(ClientHandler senderClientHandler, String message) {
        Packet packet = new Packet(PacketType.publicMessage);
        packet.setPublicMessage(message);
        for (ClientHandler clientHandler : clientHandlers.values()) {
            clientHandler.sendPacket(packet);
        }
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

    public void setPort(int port) {
        this.port = port;
    }
}
