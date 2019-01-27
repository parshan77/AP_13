package Controller;

import Exceptions.MaxLevelExceededException;
import Exceptions.NotEnoughMoneyException;
import Model.Warehouse;
import View.Animations.BuzzAnimation;
import View.GamePlayView;
import View.WarehouseViewer;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
}
