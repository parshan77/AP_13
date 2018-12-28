package Model.Workshops;

import Model.Mission;
import Model.Placement.Map;
import Model.Placement.Position;
import Model.Products.Product;

import java.util.ArrayList;

public class CakeBakery extends Workshop {
    private static int[] CakeBakeryProcessTimes = {6, 4, 2, 1};

    public CakeBakery(Mission mission) {
        super("CakeBakery", new String[]{"Flour", "Cookie"}, "Cake", mission, CakeBakeryProcessTimes);
    }

    @Override
    public void show() {

    }

    @Override
    protected void putProductsInMap(ArrayList<Product> processedProducts) {
        int row = Map.MAP_SIZE - 1;
        int column = Map.MAP_SIZE - 1;
        for (Product processedProduct : processedProducts) {
            processedProduct.setPosition(new Position(row, column));
            mission.getMap().addToMap(processedProduct);
            column--;
        }
    }
}
