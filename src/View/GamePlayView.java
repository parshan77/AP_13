package View;

import Controller.AnimalController;
import Controller.WorkshopController;
import Exceptions.NotEnoughMoneyException;
import Exceptions.NotFoundException;
import Exceptions.TradingListIsEmptyException;
import Model.Animals.Domestics.Cow;
import Model.Animals.Domestics.Hen;
import Model.Animals.Domestics.Sheep;
import Model.Animals.Predators.Bear;
import Model.Animals.Predators.Lion;
import Model.Animals.Seekers.Cat;
import Model.Animals.Seekers.Dog;
import Model.LevelRequirementsChecker;
import Model.Mission;
import Model.Network.Packet.Profile;
import Model.Placement.Direction;
import Model.Placement.Map;
import Model.Placement.Position;
import Model.Products.*;
import Model.Vehicles.Helicopter;
import Model.Vehicles.Truck;
import Model.Warehouse;
import View.Animations.AnimalAnimation;
import View.Animations.BuzzAnimation;
import View.Animations.SpriteAnimation;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;

public class GamePlayView extends Application {
    private final int cellWidth = 50;
    private final int cellHeight = 25;
    private final int mapWidth = 700;
    private final int mapHeight = 350;
    private int stageWidth;
    private int stageHeight;
    private int mapX;
    private int mapY;
    private Group root;
    private Mission mission;
    private int turnsPerSecond;

    private AnimationTimer timer;

    private Label customWorkshopSetupLabel;
    private Label customWorkshopSetupCost;
    private ImageView setupCustomWorkshopButton;

    private ImageView buyHenButton;
    private ImageView buySheepButton;
    private ImageView buyCowButton;
    private ImageView buyDogButton;
    private ImageView buyCatButton;
    private Label catCostLabel;
    private Button catUpgradeButton;

    private Label moneyLabel;

    private HelicopterViewer helicopterViewer;
    private TruckViewer truckViewer;

    private WellViewer wellViewer;
    private WarehouseViewer warehouseViewer ;

    private WorkshopViewer eggPowderPlantViewer;
    private WorkshopViewer spinneryViewer;
    private WorkshopViewer weavingFactoryViewer;
    private WorkshopViewer cakeBakeryViewer;
    private WorkshopViewer cookieBakeryViewer;
    private WorkshopViewer customWorkshopViewer;
    private WorkshopViewer sewingFactoryViewer;

    private ArrayList<AnimalViewer> animalViewers = new ArrayList<>();
    private ArrayList<ArrayList<CellViewer>> cellViewers = new ArrayList<>();

    private ArrayList<Animation> inProcessAnimations = new ArrayList<>();
    ArrayList<String> contacts = new ArrayList<>();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        contacts.add("ali");
        LevelRequirementsChecker lrc = new LevelRequirementsChecker(0, 3, 0,
                0, 0, 0, 0, 3, 0,
                0, 0, 0, 0);
        mission = new Mission(10000, "GraphicTest", lrc, null, this);
        turnsPerSecond = 1;

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
        showMenuButton();
        showSettingsButton();
        showMapRectangle(root);
        showCells(root);
        wellViewer = new WellViewer(this);
        truckViewer = new TruckViewer(this);
        helicopterViewer = new HelicopterViewer(this);
        warehouseViewer = new WarehouseViewer(this);
        showWorkshops();
        showTimer(root, stageWidth);
        showBuyLabels(root);
        showMoneyLabel(root);

        showPvChat(contacts);
        showChat();
        buyHenButton.setOnMouseClicked(event -> finishMission());
    }

    public void finishMission() {
        pause();

        Rectangle fillScreenRectangle = new Rectangle(0, 0, stageWidth, stageHeight);
        fillScreenRectangle.setFill(Color.BLACK);
        fillScreenRectangle.setOpacity(0.4);
        root.getChildren().add(fillScreenRectangle);

        Rectangle statusRectangle = new Rectangle(stageWidth / 2 - 200, stageHeight / 2 - 150, 400, 300);
        statusRectangle.setFill(Color.LIGHTGREEN);
        statusRectangle.setArcHeight(10);
        statusRectangle.setArcWidth(10);
        root.getChildren().addAll(statusRectangle);

        Label winLabel = new Label("You Win!");
        winLabel.setTextFill(Color.BLACK);
        winLabel.setFont(Font.font(40));
        winLabel.relocate(stageWidth / 2 - 75,stageHeight / 2 - 40);
        root.getChildren().addAll(winLabel);

        Button backToVillageButton = new Button("Go To Village");
        backToVillageButton.setStyle("-fx-background-color: #4d6fff; -fx-font-size: 12");
        backToVillageButton.relocate(statusRectangle.getWidth() + statusRectangle.getX()- 110
                , statusRectangle.getHeight() + statusRectangle.getY()- 40);
        backToVillageButton.setPrefSize(100, 20);
        root.getChildren().addAll(backToVillageButton);

        backToVillageButton.setOnMousePressed(event ->
                backToVillageButton.setStyle("-fx-background-color: #4b6de6; -fx-font-size: 12"));

        backToVillageButton.setOnMouseReleased(event -> {
            backToVillageButton.setStyle("-fx-background-color: #4d6fff; -fx-font-size: 12");
        });

        // TODO: 2/3/2019 backToVillage ro bezan
    }

    private void showMenuButton() {
        String url = "File:Textures\\Buttons\\menu2.png";
        Image image = new Image(url);
        ImageView imageView = new ImageView(image);

        imageView.relocate(20, 20);
        root.getChildren().add(imageView);

        imageView.setOnMouseClicked(event -> pauseGame());
    }

    private void showSettingsButton() {
        String url = "File:Textures\\Buttons\\settings.png";
        Image image = new Image(url);
        ImageView imageView = new ImageView(image);

        imageView.relocate(20, 20 + 60 + 20);
        root.getChildren().add(imageView);

        imageView.setOnMouseClicked(event -> pauseGame());
    }

    public void pauseGame() {
        Rectangle rectangle = new Rectangle(0, 0, stageWidth, stageHeight);
        rectangle.setFill(Color.BLACK);
        rectangle.setOpacity(0.4);
        root.getChildren().add(rectangle);
        rectangle.setOnMouseClicked(event -> {
            root.getChildren().remove(rectangle);
            resume();
        });
        pause();
    }

    private void resume() {
        for (Animation animation : inProcessAnimations) {
            animation.play();
        }
        timer.start();
    }

    private void pause() {
        for (Animation animation : inProcessAnimations) {
            animation.pause();
        }
        timer.stop();
    }

    public void addAnimation(Animation animation) {
        inProcessAnimations.add(animation);
    }

    public void discardAnimation(Animation animation) {
        inProcessAnimations.remove(animation);
    }

    public void addAnimal(AnimalViewer animalViewer) {
        animalViewers.add(animalViewer);
    }

    public void discardAnimal(AnimalViewer animalViewer) {
        animalViewers.remove(animalViewer);
    }

    public WarehouseViewer getWarehouseViewer() {
        return warehouseViewer;
    }

    public int getMapX() {
        return mapX;
    }

    public int getMapY() {
        return mapY;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public HelicopterViewer getHelicopterViewer() {
        return helicopterViewer;
    }

    public WellViewer getWellViewer() {
        return wellViewer;
    }

    private void showTimer(Group root, int stageWidth) {
        String url = "File:Textures\\Labels\\time_gold.png";
        Image timerPlaceImg = new Image(url);
        ImageView timerPlaceView = new ImageView(timerPlaceImg);
        timerPlaceView.relocate(stageWidth / 2.0 - 75, 5);
        timerPlaceView.setFitWidth(120);
        timerPlaceView.setPreserveRatio(true);
        root.getChildren().add(timerPlaceView);

        Label timerLabel = new Label("00:00");
        timerLabel.setFont(Font.font(20));
        timerLabel.relocate(timerPlaceView.getLayoutX() + 38, 0);
        timerLabel.setPrefSize(100, 50);
        timerLabel.setTextFill(Color.GOLD);
        root.getChildren().add(timerLabel);

        timer = new AnimationTimer() {
            long startingTime = 0;
            long SECOND = 1_000_000_000;
            long turnsPassed = 0;
            long turnTime = SECOND / turnsPerSecond;
            long secondsPassed = 0;

            @Override
            public void handle(long now) {
                if (startingTime == 0) {
                    startingTime = now;
                } else {
                    if (now - startingTime > turnTime) {
                        turnsPassed++;
                        mission.clock();
                    }
                    if (now - startingTime > SECOND) {
                        secondsPassed++;
                        startingTime = now;
                        int sec = (int) secondsPassed % 60;
                        int min = (int) (secondsPassed / 60);
                        showTime(sec, min);
                    }
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

    public WorkshopViewer getWorkshopViewer(String workshopName) {
        switch (workshopName.toLowerCase()) {
            case "cakebakery":
                return cakeBakeryViewer;
            case "cookiebakery":
                return cookieBakeryViewer;
            case "customworkshop":
                return customWorkshopViewer;
            case "eggpowderplant":
                return eggPowderPlantViewer;
            case "sewingfactory":
                return sewingFactoryViewer;
            case "spinnery":
                return spinneryViewer;
            case "weavingfactory":
                return weavingFactoryViewer;
        }
        return null;
    }

    public void setCustomWorkshopViewer(WorkshopViewer workshopViewer) {
        this.customWorkshopViewer = workshopViewer;
    }

    private void showWorkshops() {
        cakeBakeryViewer = new WorkshopViewer(this, "CakeBakery",
                mapX + mapWidth + 40, mapY - 80);
        sewingFactoryViewer = new WorkshopViewer(this, "SewingFactory",
                mapX + mapWidth + 40, mapY + 80);
        cookieBakeryViewer = new WorkshopViewer(this, "CookieBakery",
                mapX + mapWidth + 60, mapY + 180);
        weavingFactoryViewer = new WorkshopViewer(this, "WeavingFactory",
                mapX - 180, mapY - 45);
        spinneryViewer = new WorkshopViewer(this, "Spinnery",
                mapX - 180, mapY + 70);
        eggPowderPlantViewer = new WorkshopViewer(this, "EggPowderPlant",
                mapX - 180, mapY + 200);
        showCustomWorkshopButton();
    }

    private void showCustomWorkshopButton() {
        String url = "File:Textures\\Buttons\\CustomWorkshopButton.png";
        Image image = new Image(url);
        setupCustomWorkshopButton = new ImageView(image);
        setupCustomWorkshopButton.relocate(mapX + mapWidth - 250, mapY - 140);
        setupCustomWorkshopButton.setScaleX(1.2);
        setupCustomWorkshopButton.setScaleY(1.2);
        root.getChildren().add(setupCustomWorkshopButton);
        setupCustomWorkshopButton.setOnMouseClicked(event -> viewCustomWorkshopPanel());

        customWorkshopSetupLabel = new Label("Custom\nWorkshop");
        customWorkshopSetupLabel.setTextAlignment(TextAlignment.CENTER);
        customWorkshopSetupLabel.setTextFill(Color.BLACK);
        customWorkshopSetupLabel.setFont(Font.font(11));
        customWorkshopSetupLabel.relocate(setupCustomWorkshopButton.getLayoutX() + 8, setupCustomWorkshopButton.getLayoutY() + 3);
        root.getChildren().add(customWorkshopSetupLabel);
        customWorkshopSetupLabel.setOnMouseClicked(event -> viewCustomWorkshopPanel());


        customWorkshopSetupCost = new Label("1500");
        customWorkshopSetupCost.setTextFill(Color.GOLD);
        customWorkshopSetupCost.setFont(Font.font(15));
        customWorkshopSetupCost.relocate(setupCustomWorkshopButton.getLayoutX() + 15, setupCustomWorkshopButton.getLayoutY() + 53);
        root.getChildren().add(customWorkshopSetupCost);
        customWorkshopSetupCost.setOnMouseClicked(event -> viewCustomWorkshopPanel());

    }

    private void viewCustomWorkshopPanel() {
        Rectangle pauseRectangle = new Rectangle(0, 0, stageWidth, stageHeight);
        pauseRectangle.setFill(Color.BLACK);
        pauseRectangle.setOpacity(0.4);
        root.getChildren().addAll(pauseRectangle);

        pause();
        Rectangle customWSHRectangle = new Rectangle(0, 0, Color.BEIGE);
        customWSHRectangle.setOpacity(0.7);
        customWSHRectangle.relocate(setupCustomWorkshopButton.getLayoutX() - 150, setupCustomWorkshopButton.getLayoutY());
        KeyValue heightValue = new KeyValue(customWSHRectangle.heightProperty(), 200);
        KeyValue widthValue = new KeyValue(customWSHRectangle.widthProperty(), 300);
        KeyFrame widthFrame = new KeyFrame(Duration.millis(600), widthValue);
        KeyFrame heightFrame = new KeyFrame(Duration.millis(600), heightValue);
        Timeline timeline = new Timeline(heightFrame, widthFrame);
        timeline.play();
        root.getChildren().add(customWSHRectangle);

        Button okButton = new Button("Build!");
        okButton.setStyle("-fx-background-color: #5352ff; -fx-text-fill: white; -fx-font-size: 12 ; -fx-opacity: 0.7");
        okButton.relocate(customWSHRectangle.getLayoutX() + 235,
                customWSHRectangle.getLayoutY() + 170);
        okButton.setPrefSize(60, 20);


        Label inputsLabel = new Label("inputs:");
        Label outputsLabel = new Label("output:");
        inputsLabel.setTextFill(Color.BLACK);
        outputsLabel.setTextFill(Color.BLACK);
        TextField inputText = new TextField();
        TextField outputText = new TextField();
        inputsLabel.relocate(customWSHRectangle.getLayoutX() + 10, customWSHRectangle.getLayoutY() + 60);
        outputsLabel.relocate(inputsLabel.getLayoutX(), inputsLabel.getLayoutY() + 50);
        inputText.relocate(inputsLabel.getLayoutX() + inputsLabel.getWidth() + 50,
                inputsLabel.getLayoutY());
        outputText.relocate(outputsLabel.getLayoutX() + outputsLabel.getWidth() + 50,
                outputsLabel.getLayoutY());

        Image exitImage = new Image("File:Textures\\Buttons\\exit.png");
        ImageView exitImageView = new ImageView(exitImage);
        exitImageView.relocate(customWSHRectangle.getLayoutX() + 245, customWSHRectangle.getLayoutY());
        exitImageView.setFitWidth(50);
        exitImageView.setPreserveRatio(true);


        timeline.setOnFinished(event -> {
            root.getChildren().addAll(okButton, inputsLabel, inputText, outputText, outputsLabel, exitImageView);
        });

        okButton.setOnMousePressed(event -> {
            okButton.setStyle("-fx-background-color: #030aff; -fx-text-fill: white;-fx-font-size: 12 ; -fx-opacity: 0.7");
        });

        okButton.setOnMouseReleased(event -> {
            okButton.setStyle("-fx-background-color: #5352ff; -fx-text-fill: white;-fx-font-size: 12 ; -fx-opacity: 0.7");

            if (!(inputText.getText().equals("")) && !(outputText.getText().equals(""))) {
                String[] inputs = inputText.getText().split("[\\s,]");
                String output = outputText.getText();
                try {
                    mission.spendMoney(Integer.parseInt(customWorkshopSetupCost.getText()));
                } catch (NotEnoughMoneyException e) {
                    BuzzAnimation.play(moneyLabel);
                    return;
                }
                try {
                    WorkshopController.setupCustomWorkshop(this, inputs, output,
                            (int) setupCustomWorkshopButton.getLayoutX() - 60,
                            (int) setupCustomWorkshopButton.getLayoutY() );
                } catch (NotFoundException e) {
                    mission.addMoney(Integer.parseInt(customWorkshopSetupCost.getText()));
                    return;
                }
                root.getChildren().removeAll(okButton, inputsLabel, inputText, outputText, outputsLabel,
                            exitImageView, pauseRectangle, customWSHRectangle, customWorkshopSetupCost,
                        customWorkshopSetupLabel,setupCustomWorkshopButton);
                    resume();
                }
        });
        exitImageView.setOnMouseClicked(event -> {
            root.getChildren().removeAll(okButton, inputsLabel, inputText, outputText, outputsLabel, exitImageView,
                    pauseRectangle, customWSHRectangle);
            resume();
        });
    }

    private void showMoneyLabel(Group root) {
        String url = "File:Textures\\Labels\\coin_40_anim.png";
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

        String startingMoney = Integer.toString(mission.getMoney());
        moneyLabel = new Label(startingMoney);
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
        String url = "File:Textures\\Buttons\\Cat_level1.png";
        Image image = new Image(url);
        buyCatButton = new ImageView(image);
        double frameWidth = image.getWidth();
        double frameHeight = image.getHeight() / 4;
        buyCatButton.relocate(buyDogButton.getLayoutX(), buyDogButton.getLayoutY() + buyDogButton.getImage().getHeight() / 4);
        buyCatButton.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        root.getChildren().add(buyCatButton);
        buyCatButton.setOnMouseClicked(event -> AnimalController.buyAnimal("Cat", this));

        String buyCost = Integer.toString(Cat.getBuyCost());
        catCostLabel = new Label(buyCost);
        catCostLabel.relocate(buyCatButton.getLayoutX() + 13, buyCatButton.getLayoutY() + 28.5);
        catCostLabel.setTranslateY(10);
        catCostLabel.setTextFill(Color.WHITE);
        catCostLabel.setFont(Font.font(11));
        root.getChildren().addAll(catCostLabel);
        catCostLabel.setOnMouseClicked(event -> AnimalController.buyAnimal("Cat", this));

        catUpgradeButton = new Button("Upgrade Cat");
        catUpgradeButton.relocate(buyCatButton.getLayoutX() - 7,
                buyCatButton.getLayoutY() + buyCatButton.getImage().getHeight() / 4 + 5);
        catUpgradeButton.setPrefSize(80, 30);
        catUpgradeButton.setStyle("-fx-font-size: 11; -fx-background-color: rgb(0,106,255); -fx-text-fill: white");
        root.getChildren().add(catUpgradeButton);

        catUpgradeButton.setOnMousePressed(event -> {
            catUpgradeButton.setStyle("-fx-font-size: 11; -fx-background-color: rgb(0,56,255); -fx-text-fill: white");
        });

        catUpgradeButton.setOnMouseReleased(event -> {
            catUpgradeButton.setStyle("-fx-font-size: 11; -fx-background-color: rgb(0,106,255); -fx-text-fill: white");
            AnimalController.upgradeCats(this);
        });
    }

    private void showDogLabel(Group root) {
        String url = "File:Textures\\Buttons\\Dog.png";
        Image image = new Image(url);
        buyDogButton = new ImageView(image);
        double frameWidth = image.getWidth();
        double frameHeight = image.getHeight() / 4;
        buyDogButton.relocate(buyCowButton.getLayoutX(), buyCowButton.getLayoutY() + buyCowButton.getImage().getHeight() / 4);
        buyDogButton.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        root.getChildren().add(buyDogButton);
        buyDogButton.setOnMouseClicked(event -> AnimalController.buyAnimal("Dog", this));

        String buyCost = Integer.toString(Dog.getBuyCost());
        Label dogCostButton = new Label(buyCost);
        dogCostButton.relocate(buyDogButton.getLayoutX() + 14, buyDogButton.getLayoutY() + 28.5);
        dogCostButton.setTranslateY(10);
        dogCostButton.setTextFill(Color.WHITE);
        dogCostButton.setFont(Font.font(11));
        root.getChildren().addAll(dogCostButton);
        dogCostButton.setOnMouseClicked(event -> AnimalController.buyAnimal("Dog", this));

    }

    private void showCowLabel(Group root) {
        String url = "File:Textures\\Buttons\\Cow.png";
        Image image = new Image(url);
        buyCowButton = new ImageView(image);
        double frameWidth = image.getWidth();
        double frameHeight = image.getHeight() / 4;
        buyCowButton.relocate(buySheepButton.getLayoutX(), buySheepButton.getLayoutY() + buySheepButton.getImage().getHeight() / 4);
        buyCowButton.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        root.getChildren().add(buyCowButton);
        buyCowButton.setOnMouseClicked(event -> AnimalController.buyAnimal("Cow", this));

        String buyCost = Integer.toString(Cow.getBuyCost());
        Label buyCowLabel = new Label(buyCost);
        buyCowLabel.relocate(buyCowButton.getLayoutX() + 14, buyCowButton.getLayoutY() + 28.5);
        buyCowLabel.setTranslateY(10);
        buyCowLabel.setTextFill(Color.WHITE);
        buyCowLabel.setFont(Font.font(11));
        root.getChildren().addAll(buyCowLabel);
        buyCowLabel.setOnMouseClicked(event -> AnimalController.buyAnimal("Cow", this));
    }

    private void showSheepLabel(Group root) {
        String url = "File:Textures\\Buttons\\Sheep.png";
        Image image = new Image(url);
        buySheepButton = new ImageView(image);
        double frameWidth = image.getWidth();
        double frameHeight = image.getHeight() / 4;
        buySheepButton.relocate(buyHenButton.getLayoutX(), buyHenButton.getLayoutY() + buyHenButton.getImage().getHeight() / 4);
        buySheepButton.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        root.getChildren().add(buySheepButton);
        buySheepButton.setOnMouseClicked(event -> AnimalController.buyAnimal("Sheep", this));

        String buyCost = Integer.toString(Sheep.getBuyCost());
        Label sheepCostLabel = new Label(buyCost);
        sheepCostLabel.relocate(buySheepButton.getLayoutX() + 17, buySheepButton.getLayoutY() + 28.5);
        sheepCostLabel.setTranslateY(10);
        sheepCostLabel.setTextFill(Color.WHITE);
        sheepCostLabel.setFont(Font.font(11));
        root.getChildren().addAll(sheepCostLabel);
        sheepCostLabel.setOnMouseClicked(event -> AnimalController.buyAnimal("Sheep", this));

    }

    private void showHenLabel(Group root) {
        String url = "File:Textures\\Buttons\\Hen.png";
        Image image = new Image(url);
        buyHenButton = new ImageView(image);
        double frameWidth = image.getWidth();
        double frameHeight = image.getHeight() / 4;
        buyHenButton.relocate(20, 200);
        buyHenButton.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        root.getChildren().add(buyHenButton);
        buyHenButton.setOnMouseClicked(event -> AnimalController.buyAnimal("Hen", this));

        String buyCost = Integer.toString(Hen.getBuyCost());
        Label henCostLabel = new Label(buyCost);
        henCostLabel.relocate(buyHenButton.getLayoutX() + 17, buyHenButton.getLayoutY() + 28.5);
        henCostLabel.setTranslateY(10);
        henCostLabel.setTextFill(Color.WHITE);
        henCostLabel.setFont(Font.font(11));
        root.getChildren().addAll(henCostLabel);
        henCostLabel.setOnMouseClicked(event -> AnimalController.buyAnimal("Hen", this));

    }

    public CellViewer getCellViewer(int row, int column) {
        return cellViewers.get(row).get(column);
    }

    public TruckViewer getTruckViewer() { return truckViewer;
    }

    public int getCellCenterX(int row, int column) {
        return mapX + cellWidth / 2 + column * cellWidth;
    }

    public int getCellCenterY(int row, int column) {
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
            cellViewers.add(new ArrayList<>());
            for (int column = 0; column < columns; column++) {
                CellViewer cellViewer = new CellViewer(this, row, column);
                cellViewers.get(row).add(cellViewer);
            }
        }
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public int getCellHeight() {
        return cellHeight;
    }

    private void setBackground(Group root, int width, int height) {
        Image backgroundImg = new Image("File:Textures\\GameBackGround.jpg");
        ImageView background = new ImageView(backgroundImg);
        background.setFitWidth(width);
        background.setFitHeight(height);
        background.setPreserveRatio(false);
        root.getChildren().add(background);
    }

    public Group getRoot() {
        return root;
    }

    public Label getMoneyLabel() {
        return moneyLabel;
    }

    public Mission getMission() {
        return mission;
    }

    public int getTurnsPerSecond() {
        return turnsPerSecond;
    }

    public void showChat(){
        Image image = new Image("file:Textures\\pictures\\chat.png");
        ImageView imageView = new ImageView(image);
        imageView.setX(28);
        imageView.setY(605);
        imageView.setFitHeight(50);
        imageView.setFitWidth(40);
        root.getChildren().add(imageView);

        Image cross = new Image("file:Textures\\pictures\\exit.png");
        ImageView crossView = new ImageView(cross);
        crossView.setX(70);
        crossView.setY(605);
        crossView.setFitHeight(10);
        crossView.setFitWidth(10);

        TextArea textArea = new TextArea();
        TextField textField = new TextField();
        Button button = new Button("Send");
        Rectangle rectangle = new Rectangle(70, 605, 240, 205);

        imageView.setOnMouseClicked(event -> {

            root.getChildren().add(rectangle);
            rectangle.setFill(Color.LIGHTBLUE);


            textField.setPromptText("Type something...");
            textField.setPrefWidth(150);
            textField.setPrefHeight(25);
            textField.relocate(80, 775);
            root.getChildren().add(textField);


            button.setPrefSize(70, 10);
            button.relocate(235, 775);
            root.getChildren().add(button);


            textArea.relocate(80, 615);
            textArea.setPrefWidth(220);
            textArea.setPrefHeight(150);
            root.getChildren().add(textArea);

            root.getChildren().add(crossView);
        });
        crossView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().removeAll(rectangle, textArea, textField, button, crossView);
            }
        });
    }

    public void showPvChat(ArrayList<String> contacts){
        Text title = new Text("Contacts");
        title.setFont(Font.font("Bernard MT Condensed", 30));
        title.setFill(Color.RED);
        title.relocate(1400, 10);
        root.getChildren().add(title);
        Rectangle rectangle = new Rectangle(1360, 40, 1500, 40 * contacts.size());
        rectangle.setFill(Color.LIGHTBLUE);
        //root.getChildren().add(rectangle);
        for (int i = 0; i < contacts.size(); i++) {
            Text name = new Text(contacts.get(i));
            name.setFont(Font.font("Arial Rounded MT Bold", 20));
            name.setFill(Color.BLACK);
            name.relocate(1400, 60 + 30 * i);
            root.getChildren().add(name);

            name.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    name.setFont(Font.font("Arial Rounded MT Bold", 30));
                    name.setFill(Color.RED);
                }
            });

            name.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    name.setFont(Font.font("Arial Rounded MT Bold", 20));
                    name.setFill(Color.BLACK);
                }
            });

            name.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    pauseGame();
                    showProfile(new Profile());
                    /*Rectangle rectangle = new Rectangle(1125, 50, 240, 205);
                    root.getChildren().add(rectangle);
                    rectangle.setFill(Color.LIGHTBLUE);

                    Image exit = new Image("file:Textures\\pictures\\exit.png");
                    ImageView imageView = new ImageView(exit);
                    imageView.relocate(1127, 52);
                    imageView.setFitWidth(10);
                    imageView.setFitHeight(10);
                    root.getChildren().add(imageView);

                    TextField textField = new TextField();
                    textField.setPromptText("Type something...");
                    textField.setPrefWidth(150);
                    textField.setPrefHeight(25);
                    textField.relocate(1135, 215);
                    root.getChildren().add(textField);

                    Button button = new Button("Send");
                    button.setPrefSize(70, 10);
                    button.relocate(1290, 215);
                    root.getChildren().add(button);

                    TextArea textArea = new TextArea();
                    textArea.relocate(1135, 60);
                    textArea.setPrefWidth(220);
                    textArea.setPrefHeight(150);
                    root.getChildren().add(textArea);*/

                    /*Image cross = new Image("file:Textures\\pictures\\cross.png");
                    ImageView crossView = new ImageView(cross);
                    crossView.setX(68);
                    crossView.setY(603);
                    root.getChildren().add(crossView);*/

                }
            });
        }

    }

    public void showProfile(Profile profile){
        Group group = new Group();
        GridPane gridPane = new GridPane();
        Rectangle rectangle = new Rectangle();
        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

        profile.getFriends().add("Reza");

        rectangle.relocate(screenWidth / 2 - 600, screenHeight / 2 - 400);
        rectangle.setHeight(800);
        rectangle.setWidth(1200);
        rectangle.setFill(Color.LIGHTBLUE);
        rectangle.setArcHeight(50);
        rectangle.setArcWidth(50);
        group.getChildren().add(rectangle);

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        Text nameText = new Text("Name : ");
        nameText.setFont(Font.font("Bernard MT Condensed", 30));
        nameText.setFill(Color.BLACK);
        gridPane.add(nameText, 0, 0);

        Text name = new Text(profile.getName());
        name.setFill(Color.BLACK);
        name.setFont(Font.font("Arial Rounded MT Bold", 25));
        gridPane.add(name, 1, 0);

        Text unText = new Text("User name : ");
        unText.setFont(Font.font("Bernard MT Condensed", 30));
        unText.setFill(Color.BLACK);
        gridPane.add(unText, 0, 1);

        Text un = new Text(profile.getUsername());
        un.setFill(Color.BLACK);
        un.setFont(Font.font("Arial Rounded MT Bold", 25));
        gridPane.add(un, 1, 1);

        Text tradesText = new Text("Trades : ");
        tradesText.setFont(Font.font("Bernard MT Condensed", 30));
        tradesText.setFill(Color.BLACK);
        gridPane.add(tradesText, 0, 2);

        Text numberOfTrades = new Text(String.valueOf(profile.getNumberOfTrades()));
        numberOfTrades.setFill(Color.BLACK);
        numberOfTrades.setFont(Font.font("Arial Rounded MT Bold", 25));
        gridPane.add(numberOfTrades, 1, 2);

        Text commonGames = new Text("Common Games : ");
        commonGames.setFont(Font.font("Bernard MT Condensed", 30));
        commonGames.setFill(Color.BLACK);
        gridPane.add(commonGames, 0, 3);

        Text numberOfCommonGames = new Text(String.valueOf(profile.getNumbrOfCommonGames()));
        numberOfCommonGames.setFill(Color.BLACK);
        numberOfCommonGames.setFont(Font.font("Arial Rounded MT Bold", 25));
        gridPane.add(numberOfCommonGames, 1, 3);

        Text friends = new Text("Friends : ");
        friends.setFont(Font.font("Bernard MT Condensed", 30));
        friends.setFill(Color.BLACK);
        gridPane.add(friends, 2, 0);
        gridPane.relocate(screenWidth / 2 - 600 + 10, screenHeight / 2 - 400 + 10);

        double rectX = screenWidth / 2 - 600;
        double rectY = screenHeight / 2 - 400;

        Image cross = new Image("file:Textures\\pictures\\exit.png");
        ImageView crossView = new ImageView(cross);
        crossView.setFitWidth(10);
        crossView.setFitHeight(10);
        crossView.relocate(rectX + 1170, rectY);
        group.getChildren().add(crossView);

        crossView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().remove(group);
            }
        });

        boolean[] isChatOpen = new boolean[profile.getFriends().size()];
        for (int i = 0; i < isChatOpen.length; i++) {
            isChatOpen[i] = false;
        }

        for (int i = 0; i < profile.getFriends().size(); i++) {
            Text friend = new Text(profile.getFriends().get(i));
            friend.setFill(Color.BLACK);
            friend.setFont(Font.font("Arial Rounded MT Bold", 25));
            gridPane.add(friend, 3, i);

            friend.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    friend.setFill(Color.RED);
                    friend.setFont(Font.font("Arial Rounded MT Bold", 30));
                }
            });

            friend.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    friend.setFill(Color.BLACK);
                    friend.setFont(Font.font("Arial Rounded MT Bold", 25));
                }
            });

            final int index = i;
            friend.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (!isChatOpen[index]) {
                        Image cross = new Image("file:Textures\\pictures\\exit.png");
                        ImageView crossView2 = new ImageView(cross);
                        crossView2.setFitWidth(10);
                        crossView2.setFitHeight(10);
                        crossView2.relocate(rectX + 600, rectY);

                        Button button = new Button("Send");
                        button.relocate(rectX + 940, rectY + 570);
                        button.setPrefWidth(50);


                        TextArea textArea = new TextArea();
                        textArea.relocate(rectX + 610, rectY + 10);
                        textArea.setPrefHeight(550);
                        textArea.setPrefWidth(380);

                        TextField textField = new TextField();
                        textField.relocate(rectX + 610, rectY + 570);
                        textField.setPromptText("Type something...");
                        textField.setPrefWidth(330);

                        isChatOpen[index] = true;
                        Group group1 = new Group();
                        group1.getChildren().addAll(textArea, textField, button, crossView2);
                        root.getChildren().add(group1);

                        crossView2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                               root.getChildren().remove(group1);
                                isChatOpen[index] = false;
                            }
                        });
                    }
                }
            });
        }
            group.getChildren().add(gridPane);
        root.getChildren().add(group);
    }


    public void showTruckMenu(){

        LevelRequirementsChecker lrc = new LevelRequirementsChecker(0, 3, 0,
                0, 0, 0, 0, 3, 0, 0, 0, 0, 0);
        Mission mission = new Mission(10000, "GraphicTest", lrc, null, null);
        Warehouse warehouse = new Warehouse(mission);
        Truck truck = new Truck(mission);
        Pane pane = new Pane();
        //borderPane.setAlignment(borderPane.getLeft(), Pos.TOP_CENTER);
        Image image = new Image("file:Textures\\pictures\\page.jpg");
        Scene scene = new Scene(pane);
        //primaryStage.setFullScreen(true);
        //primaryStage.setScene(scene);
        /*primaryStage.setMaxHeight(image.getHeight());
        primaryStage.setMaxWidth(image.getWidth());
        primaryStage.setMinHeight(image.getHeight());
        primaryStage.setMinWidth(image.getWidth());*/

        warehouse.getItems().add(new Egg());
        warehouse.getItems().add(new Wool());
        warehouse.getItems().add(new Wool());
        warehouse.getItems().add(new Lion(new Map(mission), new Direction(), new Position(1,1)));
        warehouse.getItems().add(new Cookie());
        warehouse.getItems().add(new Cake());
        warehouse.getItems().add(new Dress());
        warehouse.getItems().add(new Milk());
        warehouse.getItems().add(new Bear(new Map(mission), new Direction(), new Position(1,1)));

        Image background = new Image("file:Textures\\pictures\\page.jpg");
        ImageView imageView = new ImageView(background);
        imageView.setPreserveRatio(false);

        imageView.setFitHeight(Screen.getPrimary().getVisualBounds().getHeight());
        imageView.setFitWidth(Screen.getPrimary().getVisualBounds().getWidth());


        pane.getChildren().add(imageView);
        Text goods1 = new Text("Goods");
        goods1.setFont(Font.font("Arial Rounded MT Bold", 30));
        goods1.setFill(Color.BLANCHEDALMOND);
        goods1.setX(80);
        goods1.setY(130);
        pane.getChildren().add(goods1);

        Text price1 = new Text("Price");
        price1.setFont(Font.font("Arial Rounded MT Bold", 30));
        price1.setFill(Color.BLANCHEDALMOND);
        price1.setX(220);
        price1.setY(130);
        pane.getChildren().add(price1);

        Text sheep = new Text("Ship");
        sheep.setFont(Font.font("Arial Rounded MT Bold", 30));
        sheep.setFill(Color.BLANCHEDALMOND);
        sheep.setX(395);
        sheep.setY(130);
        pane.getChildren().add(sheep);

        Text goods2 = new Text("Goods");
        goods2.setFont(Font.font("Arial Rounded MT Bold", 30));
        goods2.setFill(Color.BLANCHEDALMOND);
        goods2.setX(570);
        goods2.setY(130);
        pane.getChildren().add(goods2);

        Text price3 = new Text("Price");
        price3.setFont(Font.font("Arial Rounded MT Bold", 30));
        price3.setFill(Color.BLANCHEDALMOND);
        price3.setX(710);
        price3.setY(130);
        pane.getChildren().add(price3);

        Text sheep2 = new Text("Ship");
        sheep2.setFont(Font.font("Arial Rounded MT Bold", 30));
        sheep2.setFill(Color.BLANCHEDALMOND);
        sheep2.setX(900);
        sheep2.setY(130);
        pane.getChildren().add(sheep2);

        Text animals = new Text("Animals");
        animals.setFont(Font.font("Arial Rounded MT Bold", 30));
        animals.setFill(Color.BLANCHEDALMOND);
        animals.setX(1050);
        animals.setY(130);
        pane.getChildren().add(animals);

        Text price2 = new Text("Price");
        price2.setFont(Font.font("Arial Rounded MT Bold", 30));
        price2.setFill(Color.BLANCHEDALMOND);
        price2.setX(1200);
        price2.setY(130);
        pane.getChildren().add(price2);

        Text ship = new Text("Ship");
        ship.setFont(Font.font("Arial Rounded MT Bold", 30));
        ship.setFill(Color.BLANCHEDALMOND);
        ship.setX(1380);
        ship.setY(130);
        pane.getChildren().add(ship);

        Text title = new Text("Ship Products");
        title.setFont(Font.font("Arial Rounded MT Bold", 50));
        title.setFill(Color.WHITE);
        title.setX(600);
        title.setY(50);
        pane.getChildren().add(title);


        Image truckImage = new Image("file:C:\\Users\\Kasra\\Desktop\\FarmFrenzy\\Textures\\UI\\Truck\\0" + String.valueOf(truck.getLevel() + 1) + ".png");
        ImageView imageView1 = new ImageView(truckImage);
        imageView1.setFitWidth(truckImage.getWidth() + 100);
        imageView1.setFitHeight(truckImage.getHeight() + 100);
        imageView1.setX(1050);
        imageView1.setY(200);
        pane.getChildren().add(imageView1);


        Image coinImage = new Image("file:Textures\\pictures\\coin_48.png");
        ImageView imageView2 = new ImageView(coinImage);
        imageView2.setX(1100);
        imageView2.setY(665);
        pane.getChildren().add(imageView2);


        Set<String> n = new HashSet<>();
        for (int i = 0; i < warehouse.getItems().size(); i++) {
            n.add(warehouse.getItems().get(i).getName());
        }


        int ctr = 0;
        int cost = 0;
        ArrayList<String> names = new ArrayList<>(n);
        /*if (warehouse.getItems().size() > 0)
            names.add(warehouse.getItems().get(0).getName());
        for (int i = 1; i < warehouse.getItems().size(); i++) {
            for (int j = 0; j < i; j++) {
                if (!warehouse.getItems().get(i).getName().toLowerCase().equals(warehouse.getItems().get(j).getName().toLowerCase())) {
                    ctr++;
                }
                else{
                    System.out.println(warehouse.getItems().get(j).getName().toUpperCase());
                }
                if (ctr == i) {
                    names.add(warehouse.getItems().get(i).getName());

                }
                ctr = 0;
            }
        }*/
        Text priceText = new Text("0");
        priceText.setFont(Font.font("Arial Rounded MT Bold", 40));
        priceText.setFill(Color.YELLOW);
        priceText.setX(1280);
        priceText.setY(705);
        pane.getChildren().add(priceText);






        int[] nums = new int[names.size()];
        for (int i = 0; i < names.size(); i++) {
            int num = 0, price = 0;
            for (int j = 0; j < warehouse.getItems().size(); j++) {
                if (names.get(i).toLowerCase().equals(warehouse.getItems().get(j).getName().toLowerCase())) {
                    num++;
                    price = warehouse.getItems().get(j).getSellCost();
                }
            }
            nums[i] = num;
            /*if (names.get(i).toLowerCase().equals("hen")) {
                Image image3 = new Image("file:C:\\Users\\Kasra\\Desktop\\FarmFrenzy\\Textures\\guinea_fowl.png");
                ImageView imageView3 = new ImageView(image3);
                imageView3.setX(540);
                imageView3.setY(90 + 20 * i);
                pane.getChildren().add(imageView3);
            }*/

            Image productImage = new Image("file:Textures\\pictures\\" + names.get(i) + ".png");
            ImageView imageView3 = new ImageView(productImage);
            imageView3.setX(60);
            imageView3.setY(145 + 45 * i);
            if (names.get(i).equals("Lion") || names.get(i).equals("Bear")){
                imageView3.setFitHeight(productImage.getHeight() / 2);
                imageView3.setFitWidth(productImage.getWidth() / 2);
            }
            else {
                imageView3.setFitHeight(productImage.getHeight() * 2 / 3);
                imageView3.setFitWidth(productImage.getWidth() * 2 / 3);
            }

            pane.getChildren().add(imageView3);
            Image crossImage = new Image("file:Textures\\pictures\\cross.png");
            ImageView imageView4 = new ImageView(crossImage);
            imageView4.setX(115);
            imageView4.setY(150 + 45 * i);
            pane.getChildren().add(imageView4);

            Text numberOfProduct = new Text(String.valueOf(num));
            numberOfProduct.setX(140);
            numberOfProduct.setY(165 + 45 * i);
            numberOfProduct.setFont(Font.font("Arial Rounded MT Bold", 23));
            numberOfProduct.setFill(Color.BLANCHEDALMOND);
            pane.getChildren().add(numberOfProduct);

            Text priceOfProduct = new Text(String.valueOf(price));
            priceOfProduct.setFill(Color.BLANCHEDALMOND);
            priceOfProduct.setFont(Font.font("Arial Rounded MT Bold", 23));
            priceOfProduct.setX(235);
            priceOfProduct.setY(165 + 45 * i);
            pane.getChildren().add(priceOfProduct);

            Image coinImage2 = new Image("file:Textures\\pictures\\coin_48.png");
            ImageView imageView5 = new ImageView(coinImage2);
            imageView5.setFitHeight(coinImage2.getHeight() * 2 / 3);
            imageView5.setFitWidth(coinImage2.getWidth() * 2 / 3);
            imageView5.setX(285);
            imageView5.setY(145 + 44 * i);
            pane.getChildren().add(imageView5);

            Image buttonImage = new Image("file:Textures\\pictures\\button.png");
            ImageView imageView6 = new ImageView(buttonImage);
            imageView6.setX(375);
            imageView6.setY(150 + 44 * i);
            imageView6.setFitWidth(buttonImage.getWidth());
            pane.getChildren().add(imageView6);

            Text buttonTextOne = new Text("1");
            buttonTextOne.setFont(Font.font("Arial Rounded MT Bold", 23));
            buttonTextOne.setFill(Color.WHITE);
            buttonTextOne.setX(390);
            buttonTextOne.setY(168 + 44 * i);
            pane.getChildren().add(buttonTextOne);

            ImageView imageView7 = new ImageView(buttonImage);
            imageView7.setX(420);
            imageView7.setY(150 + 44 * i);
            imageView7.setFitWidth(buttonImage.getWidth());
            pane.getChildren().add(imageView7);

            Text buttonTextAll = new Text("all");
            buttonTextAll.setFont(Font.font("Arial Rounded MT Bold", 20));
            buttonTextAll.setFill(Color.WHITE);
            buttonTextAll.setX(432);
            buttonTextAll.setY(165 + 44 * i);
            pane.getChildren().add(buttonTextAll);


            final int index = i;
            final int no = num;

            buttonTextOne.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int price = Integer.parseInt(priceText.getText());
                    for (int k = 0; k < warehouse.getItems().size(); k++) {
                        if (names.get(index).equals(warehouse.getItems().get(k).getName())){
                            if (Integer.parseInt(numberOfProduct.getText()) > 0) {
                                pane.getChildren().remove(priceText);
                                pane.getChildren().remove(numberOfProduct);
                                price += warehouse.getItems().get(k).getSellCost();
                                priceText.setText(String.valueOf(price));
                                numberOfProduct.setText(String.valueOf(Integer.parseInt(numberOfProduct.getText()) - 1));
                                pane.getChildren().add(numberOfProduct);
                                pane.getChildren().add(priceText);
                                break;
                            }
                        }
                    }
                }
            });

            imageView6.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int price = Integer.parseInt(priceText.getText());
                    for (int k = 0; k < warehouse.getItems().size(); k++) {
                        if (names.get(index).equals(warehouse.getItems().get(k).getName())){
                            if (Integer.parseInt(numberOfProduct.getText()) > 0) {
                                pane.getChildren().remove(priceText);
                                pane.getChildren().remove(numberOfProduct);
                                price += warehouse.getItems().get(k).getSellCost();
                                priceText.setText(String.valueOf(price));
                                numberOfProduct.setText(String.valueOf(Integer.parseInt(numberOfProduct.getText()) - 1));
                                pane.getChildren().add(numberOfProduct);
                                pane.getChildren().add(priceText);
                                break;
                            }
                        }
                    }
                }
            });

            buttonTextAll.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int price = Integer.parseInt(priceText.getText());
                    for (int k = 0; k < warehouse.getItems().size(); k++) {
                        if (names.get(index).equals(warehouse.getItems().get(k).getName())) {
                            pane.getChildren().remove(priceText);
                            pane.getChildren().remove(numberOfProduct);
                            price += warehouse.getItems().get(k).getSellCost() * Integer.parseInt(numberOfProduct.getText());
                            priceText.setText(String.valueOf(price));
                            numberOfProduct.setText(String.valueOf(0));
                            pane.getChildren().add(numberOfProduct);
                            pane.getChildren().add(priceText);
                            break;
                        }
                    }
                }
            });

            imageView7.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int price = Integer.parseInt(priceText.getText());
                    for (int k = 0; k < warehouse.getItems().size(); k++) {
                        if (names.get(index).equals(warehouse.getItems().get(k).getName())) {
                            pane.getChildren().remove(priceText);
                            pane.getChildren().remove(numberOfProduct);
                            price += warehouse.getItems().get(k).getSellCost() * Integer.parseInt(numberOfProduct.getText());
                            priceText.setText(String.valueOf(price));
                            numberOfProduct.setText(String.valueOf(0));
                            pane.getChildren().add(numberOfProduct);
                            pane.getChildren().add(priceText);
                            break;
                        }
                    }
                }
            });
        }

        Image button = new Image("file:Textures\\pictures\\button.png");
        ImageView cancelButton = new ImageView(button);
        cancelButton.setX(500);
        cancelButton.setY(750);
        cancelButton.setFitWidth(button.getWidth() * 2);
        cancelButton.setFitHeight(button.getHeight() + 10);
        pane.getChildren().add(cancelButton);

        ImageView okButton = new ImageView(button);
        okButton.setX(400);
        okButton.setY(750);
        okButton.setFitWidth(button.getWidth() * 2);
        okButton.setFitHeight(button.getHeight() + 10);
        pane.getChildren().add(okButton);

        Text ok = new Text("Ok");
        ok.setFill(Color.WHITE);
        ok.setFont(Font.font("Arial Rounded MT Bold", 20));
        ok.setX(435);
        ok.setY(770);
        pane.getChildren().add(ok);

        okButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    truck.go();
                } catch (TradingListIsEmptyException e) {
                    e.printStackTrace();
                }
            }
        });

        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    truck.go();
                } catch (TradingListIsEmptyException e) {
                    e.printStackTrace();
                }
            }
        });

        Text cancel = new Text("Cancel");
        cancel.setFont(Font.font("Arial Rounded MT Bold", 20));
        cancel.setFill(Color.WHITE);
        cancel.setX(515);
        cancel.setY(770);
        pane.getChildren().add(cancel);

        ImageView sellToServer = new ImageView(button);
        sellToServer.setFitWidth(button.getWidth() * 3);
        sellToServer.setFitHeight(button.getHeight() + 10);
        sellToServer.setX(600);
        sellToServer.setY(750);
        pane.getChildren().add(sellToServer);

        Text sellToServerText = new Text("Sell to server");
        sellToServerText.setFill(Color.WHITE);
        sellToServerText.setFont(Font.font("Arial Rounded MT Bold", 15));
        sellToServerText.setX(625);
        sellToServerText.setY(770);
        pane.getChildren().add(sellToServerText);

        Image exit = new Image("file:Textures\\pictures\\exit.png");
        ImageView exitView = new ImageView(exit);
        exitView.setFitHeight(25);
        exitView.setFitWidth(25);
        exitView.relocate(20, 10);
        pane.getChildren().add(exitView);

        root.getChildren().add(pane);

        exitView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().remove(pane);
            }
        });
    }

    public void showHelicopterMenu(){
        Pane pane = new Pane();
        Image back = new Image("file:Textures\\pictures\\helicopter.png");
        ImageView backView = new ImageView(back);
        backView.setFitWidth(Screen.getPrimary().getVisualBounds().getWidth());
        backView.setFitHeight(Screen.getPrimary().getVisualBounds().getHeight());
        pane.getChildren().add(backView);
        LevelRequirementsChecker lrc = new LevelRequirementsChecker(0, 3, 0,
                0, 0, 0, 0, 3, 0, 0, 0, 0, 0);
        Mission mission = new Mission(10000, "GraphicTest", lrc, null, null);

        Helicopter helicopter = new Helicopter(mission);

        Image helicopterImage = new Image("file:Textures\\UI\\Helicopter\\0"
                + String.valueOf(helicopter.getLevel() + 1) + ".png");
        ImageView heliView = new ImageView(helicopterImage);
        heliView.setFitHeight(helicopterImage.getHeight() + 200);
        heliView.setFitWidth(helicopterImage.getWidth() + 200);
        heliView.setX(750);
        heliView.setY(280);
        pane.getChildren().add(heliView);

        Text goods = new Text("Goods");
        goods.setFill(Color.BLANCHEDALMOND);
        goods.setFont(Font.font("Arial Rounded MT Bold", 30));
        goods.setX(85);
        goods.setY(145);
        pane.getChildren().add(goods);

        Text price1 = new Text("Price");
        price1.setFill(Color.BLANCHEDALMOND);
        price1.setFont(Font.font("Arial Rounded MT Bold", 30));
        price1.setX(250);
        price1.setY(145);
        pane.getChildren().add(price1);

        Text order = new Text("Order");
        order.setFill(Color.BLANCHEDALMOND);
        order.setFont(Font.font("Arial Rounded MT Bold", 30));
        order.setX(450);
        order.setY(145);
        pane.getChildren().add(order);

        Text title = new Text("Order Goods");
        title.setFill(Color.BLANCHEDALMOND);
        title.setFont(Font.font("Arial Rounded MT Bold", 50));
        title.setX(185);
        title.setY(65);
        pane.getChildren().add(title);

        int cost = 0;
        Text costText = new Text(String.valueOf(cost));
        costText.setFont(Font.font("Arial Rounded MT Bold", 40));
        costText.setFill(Color.YELLOW);
        costText.setX(355);
        costText.setY(668);
        pane.getChildren().add(costText);

        Image coin = new Image("file:Textures\\pictures\\coin_48.png");
        ImageView coinView = new ImageView(coin);
        coinView.setX(180);
        coinView.setY(630);
        pane.getChildren().add(coinView);

        Image button = new Image("file:Textures\\pictures\\button.png");
        ImageView okButton = new ImageView(button);
        okButton.setX(250);
        okButton.setY(725);
        okButton.setFitWidth(button.getWidth() * 2);
        okButton.setFitHeight(button.getHeight() + 10);
        pane.getChildren().add(okButton);

        ImageView cancelButton = new ImageView(button);
        cancelButton.setX(350);
        cancelButton.setY(725);
        cancelButton.setFitWidth(button.getWidth() * 2);
        cancelButton.setFitHeight(button.getHeight() + 10);
        pane.getChildren().add(cancelButton);

        Text ok = new Text("OK");
        ok.setFill(Color.WHITE);
        ok.setFont(Font.font("Arial Rounded MT Bold", 25));
        ok.setX(280);
        ok.setY(747);
        pane.getChildren().add(ok);

        okButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    helicopter.go();
                } catch (TradingListIsEmptyException e) {
                    e.printStackTrace();
                }
            }
        });

        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    helicopter.go();
                } catch (TradingListIsEmptyException e) {
                    e.printStackTrace();
                }
            }
        });

        Text cancel = new Text("Cancel");
        cancel.setFont(Font.font("Arial Rounded MT Bold", 20));
        cancel.setFill(Color.WHITE);
        cancel.setX(365);
        cancel.setY(747);
        pane.getChildren().add(cancel);

        ImageView buyFromserver = new ImageView(button);
        buyFromserver.setFitHeight(button.getHeight() + 10);
        buyFromserver.setFitWidth(button.getWidth() * 3 + 20);
        buyFromserver.setX(450);
        buyFromserver.setY(725);
        pane.getChildren().add(buyFromserver);

        Text buyFromServerText = new Text("buy from server");
        buyFromServerText.setFill(Color.WHITE);
        buyFromServerText.setFont(Font.font("Arial Rounded MT Bold", 15));
        buyFromServerText.setX(475);
        buyFromServerText.setY(745);
        pane.getChildren().add(buyFromServerText);


        Product[] products = {new Cake(), new Cloth(), new Cookie(), new Dress(), new EggPowder(), new Fiber(), new Flour(), new Milk()};
        for (int i = 0; i < products.length; i++) {
            Image image = new Image("file:Textures\\Product\\" + products[i].getName() + ".png");
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(image.getHeight() * 3 / 4);
            imageView.setFitWidth(image.getWidth() * 3 / 4);
            imageView.setX(130);
            imageView.setY(160 + 45 * i);
            pane.getChildren().add(imageView);

            int price = products[i].getBuyCost();
            Text priceOfProduct = new Text(String.valueOf(price));
            priceOfProduct.setFont(Font.font("Arial Rounded MT Bold", 23));
            priceOfProduct.setFill(Color.BLANCHEDALMOND);
            priceOfProduct.setX(265);
            priceOfProduct.setY(185 + 45 * i);
            pane.getChildren().add(priceOfProduct);

            Image buttonImage = new Image("file:Textures\\pictures\\button.png");
            ImageView orderButton = new ImageView(buttonImage);
            orderButton.setFitHeight(image.getHeight() * 2 / 3);
            orderButton.setFitWidth(image.getWidth() * 2 / 3);
            orderButton.setX(485);
            orderButton.setY(165 + 44 * i);
            pane.getChildren().add(orderButton);

            Text buttonTextOne = new Text("1");
            buttonTextOne.setFont(Font.font("Arial Rounded MT Bold", 23));
            buttonTextOne.setFill(Color.WHITE);
            buttonTextOne.setX(493);
            buttonTextOne.setY(186 + 44 * i);
            pane.getChildren().add(buttonTextOne);


            final int index = i;
            orderButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int cost = Integer.parseInt(costText.getText());
                    cost += products[index].getBuyCost();
                    pane.getChildren().remove(costText);
                    costText.setText(String.valueOf(cost));
                    pane.getChildren().add(costText);

                }
            });

            buttonTextOne.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int cost = Integer.parseInt(costText.getText());
                    cost += products[index].getBuyCost();
                    pane.getChildren().remove(costText);
                    costText.setText(String.valueOf(cost));
                    pane.getChildren().add(costText);
                }
            });
        }


        Image exit = new Image("file:Textures\\pictures\\exit.png");
        ImageView exitView = new ImageView(exit);
        exitView.relocate(20, 10);
        exitView.setFitWidth(25);
        exitView.setFitHeight(25);
        pane.getChildren().add(exitView);
        root.getChildren().add(pane);

        exitView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().remove(pane);
            }
        });
    }

}


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