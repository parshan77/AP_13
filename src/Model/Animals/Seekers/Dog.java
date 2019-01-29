package Model.Animals.Seekers;

import Exceptions.NotFoundException;
import Model.Animals.Predator;
import Model.Animals.Seeker;
import Model.Placement.Direction;
import Model.Placement.Map;
import Model.Placement.Position;

import java.util.ArrayList;

public class Dog extends Seeker {
    private static int DOG_PACE = 1;
    private static int DOG_BUY_COST = 1100;

    public Dog(Map map, Direction direction, Position position) {
        super(map, direction, position);
        name = "Dog";
        pace = DOG_PACE;
    }

    public static int getBuyCost() {
        return DOG_BUY_COST;
    }

    private void kill(ArrayList<Predator> predators) {
        try {
            for (Predator predator : predators) {
                map.discardAnimal(predator);
//                map.discardAnimal(this);
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void step() {
        Predator closestPredator = map.getClosestPredator(position);
        if (closestPredator == null) {
            super.step();
        } else {
            super.smartStep(closestPredator.getPosition());
            if (map.getPredatorsInCell(position) != null) {
                kill(map.getPredatorsInCell(position));
            }
        }
    }

    @Override
    public void move() {
        for (int i = 0; i < pace; i++) {
            this.step();
        }
    }
}
