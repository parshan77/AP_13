package Controller;

import Exceptions.MaxLevelExceededException;
import Exceptions.NotEnoughMoneyException;
import Model.Vehicles.Helicopter;
import Model.Vehicles.Truck;
import View.Animations.BuzzAnimation;
import View.GamePlayView;
import View.HelicopterViewer;
import View.TruckViewer;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TruckController {
    public static void upgrade(GamePlayView gamePlayView) {
        Truck truck = gamePlayView.getMission().getTruck();
        TruckViewer viewer = gamePlayView.getTruckViewer();
        ImageView truckImageView = viewer.getImageView();
        Label upgradeCostLabel = viewer.getUpgradeCostLabel();
        try {
            truck.upgrade();
        } catch (NotEnoughMoneyException e) {
            BuzzAnimation.play(gamePlayView.getMoneyLabel());
            return;
        } catch (MaxLevelExceededException e) {
            //etefagh nemiofte age event handler null beshe
            return;
        }

        String url = "File:Textures\\Service\\Truck\\0" + (truck.getLevel() + 1) + ".png";
        Image truckImg = new Image(url);
        truckImageView.setImage(truckImg);
        if (!truck.isFullyUpgraded()) {
            String upgradeCost = Integer.toString(truck.getUpgradeCost());
            upgradeCostLabel.setText(upgradeCost);
        } else {
            upgradeCostLabel.setText("MAX");
            // TODO: 1/27/2019 event handlere button e upgrade ro null kon
        }
    }


}
