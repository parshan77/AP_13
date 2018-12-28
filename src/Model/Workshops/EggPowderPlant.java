package Model.Workshops;

import Model.Mission;
import Model.Placement.Map;
import Model.Placement.Position;
import Model.Products.Product;

import java.util.ArrayList;

public class EggPowderPlant extends Workshop {
    private static int[] EggPowderPlantProcessTimes = {6, 4, 2, 1};

    public EggPowderPlant(Mission mission) {
        super("EggPowderPlant", new String[]{"Egg"}, "EggPowder", mission, EggPowderPlantProcessTimes);
    }

    @Override
    public void show() {

    }

    @Override
    protected void putProductsInMap(ArrayList<Product> processedProducts) {
        int row = 0;
        int column = Map.MAP_SIZE - 1;
        for (Product processedProduct : processedProducts) {
            processedProduct.setPosition(new Position(row, column));
            mission.getMap().addToMap(processedProduct);
            column--;
        }
    }
}
