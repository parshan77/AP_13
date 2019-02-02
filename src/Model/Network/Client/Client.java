package Model.Network.Client;

import Interfaces.Storable;
import Model.Network.Packet.FriendRequest;
import Model.Network.Packet.Packet;
import Model.Network.Packet.PacketType;
import Model.Network.Server.ClientHandler;
import View.GamePlayView;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private ServerHandler serverHandler;
    private Thread serverHandlerThread;

    private ArrayList<FriendRequest> acceptedFriendRequests = new ArrayList<>();
    private int port;
    private String host;
    private Socket socket;
    private GamePlayView gamePlayView;

    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    private String username;
    private String name;

    public Client(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public void setup() {
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
            return;
            // TODO: 2/1/2019 chikaresh konam?!
        }
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return;
            // TODO: 2/1/2019 chikaresh konam!?
        }
        serverHandler = new ServerHandler(socket, objectInputStream, objectOutputStream, this);
        serverHandlerThread = new Thread(serverHandler);
        serverHandlerThread.start();
    }

    public void getUsernameCheckingAnswer(Packet packet) {
        boolean isValid = packet.isUsernameValid();
        if (isValid) {
            // TODO: 2/1/2019 ye peighami bede
        }else {
            // TODO: 2/1/2019 ye peighame dg bede!:))
        }
    }

    public void checkUsernameValidation(String username) {
        Packet packet = new Packet(PacketType.checkUsernameValidation);
        packet.setUsernameToBeChecked(username);
        serverHandler.sendPacket(packet);
    }

    public void getOnlinePlayersList(Packet packet) {
        ArrayList<String> onlinePlayers = packet.getOnlinePlayers();
        // TODO: 2/1/2019 neshunesh bedim
    }

    public void sendOnlinePlayersList() {
        Packet packet = new Packet(PacketType.getOnlinePlayers);
        serverHandler.sendPacket(packet);
    }

    public void sendPublicMessage(String message) {
        Packet packet = new Packet(PacketType.publicMessage);
        packet.setPublicMessageSenderName(this.name);
        packet.setPublicMessage(message);
        serverHandler.sendPacket(packet);
    }

    public void getPublicMessage(Packet packet) {
        String message = packet.getPublicMessage();
        String senderName = packet.getPublicMessageSenderName();
        // TODO: 2/1/2019 add kon be gameview
    }

    public void getFriendRequestAnswer(Packet packet) {
        FriendRequest friendRequest = packet.getFriendRequest();
        if (friendRequest.isAccepted())
            acceptedFriendRequests.add(friendRequest);
    }

    public void sendFriendRequstAnswer(boolean status, Packet packet) {
        FriendRequest friendRequest = packet.getFriendRequest();
        friendRequest.setAccepted(status);
        packet.setPacketType(PacketType.friendRequestAnswer);
        serverHandler.sendPacket(packet);
        if (status)
            acceptedFriendRequests.add(friendRequest);
    }

    public void getFriendRequest(Packet packet) {
        FriendRequest friendRequest = packet.getFriendRequest();
        // TODO: 2/1/2019 check konim ke ghabul karde ya na
    }

    public void sendFriendRequest(String receiverUsername) {
        Packet packet = new Packet(PacketType.friendRequest);
        FriendRequest friendRequest = new FriendRequest(this.username, receiverUsername);
        packet.setFriendRequest(friendRequest);
        serverHandler.sendPacket(packet);
    }

    public void sendDonateProductsRequest(ArrayList<Storable> items) {
        Packet packet = new Packet(PacketType.donateStorables);
        packet.setDonatedStorables(items);
        serverHandler.sendPacket(packet);
    }

    public void sendSellItemRequest(ArrayList<Storable> soldItems) {
        Packet packet = new Packet(PacketType.sellToServer);
        packet.setSoldStorables(soldItems);
        serverHandler.sendPacket(packet);
    }

    public void getBuyRequestAnswer(Packet packet){
        Storable storable = packet.getBuyedProduct();
        // TODO: 2/1/2019 add kon be anbar
    }

    public void sendBuyRequest(String productName) {
        Packet packet = new Packet(PacketType.buyFromServer);
        packet.setBuyingProductName(productName);
        serverHandler.sendPacket(packet);
    }

    public void sendNameAndUsername(String name, String username) {
        Packet packet = new Packet(PacketType.SendNameAndUsername);
        packet.setClientName(name);
        packet.setClientUsername(username);
        this.username = username;
        this.name = name;
        serverHandler.sendPacket(packet);
    }

    public void getPVMessage(Packet packet) {
        String message = packet.getPvMessage();
        String senderName = packet.getPvMessageSenderUserName();
        // TODO: 2/1/2019 tuye box esh neshun bedim
    }

    public void sendPVMessage(String receiverUsername, String message){
        Packet packet = new Packet(PacketType.PVMessage);
        packet.setPvMessage(message);
        packet.setPvMessageReceiverUsername(receiverUsername);
        packet.setPvMessageSenderUserName(username);
        serverHandler.sendPacket(packet);
        // TODO: 2/1/2019 tuye box e khodesham neshun bedim
    }

    public void disconnect() {
        // TODO: 2/2/2019 bayad ye chizi neshun dade beshe ke bargard be main menu
        // TODO: 2/2/2019 serverhandler thread bayad stop beshe
    }
}
