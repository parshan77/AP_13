package View;

import Controller.WellController;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class WellViewer {
    private GamePlayView gamePlayView;
    private Group root;
    private ImageView wellImageView;
    private ProgressBar wellProgressBar;
    private Label upgradeCostLabel;
    private ImageView upgradeButton;

    public WellViewer(GamePlayView gamePlayView) {
        this.gamePlayView = gamePlayView;
        root = gamePlayView.getRoot();
        showWell();
    }

    private void showWell() {
        String url = "File:Textures\\Service\\Well\\01.png";
        Image image = new Image(url);
        wellImageView = new ImageView(image);
        wellImageView.relocate(gamePlayView.getMapX() + 200, gamePlayView.getMapY() - 150);
        wellImageView.setViewport(new Rectangle2D(0, 0, image.getWidth() / 4, image.getHeight() / 4));
        root.getChildren().addAll(wellImageView);
        wellImageView.setOnMouseClicked(event -> WellController.refill(gamePlayView));

        wellProgressBar = new ProgressBar();
        wellProgressBar.setProgress(1);
        wellProgressBar.setStyle("-fx-accent: #00aabb;");
        wellProgressBar.setPrefSize(100, 10);
        wellProgressBar.getTransforms().setAll(
                new Translate(wellImageView.getLayoutX() + (image.getWidth() / 4) - 20,
                        wellImageView.getLayoutY() + image.getHeight() / 4 - 20),
                new Rotate(-90, 0, 0)
        );
        root.getChildren().add(wellProgressBar);

        Image wellUpgradeimage = new Image("File:Textures\\Buttons\\upgrade.png");
        upgradeButton = new ImageView(wellUpgradeimage);
        double frameWidth = wellUpgradeimage.getWidth();
        double frameHeight = wellUpgradeimage.getHeight() / 4;
        upgradeButton.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        upgradeButton.relocate(wellImageView.getLayoutX(), wellImageView.getLayoutY());
        root.getChildren().add(upgradeButton);
        upgradeButton.setOnMouseClicked(event -> WellController.upgrade(gamePlayView));

        upgradeCostLabel = new Label("1500");
        upgradeCostLabel.relocate(upgradeButton.getLayoutX() + 30, upgradeButton.getLayoutY() + 5);
        upgradeCostLabel.setFont(Font.font(14));
        upgradeCostLabel.setTextFill(Color.GOLD);
        upgradeCostLabel.setOnMouseClicked(event -> WellController.upgrade(gamePlayView));
        root.getChildren().add(upgradeCostLabel);
    }

    public ImageView getWellImageView() {
        return wellImageView;
    }

    public ProgressBar getWellProgressBar() {
        return wellProgressBar;
    }

    public Label getUpgradeCostLabel() {
        return upgradeCostLabel;
    }

    public ImageView getUpgradeButton() {
        return upgradeButton;
    }
}
