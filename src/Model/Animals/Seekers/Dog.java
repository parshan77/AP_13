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

    public Dog(Map map, Direction direction, Position position) {
        super(map, direction, position);
        name = "Dog";
        pace = DOG_PACE;
    }

    private void kill(ArrayList<Predator> predators) {
        try {
            for (Predator predator : predators) {
                map.discardAnimal(predator);
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        try {
            map.discardAnimal(this);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void step() {
        super.step();
        if (map.getPredatorsInCell(position) != null) {
            kill(map.getPredatorsInCell(position));
        }
    }

    @Override
    public void show() {

    }
}
