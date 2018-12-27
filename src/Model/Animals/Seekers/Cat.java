package Model.Animals.Seekers;

import Exceptions.CapacityExceededException;
import Exceptions.LevelFinishedException;
import Exceptions.MaxLevelExceededException;
import Exceptions.NotEnoughMoneyException;
import Interfaces.Upgradable;
import Model.Animals.Seeker;
import Model.Mission;
import Model.Placement.Direction;
import Model.Placement.Position;
import Model.Products.Product;

import java.util.ArrayList;


public class Cat extends Seeker implements Upgradable {
    private int level;
    private Mission mission;
    private static int CAT_PACE = 1;

    // TODO: 12/27/2018 harkate gorbe chejurie? vaghti kala bashe ru zamin soratesh taghir mikone?

    public Cat(Mission mission, Direction direction, Position position,int startingLevel) {
        super(mission.getMap(), direction, position);
        this.mission = mission;
        level = startingLevel;
        name = "Cat";
        pace = CAT_PACE;
    }

    private void collect() throws LevelFinishedException {
        ArrayList<Product> collectedProducts = map.getAndDiscardProductsInCell(position);
        if (collectedProducts.isEmpty())
            return;
        ArrayList<Product> storedProducts = new ArrayList<>();
        for (Product product : collectedProducts) {
            try {
                mission.getWarehouse().store(product);
                storedProducts.add(product);
            } catch (CapacityExceededException e) {
                break;
            }
        }
        collectedProducts.removeAll(storedProducts);
        if (collectedProducts.isEmpty())
            return;
        for (Product product : collectedProducts) {
            map.addToMap(product);
        }
    }

    @Override
    public void move() {
        // TODO: 12/27/2018 exception e level finished ro chikaresh konam!?
        if (level == 0)
            for (int i = 0; i < pace; i++)
                normalStep();
        else
            for (int i = 0; i < pace; i++)
                smartStep();
    }

    private void normalStep() throws LevelFinishedException {
        super.step();
        collect();
    }

    private void smartStep() throws LevelFinishedException {
        Product closestProduct = map.getClosestProduct(position);
        if (super.smartStep(closestProduct.getPosition()))
            collect();
        // TODO: 12/27/2018 if lazem nist, haminjuri mahze etminan gozashtam
    }

    @Override
    public void upgrade() throws NotEnoughMoneyException, MaxLevelExceededException {

    }

    @Override
    public void show() {

    }
}
