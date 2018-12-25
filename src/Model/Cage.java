package Model;

import Exceptions.MaxLevelExceededException;
import Exceptions.NotEnoughMoneyException;
import Interfaces.Upgradable;
import Interfaces.VisibleInMap;
import Model.Animals.Predator;
import Model.Placement.Position;

public class Cage implements VisibleInMap, Upgradable {
    private Position position;
    private Mission mission;
    private Predator cagedPredator;
    private int level = 0;
    private static int MAX_LEVEL = 3;
    private static int[] BROKE_TIMES = {6, 8, 10, 12};
    private static int[] PROGRESS_MAX_VALUES = {100, 60, 40, 20};
    private static int[] UPGRADE_COSTS = {0, 100, 500, 5000};
    private int brokeTime = 6;
    private int progressMaxValue = 100;

    public Cage(Mission mission, Position position, Predator predator) {
        this.position = position;
        this.mission = mission;
        this.cagedPredator = predator;
    }

    public Predator getCagedPredator() {
        return cagedPredator;
    }

    @Override
    public void show() {
        //todo
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void upgrade() throws NotEnoughMoneyException, MaxLevelExceededException {
        if (level == MAX_LEVEL){
            throw new MaxLevelExceededException();
        }
        mission.spendMoney(UPGRADE_COSTS[level+1]);
        level++;
        brokeTime = BROKE_TIMES[level];
        progressMaxValue = PROGRESS_MAX_VALUES[level];
    }
}
