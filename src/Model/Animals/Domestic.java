package Model.Animals;

import Exceptions.AnimalDiedException;
import Exceptions.NotFoundException;
import Model.Placement.Direction;
import Model.Placement.Map;
import Model.Plant;
import Model.Placement.Position;
import Model.Products.Product;
import Utils.Utils;


public abstract class Domestic extends Animal {
    private String productName;
    private double hunger = 0;
    private static double HUNGER_INCREASING_VALUE_PER_TURN = 0.5;
    private static double DYING_HUNGER_LIMIT = 10;
    private static double LIMIT_OF_BEING_HUNGERY = 4;
    private static double HUNGER_DECREASING_VALUE_AFTER_EATING = 2;
    private int hungryMovingPace;

    public Domestic(Map map, Direction direction, Position position, String productName, int hungryMovingPace) {
        super(map, direction, position);
        this.productName = productName;
        this.hungryMovingPace = hungryMovingPace;
    }

    public void makeProduct() {
        try {
            Product output = Utils.getProductObject(productName);
            output.setPosition(new Position(position.getRow(), position.getColumn()));
            map.addToMap(output);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    public void makeHungry() throws AnimalDiedException {
        hunger += HUNGER_INCREASING_VALUE_PER_TURN;
        if (hunger >= DYING_HUNGER_LIMIT)
            throw new AnimalDiedException();
    }

    public void move() {
        int coveredDistance = 0;
        //momkene ghabl az inke be andaze hunger pace rah bere alaf bokhore
        //ba'd az sir shodan dg nabayad ba sorate harkate moghe e gorosnegi harkat kone
        while ((hunger >= LIMIT_OF_BEING_HUNGERY) && (coveredDistance < hungryMovingPace)) {
            smartStep();
            coveredDistance++;
        }
        if (coveredDistance < pace)
            for (int i = 0; i < (pace - coveredDistance); i++)
                step();
    }

    public void step() {
        super.step();
        if (map.isPlanted(position))
            if (hunger >= LIMIT_OF_BEING_HUNGERY) {
                try {
                    eat();
                } catch (NotFoundException e) {
                    e.printStackTrace();
                }
            }
    }

    private void smartStep() {
        Plant closestPlant = map.getClosestPlant(position);
        if (closestPlant == null) step();
        else if (super.smartStep(closestPlant.getPosition())) try {
            eat();
        } catch (NotFoundException e) {
            e.printStackTrace();        //nabayad rokh bede
        }
    }

    private void eat() throws NotFoundException {
        hunger -= HUNGER_DECREASING_VALUE_AFTER_EATING;
        map.removePlant(position);
        if (map.isPlanted(position))
            throw new NotFoundException();
    }
}



