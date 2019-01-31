package Model.Network.Packet;

import Interfaces.Storable;
import Model.Products.Product;

import java.util.ArrayList;
import java.util.logging.SocketHandler;

public class Packet {
    private PacketType packetType;

    private String clientName = null;

    private String buyingProductName = null;
    private Storable buyedProduct = null;
    private boolean wasProductAvailable;

    private String sellingProductName = null;
    private ArrayList<Storable> donatedStorables = null;

    private String pvMessage = null;
    private FriendRequest friendRequest = null;
    private String publicMessage = null;
    private String usernameToBeChecked = null;
    private ArrayList<String> onlinePlayers = null;

    private boolean isUsernameValid;

    public Packet(PacketType packetType) {
        this.packetType = packetType;
    }


    public boolean isWasProductAvailable() {
        return wasProductAvailable;
    }

    public void setWasProductAvailable(boolean wasProductAvailable) {
        this.wasProductAvailable = wasProductAvailable;
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

    public String getSellingProductName() {
        return sellingProductName;
    }

    public void setSellingProductName(String sellingProductName) {
        this.sellingProductName = sellingProductName;
    }

    public String getPvMessage() {
        return pvMessage;
    }

    public void setPvMessage(String pvMessage) {
        this.pvMessage = pvMessage;
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
