package Model.Workshops;

import Model.Mission;
import Model.Placement.Map;
import Model.Placement.Position;
import Model.Products.Product;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class CookieBakery extends Workshop {
    private static int[] CookieBakeryProcessTimes = {6, 4, 2, 1};

    public CookieBakery(Mission mission) {
        super("CookieBakery", new String[]{"EggPowder"}, "Cookie", mission, CookieBakeryProcessTimes);
        imageView = mission.getGamePlayView().getCookieBakery();
    }


    @Override
    protected void putProductsInMap(ArrayList<Product> processedProducts) {
        int row = Map.MAP_SIZE / 2;
        int column = Map.MAP_SIZE - 1;
        for (Product processedProduct : processedProducts) {
            processedProduct.setPosition(new Position(row, column));
            mission.getMap().addToMap(processedProduct);
            column--;
        }
    }
}
