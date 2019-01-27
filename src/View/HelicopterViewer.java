package View;

import Controller.HelicopterController;
import Model.Vehicles.Helicopter;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HelicopterViewer  {
    private GamePlayView gamePlayView;
    private ImageView imageView;
    private Label upgradeCostLabel;
    private ImageView upgradeButton;
    private Group root;
    private Helicopter helicopter;

    public HelicopterViewer(GamePlayView gamePlayView) {
        this.gamePlayView = gamePlayView;
        helicopter = gamePlayView.getMission().getHelicopter();
        root = gamePlayView.getRoot();
        show();
    }

    private void show() {
        Image helicopterImage = new Image("File:Textures\\Service\\Helicopter\\01.png");
        imageView = new ImageView(helicopterImage);
        imageView.relocate(gamePlayView.getMapX()+gamePlayView.getMapWidth()- 150,
                gamePlayView.getMapY()+gamePlayView.getMapHeight());
        root.getChildren().add(imageView);

        Image upgradeImg = new Image("File:Textures\\Buttons\\upgrade.png");
        upgradeButton = new ImageView(upgradeImg);
        double frameWidth = upgradeImg.getWidth();
        double frameHeight = upgradeImg.getHeight() / 4;
        upgradeButton.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        upgradeButton.relocate(imageView.getLayoutX(), imageView.getLayoutY());
        root.getChildren().add(upgradeButton);
        upgradeButton.setOnMouseClicked(event -> HelicopterController.upgrade(gamePlayView));

        upgradeCostLabel = new Label(Integer.toString(helicopter.getUpgradeCost()));
        upgradeCostLabel.relocate(upgradeButton.getLayoutX() + 30, upgradeButton.getLayoutY() + 5);
        upgradeCostLabel.setFont(Font.font(14));
        upgradeCostLabel.setTextFill(Color.GOLD);
        upgradeCostLabel.setOnMouseClicked(event -> HelicopterController.upgrade(gamePlayView));
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
}
