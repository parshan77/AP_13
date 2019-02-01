package Utils;

import Exceptions.NotFoundException;
import Interfaces.Storable;
import Model.Placement.Direction;
import Model.Placement.Map;
import Model.Placement.Position;
import Model.Products.*;
import javafx.scene.image.Image;

public class Utils {

    // TODO: 1/25/2019 ye esme khub vase in peida kon
    public static int getRandomNumber() {
        return (int) (Math.random() * 3) - 1;
    }

    public static Position getRandomPosition() {
        int row = (int) (Math.random() * Map.MAP_SIZE);
        int column = (int) (Math.random() * Map.MAP_SIZE);
        return new Position(row, column);
    }

    public static Direction getRandomDirection() {
        int random = (int) (Math.random() * 8);
        if (random == 0)
            return new Direction(-1, -1);
        else if (random == 1)
            return new Direction(0, -1);
        else if (random == 2)
            return new Direction(1, -1);
        else if (random == 3)
            return new Direction(1, 0);
        else if (random == 4)
            return new Direction(1, 1);
        else if (random == 5)
            return new Direction(0, 1);
        else if (random == 6)
            return new Direction(-1, 1);
        else
            return new Direction(-1, 0);
    }

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
            case "milk":
                return new Milk();
            case "wool":
                return new Wool();
        }
        throw new NotFoundException();
    }

    public static int getProductBuyCost(String productName) throws NotFoundException {
        switch (productName.toLowerCase()) {
            case "cake":
                return new Cake().getBuyCost();
            case "cloth":
                return new Cloth().getBuyCost();
            case "cookie":
                return new Cookie().getBuyCost();
            case "dress":
                return new Dress().getBuyCost();
            case "egg":
                return new Egg().getBuyCost();
            case "eggpowder":
                return new EggPowder().getBuyCost();
            case "fiber":
                return new Fiber().getBuyCost();
            case "milk":
                return new Milk().getBuyCost();
            case "wool":
                return new Wool().getBuyCost();
        }
        throw new NotFoundException();
    }

    public static int getProductSellCost(String productName)throws NotFoundException {
        switch (productName.toLowerCase()) {
            case "cake":
                return new Cake().getSellCost();
            case "cloth":
                return new Cloth().getSellCost();
            case "cookie":
                return new Cookie().getSellCost();
            case "dress":
                return new Dress().getSellCost();
            case "egg":
                return new Egg().getSellCost();
            case "eggpowder":
                return new EggPowder().getSellCost();
            case "fiber":
                return new Fiber().getSellCost();
            case "milk":
                return new Milk().getSellCost();
            case "wool":
                return new Wool().getSellCost();
        }
        throw new NotFoundException();
    }
}
