package Model.Network.Packet;

import java.util.ArrayList;

public class Profile {
    private String name;
    private String username;
    private int numberOfTrades;
    private int numbrOfCommonGames;

    public String getName() {
        return name;
    }

    public int getNumbrOfCommonGames() {
        return numbrOfCommonGames;
    }

    private ArrayList<String> friends = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNumberOfTrades() {
        return numberOfTrades;
    }

    public void setNumberOfTrades(int numberOfTrades) {
        this.numberOfTrades = numberOfTrades;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }
}
