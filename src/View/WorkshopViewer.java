package View;

import Controller.WellController;
import Controller.WorkshopController;
import Model.Workshops.Workshop;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class WorkshopViewer {
    private ImageView imageView;
    private GamePlayView gamePlayView;
    private Group root;
    private Label upgradeCostLabel;
    private ImageView upgradeButton;
    private String workshopName;

    public WorkshopViewer(GamePlayView gamePlayView, String workshopName, int x, int y) {
        this.gamePlayView = gamePlayView;
        this.workshopName = workshopName;
        root = gamePlayView.getRoot();
        showWorkshop(x,y);
    }

    private void showWorkshop(int x, int y) {
        String url = "File:Textures\\Workshops\\" + workshopName + "\\01.png";
        Image image = new Image(url);
        double imageWidth = image.getWidth() / 4;
        double imageHeight = image.getHeight() / 4;
        imageView = new ImageView(image);
        imageView.relocate(x, y);
        imageView.setViewport(new Rectangle2D(0, 0, imageWidth, imageHeight));
        root.getChildren().add(imageView);

        imageView.setOnMouseClicked(event ->
                WorkshopController.startWorkshop(gamePlayView, workshopName));

        Image upgradeImage = new Image("File:Textures\\Buttons\\upgrade.png");
        ImageView cakeBakeryUpgradeButton = new ImageView(upgradeImage);
        double frameWidth = upgradeImage.getWidth();
        double frameHeight = upgradeImage.getHeight() / 4;
        cakeBakeryUpgradeButton.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        cakeBakeryUpgradeButton.relocate(imageView.getLayoutX(), imageView.getLayoutY());
        root.getChildren().add(cakeBakeryUpgradeButton);
        cakeBakeryUpgradeButton.setOnMouseClicked(event ->
                WorkshopController.upgrade(gamePlayView, workshopName));

        upgradeCostLabel = new Label("1500");
        upgradeCostLabel.relocate(cakeBakeryUpgradeButton.getLayoutX() + 30,
                cakeBakeryUpgradeButton.getLayoutY() + 5);
        upgradeCostLabel.setFont(Font.font(14));
        upgradeCostLabel.setTextFill(Color.GOLD);
        upgradeCostLabel.setOnMouseClicked(event -> WorkshopController.upgrade(gamePlayView,workshopName));
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

    public String getWorkshopName() {
        return workshopName;
    }
}
