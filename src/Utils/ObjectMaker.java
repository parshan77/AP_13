package Utils;

import Exceptions.ProductNameNotFoundException;
import Model.Products.*;

public class ObjectMaker {
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
