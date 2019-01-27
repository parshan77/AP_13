package Model.Workshops;

import Model.Mission;
import Model.Placement.Map;
import Model.Placement.Position;
import Model.Products.Product;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class CustomWorkshop extends Workshop {
    private static int[] CustomWorkshopProcessTimes = {6, 4, 2, 1};

    public CustomWorkshop(String name, String[] inputsNames, String outputName, Mission mission) {
        super(name, inputsNames, outputName, mission, CustomWorkshopProcessTimes);
        imageView = mission.getGamePlayView().getCustomWorkshop();
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
}
