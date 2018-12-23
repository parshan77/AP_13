package Controller;

import Model.Mission;

import java.util.Scanner;

public class MissionController {
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
                    switch (splitedInput[1]) {
                        case "":
                            //todo
                    }
                    break;
                case "pickup":
                    int row = Integer.parseInt(splitedInput[1]);
                    int column = Integer.parseInt(splitedInput[2]);
                    mission.getMap().pickUpProduct(row, column);
                    //todo
                case "turn":
                    int numberOfTurns = Integer.parseInt(splitedInput[1]);
                    mission.passTurnRequest(numberOfTurns);
                case "cage":
                    row = Integer.parseInt(splitedInput[1]);
                    column = Integer.parseInt(splitedInput[2]);
//                    mission.getMap().getCell(row,column).
            }
            break;
        }
    }
}
