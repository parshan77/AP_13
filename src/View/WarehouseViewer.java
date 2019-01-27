package View;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
        Image warehouseImage = new Image("File:Textures\\Service\\Depot\\02.png");
        //width = 180       height = 148
        warehouseImageView = new ImageView(warehouseImage);
        warehouseImageView.relocate(gamePlayView.getMapX() + gamePlayView.getMapWidth() / 2.0 - 90,
                gamePlayView.getMapY() + gamePlayView.getMapHeight() + 20);
        warehouseImageView.setScaleX(1.2);
        warehouseImageView.setScaleY(1.2);
        root.getChildren().add(warehouseImageView);
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
