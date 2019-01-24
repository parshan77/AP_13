package View.Animations.Animals;

import View.Animations.SpriteAnimation;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class MoveAnimation {
    private static final Duration spriteDuration = Duration.millis(1050);
    private static final Duration pathTransitionDuration = Duration.millis(5200);
    private static final int spriteAnimationPerTransition = 7;


    public static void play(Group root, ImageView imageView, Image image, int count, int rows,
                            int columns, int startX, int startY, int finishX, int finishY) {
        int imgWidth = (int) image.getWidth() / columns;
        int imgHeight = (int) image.getHeight() / rows;
        imageView.setViewport(new Rectangle2D(0, 0, imgWidth, imgHeight));

        Animation spriteAnimation = new SpriteAnimation(imageView, spriteDuration, count, columns,
                0, 0, imgWidth, imgHeight);
        spriteAnimation.setCycleCount(Animation.INDEFINITE);

        Path way = new Path(new MoveTo(startX, startY), new LineTo(finishX, finishY));
        PathTransition moveAnimation = new PathTransition(pathTransitionDuration, way, imageView);
        moveAnimation.setCycleCount(1);
        moveAnimation.setAutoReverse(false);
        moveAnimation.setOnFinished(event -> spriteAnimation.stop());

        root.getChildren().add(imageView);
        spriteAnimation.play();
        moveAnimation.play();
    }

    public static void up(String pathToDirectory, Group root, int startX, int startY, int finishX, int finishY,
                          int count, int rows, int columns) {
        String path = pathToDirectory + "up.png";
        Image img = new Image(path);
        ImageView imgView = new ImageView(img);
        MoveAnimation.play(root, imgView, img, count, rows, columns, startX, startY, finishX, finishY);
    }

    public static void down(String pathToDirectory, Group root, int startX, int startY, int finishX, int finishY,
                            int count, int rows, int columns) {
        String path = pathToDirectory + "down.png";
        Image img = new Image(path);
        ImageView imageView = new ImageView(img);
        MoveAnimation.play(root, imageView, img, count, rows, columns, startX, startY, finishX, finishY);
    }

    public static void right(String pathToDirectory, Group root, int startX, int startY, int finishX, int finishY,
                             int count, int rows, int columns) {
        String path = pathToDirectory + "left.png";
        Image img = new Image(path);
        ImageView imageView = new ImageView(img);
        imageView.setScaleX(-1);
        MoveAnimation.play(root, imageView, img, count, rows, columns, startX, startY, finishX, finishY);
    }

    public static void left(String pathToDirectory, Group root, int startX, int startY, int finishX, int finishY,
                            int count, int rows, int columns) {
        String path = pathToDirectory + "left.png";
        Image img = new Image(path);
        ImageView imgView = new ImageView(img);
        MoveAnimation.play(root, imgView, img, count, rows, columns, startX, startY, finishX, finishY);
    }

    public static void upLeft(String pathToDirectory, Group root, int startX, int startY, int finishX, int finishY,
                              int count, int rows, int columns) {
        String path = pathToDirectory + "up_left.png";
        Image img = new Image(path);
        ImageView imgView = new ImageView(img);
        MoveAnimation.play(root, imgView, img, count, rows, columns, startX, startY, finishX, finishY);
    }

    public static void upRight(String pathToDirectory, Group root, int startX, int startY, int finishX, int finishY,
                               int count, int rows, int columns) {
        String path = pathToDirectory + "up_left.png";
        Image img = new Image(path);
        ImageView imgView = new ImageView(img);
        imgView.setScaleX(-1);
        MoveAnimation.play(root, imgView, img, count, rows, columns, startX, startY, finishX, finishY);
    }

    public static void downRight(String pathToDirectory, Group root, int startX, int startY, int finishX, int finishY,
                                 int count, int rows, int columns) {
        String path = pathToDirectory + "down_left.png";
        Image img = new Image(path);
        ImageView imgView = new ImageView(img);
        imgView.setScaleX(-1);
        MoveAnimation.play(root, imgView, img, count, rows, columns, startX, startY, finishX, finishY);
    }

    public static void downLeft(String pathToDirectory, Group root, int startX, int startY, int finishX, int finishY,
                                int count, int rows, int columns) {
        String path = pathToDirectory + "down_left.png";
        Image img = new Image(path);
        ImageView imgView = new ImageView(img);
        MoveAnimation.play(root, imgView, img, count, rows, columns, startX, startY, finishX, finishY);
    }

    public static void eat(String pathToDirectory, Group root, int cellX, int cellY,
                           int count, int rows, int columns) {
        String path = pathToDirectory + "eat.png";
        Image image = new Image(path);
        ImageView imageView = new ImageView(image);

        int imgWidth = (int) image.getWidth() / columns;
        int imgHeight = (int) image.getHeight() / rows;
        imageView.setViewport(new Rectangle2D(0, 0, imgWidth, imgHeight));
        imageView.relocate(cellX - 50, cellY - 50);
        System.out.println(imageView.getLayoutX() + "  " + imageView.getLayoutY());
        root.getChildren().add(imageView);
        Animation eatAnimation = new SpriteAnimation(imageView, spriteDuration, count, columns,
                0, 0, imgWidth, imgHeight);
        eatAnimation.setCycleCount(spriteAnimationPerTransition);
        eatAnimation.play();
    }

    public static void die(String pathToDirectory, Group root, int cellX, int cellY,
                           int count, int rows, int columns) {
        String path = pathToDirectory + "death.png";
        Image image = new Image(path);
        ImageView imageView = new ImageView(image);

        int imgWidth = (int) image.getWidth() / columns;
        int imgHeight = (int) image.getHeight() / rows;
        imageView.setViewport(new Rectangle2D(0, 0, imgWidth, imgHeight));
        imageView.relocate(cellX - 50, cellY - 50);
        System.out.println(imageView.getLayoutX() + "  " + imageView.getLayoutY());
        root.getChildren().add(imageView);
        Animation eatAnimation = new SpriteAnimation(imageView, spriteDuration, count, columns,
                0, 0, imgWidth, imgHeight);
        eatAnimation.setCycleCount(1);
        eatAnimation.play();
    }

    public static void caged(String pathToDirectory, Group root, int cellX, int cellY,
                             int count, int rows, int columns) {
        String path = pathToDirectory + "caged.png";
        Image image = new Image(path);
        ImageView imageView = new ImageView(image);

        int imgWidth = (int) image.getWidth() / columns;
        int imgHeight = (int) image.getHeight() / rows;
        imageView.setViewport(new Rectangle2D(0, 0, imgWidth, imgHeight));
        imageView.relocate(cellX - 50, cellY - 50);
        System.out.println(imageView.getLayoutX() + "  " + imageView.getLayoutY());
        root.getChildren().add(imageView);
        Animation cagedAnimation = new SpriteAnimation(imageView, spriteDuration, count, columns,
                0, 0, imgWidth, imgHeight);
        cagedAnimation.setCycleCount(Animation.INDEFINITE);
        cagedAnimation.play();
    }

    public static void battle(String pathToDirectory, Group root, int cellX, int cellY,
                             int count, int rows, int columns) {
        String path = pathToDirectory + "battle.png";
        Image image = new Image(path);
        ImageView imageView = new ImageView(image);
        imageView.setScaleX(0.8);
        imageView.setScaleY(0.8);

        int imgWidth = (int) image.getWidth() / columns;
        int imgHeight = (int) image.getHeight() / rows;
        imageView.setViewport(new Rectangle2D(0, 0, imgWidth, imgHeight));
        imageView.relocate(cellX - 120, cellY - 120);
        System.out.println(imageView.getLayoutX() + "  " + imageView.getLayoutY());
        root.getChildren().add(imageView);
        Animation cagedAnimation = new SpriteAnimation(imageView, spriteDuration, count, columns,
                0, 0, imgWidth, imgHeight);
        cagedAnimation.setCycleCount(Animation.INDEFINITE);
        cagedAnimation.play();
    }
}
