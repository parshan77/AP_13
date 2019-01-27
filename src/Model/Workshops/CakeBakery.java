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
        imageView = mission.getGamePlayView().getCakeBakery();
    }


    @Override
    protected void putProductsInMap(ArrayList<Product> processedProducts) {
        // TODO: 1/27/2019 jahashun avaz shode
        int row = Map.MAP_SIZE - 1;
        int column = Map.MAP_SIZE - 1;
        for (Product processedProduct : processedProducts) {
            processedProduct.setPosition(new Position(row, column));
            mission.getMap().addToMap(processedProduct);
            column--;
        }
    }
}
