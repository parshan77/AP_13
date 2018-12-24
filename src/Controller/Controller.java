package Controller;

import Model.Mission;

import java.util.Scanner;

public class Controller {
    private static Mission mission;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //todo:sharte while ro dorost kon
        mission = new Mission();        //todo:level o ina
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
                    plantRequestHandler(row,column);
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
                    if (splitedInput[1].equals("add"))
                        addToTruckListRequestHandler(splitedInput[2], Integer.parseInt(splitedInput[3]));
                    else if (splitedInput[1].equals("clear")) clearTruckListRequestHandler();
                    else if (splitedInput[1].equals("go")) goTruckRequestHandler();
                    break;

                case "helicopter":
                    if (splitedInput[1].equals("add"))
                        addToHelicopterListRequestHandler(splitedInput[2], Integer.parseInt(splitedInput[3]));
                    else if (splitedInput[1].equals("clear")) clearHelicopterListRequestHandler();
                    else if (splitedInput[1].equals("go")) goHelicopterRequestHandler();
                    break;
            }
            break;
        }
    }

    private static void goTruckRequestHandler() {

    }

    private static void goHelicopterRequestHandler() {

    }

    private static void clearHelicopterListRequestHandler() {

    }

    private static void addToHelicopterListRequestHandler(String itemName, int itemCount) {

    }

    private static void clearTruckListRequestHandler() {

    }

    private static void addToTruckListRequestHandler(String itemName, int itemCount) {

    }

    private static void turnRequestHandler(int turnsPassed) {

    }

    private static void printRequestHandler(String input) {
        //todo: input kolle dasture vurudie : print info map ...
    }

    private static void loadGameRequestHandler(String pathToJsonFile) {

    }

    private static void saveGameRequestHandler(String pathToJsonFile) {

    }

    private static void runRequestHandler(String mapName) {

    }

    private static void loadCostumeRequestHandler(String pathToCostumeDirectory) {

    }

    private static void upgradeRequestHandler(String upgradingUnitName) {

    }

    private static void wellRequestHandler() {

    }

    private static void cageAnimalController(int row, int column) {

    }

    private static void plantRequestHandler(int row, int column) {

    }

    private static void pickupRequestController(int row, int column) {

    }

    private static void buyAnimalRequestHandler(String animalName) {

    }
}
