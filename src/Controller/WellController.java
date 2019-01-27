package Controller;

import Exceptions.MaxLevelExceededException;
import Exceptions.NotEnoughMoneyException;
import Model.Well;
import View.Animations.BuzzAnimation;
import View.Animations.WellExtractingAnimation;
import View.GamePlayView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class WellController {

    public static void upgrade(GamePlayView gamePlayView) {
        Well well = gamePlayView.getMission().getWell();
        ImageView wellImageview = gamePlayView.getWellViewer().getWellImageView();
        Label upgradeLabel = gamePlayView.getWellViewer().getUpgradeCostLabel();
        try {
            well.upgrade();
        } catch (NotEnoughMoneyException e) {
            BuzzAnimation.play(gamePlayView.getMoneyLabel());
        } catch (MaxLevelExceededException e) {
            // TODO: 1/26/2019 lazeme chizi ezafe beshe?
        }

        String url = "File:Textures\\Service\\Well\\0" + (well.getLevel()+1) + ".png";
        Image wellImg = new Image(url);
        wellImageview.setImage(wellImg);
        wellImageview.setViewport(new Rectangle2D(0, 0,
                wellImg.getWidth() / 4, wellImg.getHeight() / 4));

        if (!well.isFullyUpgraded()) {
            String upgradeCost = Integer.toString(well.getUpgradeCost());
            upgradeLabel.setText(upgradeCost);
        } else {
            upgradeLabel.setText("MAX");
            // TODO: 1/27/2019 event handlere button e upgrade ro null kon
        }
    }

    public static void refill(GamePlayView gamePlayView) {
        Well well = gamePlayView.getMission().getWell();
        ImageView wellImageview = gamePlayView.getWellViewer().getWellImageView();
        ProgressBar wellProgressBar = gamePlayView.getWellViewer().getWellProgressBar();
        if (!well.isEmpty()) {
            BuzzAnimation.play(wellProgressBar);
            return;
        }
        try {
            gamePlayView.getMission().spendMoney(well.getRefillCost());
        } catch (NotEnoughMoneyException e) {
            BuzzAnimation.play(gamePlayView.getMoneyLabel());
            return;
        }
        wellImageview.setOnMouseClicked(event -> {});
        int wellRefillTime = well.getRefillTime() * gamePlayView.getTurnPerSecond();
        Animation extractingAnimation = WellExtractingAnimation.play(wellImageview,
                Duration.millis(wellRefillTime * 1000));
        extractingAnimation.setOnFinished(event -> WellController.refill(gamePlayView));

        KeyValue keyValue = new KeyValue(wellProgressBar.progressProperty(), 1);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(wellRefillTime * 1000), keyValue);
        Timeline timeline = new Timeline(keyFrame);
        timeline.setAutoReverse(false);
        timeline.setCycleCount(1);
        timeline.play();
    }
}
