package View;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameView extends Application {
    private final int cellWidth = 100;
    private final int cellHeight = 45;
    private final int mapWidth = 700;
    private final int mapHeight = 315;
    private int stageWidth;
    private int stageHeight;
    private int mapX;
    private int mapY;
    private Group root;

    private ImageView buyHen;
    private ImageView buySheep;
    private ImageView buyCow;
    private ImageView buyDog;
    private ImageView buyCat;

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


        Button testButton = new Button("test");
        testButton.relocate(30, 30);
        testButton.resize(300, 100);
        testButton.setOnMouseClicked(event -> {

        });
        root.getChildren().add(testButton);
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
        buyCat = new ImageView(image);
        double frameWidth = image.getWidth();
        double frameHeight = image.getHeight() / 4;
        buyCat.relocate(buyDog.getLayoutX(), buyDog.getLayoutY() + buyDog.getImage().getHeight() / 4);
        buyCat.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        root.getChildren().add(buyCat);
    }

    private void showDogLabel(Group root) {
        String url = "File:C:\\Users\\parshan\\Desktop\\FarmFrenzy\\Textures\\Buttons\\Dog.png";
        Image image = new Image(url);
        buyDog = new ImageView(image);
        double frameWidth = image.getWidth();
        double frameHeight = image.getHeight() / 4;
        buyDog.relocate(buyCow.getLayoutX(), buyCow.getLayoutY() + buyCow.getImage().getHeight() / 4);
        buyDog.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        root.getChildren().add(buyDog);
    }

    private void showCowLabel(Group root) {
        String url = "File:C:\\Users\\parshan\\Desktop\\FarmFrenzy\\Textures\\Buttons\\Cow.png";
        Image image = new Image(url);
        buyCow = new ImageView(image);
        double frameWidth = image.getWidth();
        double frameHeight = image.getHeight() / 4;
        buyCow.relocate(buySheep.getLayoutX(), buySheep.getLayoutY() + buySheep.getImage().getHeight() / 4);
        buyCow.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        root.getChildren().add(buyCow);
    }

    private void showSheepLabel(Group root) {
        String url = "File:C:\\Users\\parshan\\Desktop\\FarmFrenzy\\Textures\\Buttons\\Sheep.png";
        Image image = new Image(url);
        buySheep = new ImageView(image);
        double frameWidth = image.getWidth();
        double frameHeight = image.getHeight() / 4;
        buySheep.relocate(buyHen.getLayoutX(), buyHen.getLayoutY() + buyHen.getImage().getHeight() / 4);
        buySheep.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        root.getChildren().add(buySheep);
    }

    private void showHenLabel(Group root) {
        String url = "File:C:\\Users\\parshan\\Desktop\\FarmFrenzy\\Textures\\Buttons\\Hen.png";
        Image image = new Image(url);
        buyHen = new ImageView(image);
        double frameWidth = image.getWidth();
        double frameHeight = image.getHeight() / 4;
        buyHen.relocate(100, 250);
        buyHen.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        root.getChildren().add(buyHen);
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
                    showTime(sec,min);
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

    private void showWorkshops(Group root) {
        showCakeBakery(root);
        showSewingFactory(root);
        showCookieBakery(root);
        showWeavingFactory(root);
        showSpinnery(root);
        showEggPowderPlant(root);
    }

    private void showEggPowderPlant(Group root) {
        String url = "File:C:\\Users\\parshan\\Desktop\\FarmFrenzy\\Textures\\Workshops\\EggPowderPlant\\01.png";
        Image img = new Image(url);
        double imageWidth = img.getWidth() / 4;
        double imageHeight = img.getHeight() / 4;
        ImageView imgView = new ImageView(img);
        imgView.relocate(mapX - 150, mapY + 200);
        imgView.setViewport(new Rectangle2D(0, 0, imageWidth, imageHeight));
        root.getChildren().add(imgView);

        imgView.setOnMouseClicked(event -> {
            WorkshopAnimation.play(imgView, img);
        });
    }

    private void showSpinnery(Group root) {
        String url = "File:C:\\Users\\parshan\\Desktop\\FarmFrenzy\\Textures\\Workshops\\Spinnery\\01.png";
        Image spinneryImage = new Image(url);
        double imageWidth = spinneryImage.getWidth() / 4;
        double imageHeight = spinneryImage.getHeight() / 4;
        ImageView spinneryImageView = new ImageView(spinneryImage);
        spinneryImageView.relocate(mapX - 150, mapY + 80);
        spinneryImageView.setViewport(new Rectangle2D(0, 0, imageWidth, imageHeight));
        root.getChildren().add(spinneryImageView);

        spinneryImageView.setOnMouseClicked(event -> {
            WorkshopAnimation.play(spinneryImageView, spinneryImage);
        });
    }

    private void showWeavingFactory(Group root) {
        String url = "File:C:\\Users\\parshan\\Desktop\\FarmFrenzy\\Textures\\Workshops\\WeavingFactory\\01.png";
        Image image = new Image(url);
        double imageWidth = image.getWidth() / 4;
        double imageHeight = image.getHeight() / 4;
        ImageView imageView = new ImageView(image);
        imageView.relocate(mapX - 150, mapY - 30);
        imageView.setViewport(new Rectangle2D(0, 0, imageWidth, imageHeight));
        root.getChildren().add(imageView);

        imageView.setOnMouseClicked(event -> {
            WorkshopAnimation.play(imageView, image);
        });
    }

    private void showCookieBakery(Group root) {
        String url = "File:C:\\Users\\parshan\\Desktop\\FarmFrenzy\\Textures\\Workshops\\CookieBakery\\01.png";
        Image image = new Image(url);
        double imageWidth = image.getWidth() / 4;
        double imageHeight = image.getHeight() / 4;
        ImageView imageView = new ImageView(image);
        imageView.relocate(mapX + mapWidth + 30, mapY + 180);
        imageView.setViewport(new Rectangle2D(0, 0, imageWidth, imageHeight));
        root.getChildren().add(imageView);

        imageView.setOnMouseClicked(event -> {
            WorkshopAnimation.play(imageView, image);
        });
    }

    private void showSewingFactory(Group root) {
        String url = "File:C:\\Users\\parshan\\Desktop\\FarmFrenzy\\Textures\\Workshops\\SewingFactory\\01.png";
        Image image = new Image(url);
        //width = 680       height = 520
        double imageWidth = image.getWidth() / 4;
        double imageHeight = image.getHeight() / 4;
        ImageView imageView = new ImageView(image);
        imageView.relocate(mapX + mapWidth, mapY + 80);
        imageView.setViewport(new Rectangle2D(0, 0, imageWidth, imageHeight));
        root.getChildren().add(imageView);

        imageView.setOnMouseClicked(event -> {
            WorkshopAnimation.play(imageView, image);
        });
    }

    private void showCakeBakery(Group root) {
        String url = "File:C:\\Users\\parshan\\Desktop\\FarmFrenzy\\Textures\\Workshops\\CakeBakery\\01.png";
        Image image = new Image(url);
        double imageWidth = image.getWidth() / 4;
        double imageHeight = image.getHeight() / 4;
        ImageView imageView = new ImageView(image);
        imageView.relocate(mapX + mapWidth - 10, mapY - 80);
        imageView.setViewport(new Rectangle2D(0, 0, imageWidth, imageHeight));
        root.getChildren().add(imageView);

        imageView.setOnMouseClicked(event -> {
            WorkshopAnimation.play(imageView, image);
        });
    }

    private void showWarehouse(Group root) {
        Image warehouseImage = new Image("File:C:\\Users\\parshan\\Desktop\\FarmFrenzy\\Textures\\Service\\Depot\\02.png");
        //width = 180       height = 148
        ImageView warehouseImageView = new ImageView(warehouseImage);
        warehouseImageView.relocate(mapX + mapWidth / 2.0 - 90, mapY + mapHeight + 20);
        warehouseImageView.setScaleX(1.2);
        warehouseImageView.setScaleY(1.2);
//        System.out.println("width" + warehouseImage.getWidth() + " Height" + warehouseImage.getHeight());
        root.getChildren().add(warehouseImageView);
    }

    private void showHelicopter(Group root) {
        Image helicopterImage = new Image("File:C:\\Users\\parshan\\Desktop\\FarmFrenzy\\Textures\\Service\\Helicopter\\01.png");
        ImageView helicopterImageView = new ImageView(helicopterImage);
        helicopterImageView.relocate(mapX + mapWidth - 150, mapY + mapHeight);
        root.getChildren().add(helicopterImageView);
    }

    private void showTruck(Group root) {
        Image truckImg = new Image("File:C:\\Users\\parshan\\Desktop\\FarmFrenzy\\Textures\\Service\\Truck\\01.png");
        ImageView truckImageView = new ImageView(truckImg);
        truckImageView.relocate(mapX, mapY + mapHeight + 30);
        root.getChildren().add(truckImageView);
    }

    private void showWell(Group root) {
        String url = "File:C:\\Users\\parshan\\Desktop\\FarmFrenzy\\Textures\\Service\\Well\\01.png";
        Image image = new Image(url);
        ImageView imageView = new ImageView(image);
        imageView.relocate(mapX + 250, mapY - 130);
        imageView.setViewport(new Rectangle2D(0, 0, image.getWidth() / 4, image.getHeight() / 4));
        root.getChildren().addAll(imageView);

        imageView.setOnMouseClicked(event -> {
            Animation wellExtractingAnimation = new SpriteAnimation(imageView, Duration.millis(800), 16,
                    4, 0, 0,
                    (int) image.getWidth() / 4, (int) image.getHeight() / 4);
            wellExtractingAnimation.setCycleCount(10);
            wellExtractingAnimation.play();
            wellExtractingAnimation.setOnFinished(event1 -> {
                imageView.setViewport(new Rectangle2D(0, 0,
                        image.getWidth() / 4, image.getHeight() / 4));
            });
        });
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