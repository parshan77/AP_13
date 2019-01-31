package Controller;

import Exceptions.CapacityExceededException;
import Exceptions.MaxLevelExceededException;
import Exceptions.MissionCompletedException;
import Exceptions.NotEnoughMoneyException;
import Interfaces.Storable;
import Model.Placement.Map;
import Model.Products.Product;
import Model.Warehouse;
import View.Animations.BuzzAnimation;
import View.GamePlayView;
import View.ProductViewer;
import View.WarehouseViewer;
import javafx.animation.PathTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.ArrayList;

public class WarehouseController {

    public static void upgrade(GamePlayView gamePlayView) {
        Warehouse warehouse = gamePlayView.getMission().getWarehouse();
        WarehouseViewer viewer = gamePlayView.getWarehouseViewer();
        ImageView warehouseImageView = viewer.getWarehouseImageView();
        try {
            warehouse.upgrade();
        } catch (NotEnoughMoneyException e) {
            BuzzAnimation.play(gamePlayView.getMoneyLabel());
        } catch (MaxLevelExceededException e) {
            //rokh nemide
        }

        String url = "File:Textures\\Service\\Depot\\0" + (warehouse.getLevel() + 1) + ".png";
        Image wellImg = new Image(url);
        warehouseImageView.setImage(wellImg);


        Label upgradeCostLabel = viewer.getUpgradeCostLabel();
        if (!warehouse.isFullyUpgraded()) {
            String upgradeCost = Integer.toString(warehouse.getUpgradeCost());
            upgradeCostLabel.setText(upgradeCost);
        } else {
            upgradeCostLabel.setText("MAX");
            // TODO: 1/27/2019 event handler e button esh ro haminja null kon
        }
    }

    public static void storeProductsInCell(ProductViewer productViewer) {
        Product product = productViewer.getProduct();
        GamePlayView gamePlayView = productViewer.getGamePlayView();
        Warehouse warehouse = gamePlayView.getWarehouseViewer().getWarehouse();
        Map map = gamePlayView.getMission().getMap();
        ImageView productImageView = productViewer.getImageView();
        ImageView warehouseImageView = gamePlayView.getWarehouseViewer().getWarehouseImageView();

        ArrayList<Product> products = map.getAndDiscardProductsInCell(product.getPosition());
        for (Product product1 : products) {
            try {
                warehouse.store(product);
            } catch (CapacityExceededException e) {
                // TODO: 1/31/2019 warehouse ro buzz kon
                return;
            } catch (MissionCompletedException e) {
                e.printStackTrace();
                // TODO: 1/30/2019 bezan ino
            }
            Path path = new Path(new MoveTo(productImageView.getImage().getWidth() / 2,
                    productImageView.getImage().getHeight() / 2),
                    new LineTo(warehouseImageView.getLayoutX()
                            - productImageView.getLayoutX()
                            + warehouseImageView.getImage().getWidth() / 2,
                            warehouseImageView.getLayoutY()
                                    - productImageView.getLayoutY()
                                    + warehouseImageView.getImage().getHeight() / 2));
            PathTransition pathTransition = new PathTransition(Duration.millis(1000), path);
            pathTransition.setNode(productImageView);
            pathTransition.setCycleCount(1);
            pathTransition.play();
            pathTransition.setOnFinished(event -> {
                gamePlayView.getRoot().getChildren().remove(productImageView);
            });
        }
    }

    public static void storeProductOutofMap(ProductViewer productViewer, int x, int y) {
        Product product = productViewer.getProduct();
        GamePlayView gamePlayView = productViewer.getGamePlayView();
        WarehouseViewer warehouseViewer = gamePlayView.getWarehouseViewer();
        Warehouse warehouse = gamePlayView.getMission().getWarehouse();
        ImageView productImageView = productViewer.getImageView();
        ImageView warehouseImageView = gamePlayView.getWarehouseViewer().getWarehouseImageView();

        try {
            warehouse.store(product);
        } catch (CapacityExceededException e) {
            return;
            // TODO: 1/31/2019 warehouse ro buzz kon
        } catch (MissionCompletedException e) {
            e.printStackTrace();
            // TODO: 1/30/2019 bezan ino
        }

        Path path = new Path(new MoveTo(productImageView.getImage().getWidth() / 2,
                productImageView.getImage().getHeight() / 2),
                new LineTo(warehouseImageView.getLayoutX()
                        - productImageView.getLayoutX()
                        + warehouseImageView.getImage().getWidth() / 2,
                        warehouseImageView.getLayoutY()
                                - productImageView.getLayoutY()
                                + warehouseImageView.getImage().getHeight() / 2));
        PathTransition pathTransition = new PathTransition(Duration.millis(1000), path);
        pathTransition.setNode(productImageView);
        pathTransition.setCycleCount(1);
        pathTransition.play();
        pathTransition.setOnFinished(event -> {
            gamePlayView.getRoot().getChildren().remove(productImageView);
        });
    }
}
