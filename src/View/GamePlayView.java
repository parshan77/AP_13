package View;

import Controller.WorkshopController;
import Model.LevelRequirementsChecker;
import Model.Mission;
import View.Animations.SpriteAnimation;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
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
    private Mission mission;
    private int turnPerSecond;

    private ImageView buyHenButton;
    private ImageView buySheepButton;
    private ImageView buyCowButton;
    private ImageView buyDogButton;
    private ImageView buyCatButton;

    private ImageView helicopter;
    private ImageView truck;

    private Label moneyLabel;

    private WellViewer wellViewer;
    private WarehouseViewer warehouseViewer;

    private WorkshopViewer eggPowderPlantViewer;
    private WorkshopViewer spinneryViewer;
    private WorkshopViewer weavingFactoryViewer;
    private WorkshopViewer cakeBakeryViewer;
    private WorkshopViewer cookieBakeryViewer;
    private WorkshopViewer customWorkshopViewer;
    private WorkshopViewer sewingFactoryViewer;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        LevelRequirementsChecker lrc = new LevelRequirementsChecker(0, 3, 0,
                0, 0, 0, 0, 3, 0,
                0, 0, 0, 0);
        mission = new Mission(10000, "GraphicTest", lrc, null);
        turnPerSecond = 1;

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
        showTruck(root);
        showHelicopter(root);
        warehouseViewer = new WarehouseViewer(this);
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

    public WellViewer getWellViewer() {
        return wellViewer;
    }

    private void showTimer(Group root, int stageWidth) {
        String url = "File:Textures\\Labels\\time_gold.png";
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

    private void showWorkshops(Group root) {
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
        // TODO: 1/27/2019 custom workshop
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
        String url = "File:Textures\\Buttons\\Cat_level1.png";
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
        String url = "File:Textures\\Buttons\\Dog.png";
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
        String url = "File:Textures\\Buttons\\Cow.png";
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
        String url = "File:Textures\\Buttons\\Sheep.png";
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
        String url = "File:Textures\\Buttons\\Hen.png";
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

    private void showHelicopter(Group root) {
        Image helicopterImage = new Image("File:Textures\\Service\\Helicopter\\01.png");
        helicopter = new ImageView(helicopterImage);
        helicopter.relocate(mapX + mapWidth - 150, mapY + mapHeight);
        root.getChildren().add(helicopter);
    }

    private void showTruck(Group root) {
        Image truckImg = new Image("File:Textures\\Service\\Truck\\01.png");
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

    public int getTurnPerSecond() {
        return turnPerSecond;
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