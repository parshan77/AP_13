package Model.Network.Server;

import Exceptions.NotFoundException;
import Interfaces.Storable;
import Model.Network.Packet.FriendRequest;
import Model.Network.Packet.PacketType;
import Model.Network.Packet.Packet;
import Model.Products.Product;
import View.ServerViewer;

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
    private ArrayList<FriendRequest> acceptedFriendRequests = new ArrayList<>();

    private ServerViewer serverViewer;

    public Server(int port) {
        this.port = port;
    }

    public void handleRequest(ClientHandler clientHandler, Packet packet) {
        switch (packet.getPacketType()) {
            case checkUsernameValidation:
                isUsernameValid(clientHandler, packet.getUsernameToBeChecked());
                break;

            case SendNameAndUsername:
                setNameAndUsername(clientHandler, packet.getClientUsername(), packet.getClientName());
                break;

            case buyFromServer:
                buyFromServer(clientHandler, packet.getBuyingProductName());
                break;
            case sellToServer:
                sellToServer(clientHandler, packet);
                break;
            case friendRequest:
                sendFriendRequest(clientHandler, packet.getFriendRequest());
                break;
            case friendRequestAnswer:
                // TODO: 2/3/2019
            case publicMessage:
                sendPublicMessage(clientHandler, packet.getPublicMessage());
                break;
            case PVMessage:
                sendPVMessage(clientHandler, packet);
                break;
            case getOnlinePlayers:
                getOnlinePlayersList(clientHandler);
                break;
            case donateStorables:
                donateToServer(clientHandler, packet.getDonatedStorables());
                break;

        }
    }

    public void setup() {

        System.out.println("server setup");
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            return;
            // TODO: 1/31/2019 kei in exception ro mide?! -> vaghti masalan portesh estefade shode bashe
        }
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("client Connected");
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

    public void save() {

    }

    public void load() {

    }

    public void sendTextFromServer(String text) {
        Packet packet = new Packet(PacketType.publicMessage);
        packet.setPublicMessage(text);
        packet.setPublicMessageSenderName("Server");
        serverViewer.showMessage("Server: " + text);
        for (ClientHandler clientHandler : clientHandlers.values()) {
            clientHandler.sendPacket(packet);
        }
    }

    public void disconnect(Socket socket) {
        ClientHandler clientHandler = clientHandlers.get(socket);
        clientHandlers.remove(socket);
        clientsSockets.remove(socket);
        Thread clientHandlerThread = clientHandlerThreads.get(clientHandler);
        clientHandlerThread.interrupt();
        // TODO: 2/2/2019 chejuri threadesh ro stop konam?!

        serverViewer.discardFromLeaderBoard(clientHandler.getUsername());
        serverViewer.discardFromOnlinesList(clientHandler.getUsername());
    }

    private void setNameAndUsername(ClientHandler clientHandler, String username, String name) {
        clientHandler.setUsername(username);
        clientHandler.setName(name);
        serverViewer.showMessage(username + "joined");
        serverViewer.addNameToOnlines(username);
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
    }

    public void handlerFriendRequestAnswer(ClientHandler clientHandler, Packet packet) {
        FriendRequest friendRequest = packet.getFriendRequest();
        if (friendRequest.isAccepted()) {
            acceptedFriendRequests.add(friendRequest);
        }

        String receiverUsername = friendRequest.getReceiverUsername();
        ClientHandler recevierClientHandler = null;
        try {
            recevierClientHandler = getClientHandler(receiverUsername);
            recevierClientHandler.sendPacket(packet);
        } catch (NotFoundException e) {
            e.printStackTrace();
            // TODO: 2/2/2019 yani taraf o peida nakardim tu client ha
        }
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

    public void setServerViewer(ServerViewer serverViewer) {
        this.serverViewer = serverViewer;
    }

}
