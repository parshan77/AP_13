package View.Animations;

import Exceptions.NotFoundException;
import Model.Animals.Animal;
import Model.Animals.Domestic;
import Model.Animals.Domestics.Cow;
import Model.Animals.Domestics.Hen;
import Model.Animals.Domestics.Sheep;
import Model.Animals.Predators.Bear;
import Model.Animals.Predators.Lion;
import Model.Animals.Seekers.Cat;
import Model.Animals.Seekers.Dog;
import Model.Placement.Map;
import Model.Plant;
import View.AnimalViewer;
import View.GamePlayView;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class AnimalAnimation {
    private static final int spriteAnimationPerTransition = 2;

    public static void play(AnimalViewer animalViewer, int count, int rows, int columns,
                            int startX, int startY, int finishX, int finishY) {
        ImageView imageView = animalViewer.getImageView();
        Image image = imageView.getImage();
        GamePlayView gamePlayView = animalViewer.getGamePlayView();
        int pace = animalViewer.getAnimal().getPace();

        int imgWidth = (int) image.getWidth() / columns;
        int imgHeight = (int) image.getHeight() / rows;
        imageView.setViewport(new Rectangle2D(0, 0, imgWidth, imgHeight));

        double transitionTime = 1000.0 / gamePlayView.getTurnsPerSecond() / pace;
        Duration spriteDuration = Duration.millis(transitionTime / spriteAnimationPerTransition);
        Duration pathTransitionDuration = Duration.millis(transitionTime);

        Animation spriteAnimation = new SpriteAnimation(imageView, spriteDuration, count, columns,
                0, 0, imgWidth, imgHeight);
        spriteAnimation.setCycleCount(Animation.INDEFINITE);

        Path way = new Path(new MoveTo(startX - imageView.getLayoutX(), startY - imageView.getLayoutY()),
                new LineTo(finishX - imageView.getLayoutX(), finishY - imageView.getLayoutY()));
        PathTransition moveAnimation = new PathTransition(pathTransitionDuration, way, imageView);
        moveAnimation.setInterpolator(Interpolator.LINEAR);
        moveAnimation.setCycleCount(1);
        moveAnimation.setAutoReverse(false);
        moveAnimation.setOnFinished(event -> spriteAnimation.stop());

        spriteAnimation.play();
        moveAnimation.play();
    }

    public static void eat(AnimalViewer animalViewer, Plant plant) {
        GamePlayView gamePlayView = animalViewer.getGamePlayView();
        Domestic animal = (Domestic) animalViewer.getAnimal();
        String path = "file:Textures\\Animals\\" + animal.getName() + "\\eat.png";

        int row = animal.getPosition().getRow();
        int column = animal.getPosition().getColumn();

        Image image = new Image(path);
        ImageView imageView = animalViewer.getImageView();
        imageView.setImage(image);

        int columns = getFramesColumns(animal, "eat");
        int rows = getFramesRows(animal, "eat");
        int count = getFramesCount(animal, "eat");

        int frameWidth = (int) (image.getWidth() / 5);
        int frameHeight = (int) (image.getHeight() / 5);

        int x = gamePlayView.getCellCenterX(row, column) - 50;
        int y = gamePlayView.getCellCenterY(row, column) - 50;


        imageView.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));

        Duration spriteDuration = Duration.millis(2000);
        Animation eatAnimation = new SpriteAnimation(imageView, spriteDuration, 24, 5,
                0, 0, frameWidth, frameHeight);
        eatAnimation.setCycleCount(3);
        eatAnimation.setAutoReverse(false);
        eatAnimation.play();
        eatAnimation.setOnFinished(event -> {
            System.out.println("eat animation finished");
            ImageView grassImageView = gamePlayView.getCellViewer(row, column).getGrassImageView();
            gamePlayView.getRoot().getChildren().remove(grassImageView);
        });
    }

    public static void up(AnimalViewer animalViewer, int startX, int startY, int finishX, int finishY) {
        Animal animal = animalViewer.getAnimal();
        String path = "file:Textures\\Animals\\" + animal.getName() + "\\up.png";
        Image image = new Image(path);
        ImageView imageView = animalViewer.getImageView();
        if (!imageView.getImage().equals(image))
            imageView.setImage(image);
        play(animalViewer, getFramesCount(animal, "up"),
                getFramesRows(animal, "up"),
                getFramesColumns(animal, "up"),
                startX, startY, finishX, finishY);
    }

    public static void down(AnimalViewer animalViewer, int startX, int startY, int finishX, int finishY) {
        Animal animal = animalViewer.getAnimal();
        String path = "file:Textures\\Animals\\" + animal.getName() + "\\down.png";
        Image image = new Image(path);
        ImageView imageView = animalViewer.getImageView();
        if (!imageView.getImage().equals(image))
            imageView.setImage(image);
        play(animalViewer, getFramesCount(animal, "down"),
                getFramesRows(animal, "down"),
                getFramesColumns(animal, "down")
                , startX, startY, finishX, finishY);
    }

    public static void right(AnimalViewer animalViewer, int startX, int startY, int finishX, int finishY) {
        Animal animal = animalViewer.getAnimal();
        String path = "file:Textures\\Animals\\" + animal.getName() + "\\left.png";
        Image image = new Image(path);
        ImageView imageView = animalViewer.getImageView();

        if (!imageView.getImage().equals(image))
            imageView.setImage(image);
        animalViewer.getImageView().setScaleX(-1);
        play(animalViewer, getFramesCount(animal, "right"),
                getFramesRows(animal, "right"),
                getFramesColumns(animal, "right"),
                startX, startY, finishX, finishY);
    }

    public static void left(AnimalViewer animalViewer, int startX, int startY, int finishX, int finishY) {
        Animal animal = animalViewer.getAnimal();
        String path = "file:Textures\\Animals\\" + animal.getName() + "\\left.png";
        Image image = new Image(path);
        ImageView imageView = animalViewer.getImageView();
        if (!imageView.getImage().equals(image))
            imageView.setImage(image);
        imageView.setScaleX(1);
        play(animalViewer, getFramesCount(animal, "left"),
                getFramesRows(animal, "left"),
                getFramesColumns(animal, "left"),
                startX, startY, finishX, finishY);
    }

    public static void upLeft(AnimalViewer animalViewer, int startX, int startY, int finishX, int finishY) {
        Animal animal = animalViewer.getAnimal();
        String path = "file:Textures\\Animals\\" + animal.getName() + "\\up_left.png";
        Image image = new Image(path);
        ImageView imageView = animalViewer.getImageView();
        if (!imageView.getImage().equals(image))
            imageView.setImage(image);
        imageView.setScaleX(1);
        play(animalViewer, getFramesCount(animal, "up_left"),
                getFramesRows(animal, "up_left"),
                getFramesColumns(animal, "up_left"), startX, startY, finishX, finishY);
    }

    public static void upRight(AnimalViewer animalViewer, int startX, int startY, int finishX, int finishY) {
        Animal animal = animalViewer.getAnimal();
        String path = "file:Textures\\Animals\\" + animal.getName() + "\\up_left.png";
        Image image = new Image(path);
        ImageView imageView = animalViewer.getImageView();
        if (!imageView.getImage().equals(image))
            imageView.setImage(image);
        animalViewer.getImageView().setScaleX(-1);
        play(animalViewer, getFramesCount(animal, "up_right"),
                getFramesRows(animal, "up_right"),
                getFramesColumns(animal, "up_right"), startX, startY, finishX, finishY);
    }

    public static void downRight(AnimalViewer animalViewer, int startX, int startY, int finishX, int finishY) {
        Animal animal = animalViewer.getAnimal();
        String path = "file:Textures\\Animals\\" + animal.getName() + "\\down_left.png";
        Image image = new Image(path);
        ImageView imageView = animalViewer.getImageView();
        if (!imageView.getImage().equals(image))
            imageView.setImage(image);
        animalViewer.getImageView().setScaleX(-1);
        play(animalViewer, getFramesCount(animal, "down_right"),
                getFramesRows(animal, "down_right"),
                getFramesColumns(animal, "down_right"), startX, startY, finishX, finishY);
    }

    public static void downLeft(AnimalViewer animalViewer, int startX, int startY, int finishX, int finishY) {
        Animal animal = animalViewer.getAnimal();
        String path = "file:Textures\\Animals\\" + animal.getName() + "\\down_left.png";
        Image image = new Image(path);
        ImageView imageView = animalViewer.getImageView();
        if (!imageView.getImage().equals(image))
            imageView.setImage(image);
        imageView.setScaleX(1);
        play(animalViewer, getFramesCount(animal, "down_left"),
                getFramesRows(animal, "down_left"),
                getFramesColumns(animal, "down_left"), startX, startY, finishX, finishY);
    }

    public static void battle(AnimalViewer dogViewer, int cellX, int cellY) {
        Group root = dogViewer.getGamePlayView().getRoot();

        String path = "file:Textures\\Animals\\Dog\\battle.png";
        Image image = new Image(path);
        ImageView imageView = new ImageView(image);
        imageView.setScaleX(0.8);
        imageView.setScaleY(0.8);

        int rows = 4, columns = 5, count = 20;
        int imgWidth = (int) image.getWidth() / columns;
        int imgHeight = (int) image.getHeight() / rows;
        imageView.setViewport(new Rectangle2D(0, 0, imgWidth, imgHeight));
        imageView.relocate(cellX - 120, cellY - 120);

        // TODO: 1/28/2019 spriteDuration ro dorost kon
        Duration spriteDuration = Duration.millis(1000);
        Animation battleAnimation = new SpriteAnimation(imageView, spriteDuration, count, columns,
                0, 0, imgWidth, imgHeight);
        battleAnimation.setCycleCount(3);
        battleAnimation.play();
        root.getChildren().addAll(imageView);

        Dog dog = (Dog) dogViewer.getAnimal();
        Map map = dog.getMap();

        // TODO: 1/30/2019 baraye concurrent modification nagereftan inkaro kardam
        new AnimationTimer() {
            long startingTime = 0;
            long SECOND = 1_000_000_000;

            @Override
            public void handle(long now) {
                if (startingTime == 0)
                    startingTime = now;
                else if (now - startingTime > SECOND / 10) {
                    try {
                        map.discardAnimal(dog);
                    } catch (NotFoundException e) {
                        e.printStackTrace();
                    }
                    stop();
                }
            }
        }.start();

        battleAnimation.setOnFinished(event -> {
            root.getChildren().remove(imageView);
        });
    }

    public static int getFramesCount(Animal animal, String animationName) {
        if (animal instanceof Cow)
            return 24;
        if (animal instanceof Bear)
            return 24;
        if (animal instanceof Cat)
            if (animationName.equals("battle")) return 20;
            else return 24;
        if (animal instanceof Dog)
            if (animationName.equals("battle")) return 20;
            else return 24;
        if (animal instanceof Hen)
            return 24;
        if (animal instanceof Lion)
            return 24;
        if (animal instanceof Sheep)
            return 24;
        return -1;
    }

    public static int getFramesRows(Animal animal, String animationName) {
        if (animal instanceof Cow)
            if ((animationName.equals("up")) || (animationName.equals("eat"))) return 6;
            else return 8;
        else if (animal instanceof Bear)
            if (animationName.equals("caged")) return 4;
            else return 6;
        else if (animal instanceof Cat)
            if ((animationName.equals("right")) || (animationName.equals("left"))) return 6;
            else return 4;
        else if (animal instanceof Dog)
            if ((animationName.equals("up")) || (animationName.equals("down")) || (animationName.equals("left")) ||
                    (animationName.equals("right")) || animationName.equals("battle"))
                return 4;
            else return 5;
        else if (animal instanceof Hen)
            return 5;
        else if (animal instanceof Lion)
            if ((animationName.equals("up")) || (animationName.equals("up_left")) ||
                    (animationName.equals("up_right")) || (animationName.equals("caged")))
                return 4;
            else if (animationName.equals("down"))
                return 5;
            else if ((animationName.equals("right")) || (animationName.equals("left")))
                return 8;
            else return 6;
        else if (animal instanceof Sheep)
            if ((animationName.equals("up")) || (animationName.equals("down")) ||
                    (animationName.equals("up_left")) || (animationName.equals("up_right")))
                return 5;
            else return 6;
        return -1;
    }

    public static int getFramesColumns(Animal animal, String animationName) {
        if (animal instanceof Cow)
            if ((animationName.equals("up")) || (animationName.equals("eat"))) return 4;
            else return 3;
        if (animal instanceof Bear)
            if (animationName.equals("caged")) return 6;
            else return 4;
        if (animal instanceof Cat)
            if (animationName.equals("battle")) return 5;
            else if ((animationName.equals("right")) || (animationName.equals("left"))) return 4;
            else return 6;
        if (animal instanceof Dog)
            if ((animationName.equals("up")) || (animationName.equals("down")) ||
                    (animationName.equals("left")) || (animationName.equals("right")))
                return 6;
            else return 5;
        if (animal instanceof Hen)
            return 5;
        if (animal instanceof Lion)
            if ((animationName.equals("up")) || (animationName.equals("up_left")) ||
                    (animationName.equals("up_right")) || (animationName.equals("caged")))
                return 6;
            else if (animationName.equals("down"))
                return 5;
            else if ((animationName.equals("right")) || (animationName.equals("left")))
                return 3;
            else return 4;
        if (animal instanceof Sheep)
            if ((animationName.equals("up")) || (animationName.equals("down")) ||
                    (animationName.equals("up_left")) || (animationName.equals("up_right")))
                return 5;
            else return 4;
        return -1;
    }

    public static void die(AnimalViewer animalViewer) {
        Animal domestic = animalViewer.getAnimal();
        String path = "File:Textures\\Animals\\"+domestic.getName() + "\\death.png";

        Image image = new Image(path);
        ImageView imageView = animalViewer.getImageView();
        imageView.setImage(image);

        int count = AnimalAnimation.getFramesCount(domestic, "death");
        int rows = AnimalAnimation.getFramesRows(domestic, "death");
        int columns = AnimalAnimation.getFramesColumns(domestic, "death");

        int frameWidth = (int) image.getWidth() / columns;
        int frameHeight = (int) image.getHeight() / rows;
        imageView.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
//        imageView.relocate(cellX - frameWidth, cellY - frameHeight);

        Duration spriteDuration = Duration.millis(1500);
        Animation dieAnimation = new SpriteAnimation(imageView, spriteDuration, count, columns,
                0, 0, frameWidth, frameHeight);
        dieAnimation.setCycleCount(1);
        dieAnimation.setAutoReverse(false);

        dieAnimation.play();
        dieAnimation.setOnFinished(event ->
                animalViewer.getGamePlayView().getRoot().getChildren().remove(animalViewer.getImageView()));
    }

    public static void caged(Animal animal, int row, int column) {
        AnimalViewer animalViewer = animal.getAnimalViewer();

        int cellX = animalViewer.getGamePlayView().getCellCenterX(row, column);
        int cellY = animalViewer.getGamePlayView().getCellCenterY(row, column);

        int rows = getFramesRows(animal,"caged");
        int columns = getFramesColumns(animal, "caged");
        int count = getFramesCount(animal, "caged");


        String path = "file:Textures\\Animals\\" + animal.getName() + "\\caged.png";
        Image image = new Image(path);
        ImageView imageView = animalViewer.getImageView();
        imageView.setImage(image);


        int imgWidth = (int) image.getWidth() / columns;
        int imgHeight = (int) image.getHeight() / rows;
        imageView.setViewport(new Rectangle2D(0, 0, imgWidth, imgHeight));
        imageView.relocate(cellX - 50, cellY - 50);

        // TODO: 1/28/2019 spriteDuration ro dorost kon
        Duration spriteDuration = Duration.millis(1000);
        Animation cagedAnimation = new SpriteAnimation(imageView, spriteDuration, count, columns,
                0, 0, imgWidth, imgHeight);
        cagedAnimation.setCycleCount(Animation.INDEFINITE);
        cagedAnimation.play();
    }

}
