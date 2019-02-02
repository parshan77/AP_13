package View;

import Controller.TruckController;
import Model.Vehicles.Truck;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TruckViewer {
    private GamePlayView gamePlayView;
    private ImageView imageView;
    private Label upgradeCostLabel;
    private ImageView upgradeButton;
    private Group root;
    private Truck truck;

    public TruckViewer(GamePlayView gamePlayView) {
        this.gamePlayView = gamePlayView;
        truck = gamePlayView.getMission().getTruck();
        root = gamePlayView.getRoot();
        showIcon();
    }

    private void showIcon() {
        Image truckImg = new Image("File:Textures\\Service\\Truck\\01.png");
        imageView = new ImageView(truckImg);
        imageView.relocate(gamePlayView.getMapX(),
                gamePlayView.getMapY() + gamePlayView.getMapHeight() + 30);
        root.getChildren().add(imageView);

        Image upgradeImg = new Image("File:Textures\\Buttons\\upgrade.png");
        upgradeButton = new ImageView(upgradeImg);
        double frameWidth = upgradeImg.getWidth();
        double frameHeight = upgradeImg.getHeight() / 4;
        upgradeButton.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        upgradeButton.relocate(this.imageView.getLayoutX(), this.imageView.getLayoutY());
        root.getChildren().add(upgradeButton);
        upgradeButton.setOnMouseClicked(event -> TruckController.upgrade(gamePlayView));

        upgradeCostLabel = new Label(Integer.toString(truck.getUpgradeCost()));
        upgradeCostLabel.relocate(upgradeButton.getLayoutX() + 30, upgradeButton.getLayoutY() + 5);
        upgradeCostLabel.setFont(Font.font(14));
        upgradeCostLabel.setTextFill(Color.GOLD);
        upgradeCostLabel.setOnMouseClicked(event -> TruckController.upgrade(gamePlayView));
        root.getChildren().add(upgradeCostLabel);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Label getUpgradeCostLabel() {
        return upgradeCostLabel;
    }

    public ImageView getUpgradeButton() {
        return upgradeButton;
    }

    public Truck getTruck() {
        return truck;
    }





}
