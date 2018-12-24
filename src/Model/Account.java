package Model;

import java.util.ArrayList;

public class Account {
    private ArrayList<Mission> missions = new ArrayList<>();
    private int stars = 100;
    private String UserName;

    public Account(String userName) {
        UserName = userName;
    }

    public String getUserName() {
        return UserName;
    }

    public Mission getMission(String name) {
        for (Mission mission : missions)
            if (mission.getName().equals(name)) return mission;
        return null;
    }
}
