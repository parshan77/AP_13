package Model;

import java.util.ArrayList;

public class Account {
    private ArrayList<Mission> missions = new ArrayList<>();
//    private int stars = 100;
    private String UserName;

    Account(String userName) {
        UserName = userName;
    }

    String getUserName() {
        return UserName;
    }

    public void addMission(Mission mission) {
        missions.add(mission);
    }

    public Mission getMission(String name) {
        for (Mission mission : missions)
            if (mission.getName().toLowerCase().equals(name.toLowerCase()))
                return mission;
        return null;
    }
}
