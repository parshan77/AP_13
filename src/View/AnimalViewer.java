package View;

import Controller.AnimalController;
import Exceptions.NotFoundException;
import Model.Animals.Animal;
import Model.Animals.Domestic;
import Model.Animals.Predator;
import Model.Placement.Map;
import Model.Plant;
import View.Animations.AnimalAnimation;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AnimalViewer {
    private Animal animal;
    private GamePlayView gamePlayView;
    private ImageView imageView;

    private Animation movingAnimation;

    public AnimalViewer(Animal animal, GamePlayView gamePlayView) {
        this.animal = animal;
        this.gamePlayView = gamePlayView;
        String directionName = animal.getDirection().getName();
        String url = "File:Textures\\Animals\\" + animal.getName() + "\\" +
                getAddress(directionName) + ".png";

        Image image = new Image(url);
        imageView = new ImageView(image);
        if ((directionName.equals("right")) || (directionName.equals("up_right")) || (directionName.equals("down_right")))
            imageView.setScaleX(-1);

        int x = gamePlayView.getCellCenterX(animal.getPosition().getRow(), animal.getPosition().getColumn());
        int y = gamePlayView.getCellCenterY(animal.getPosition().getRow(), animal.getPosition().getColumn());

        double frameWidth = image.getWidth() / AnimalAnimation.getFramesColumns(animal, animal.getDirection().getName());
        double frameHeight = image.getHeight() / AnimalAnimation.getFramesRows(animal, animal.getDirection().getName());
        imageView.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));

        imageView.relocate(x - frameWidth / 2, y - frameHeight / 2);

        gamePlayView.getRoot().getChildren().add(imageView);

        if (animal instanceof Predator)
            imageView.setOnMouseClicked(event -> AnimalController.cage((Predator) animal));
    }

    public String getAddress(String directionName) {
        if (directionName.equals("up_right"))
            return "up_left";
        if (directionName.equals("right"))
            return "left";
        if (directionName.equals("down_right"))
            return "down_left";
        return directionName;
    }

    public void playMoveAnimation(int startingRow, int startingColumn,
                                  int finishingRow, int finishingColumn) {
        int rowDirection = finishingRow - startingRow;
        int columnDirection = finishingColumn - startingColumn;

        if ((animal instanceof Domestic)&&(((Domestic) animal).isDead())) {
            return;
        }

        if ((rowDirection == 1) && (columnDirection == -1)) {
            AnimalAnimation.downLeft(this,
                    gamePlayView.getCellCenterX(startingRow, startingColumn),
                    gamePlayView.getCellCenterY(startingRow, startingColumn),
                    gamePlayView.getCellCenterX(finishingRow, finishingColumn),
                    gamePlayView.getCellCenterY(finishingRow, finishingColumn));
        } else if ((rowDirection == 1) && (columnDirection == 0)) {
            AnimalAnimation.down(this,
                    gamePlayView.getCellCenterX(startingRow, startingColumn),
                    gamePlayView.getCellCenterY(startingRow, startingColumn),
                    gamePlayView.getCellCenterX(finishingRow, finishingColumn),
                    gamePlayView.getCellCenterY(finishingRow, finishingColumn));
        } else if ((rowDirection == 1) && (columnDirection == 1)) {
            AnimalAnimation.downRight(this,
                    gamePlayView.getCellCenterX(startingRow, startingColumn),
                    gamePlayView.getCellCenterY(startingRow, startingColumn),
                    gamePlayView.getCellCenterX(finishingRow, finishingColumn),
                    gamePlayView.getCellCenterY(finishingRow, finishingColumn));
        } else if ((rowDirection == 0) && (columnDirection == 1)) {
            AnimalAnimation.right(this,
                    gamePlayView.getCellCenterX(startingRow, startingColumn),
                    gamePlayView.getCellCenterY(startingRow, startingColumn),
                    gamePlayView.getCellCenterX(finishingRow, finishingColumn),
                    gamePlayView.getCellCenterY(finishingRow, finishingColumn));
        } else if ((rowDirection == -1) && (columnDirection == 1)) {
            AnimalAnimation.upRight(this,
                    gamePlayView.getCellCenterX(startingRow, startingColumn),
                    gamePlayView.getCellCenterY(startingRow, startingColumn),
                    gamePlayView.getCellCenterX(finishingRow, finishingColumn),
                    gamePlayView.getCellCenterY(finishingRow, finishingColumn));
        } else if ((rowDirection == -1) && (columnDirection == 0)) {
            AnimalAnimation.up(this,
                    gamePlayView.getCellCenterX(startingRow, startingColumn),
                    gamePlayView.getCellCenterY(startingRow, startingColumn),
                    gamePlayView.getCellCenterX(finishingRow, finishingColumn),
                    gamePlayView.getCellCenterY(finishingRow, finishingColumn));
        } else if ((rowDirection == -1) && (columnDirection == -1)) {
            AnimalAnimation.upLeft(this,
                    gamePlayView.getCellCenterX(startingRow, startingColumn),
                    gamePlayView.getCellCenterY(startingRow, startingColumn),
                    gamePlayView.getCellCenterX(finishingRow, finishingColumn),
                    gamePlayView.getCellCenterY(finishingRow, finishingColumn));
        } else if ((rowDirection == 0) && (columnDirection == -1)) {
            AnimalAnimation.left(this,
                    gamePlayView.getCellCenterX(startingRow, startingColumn),
                    gamePlayView.getCellCenterY(startingRow, startingColumn),
                    gamePlayView.getCellCenterX(finishingRow, finishingColumn),
                    gamePlayView.getCellCenterY(finishingRow, finishingColumn));
        }
    }

    public void playDieAnimation(Domestic domestic) {
        new AnimationTimer() {
            long start = 0;
            @Override
            public void handle(long now) {
                if (start == 0) start = now;
                else if (now - start > 1_000_000_000) {
                    AnimalAnimation.die(domestic.getAnimalViewer());
                    stop();
                }
            }
        }.start();
    }

    public void playEatAnimation() {

        new AnimationTimer() {
            Map map = gamePlayView.getMission().getMap();
            Plant plant = map.getPlant(animal.getPosition());

            long start = 0;
            @Override
            public void handle(long now) {
                if (start == 0) start = now;
                else if (now - start > 1_000_000_000) {
                    try {
                        gamePlayView.getMission().removeDomesticMovingRequest(((Domestic)animal).getMovingRequest());
                    } catch (NotFoundException e) {
                        e.printStackTrace();
                    }
                    AnimalAnimation.eat(animal.getAnimalViewer(),plant);
                    stop();
                }
            }
        }.start();
    }

    public void cage(Predator predator) {
        int row = predator.getPosition().getRow();
        int column = predator.getPosition().getColumn();
        AnimalAnimation.caged(predator, row, column);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Animal getAnimal() {
        return animal;
    }

    public GamePlayView getGamePlayView() {
        return gamePlayView;
    }

    public Animation getMovingAnimation() {
        return movingAnimation;
    }

    public void setMovingAnimation(Animation movingAnimation) {
        this.movingAnimation = movingAnimation;
    }
}
