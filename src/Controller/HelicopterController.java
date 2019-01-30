package Controller;

import Exceptions.MaxLevelExceededException;
import Exceptions.NotEnoughMoneyException;
import Model.Vehicles.Helicopter;
import Model.Vehicles.Vehicle;
import View.Animations.BuzzAnimation;
import View.GamePlayView;
import View.HelicopterViewer;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelicopterController {
    public static void upgrade( GamePlayView gamePlayView) {
        Helicopter helicopter = gamePlayView.getMission().getHelicopter();
        HelicopterViewer viewer = gamePlayView.getHelicopterViewer();
        ImageView helicopterImageView = viewer.getImageView();
        Label upgradeCostLabel = viewer.getUpgradeCostLabel();

        try {
            helicopter.upgrade();
        } catch (NotEnoughMoneyException e) {
            BuzzAnimation.play(gamePlayView.getMoneyLabel());
            return;
        } catch (MaxLevelExceededException e) {
            return;
        }

        String url = "File:Textures\\Service\\Helicopter\\0" + (helicopter.getLevel() + 1) + ".png";
        Image helicopterImage = new Image(url);
        helicopterImageView.setImage(helicopterImage);
        if (!helicopter.isFullyUpgraded()) {
            String upgradeCost = Integer.toString(helicopter.getUpgradeCost());
            upgradeCostLabel.setText(upgradeCost);
        } else {
            upgradeCostLabel.setText("MAX");
            // TODO: 1/27/2019 event handlere button e upgrade ro null kon
        }
    }
}
