package Model.Identity;

import Exceptions.NotFoundException;
import Model.Mission;

import java.util.ArrayList;

public class Account {
    private ArrayList<Mission> missions = new ArrayList<>();
//    private int stars = 100;
    private String username;
    private String password;

    Account(String userName, String password) {
        this.username= userName;
        this.password = password;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public void addMission(Mission mission) {
        missions.add(mission);
    }

    public Mission getMission(String name) throws NotFoundException {
        for (Mission mission : missions)
            if (mission.getName().toLowerCase().equals(name.toLowerCase()))
                return mission;
        throw new NotFoundException();
    }

    public ArrayList<Mission> getMissions() {
        return missions;
    }
}
