package Controller;

import Exceptions.MaxLevelExceededException;
import Exceptions.NotEnoughMoneyException;
import Exceptions.NotEnoughResourcesException;
import Model.Mission;
import Model.Products.Product;
import Model.Workshops.Workshop;
import View.Animations.BuzzAnimation;
import View.Animations.WorkshopAnimation;
import View.GamePlayView;
import View.WorkshopViewer;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class WorkshopController {
    public static void startWorkshop(GamePlayView gamePlayView, String workshopName) {
        WorkshopViewer viewer = gamePlayView.getWorkshopViewer(workshopName);
        ImageView workshopImageView = viewer.getImageView();
        Mission mission = gamePlayView.getMission();
        Workshop workshop = mission.getWorkshop(workshopName);

        ArrayList<Product> inputs;
        try {
            inputs = workshop.collectInputs();      //intellij eshtebah mikone.
        } catch (NotEnoughResourcesException e) {
//            BuzzAnimation.play(gamePlayView.getWarehouseViewer().getWarehouseImageView());
            // TODO: 1/27/2019 buzz baraye imageview moshkel dare
            return;
        }
        Animation processingAnim = WorkshopAnimation.play(workshopImageView);
        processingAnim.setOnFinished(event -> {
            workshop.start(inputs);
//            gamePlayView.putProductsInMap()
            // TODO: 1/27/2019 put products in map
        });
    }

    public static void upgrade(GamePlayView gamePlayView, String workshopName) {
        Mission mission = gamePlayView.getMission();
        Workshop workshop = mission.getWorkshop(workshopName);
        WorkshopViewer viewer = gamePlayView.getWorkshopViewer(workshopName);
        ImageView workshopImageView = viewer.getImageView();

        try {
            workshop.upgrade();
        } catch (NotEnoughMoneyException e) {
            BuzzAnimation.play(gamePlayView.getMoneyLabel());
            return;
        } catch (MaxLevelExceededException e) {
            //rokh nemide
            //event handler e upgrade button ro bad az residan be max level null kon
            return;
        }
        String url = "File:Textures\\Workshops\\" + workshopName + "\\" + "0" + workshop.getLevel() + ".png";
        Image wellImg = new Image(url);
        workshopImageView.setImage(wellImg);
        workshopImageView.setViewport(new Rectangle2D(0, 0,
                wellImg.getWidth() / 4, wellImg.getHeight() / 4));

        Label upgradeCostLabel = viewer.getUpgradeCostLabel();
        if (!workshop.isFullyUpgraded()) {
            String upgradeCost = Integer.toString(workshop.getUpgradeCost());
            upgradeCostLabel.setText(upgradeCost);
        } else {
            upgradeCostLabel.setText("MAX");
            // TODO: 1/27/2019 event handler e button esh ro haminja null kon
        }

    }
}
