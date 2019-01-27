package View;

import Controller.WarehouseController;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class WarehouseViewer {
    private GamePlayView gamePlayView;
    private ImageView warehouseImageView;
    private ImageView upgradeButton;
    private Label upgradeCostLabel;
    private Group root;

    public WarehouseViewer(GamePlayView gamePlayView) {
        this.gamePlayView = gamePlayView;
        root = gamePlayView.getRoot();
        showWarehouse();
    }

    private void showWarehouse() {
        Image warehouseImage = new Image("File:Textures\\Service\\Depot\\01.png");
        //width = 180       height = 148
        warehouseImageView = new ImageView(warehouseImage);
        warehouseImageView.relocate(gamePlayView.getMapX() + gamePlayView.getMapWidth() / 2.0 - 90,
                gamePlayView.getMapY() + gamePlayView.getMapHeight() + 20);
        warehouseImageView.setScaleX(1.2);
        warehouseImageView.setScaleY(1.2);
        root.getChildren().add(warehouseImageView);

        Image upgradeImg = new Image("File:Textures\\Buttons\\upgrade.png");
        upgradeButton = new ImageView(upgradeImg);
        double frameWidth = upgradeImg.getWidth();
        double frameHeight = upgradeImg.getHeight() / 4;
        upgradeButton.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        upgradeButton.relocate(warehouseImageView.getLayoutX(), warehouseImageView.getLayoutY());
        root.getChildren().add(upgradeButton);
        upgradeButton.setOnMouseClicked(event -> WarehouseController.upgrade(gamePlayView));

        upgradeCostLabel = new Label("1500");
        upgradeCostLabel.relocate(upgradeButton.getLayoutX() + 30, upgradeButton.getLayoutY() + 5);
        upgradeCostLabel.setFont(Font.font(14));
        upgradeCostLabel.setTextFill(Color.GOLD);
        upgradeCostLabel.setOnMouseClicked(event -> WarehouseController.upgrade(gamePlayView));
        root.getChildren().add(upgradeCostLabel);
    }

    public ImageView getWarehouseImageView() {
        return warehouseImageView;
    }

    public ImageView getUpgradeButton() {
        return upgradeButton;
    }

    public Label getUpgradeCostLabel() {
        return upgradeCostLabel;
    }
}
