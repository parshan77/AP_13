package Model;

import Interfaces.VisibleInMap;
import Model.Animals.Predator;
import Model.Placement.Position;

public class Cage implements VisibleInMap{
    private Position position;
    private Predator cagedPredator;
    private String name = "Cage";
//    private int level = 0;
//    private static int MAX_LEVEL = 3;
//    private static int[] BROKE_TIMES = {6, 8, 10, 12};
//    private static int[] PROGRESS_MAX_VALUES = {100, 60, 40, 20};
//    private static int[] UPGRADE_COSTS = {0, 100, 500, 5000};
//    private int brokeTime = 6;
//    private int progressMaxValue = 100;

    public Cage(Position position, Predator cagedPredator) {
        this.position = position;
        this.cagedPredator = cagedPredator;
    }

    public Predator getCagedPredator() {
        return cagedPredator;
    }

    @Override
    public void show() {
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public String getName() {
        return name;
    }

    /*@Override
    public void upgrade() throws MaxLevelExceededException, NotEnoughMoneyException {
        if (level == MAX_LEVEL){
            throw new MaxLevelExceededException();
        }
        mission.spendMoney(UPGRADE_COSTS[level+1]);
        level++;
//        brokeTime = BROKE_TIMES[level];
//        progressMaxValue = PROGRESS_MAX_VALUES[level];
    }*/
}
