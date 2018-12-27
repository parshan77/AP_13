package Model.Workshops;

import Model.Mission;
import Model.Placement.Map;
import Model.Placement.Position;
import Model.Products.Product;

import java.util.ArrayList;

public class CustomWorkshop extends Workshop {

    public CustomWorkshop(String name, String[] inputsNames, String outputName, Mission mission) {
        super(name, inputsNames, outputName, mission);
    }

    @Override
    protected void putProductsInMap(ArrayList<Product> processedProducts) {
        int row = 0;
        int column = Map.MAP_SIZE / 2;
        for (Product processedProduct : processedProducts) {
            processedProduct.setPosition(new Position(row, column));
            mission.getMap().addToMap(processedProduct);
            row++;
        }
    }

    @Override
    public void show() {

    }
}
