package View;

import Controller.WellController;
import Controller.WorkshopController;
import Model.Mission;
import View.Animations.SpriteAnimation;
import View.Animations.WorkshopAnimation;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GamePlayView extends Application {
    private final int cellWidth = 100;
    private final int cellHeight = 45;
    private final int mapWidth = 700;
    private final int mapHeight = 315;
    private int stageWidth;
    private int stageHeight;
    private int mapX;
    private int mapY;
    private Group root;
    private Mission mission;    // TODO: 1/26/2019 constructor
    private int turnPerSecond = 1; // TODO: 1/26/2019 constructor

    private ImageView buyHenButton;
    private ImageView buySheepButton;
    private ImageView buyCowButton;
    private ImageView buyDogButton;
    private ImageView buyCatButton;

    private ImageView helicopter;
    private ImageView truck;
    private ImageView warehouse;

    private ImageView cakeBakery;
    private Label cakeBakeryUpgradeCost;
    private ImageView cakeBakeryUpgradeButton;

    private ImageView cookieBakery;
    private Label cookieBakeryUpgradeCost;
    private ImageView cookieBakeryUpgradeButton;

    private ImageView eggPowderPlant;
    private Label eggPowderPlantUpgradeCost;
    private ImageView eggPowderPlantUpgradeButton;

    private ImageView sewingFactory;
    private Label sewingFactoryUpgradeCost;
    private ImageView sewingFactoryUpgradeButton;

    private ImageView spinnery;
    private Label spinneryUpgradeCost;
    private ImageView spinneryUpgradeButton;

    private ImageView weavingFactory;
    private Label weavingFactoryUpgradeCost;
    private ImageView weavingFactoryUpgradeButton;

    private ImageView customWorkshop;
    private Label customWorkshopUpgradeCost;
    private ImageView customWorkshopUpgradeButton;

    private Label moneyLabel;

    private ImageView well;
    private ProgressBar wellProgressBar;
    private Label wellUpgradeCostLabel;


    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.setTitle("FarmFrenzy");
        primaryStage.show();
        stageWidth = (int) primaryStage.getWidth();
        stageHeight = (int) primaryStage.getHeight();
        mapX = (stageWidth - mapWidth) / 2;
        mapY = (stageHeight - mapHeight) / 2 + 30;

        setBackground(root, (int) primaryStage.getWidth(), (int) primaryStage.getHeight());
        showMapRectangle(root);
        showCells(root);
        showWell(root);
        showTruck(root);
        showHelicopter(root);
        showWarehouse(root);
        showWorkshops(root);
        showTimer(root, stageWidth);
        showBuyLabels(root);
        showMoneyLabel(root);

        Button testButton = new Button("test");
        testButton.relocate(30, 30);
        testButton.resize(300, 100);
        testButton.setOnMouseClicked(event -> {

        });
        root.getChildren().add(testButton);
    }

    private void showMoneyLabel(Group root) {
        String url = "File:C:\\Users\\parshan\\Desktop\\FarmFrenzy\\Textures\\Labels\\coin_40_anim.png";
        Image image = new Image(url);
        ImageView imageView = new ImageView(image);
        int frameWidth = (int) image.getWidth() / 4;
        int frameHeight = (int) image.getHeight() / 4;
        imageView.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        imageView.relocate(stageWidth - 150, stageHeight - 100);
        Animation starAnimation = new SpriteAnimation(imageView, Duration.millis(1000), 16, 4,
                0, 0, frameWidth, frameHeight);
        starAnimation.setCycleCount(Animation.INDEFINITE);
        starAnimation.play();
        root.getChildren().add(imageView);

        moneyLabel = new Label("100");
        moneyLabel.relocate(imageView.getLayoutX() + 40, imageView.getLayoutY() - 10);
        moneyLabel.setTextFill(Color.GOLD);
        moneyLabel.setFont(Font.font(40));
        root.getChildren().add(moneyLabel);
    }

    private void showBuyLabels(Group root) {
        showHenLabel(root);
        showSheepLabel(root);
        showCowLabel(root);
        showDogLabel(root);
        showCatLabel(root);
    }

    private void showCatLabel(Group root) {
        String url = "File:C:\\Users\\parshan\\Desktop\\FarmFrenzy\\Textures\\Buttons\\Cat_level1.png";
        Image image = new Image(url);
        buyCatButton = new ImageView(image);
        double frameWidth = image.getWidth();
        double frameHeight = image.getHeight() / 4;
        buyCatButton.relocate(buyDogButton.getLayoutX(), buyDogButton.getLayoutY() + buyDogButton.getImage().getHeight() / 4);
        buyCatButton.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        root.getChildren().add(buyCatButton);

        Label catCostButton = new Label("1500");
        catCostButton.relocate(buyCatButton.getLayoutX() + 15, buyCatButton.getLayoutY() + 28.5);
        catCostButton.setTranslateY(10);
        catCostButton.setTextFill(Color.WHITE);
        catCostButton.setFont(Font.font(11));
        root.getChildren().addAll(catCostButton);
    }

    private void showDogLabel(Group root) {
        String url = "File:C:\\Users\\parshan\\Desktop\\FarmFrenzy\\Textures\\Buttons\\Dog.png";
        Image image = new Image(url);
        buyDogButton = new ImageView(image);
        double frameWidth = image.getWidth();
        double frameHeight = image.getHeight() / 4;
        buyDogButton.relocate(buyCowButton.getLayoutX(), buyCowButton.getLayoutY() + buyCowButton.getImage().getHeight() / 4);
        buyDogButton.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        root.getChildren().add(buyDogButton);

        Label dogCostButton = new Label("1000");
        dogCostButton.relocate(buyDogButton.getLayoutX() + 15, buyDogButton.getLayoutY() + 28.5);
        dogCostButton.setTranslateY(10);
        dogCostButton.setTextFill(Color.WHITE);
        dogCostButton.setFont(Font.font(11));
        root.getChildren().addAll(dogCostButton);
    }

    private void showCowLabel(Group root) {
        String url = "File:C:\\Users\\parshan\\Desktop\\FarmFrenzy\\Textures\\Buttons\\Cow.png";
        Image image = new Image(url);
        buyCowButton = new ImageView(image);
        double frameWidth = image.getWidth();
        double frameHeight = image.getHeight() / 4;
        buyCowButton.relocate(buySheepButton.getLayoutX(), buySheepButton.getLayoutY() + buySheepButton.getImage().getHeight() / 4);
        buyCowButton.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        root.getChildren().add(buyCowButton);

        Label buyCowLabel = new Label("800");
        buyCowLabel.relocate(buyCowButton.getLayoutX() + 17, buyCowButton.getLayoutY() + 28.5);
        buyCowLabel.setTranslateY(10);
        buyCowLabel.setTextFill(Color.WHITE);
        buyCowLabel.setFont(Font.font(11));
        root.getChildren().addAll(buyCowLabel);
    }

    private void showSheepLabel(Group root) {
        String url = "File:C:\\Users\\parshan\\Desktop\\FarmFrenzy\\Textures\\Buttons\\Sheep.png";
        Image image = new Image(url);
        buySheepButton = new ImageView(image);
        double frameWidth = image.getWidth();
        double frameHeight = image.getHeight() / 4;
        buySheepButton.relocate(buyHenButton.getLayoutX(), buyHenButton.getLayoutY() + buyHenButton.getImage().getHeight() / 4);
        buySheepButton.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        root.getChildren().add(buySheepButton);

        Label sheepCostLabel = new Label("500");
        sheepCostLabel.relocate(buySheepButton.getLayoutX() + 17, buySheepButton.getLayoutY() + 28.5);
        sheepCostLabel.setTranslateY(10);
        sheepCostLabel.setTextFill(Color.WHITE);
        sheepCostLabel.setFont(Font.font(11));
        root.getChildren().addAll(sheepCostLabel);
    }

    private void showHenLabel(Group root) {
        String url = "File:C:\\Users\\parshan\\Desktop\\FarmFrenzy\\Textures\\Buttons\\Hen.png";
        Image image = new Image(url);
        buyHenButton = new ImageView(image);
        double frameWidth = image.getWidth();
        double frameHeight = image.getHeight() / 4;
        buyHenButton.relocate(20, 250);
        buyHenButton.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        root.getChildren().add(buyHenButton);

        Label henCostLabel = new Label("200");
        henCostLabel.relocate(buyHenButton.getLayoutX() + 17, buyHenButton.getLayoutY() + 28.5);
        henCostLabel.setTranslateY(10);
        henCostLabel.setTextFill(Color.WHITE);
        henCostLabel.setFont(Font.font(11));
        root.getChildren().addAll(henCostLabel);
    }

    private void showTimer(Group root, int stageWidth) {
        String url = "File:C:\\Users\\parshan\\Desktop\\FarmFrenzy\\Textures\\Labels\\time_gold.png";
        Image timerPlaceImg = new Image(url);
        ImageView timerPlaceView = new ImageView(timerPlaceImg);
        timerPlaceView.relocate(stageWidth / 2 - 75, 5);
        timerPlaceView.setFitWidth(120);
        timerPlaceView.setPreserveRatio(true);
        root.getChildren().add(timerPlaceView);

        Label timerLabel = new Label("00:01");
        timerLabel.setFont(Font.font(20));
        timerLabel.relocate(timerPlaceView.getLayoutX() + 38, 0);
        timerLabel.setPrefSize(100, 50);
        timerLabel.setTextFill(Color.GOLD);
        root.getChildren().add(timerLabel);

        AnimationTimer timer = new AnimationTimer() {
            long startingTime = 0;
            long SECOND = 1_000_000_000;
            long MINUTE = 60 * SECOND;

            @Override
            public void handle(long now) {
                if (startingTime == 0) {
                    startingTime = now;
                } else {
                    int sec = (int) ((now - startingTime) / SECOND) % 60;
                    int min = (int) ((now - startingTime) / MINUTE);
                    showTime(sec, min);
                }
            }

            private void showTime(int sec, int min) {
                String time = getTimeString(min) + ":" + getTimeString(sec);
                timerLabel.setText(time);
            }

            private String getTimeString(int sec) {
                if (sec == 0)
                    return "00";
                if (sec < 10)
                    return "0" + sec;
                return Integer.toString(sec);
            }
        };
        timer.start();
    }

    private void showWell(Group root) {
        String url = "File:Textures\\Service\\Well\\01.png";
        Image image = new Image(url);
        well = new ImageView(image);
        well.relocate(mapX + 250, mapY - 130);
        well.setViewport(new Rectangle2D(0, 0, image.getWidth() / 4, image.getHeight() / 4));
        root.getChildren().addAll(well);
        well.setOnMouseClicked(event -> WellController.refill(this));

        wellProgressBar = new ProgressBar();
        wellProgressBar.setProgress(1);
        wellProgressBar.setStyle("-fx-accent: #00aabb;");
        wellProgressBar.setPrefSize(100, 10);
        wellProgressBar.getTransforms().setAll(
                new Translate(well.getLayoutX() + (image.getWidth() / 4) - 20, well.getLayoutY() + image.getHeight() / 4 - 20),
                new Rotate(-90, 0, 0)
        );
        root.getChildren().add(wellProgressBar);

        Image wellUpgradeimage = new Image("File:Textures\\Buttons\\upgrade.png");
        ImageView wellUpgradeButton = new ImageView(wellUpgradeimage);
        double frameWidth = wellUpgradeimage.getWidth();
        double frameHeight = wellUpgradeimage.getHeight() / 4;
        wellUpgradeButton.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        wellUpgradeButton.relocate(well.getLayoutX(), well.getLayoutY());
        root.getChildren().add(wellUpgradeButton);
        wellUpgradeButton.setOnMouseClicked(event -> WellController.upgrade(this));

        wellUpgradeCostLabel = new Label("1500");
        wellUpgradeCostLabel.relocate(wellUpgradeButton.getLayoutX() + 30, wellUpgradeButton.getLayoutY() + 5);
        wellUpgradeCostLabel.setFont(Font.font(14));
        wellUpgradeCostLabel.setTextFill(Color.GOLD);
        root.getChildren().add(wellUpgradeCostLabel);
    }

    public ImageView getWorkshop(String workshopName) {
        switch (workshopName.toLowerCase()) {
            case "cakebakery":
                int i = 2;
                return cakeBakery;
            case "cookiebakery":
                return cookieBakery;
            case "customworkshop":
                return customWorkshop;
            case "eggpowderplant":
                return eggPowderPlant;
            case "sewingfactory":
                return sewingFactory;
            case "spinnery":
                return spinnery;
            case "weavingfactory":
                return weavingFactory;
        }
        return null;
    }

    private void showWorkshops(Group root) {
        showCakeBakery(root);
        showSewingFactory(root);
        showCookieBakery(root);
        showWeavingFactory(root);
        showSpinnery(root);
        showEggPowderPlant(root);
    }

    private void showEggPowderPlant(Group root) {
        String url = "File:Textures\\Workshops\\EggPowderPlant\\01.png";
        Image img = new Image(url);
        double imageWidth = img.getWidth() / 4;
        double imageHeight = img.getHeight() / 4;
        eggPowderPlant = new ImageView(img);
        eggPowderPlant.relocate(mapX - 150, mapY + 200);
        eggPowderPlant.setViewport(new Rectangle2D(0, 0, imageWidth, imageHeight));
        root.getChildren().add(eggPowderPlant);

        eggPowderPlant.setOnMouseClicked(event ->
                WorkshopController.startWorkshop(this, "EggPowderPlant"));

        Image upgradeImage = new Image("File:Textures\\Buttons\\upgrade.png");
        eggPowderPlantUpgradeButton = new ImageView(upgradeImage);
        double frameWidth = upgradeImage.getWidth();
        double frameHeight = upgradeImage.getHeight() / 4;
        eggPowderPlantUpgradeButton.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        eggPowderPlantUpgradeButton.relocate(eggPowderPlant.getLayoutX(), eggPowderPlant.getLayoutY());
        root.getChildren().add(eggPowderPlantUpgradeButton);
        eggPowderPlantUpgradeButton.setOnMouseClicked(event ->
                WorkshopController.upgrade(this, "EggPowderPlant"));

        eggPowderPlantUpgradeCost = new Label("1500");
        eggPowderPlantUpgradeCost.relocate(eggPowderPlantUpgradeButton.getLayoutX() + 30,
                eggPowderPlantUpgradeButton.getLayoutY() + 5);
        eggPowderPlantUpgradeCost.setFont(Font.font(14));
        eggPowderPlantUpgradeCost.setTextFill(Color.GOLD);
        root.getChildren().add(eggPowderPlantUpgradeCost);
    }

    private void showSpinnery(Group root) {
        String url = "File:Textures\\Workshops\\Spinnery\\01.png";
        Image spinneryImage = new Image(url);
        double imageWidth = spinneryImage.getWidth() / 4;
        double imageHeight = spinneryImage.getHeight() / 4;
        spinnery = new ImageView(spinneryImage);
        spinnery.relocate(mapX - 150, mapY + 80);
        spinnery.setViewport(new Rectangle2D(0, 0, imageWidth, imageHeight));
        root.getChildren().add(spinnery);

        spinnery.setOnMouseClicked(event ->
                WorkshopController.startWorkshop(this, "Spinnery"));


        Image upgradeImage = new Image("File:Textures\\Buttons\\upgrade.png");
        spinneryUpgradeButton = new ImageView(upgradeImage);
        double frameWidth = upgradeImage.getWidth();
        double frameHeight = upgradeImage.getHeight() / 4;
        spinneryUpgradeButton.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        spinneryUpgradeButton.relocate(spinnery.getLayoutX(), spinnery.getLayoutY());
        root.getChildren().add(spinneryUpgradeButton);
        spinneryUpgradeButton.setOnMouseClicked(event ->
                WorkshopController.upgrade(this, "Spinnery"));

        spinneryUpgradeCost = new Label("1500");
        spinneryUpgradeCost.relocate(spinneryUpgradeButton.getLayoutX() + 30,
                spinneryUpgradeButton.getLayoutY() + 5);
        spinneryUpgradeCost.setFont(Font.font(14));
        spinneryUpgradeCost.setTextFill(Color.GOLD);
        root.getChildren().add(spinneryUpgradeCost);
    }

    private void showWeavingFactory(Group root) {
        String url = "File:Textures\\Workshops\\WeavingFactory\\01.png";
        Image image = new Image(url);
        double imageWidth = image.getWidth() / 4;
        double imageHeight = image.getHeight() / 4;
        weavingFactory = new ImageView(image);
        weavingFactory.relocate(mapX - 150, mapY - 30);
        weavingFactory.setViewport(new Rectangle2D(0, 0, imageWidth, imageHeight));
        root.getChildren().add(weavingFactory);

        weavingFactory.setOnMouseClicked(event ->
                WorkshopController.startWorkshop(this, "WeavingFactory"));

        Image upgradeImage = new Image("File:Textures\\Buttons\\upgrade.png");
        weavingFactoryUpgradeButton = new ImageView(upgradeImage);
        double frameWidth = upgradeImage.getWidth();
        double frameHeight = upgradeImage.getHeight() / 4;
        weavingFactoryUpgradeButton.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        weavingFactoryUpgradeButton.relocate(weavingFactory.getLayoutX(), weavingFactory.getLayoutY());
        root.getChildren().add(weavingFactoryUpgradeButton);
        weavingFactoryUpgradeButton.setOnMouseClicked(event ->
                WorkshopController.upgrade(this, "WeavingFactory"));

        weavingFactoryUpgradeCost = new Label("1500");
        weavingFactoryUpgradeCost.relocate(weavingFactoryUpgradeButton.getLayoutX() + 30,
                weavingFactoryUpgradeButton.getLayoutY() + 5);
        weavingFactoryUpgradeCost.setFont(Font.font(14));
        weavingFactoryUpgradeCost.setTextFill(Color.GOLD);
        root.getChildren().add(weavingFactoryUpgradeCost);
    }

    private void showCookieBakery(Group root) {
        String url = "File:Textures\\Workshops\\CookieBakery\\01.png";
        Image image = new Image(url);
        double imageWidth = image.getWidth() / 4;
        double imageHeight = image.getHeight() / 4;
        cookieBakery = new ImageView(image);
        cookieBakery.relocate(mapX + mapWidth + 30, mapY + 180);
        cookieBakery.setViewport(new Rectangle2D(0, 0, imageWidth, imageHeight));
        root.getChildren().add(cookieBakery);

        cookieBakery.setOnMouseClicked(event ->
                WorkshopController.startWorkshop(this, "CookieBakery"));

        Image upgradeImage = new Image("File:Textures\\Buttons\\upgrade.png");
        cookieBakeryUpgradeButton = new ImageView(upgradeImage);
        double frameWidth = upgradeImage.getWidth();
        double frameHeight = upgradeImage.getHeight() / 4;
        cookieBakeryUpgradeButton.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        cookieBakeryUpgradeButton.relocate(cookieBakery.getLayoutX(), cookieBakery.getLayoutY());
        root.getChildren().add(cookieBakeryUpgradeButton);
        cookieBakeryUpgradeButton.setOnMouseClicked(event ->
                WorkshopController.upgrade(this, "CookieBakery"));

        cookieBakeryUpgradeCost = new Label("1500");
        cookieBakeryUpgradeCost.relocate(cookieBakeryUpgradeButton.getLayoutX() + 30,
                cookieBakeryUpgradeButton.getLayoutY() + 5);
        cookieBakeryUpgradeCost.setFont(Font.font(14));
        cookieBakeryUpgradeCost.setTextFill(Color.GOLD);
        root.getChildren().add(cookieBakeryUpgradeCost);
    }

    private void showSewingFactory(Group root) {
        String url = "File:Textures\\Workshops\\SewingFactory\\01.png";
        Image image = new Image(url);
        //width = 680       height = 520
        double imageWidth = image.getWidth() / 4;
        double imageHeight = image.getHeight() / 4;
        sewingFactory = new ImageView(image);
        sewingFactory.relocate(mapX + mapWidth, mapY + 80);
        sewingFactory.setViewport(new Rectangle2D(0, 0, imageWidth, imageHeight));
        root.getChildren().add(sewingFactory);

        sewingFactory.setOnMouseClicked(event ->
                WorkshopController.startWorkshop(this, "SewingFactory"));

        Image upgradeImage = new Image("File:Textures\\Buttons\\upgrade.png");
        sewingFactoryUpgradeButton = new ImageView(upgradeImage);
        double frameWidth = upgradeImage.getWidth();
        double frameHeight = upgradeImage.getHeight() / 4;
        sewingFactoryUpgradeButton.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        sewingFactoryUpgradeButton.relocate(sewingFactory.getLayoutX(), sewingFactory.getLayoutY());
        root.getChildren().add(sewingFactoryUpgradeButton);
        sewingFactoryUpgradeButton.setOnMouseClicked(event ->
                WorkshopController.upgrade(this, "SewingFactory"));

        sewingFactoryUpgradeCost = new Label("1500");// TODO: 1/27/2019 gheimate upgrade dafe avval dorost nist
        sewingFactoryUpgradeCost.relocate(sewingFactoryUpgradeButton.getLayoutX() + 30,
                sewingFactoryUpgradeButton.getLayoutY() + 5);
        sewingFactoryUpgradeCost.setFont(Font.font(14));
        sewingFactoryUpgradeCost.setTextFill(Color.GOLD);
        root.getChildren().add(sewingFactoryUpgradeCost);
    }

    private void showCakeBakery(Group root) {
        String url = "File:Textures\\Workshops\\CakeBakery\\01.png";
        Image image = new Image(url);
        double imageWidth = image.getWidth() / 4;
        double imageHeight = image.getHeight() / 4;
        cakeBakery = new ImageView(image);
        cakeBakery.relocate(mapX + mapWidth - 10, mapY - 80);
        cakeBakery.setViewport(new Rectangle2D(0, 0, imageWidth, imageHeight));
        root.getChildren().add(cakeBakery);

        cakeBakery.setOnMouseClicked(event ->
                WorkshopController.startWorkshop(this, "CakeBakery"));

        Image upgradeImage = new Image("File:Textures\\Buttons\\upgrade.png");
        ImageView cakeBakeryUpgradeButton = new ImageView(upgradeImage);
        double frameWidth = upgradeImage.getWidth();
        double frameHeight = upgradeImage.getHeight() / 4;
        cakeBakeryUpgradeButton.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        cakeBakeryUpgradeButton.relocate(cakeBakery.getLayoutX(), cakeBakery.getLayoutY());
        root.getChildren().add(cakeBakeryUpgradeButton);
        cakeBakeryUpgradeButton.setOnMouseClicked(event ->
                WorkshopController.upgrade(this, "CakeBakery"));

        cakeBakeryUpgradeCost = new Label("1500");
        cakeBakeryUpgradeCost.relocate(cakeBakeryUpgradeButton.getLayoutX() + 30,
                cakeBakeryUpgradeButton.getLayoutY() + 5);
        cakeBakeryUpgradeCost.setFont(Font.font(14));
        cakeBakeryUpgradeCost.setTextFill(Color.GOLD);
        root.getChildren().add(cakeBakeryUpgradeCost);
    }

    private void showWarehouse(Group root) {
        Image warehouseImage = new Image("File:C:\\Users\\parshan\\Desktop\\FarmFrenzy\\Textures\\Service\\Depot\\02.png");
        //width = 180       height = 148
        warehouse = new ImageView(warehouseImage);
        warehouse.relocate(mapX + mapWidth / 2.0 - 90, mapY + mapHeight + 20);
        warehouse.setScaleX(1.2);
        warehouse.setScaleY(1.2);
        root.getChildren().add(warehouse);
    }

    private void showHelicopter(Group root) {
        Image helicopterImage = new Image("File:C:\\Users\\parshan\\Desktop\\FarmFrenzy\\Textures\\Service\\Helicopter\\01.png");
        helicopter = new ImageView(helicopterImage);
        helicopter.relocate(mapX + mapWidth - 150, mapY + mapHeight);
        root.getChildren().add(helicopter);
    }

    private void showTruck(Group root) {
        Image truckImg = new Image("File:C:\\Users\\parshan\\Desktop\\FarmFrenzy\\Textures\\Service\\Truck\\01.png");
        truck = new ImageView(truckImg);
        truck.relocate(mapX, mapY + mapHeight + 30);
        root.getChildren().add(truck);
    }

    private int getCellCenterX(int row, int column) {
        return mapX + cellWidth / 2 + column * cellWidth;
    }

    private int getCellCenterY(int row, int column) {
        return mapY + cellHeight / 2 + row * cellHeight;
    }

    private void showMapRectangle(Group root) {
        Rectangle mapRectangle = new Rectangle(mapX, mapY, mapWidth, mapHeight);
        mapRectangle.setFill(Color.TRANSPARENT);
        mapRectangle.setStroke(Color.BLACK);
        mapRectangle.setStrokeType(StrokeType.CENTERED);
        mapRectangle.setStrokeWidth(2);
        root.getChildren().add(mapRectangle);
    }

    private void showCells(Group root) {
        int rows = mapHeight / cellHeight;
        int columns = mapWidth / cellWidth;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Rectangle cell = new Rectangle(mapX + column * cellWidth, mapY + row * cellHeight,
                        cellWidth, cellHeight);
                cell.setFill(Color.TRANSPARENT);
                cell.setStroke(Color.BLACK);
                cell.setStrokeType(StrokeType.CENTERED);
                cell.setStrokeWidth(2);
                root.getChildren().add(cell);
            }
        }
    }

    private void setBackground(Group root, int width, int height) {
        Image backgroundImg = new Image("File:C:\\Users\\parshan\\Desktop\\FarmFrenzy\\Textures\\GameBackGround.jpg");
        ImageView background = new ImageView(backgroundImg);
        background.setFitWidth(width);
        background.setFitHeight(height);
        background.setPreserveRatio(false);
        root.getChildren().add(background);
    }

    public Group getRoot() {
        return root;
    }

    public ImageView getHelicopter() {
        return helicopter;
    }

    public ImageView getTruck() {
        return truck;
    }

    public ImageView getWarehouse() {
        return warehouse;
    }

    public ImageView getWell() {
        return well;
    }

    public ImageView getCakeBakery() {
        return cakeBakery;
    }

    public ImageView getCookieBakery() {
        return cookieBakery;
    }

    public ImageView getEggPowderPlant() {
        return eggPowderPlant;
    }

    public ImageView getSewingFactory() {
        return sewingFactory;
    }

    public ImageView getSpinnery() {
        return spinnery;
    }

    public ImageView getWeavingFactory() {
        return weavingFactory;
    }

    public ImageView getCustomWorkshop() {
        return customWorkshop;
    }

    public Label getMoneyLabel() {
        return moneyLabel;
    }

    public ProgressBar getWellProgressBar() {
        return wellProgressBar;
    }

    public Mission getMission() {
        return mission;
    }

    public Label getWellUpgradeCostLabel() {
        return wellUpgradeCostLabel;
    }

    public int getTurnPerSecond() {
        return turnPerSecond;
    }

    public ImageView getWorkshopUpgradeButton(String workshopName) {
        switch (workshopName.toLowerCase()) {
            case "cakebakery":
                return cakeBakeryUpgradeButton;
            case "cookiebakery":
                return cookieBakeryUpgradeButton;
            case "customworkshop":
                return customWorkshopUpgradeButton;
            case "eggpowderplant":
                return eggPowderPlantUpgradeButton;
            case "sewingfactory":
                return sewingFactoryUpgradeButton;
            case "spinnery":
                return spinneryUpgradeButton;
            case "weavingfactory":
                int i = 23; // TODO: 1/27/2019 clean code
                return weavingFactoryUpgradeButton;
        }
        return null;
    }

    public Label getWorkshopUpgradeCostLabel(String workshopName) {
        switch (workshopName.toLowerCase()) {
            case "cakebakery":
                return cakeBakeryUpgradeCost;
            case "cookiebakery":
                return cookieBakeryUpgradeCost;
            case "customworkshop":
                return customWorkshopUpgradeCost;
            case "eggpowderplant":
                return eggPowderPlantUpgradeCost;
            case "sewingfactory":
                return sewingFactoryUpgradeCost;
            case "spinnery":
                return spinneryUpgradeCost;
            case "weavingfactory":
                int i = 27; // TODO: 1/27/2019 clean code
                return weavingFactoryUpgradeCost;
        }
        return null;
    }
}

/*String url = "File:C:\\Users\\parshan\\Desktop\\FarmFrenzy\\Textures\\Grass\\grass1.png";
        Image image = new Image(url);
        ImageView imageView = new ImageView(image);
        imageView.relocate(getCellCenterX(0,0) - 20, getCellCenterY(0,0) - 30);
*/


/*Image image = new Image("File:C:\\Users\\parshan\\Desktop\\FarmFrenzy\\Textures\\Cages\\break01.png");
        ImageView imageView = new ImageView(image);
        imageView.setScaleX(1);
        imageView.setScaleY(1);
        imageView.setViewport(new Rectangle2D(0, 0, image.getWidth() / 5, image.getHeight() / 5));
        imageView.relocate(getCellCenterX(6,6) - 120, getCellCenterY(6,6) - 150);
        root.getChildren().add(imageView);*/




/*BearAnimations.up(root,getCellCenterX(3,3), getCellCenterY(3,3),
                    getCellCenterX(2,3), getCellCenterY(2,3));

            BearAnimations.down(root,getCellCenterX(1,1), getCellCenterY(1,1),
                    getCellCenterX(2,1), getCellCenterY(2,1));*/

           /* BearAnimations.right(root,getCellCenterX(6,6), getCellCenterY(6,6),
                    getCellCenterX(6,7), getCellCenterY(6,7));

            BearAnimations.left(root,getCellCenterX(3,6), getCellCenterY(3,6),
                    getCellCenterX(3,5), getCellCenterY(3,5));*/

            /*BearAnimations.upRight(root,getCellCenterX(2,2), getCellCenterY(2,2),
                    getCellCenterX(1,3), getCellCenterY(1,3));

            BearAnimations.upLeft(root,getCellCenterX(4,4), getCellCenterY(4,4),
                    getCellCenterX(3,3), getCellCenterY(3,3));*/

           /* BearAnimations.downLeft(root,getCellCenterX(4,4), getCellCenterY(4,4),
                    getCellCenterX(5,3), getCellCenterY(5,3));

            BearAnimations.downRight(root,getCellCenterX(2,2), getCellCenterY(2,2),
                    getCellCenterX(3,3), getCellCenterY(3,3));*/
//            HenAnimations.eat(root, getCellCenterX(7, 7), getCellCenterY(7, 7));
//            HenAnimations.die(root, getCellCenterX(0, 0), getCellCenterY(0, 0));

//           BearAnimations.caged(root,getCellCenterX(3,3),getCellCenterY(3,3));
//            CatAnimations.battle(root, getCellCenterX(7, 7), getCellCenterY(7, 7));