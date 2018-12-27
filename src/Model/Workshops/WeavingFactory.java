package Model.Workshops;

import Model.Mission;
import Model.Placement.Position;
import Model.Products.Product;

import java.util.ArrayList;

public class WeavingFactory extends Workshop {

    public WeavingFactory(Mission mission) {
        super("WeavingFactory", new String[]{"Fiber"}, "Cloth", mission);
    }

    @Override
    public void show() {

    }

    @Override
    protected void putProductsInMap(ArrayList<Product> processedProducts) {
        int row = 0;
        int column = 0;
        for (Product processedProduct : processedProducts) {
            processedProduct.setPosition(new Position(row, column));
            mission.getMap().addToMap(processedProduct);
            column++;
        }
    }
}
