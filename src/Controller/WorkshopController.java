package Controller;

import Exceptions.MaxLevelExceededException;
import Exceptions.NotEnoughMoneyException;
import Exceptions.NotEnoughResourcesException;
import Exceptions.NotFoundException;
import Model.Mission;
import Model.Products.*;
import Model.Workshops.CustomWorkshop;
import Model.Workshops.Workshop;
import Utils.Utils;
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
            if (workshop.getLevel() == 2) {
                double lastX = workshopImageView.getLayoutX();
                double lastY = workshopImageView.getLayoutY();
                workshopImageView.setLayoutX(lastX - 30);
                workshopImageView.setLayoutY(lastY - 30);
            }
        } catch (NotEnoughMoneyException e) {
            BuzzAnimation.play(gamePlayView.getMoneyLabel());
            return;
        } catch (MaxLevelExceededException e) {
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
        }

    }

    public static boolean setupCustomWorkshop(GamePlayView gamePlayView, String[] inputs, String output,
                                              int workshopX, int workshopY) throws NotFoundException {
        for (String inputName : inputs) Utils.getProductObject(inputName);
        Utils.getProductObject(output);

        CustomWorkshop customWorkshop =
                new CustomWorkshop("CustomWorkshop", inputs, output, gamePlayView.getMission());
        gamePlayView.getMission().setCustomWorkshop(customWorkshop);

        WorkshopViewer customWorkshopViewer = new WorkshopViewer(gamePlayView, "CustomWorkshop",
                workshopX, workshopY);
        gamePlayView.setCustomWorkshopViewer(customWorkshopViewer);
        return true;
    }
}
