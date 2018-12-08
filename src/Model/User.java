package Model;

import Exceptions.NotEnoughMoneyException;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private long money = 0;
    private long thisturn = 0;
    private HashMap<String, Long> tasks = new HashMap<>();  //todo:hashcode ro benevis
    private int stars = 0;  //baraye shop va kharide kargah o ina be kar miad

    public User() { }

    public void addTask(String taskName, long finishingTurn) {
        tasks.put(taskName, finishingTurn);
    }

    public ArrayList<String> clock() {
        thisturn++;
        ArrayList<String> finishedTasksNames = new ArrayList<>();
        for (String s : tasks.keySet()) {
            if (tasks.get(s) == thisturn) {
                finishedTasksNames.add(s);
            }
        }
        return finishedTasksNames;
        //todo:behtare har task ro ye object konim ya inke injuri ba string ha bazi konim?
    }

    public void addMoney(long amount) {
        money += amount;
    }

    public void spendMoney(long amount) throws NotEnoughMoneyException {
        if (amount > money)
            throw new NotEnoughMoneyException();
        money -= amount;
    }
}
