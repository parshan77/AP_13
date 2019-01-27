package Model.Workshops;

import Model.Mission;
import Model.Placement.Position;
import Model.Placement.PositionInScene;
import Model.Products.Product;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class WeavingFactory extends Workshop {
    private static int[] WeavingFactoryProcessTimes = {6, 4, 2, 1};

    public WeavingFactory(Mission mission) {
        super("WeavingFactory", new String[]{"Fiber"}, "Cloth", mission, WeavingFactoryProcessTimes);
        imageView = mission.getGamePlayView().getWeavingFactory();
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
