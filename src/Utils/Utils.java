package Utils;

import Exceptions.ProductNameNotFoundException;
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

    public static Product getProductObject(String name) throws ProductNameNotFoundException {
        String lowCaseName = name.toLowerCase();
        //todo: hameye product ha ezafe beshan
        switch (lowCaseName) {
            case "cake":
                return new Cake();
            case "cookie":
                return new Cookie();
            case "egg":
                return new Egg();
            case "flour":
                return new Flour();
            case "milk":
                return new Milk();
            case "wool":
                return new Wool();
        }
        throw new ProductNameNotFoundException();
    }
}
