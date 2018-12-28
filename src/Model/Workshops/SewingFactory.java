package Model.Workshops;

import Model.Mission;
import Model.Placement.Map;
import Model.Placement.Position;
import Model.Products.Product;

import java.util.ArrayList;

public class SewingFactory extends Workshop {
    private static int[] SewingFactoryProcessTimes = {6, 4, 2, 1};

    public SewingFactory(Mission mission) {
        super("SewingFactory", new String[]{"Cloth"}, "Dress", mission, SewingFactoryProcessTimes);
    }

    @Override
    public void show() {

    }

    @Override
    protected void putProductsInMap(ArrayList<Product> processedProducts) {
        int row = Map.MAP_SIZE - 1;
        int column = 0;
        for (Product processedProduct : processedProducts) {
            processedProduct.setPosition(new Position(row, column));
            mission.getMap().addToMap(processedProduct);
            column++;
        }
    }
}
