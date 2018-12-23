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
                    new BuyAnimalRequestHandler(splitedInput[1]).start();
                    break;
                case "pickup":
                    int row = Integer.parseInt(splitedInput[1]);
                    int column = Integer.parseInt(splitedInput[2]);
                    new PickupRequestController(row, column).start();
                    break;
                case "plant":
                    row = Integer.parseInt(splitedInput[1]);
                    column = Integer.parseInt(splitedInput[2]);
                    new PlantRequestHandler(row,column).start();
                    break;
                case "cage":
                    row = Integer.parseInt(splitedInput[1]);
                    column = Integer.parseInt(splitedInput[2]);
                    new CageAnimalController(row, column).start();
                    break;
                case "well":
                    new WellRequestHandler().start();
                    break;
                case "upgrade":
                    new UpgradeRequestHandler(splitedInput[1]).start();
                    break;
                case "loadcostume":

            }
            break;
        }
    }
}
