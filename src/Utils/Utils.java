package Utils;

import Exceptions.NotFoundException;
import Model.Position;
import Model.Products.*;

public class Utils {

    public static double calculateDistance(Position position1, Position position2) {
        int row1 = position1.getRow();
        int row2 = position2.getRow();
        int column1 = position1.getColumn();
        int column2 = position2.getColumn();
        return Math.sqrt(Math.pow((row1 - row2), 2) + Math.pow((column1 - column2), 2));
    }

    public static Product getProductObject(String name) throws NotFoundException {
        String lowCaseName = name.toLowerCase();
        switch (lowCaseName) {
            case "cake":
                return new Cake();
            case "cloth":
                return new Cloth();
            case "cookie":
                return new Cookie();
            case "dress":
                return new Dress();
            case "egg":
                return new Egg();
            case "eggpowder":
                return new EggPowder();
            case "fiber":
                return new Fiber();
            case "flour":
                return new Flour();
            case "milk":
                return new Milk();
            case "wool":
                return new Wool();
        }
        throw new NotFoundException();
    }
}
