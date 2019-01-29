package Model.TimeDependentRequests;

import Model.Animals.Predator;
import Model.Animals.Predators.Bear;
import Model.Animals.Predators.Lion;
import Model.Mission;
import Model.Placement.Direction;
import Model.Placement.Position;
import Utils.Utils;

import java.util.Random;

public class PutWildAnimalInMapRequest extends TimeDependentRequest {
    Mission mission;

    public PutWildAnimalInMapRequest(Mission mission) {
        this.mission = mission;
        turnsRemained = 10;
    }

    @Override
    public void run() {
        Predator predator;
        Position position = Utils.getRandomPosition();
        Direction direction = Utils.getRandomDirection();

        int randInt = (int) (Math.random() * 2);
        if (randInt == 0)
            predator = new Bear(mission.getMap(), direction, position);
        else
            predator = new Lion(mission.getMap(), direction, position);
        mission.getMap().addToMap(predator);

//        mission.addTimeDependentRequest(new PutWildAnimalInMapRequest(mission));
    }
}
