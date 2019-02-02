package Model.Animals.Seekers;

import Controller.WarehouseController;
import Exceptions.*;
import Interfaces.Upgradable;
import Model.Animals.Seeker;
import Model.Mission;
import Model.Placement.Direction;
import Model.Placement.Position;
import Model.Plant;
import Model.Products.Product;

import java.util.ArrayList;

public class Cat extends Seeker implements Upgradable {
    private int level;
    private Mission mission;
    private static int CAT_PACE = 1;
    public static int CAT_MAX_LEVEL = 1;
    private static int CAT_UPGRADE_COST = 200;
    private static int CAT_BUY_COST = 1500;

    public Cat(Mission mission, Direction direction, Position position) {
        super(mission.getMap(), direction, position);
        this.mission = mission;
        level = mission.getCatsBeginningLevel();
        name = "Cat";
        pace = CAT_PACE;
    }

    public static int getBuyCost() {
        return CAT_BUY_COST;
    }

    private void collect() throws MissionCompletedException {
        ArrayList<Product> collectedProducts = map.getAndDiscardProductsInCell(position);
        if (collectedProducts.isEmpty())
            return;
        ArrayList<Product> storedProducts = new ArrayList<>();
        for (Product product : collectedProducts) {
            try {
                mission.getWarehouse().store(product);
                storedProducts.add(product);
                WarehouseController.storeByCat(product);
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
        if (level == 0)
            for (int i = 0; i < pace; i++)
                try {
                    normalStep();
                } catch (MissionCompletedException e) {
                    mission.setMissionAsCompleted();
                    // TODO: 2/2/2019 bezan!
                }
        else
            for (int i = 0; i < pace; i++)
                try {
                    smartStep();
                } catch (MissionCompletedException e) {
                    mission.setMissionAsCompleted();
                    // TODO: 2/2/2019 bezan
                }
    }

    private void normalStep() throws MissionCompletedException {
        super.step();
        collect();
    }

    private void smartStep() throws MissionCompletedException {
        Product closestProduct = map.getClosestProduct(position);
        if (closestProduct == null)
            step();
        else if (super.smartStep(closestProduct.getPosition()))
            collect();
        // TODO: 12/29/2018 if lazem nist, mahze etminan gozashtam
    }

    @Override
    public void upgrade() throws NotEnoughMoneyException, MaxLevelExceededException {
        if (level == CAT_MAX_LEVEL)
            throw new MaxLevelExceededException();
        mission.spendMoney(CAT_UPGRADE_COST);
        mission.increaseCatsBeginningLevel();
        for (Cat cat : mission.getMap().getCats()) {
            cat.increaseLevel();
        }
    }

    private void increaseLevel() {
        level++;
    }

    public static int getCatUpgradeCost() {
        return CAT_UPGRADE_COST;
    }

    public int getLevel() {
        return level;
    }
}
