package Model.Network.Packet;

import Interfaces.Storable;

import java.io.Serializable;
import java.util.ArrayList;

public class Packet implements Serializable {
    private PacketType packetType;

    private String clientName = null;
    private String clientUsername = null;

    private String buyingProductName = null;
    private Storable buyedProduct = null;
    private boolean wasProductAvailable;

    private ArrayList<Storable> soldStorables = null;

    private ArrayList<Storable> donatedStorables = null;

    private String pvMessageSenderUserName;
    private String pvMessage = null;
    private String pvMessageReceiverUsername = null;

    private FriendRequest friendRequest = null;

    private String publicMessageSenderName = null;
    private String publicMessage = null;

    private String usernameToBeChecked = null;
    private boolean isUsernameValid;

    private ArrayList<String> onlinePlayers = null;


    public Packet(PacketType packetType) {
        this.packetType = packetType;
    }




    public String getPublicMessageSenderName() {
        return publicMessageSenderName;
    }

    public void setPublicMessageSenderName(String publicMessageSenderName) {
        this.publicMessageSenderName = publicMessageSenderName;
    }

    public String getPvMessageSenderUserName() {
        return pvMessageSenderUserName;
    }

    public void setPvMessageSenderUserName(String pvMessageSenderUserName) {
        this.pvMessageSenderUserName = pvMessageSenderUserName;
    }

    public boolean getWasProductAvailable() {
        return wasProductAvailable;
    }

    public void setWasProductAvailable(boolean wasProductAvailable) {
        this.wasProductAvailable = wasProductAvailable;
    }

    public ArrayList<Storable> getSoldStorables() {
        return soldStorables;
    }

    public void setSoldStorables(ArrayList<Storable> soldStorable) {
        this.soldStorables = soldStorable;
    }

    public String getPvMessageReceiverUsername() {
        return pvMessageReceiverUsername;
    }

    public void setPvMessageReceiverUsername(String pvMessageReceiverUsername) {
        this.pvMessageReceiverUsername = pvMessageReceiverUsername;
    }

    public Storable getBuyedProduct() {
        return buyedProduct;
    }

    public void setBuyedProduct(Storable buyedProduct) {
        this.buyedProduct = buyedProduct;
    }

    public ArrayList<String> getOnlinePlayers() {
        return onlinePlayers;
    }

    public void setOnlinePlayers(ArrayList<String> onlinePlayers) {
        this.onlinePlayers = onlinePlayers;
    }

    public ArrayList<Storable> getDonatedStorables() {
        return donatedStorables;
    }

    public void setDonatedStorables(ArrayList<Storable> donatedStorables) {
        this.donatedStorables = donatedStorables;
    }

    public boolean isUsernameValid() {
        return isUsernameValid;
    }

    public void setUsernameValid(boolean usernameValid) {
        this.isUsernameValid = usernameValid;
    }

    public PacketType getPacketType() {
        return packetType;
    }

    public void setPacketType(PacketType packetType) {
        this.packetType = packetType;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getBuyingProductName() {
        return buyingProductName;
    }

    public void setBuyingProductName(String buyingProductName) {
        this.buyingProductName = buyingProductName;
    }

    public String getPvMessage() {
        return pvMessage;
    }

    public void setPvMessage(String pvMessage) {
        this.pvMessage = pvMessage;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public FriendRequest getFriendRequest() {
        return friendRequest;
    }

    public void setFriendRequest(FriendRequest friendRequest) {
        this.friendRequest = friendRequest;
    }

    public String getPublicMessage() {
        return publicMessage;
    }

    public void setPublicMessage(String publicMessage) {
        this.publicMessage = publicMessage;
    }

    public String getUsernameToBeChecked() {
        return usernameToBeChecked;
    }

    public void setUsernameToBeChecked(String usernameToBeChecked) {
        this.usernameToBeChecked = usernameToBeChecked;
    }
}
