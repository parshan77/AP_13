package Controller;

import Exceptions.CapacityExceededException;
import Exceptions.NotFoundException;
import Exceptions.WellNotEnoughWaterException;
import Interfaces.Storable;
import Interfaces.Tradable;
import Model.Animals.Predator;
import Model.Cage;
import Model.LevelRequirementsChecker;
import Model.Mission;
import Model.Placement.Map;
import Model.Placement.Position;
import Model.Plant;
import Model.TimeDependentRequests.RefillWellRequest;
import Model.TimeDependentRequests.UpgradeRequest;

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
        mission = new Mission(200, "firstMission", lrc);

        //todo: sharte While -> check requir
        while (true) {
            String input = scanner.next().toLowerCase();
            String[] splitedInput = input.split(" ");
            switch (splitedInput[0]) {
                //todo: switch case baraye string ha kar mikone( .equals va == )
                case "buy":
                    buyAnimalRequestHandler(splitedInput[1]);
                    break;

                case "pickup":
                    int row = Integer.parseInt(splitedInput[1]);
                    int column = Integer.parseInt(splitedInput[2]);
                    pickupRequestController(row, column);
                    break;

                case "plant":
                    row = Integer.parseInt(splitedInput[1]);
                    column = Integer.parseInt(splitedInput[2]);
                    plantRequestHandler(row, column);
                    break;

                case "cage":
                    row = Integer.parseInt(splitedInput[1]);
                    column = Integer.parseInt(splitedInput[2]);
                    cageAnimalController(row, column);
                    break;

                case "well":
                    wellRequestHandler();
                    break;

                case "upgrade":
                    upgradeRequestHandler(splitedInput[1]);
                    break;

                case "loadcostume":
                    loadCostumeRequestHandler(splitedInput[1]);
                    break;

                case "run":
                    runRequestHandler(splitedInput[1]);
                    break;

                case "savegame":
                    saveGameRequestHandler(splitedInput[1]);
                    break;

                case "loadgame":
                    loadGameRequestHandler(splitedInput[1]);
                    break;

                case "print":
                    printRequestHandler(input);
                    break;

                case "turn":
                    turnRequestHandler(Integer.parseInt(splitedInput[1]));
                    break;

                case "truck":
                    switch (splitedInput[1]) {
                        case "add":
                            addToTruckListRequestHandler(splitedInput[2], Integer.parseInt(splitedInput[3]));
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
                    switch (splitedInput[1]) {
                        case "add":
                            addToHelicopterListRequestHandler(splitedInput[2], Integer.parseInt(splitedInput[3]));
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
            break;
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
            obj = mission.getWarehouse().get(itemName);
            mission.getHelicopter().addToList((Tradable) obj);
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
            obj = mission.getWarehouse().get(itemName);
            mission.getTruck().addToList((Tradable) obj);
            // TODO: 12/24/2018 tradable = storable -> dota interface o yeki kon
        } catch (NotFoundException e) {
            System.out.println("This item does not exist!");
        } catch (CapacityExceededException e) {
            System.out.println("there isn't enough space in Truck");
        }
    }

    private static void turnRequestHandler(int turnsPassed) {
        mission.passTurnRequest(turnsPassed);
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
        mission.addTimeDependentRequest(new UpgradeRequest(upgradingUnitName, mission));
    }

    private static void wellRequestHandler() {
        mission.addTimeDependentRequest(new RefillWellRequest(mission));
    }

    private static void cageAnimalController(int row, int column) {
        ArrayList<Predator> predators = mission.getMap().getPredatorsInCell(row, column);
        if (predators == null) {
            System.out.println("There isn't any predator in that cell");
            return;
        }

        for (Predator predator : predators) {
            Cage cage = new Cage(mission, new Position(row, column), predator);
            mission.getMap().addToMap(cage);
            try {
                mission.getMap().discardAnimal(predator);
            } catch (NotFoundException e) {
                e.printStackTrace();
                //chon predator haro az map gereftim nemitune in exception ettefagh biofte
            }
        }
    }

    private static void plantRequestHandler(int row, int column) {
        int minRow = Math.max(0, row - 1);
        int minColumn = Math.max(0, column - 1);
        int maxRow = Math.min(MAP_SIZE - 1, row + 1);
        int maxColumn = Math.min(MAP_SIZE - 1, column + 1);

        try {
            mission.getWell().extractWater(1);
        } catch (WellNotEnoughWaterException e) {
            System.out.println("refill well and try again");
            return;
        }

        int numberOfPlantsPlanted = 0;
        for (int thisrow = minRow; thisrow <= maxRow; thisrow++) {
            for (int thiscolumn = minColumn; thiscolumn <= maxColumn; thiscolumn++) {
                if (!mission.getMap().isPlanted(thisrow, thiscolumn)) {
                    numberOfPlantsPlanted++;
                    Plant plant = new Plant(new Position(thisrow, thiscolumn));
                    mission.getMap().plantInCell(plant);
                }
            }
        }

        if (numberOfPlantsPlanted == 0) {
            System.out.println("all cells in that area were planted before.");
        }

    }

    private static void pickupRequestController(int row, int column) {
        mission.getMap():
    }

    private static void buyAnimalRequestHandler(String animalName) {

    }
}
