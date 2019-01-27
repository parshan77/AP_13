package Model.Workshops;

import Model.Mission;
import Model.Placement.Map;
import Model.Placement.Position;
import Model.Products.Product;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Spinnery extends Workshop {
    private static int[] SpinneryProcessTimes = {6, 4, 2, 1};

    public Spinnery(Mission mission) {
        super("Spinnery", new String[]{"Wool"}, "Fiber", mission,SpinneryProcessTimes);
        imageView = mission.getGamePlayView().getSpinnery();
    }


    @Override
    protected void putProductsInMap(ArrayList<Product> processedProducts) {
        int row = Map.MAP_SIZE / 2;
        int column = 0;
        for (Product processedProduct : processedProducts) {
            processedProduct.setPosition(new Position(row, column));
            mission.getMap().addToMap(processedProduct);
            column++;
        }
    }
}
