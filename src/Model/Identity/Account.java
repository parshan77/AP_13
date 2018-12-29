package Model.Identity;

import Exceptions.NotFoundException;
import Model.Mission;

import java.util.ArrayList;

public class Account {
    private ArrayList<Mission> missions = new ArrayList<>();
    private String username;


    Account(String userName) {
        this.username= userName;
    }

    public String getUsername() {
        return username;
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
