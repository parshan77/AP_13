package View;

import Controller.AnimalController;
import Controller.WorkshopController;
import Exceptions.NotEnoughMoneyException;
import Exceptions.NotFoundException;
import Model.Animals.Domestics.Cow;
import Model.Animals.Domestics.Hen;
import Model.Animals.Domestics.Sheep;
import Model.Animals.Predators.Lion;
import Model.Animals.Seekers.Cat;
import Model.Animals.Seekers.Dog;
import Model.LevelRequirementsChecker;
import Model.Mission;
import Model.Placement.Direction;
import Model.Placement.Position;
import Model.Products.Egg;
import View.Animations.AnimalAnimation;
import View.Animations.BuzzAnimation;
import View.Animations.SpriteAnimation;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;

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

    private Label moneyLabel;

    private HelicopterViewer helicopterViewer;
    private TruckViewer truckViewer;

    private WellViewer wellViewer;
    private WarehouseViewer warehouseViewer;

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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
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

        Button testButton = new Button("test");
        testButton.relocate(30, 30);
        testButton.resize(300, 100);

        Hen hen = new Hen(mission.getMap(), new Direction(0, 1), new Position(0, 0));
        mission.getMap().addToMap(hen);
        AnimalViewer animalViewer = new AnimalViewer(hen, this);
        hen.setAnimalViewer(animalViewer);


        testButton.setOnMouseClicked(event -> {
            pauseGame();
        });

        root.getChildren().add(testButton);
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
                mapX + mapWidth + 10, mapY - 80);
        sewingFactoryViewer = new WorkshopViewer(this, "SewingFactory",
                mapX + mapWidth + 10, mapY + 80);
        cookieBakeryViewer = new WorkshopViewer(this, "CookieBakery",
                mapX + mapWidth + 30, mapY + 180);
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
        buyHenButton.relocate(20, 250);
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

    public TruckViewer getTruckViewer() {
        return truckViewer;
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