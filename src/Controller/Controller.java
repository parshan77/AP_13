package Controller;

import Exceptions.*;
import Interfaces.Storable;
import Interfaces.VisibleInMap;
import Model.*;
import Model.Animals.Domestics.Cow;
import Model.Animals.Domestics.Hen;
import Model.Animals.Domestics.Sheep;
import Model.Animals.Predator;
import Model.Animals.Seekers.Cat;
import Model.Animals.Seekers.Dog;
import Model.Placement.Direction;
import Model.Placement.Position;
import Model.TimeDependentRequests.RefillWellRequest;
import Utils.Utils;

import java.util.ArrayList;
import java.util.Scanner;

import static Model.Placement.Map.MAP_SIZE;

public class Controller {
    private static Mission mission;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LevelRequirementsChecker lrc = new LevelRequirementsChecker(0, 3, 0,
                0, 0, 0, 0, 3, 0,
                0, 0, 0, 0);

        mission = new Mission(200, "firstMission", lrc, null);

        //todo: sharte While -> check requirements

        String input = scanner.nextLine().toLowerCase();
        gameWhile:
        while (!input.toLowerCase().equals("exit")) {

            String[] splittedInput = input.split(" ");
            switch (splittedInput[0]) {
                //todo: switch case baraye string ha kar mikone( .equals va == )
                case "buy":
                    buyAnimalRequestHandler(splittedInput[1]);
                    break;

                case "pickup":
                    int row = Integer.parseInt(splittedInput[1]);
                    int column = Integer.parseInt(splittedInput[2]);
                    try {
                        pickupRequestController(row, column);
                    } catch (LevelFinishedException e) {
                        System.out.println("level finished!");
                        break gameWhile;
                    }
                    break;

                case "plant":
                    row = Integer.parseInt(splittedInput[1]);
                    column = Integer.parseInt(splittedInput[2]);
                    plantRequestHandler(row, column);
                    break;

                case "cage":
                    row = Integer.parseInt(splittedInput[1]);
                    column = Integer.parseInt(splittedInput[2]);
                    cageAnimalController(row, column);
                    break;

                case "well":
                    wellRequestHandler();
                    break;


                // TODO: 12/28/2018 ta inja debug kardam
                case "upgrade":
                    upgradeRequestHandler(splittedInput[1]);
                    break;

                case "loadcostume":
                    loadCostumeRequestHandler(splittedInput[1]);
                    break;

                case "run":
                    runRequestHandler(splittedInput[1]);
                    break;

                case "savegame":
                    saveGameRequestHandler(splittedInput[1]);
                    break;

                case "loadgame":
                    loadGameRequestHandler(splittedInput[1]);
                    break;

                case "print":
                    printRequestHandler(input);
                    break;

                case "turn":
                    turnRequestHandler(Integer.parseInt(splittedInput[1]));
                    break;

                case "truck":
                    switch (splittedInput[1]) {
                        case "add":
                            addToTruckListRequestHandler(splittedInput[2], Integer.parseInt(splittedInput[3]));
                            break;
                        case "clear":
                            clearTruckListRequestHandler();
                            break;
                        case "go":
                            goTruckRequestHandler();
                            break;
                    }
                    break;

                case "helicopter":
                    switch (splittedInput[1]) {
                        case "add":
                            addToHelicopterListRequestHandler(splittedInput[2], Integer.parseInt(splittedInput[3]));
                            break;
                        case "clear":
                            clearHelicopterListRequestHandler();
                            break;
                        case "go":
                            goHelicopterRequestHandler();
                            break;
                    }
                    break;
            }
            input = scanner.nextLine().toLowerCase();
        }
    }

    private static void goTruckRequestHandler() {
        if (!mission.getTruck().go())
            System.out.println("Truck List is empty");
        // TODO: time?
    }

    private static void goHelicopterRequestHandler() {
        if (!mission.getHelicopter().go())
            System.out.println("Helicopter List is empty");
        //todo:time?
    }

    private static void clearHelicopterListRequestHandler() {
        mission.getHelicopter().clearList();
    }

    private static void addToHelicopterListRequestHandler(String itemName, int itemCount) {
        Storable obj;
        try {
            obj = mission.getWarehouse().getAndDiscard(itemName);
            mission.getHelicopter().addToList((Storable) obj);
        } catch (NotFoundException e) {
            System.out.println("This item does not exist!");
        } catch (CapacityExceededException e) {
            System.out.println("there isn't enough space in helicopter");
        }
    }

    private static void clearTruckListRequestHandler() {
        mission.getTruck().clearList();
    }

    private static void addToTruckListRequestHandler(String itemName, int itemCount) {
        Storable obj;
        try {
            obj = mission.getWarehouse().getAndDiscard(itemName);
            mission.getTruck().addToList((Storable) obj);
            // TODO: 12/24/2018 tradable = storable -> dota interface o yeki kon
        } catch (NotFoundException e) {
            System.out.println("This item does not exist!");
        } catch (CapacityExceededException e) {
            System.out.println("there isn't enough space in Truck");
        }
    }

    private static void turnRequestHandler(int turnsPassed) {
        mission.passSeveralTurns(turnsPassed);
    }

    private static void printRequestHandler(String input) {
        //todo: input kolle dasture vurudie : print info map ...
    }

    private static void loadGameRequestHandler(String pathToJsonFile) {
        // TODO: 12/24/2018
    }

    private static void saveGameRequestHandler(String pathToJsonFile) {
        // TODO: 12/24/2018
    }

    private static void runRequestHandler(String mapName) {
        // TODO: 12/24/2018
    }

    private static void loadCostumeRequestHandler(String pathToCostumeDirectory) {
        // TODO: 12/24/2018
    }

    private static void upgradeRequestHandler(String upgradingUnitName) {
        if (upgradingUnitName.toLowerCase().equals("cat")) {
//            Cat.upgrade();
            // TODO: 12/25/2018 upgrade e cat fargh mikone
        }
        try {
            mission.getUpgradableUnit(upgradingUnitName).upgrade();
        } catch (NotEnoughMoneyException e) {
            System.out.printf("Your money is not enough for upgrading %s\n", upgradingUnitName);
        } catch (MaxLevelExceededException maxLevelExceeded) {
            System.out.printf("%s is already at it's max possible level\n", upgradingUnitName);
        } catch (NotFoundException e2) {
            System.out.println("enter a correct name.");
        }
    }

    private static void wellRequestHandler() {
        if (!mission.getWell().isEmpty()){
            System.out.println("Well isn't Empty.");
            return;
        }
        if(mission.getWell().getRefillCost() > mission.getMoney()) {
            System.out.println("Your money isn't enough!");
            return;
        }
        mission.addTimeDependentRequest(new RefillWellRequest(mission));
    }

    private static void cageAnimalController(int row, int column) {
        ArrayList<Predator> predators = mission.getMap().getPredatorsInCell(row, column);

        if (predators == null) {
            System.out.println("There isn't any predator in that cell");
            return;
        }

        for (Predator predator : predators) {
            Cage cage = new Cage(new Position(row, column), predator);
            mission.getMap().addToMap(cage);
            try {
                mission.getMap().discardAnimal(predator);
            } catch (NotFoundException e) {
                e.printStackTrace();    //chon predator haro az map gereftim nabayad in exception ettefagh biofte
            }
        }
    }

    private static void plantRequestHandler(int row, int column) {
        int minRow = Math.max(0, row - 1);
        int minColumn = Math.max(0, column - 1);
        int maxRow = Math.min(MAP_SIZE - 1, row + 1);
        int maxColumn = Math.min(MAP_SIZE - 1, column + 1);

        int numberOfPlantsPlanted = calculatePlantsNeeded(row, column);
        if (numberOfPlantsPlanted == 0) {
            System.out.println("all cells in that area are already planted.");
            return;
        }

        try {
            mission.getWell().extractWater(Plant.PLANTING_NEEDED_WATER);
        } catch (WellNotEnoughWaterException e) {
            System.out.println("refill well first.");
            return;
        }

        for (int row1 = minRow; row1 <= maxRow; row1++)
            for (int column1 = minColumn; column1 <= maxColumn; column1++)
                if (!mission.getMap().isPlanted(row1, column1)) {
                    Plant plant = new Plant(new Position(row1, column1));
                    mission.getMap().addToMap(plant);
                }
    }

    private static int calculatePlantsNeeded(int row, int column) {
        int minRow = Math.max(0, row - 1);
        int minColumn = Math.max(0, column - 1);
        int maxRow = Math.min(MAP_SIZE - 1, row + 1);
        int maxColumn = Math.min(MAP_SIZE - 1, column + 1);

        int numberOfPlantsPlanted = 0;
        for (int row1 = minRow; row1 <= maxRow; row1++)
            for (int column1 = minColumn; column1 <= maxColumn; column1++)
                if (!mission.getMap().isPlanted(row1, column1))
                    numberOfPlantsPlanted++;

        return numberOfPlantsPlanted;
    }

    private static void pickupRequestController(int row, int column) throws LevelFinishedException {
        Warehouse warehouse = mission.getWarehouse();
        ArrayList<Storable> items = mission.getMap().getAndDiscardProductsAndCagedAnimals(row, column);
        ArrayList<Storable> storedItems = new ArrayList<>();

        for (Storable item : items) {
            try {
                warehouse.store(item);
                storedItems.add(item);
            } catch (CapacityExceededException e) {
                System.out.println("there isn't enough capacity in warehouse.");
                break;
            }
        }

        items.removeAll(storedItems);
        //age natuneste bashim ye seri az kala haro bar darim(exception) bareshun migardunim be map
        for (Storable item : items)
            mission.getMap().addToMap((VisibleInMap) item);
    }

    private static void buyAnimalRequestHandler(String animalName) {
        Direction direction = Utils.getRandomDirection();
        Position position = Utils.getRandomPosition();
        switch (animalName.toLowerCase()) {
            case "cow":
                Cow cow = new Cow(mission.getMap(), direction, position);
                mission.getMap().addToMap(cow);
                break;
            case "hen":
                Hen hen = new Hen(mission.getMap(), direction, position);
                mission.getMap().addToMap(hen);
                break;
            case "sheep":
                Sheep sheep = new Sheep(mission.getMap(), direction, position);
                mission.getMap().addToMap(sheep);
                break;
            case "cat":
                Cat cat = new Cat(mission, direction, position);
                mission.getMap().addToMap(cat);
                break;
            case "dog":
                Dog dog = new Dog(mission.getMap(), direction, position);
                mission.getMap().addToMap(dog);
                break;
        }
    }
}
