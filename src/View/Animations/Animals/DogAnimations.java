package View.Animations.Animals;

import javafx.scene.Group;

public class DogAnimations {
    private static final String pathToDirectory = "file:Textures\\Animals\\Dog\\";

    public static void up(Group root, int startX, int startY, int finishX, int finishY) {
        MoveAnimation.up(pathToDirectory, root, startX, startY, finishX, finishY, 24, 4, 6);
    }

    public static void down(Group root, int startX, int startY, int finishX, int finishY) {
        MoveAnimation.down(pathToDirectory,root,startX,startY,finishX,finishY,24,4,6);
    }

    public static void right(Group root, int startX, int startY, int finishX, int finishY) {
        MoveAnimation.right(pathToDirectory, root, startX, startY, finishX, finishY, 24, 4, 6);
    }

    public static void left(Group root, int startX, int startY, int finishX, int finishY) {
        MoveAnimation.left(pathToDirectory,root,startX,startY,finishX,finishY,24,4,6);
    }

    public static void upLeft(Group root, int startX, int startY, int finishX, int finishY) {
        MoveAnimation.upLeft(pathToDirectory, root, startX, startY, finishX, finishY, 24, 5, 5);
    }

    public static void upRight(Group root, int startX, int startY, int finishX, int finishY) {
        MoveAnimation.upRight(pathToDirectory, root, startX, startY, finishX, finishY, 24, 5, 5);
    }

    public static void downRight(Group root, int startX, int startY, int finishX, int finishY) {
        MoveAnimation.downRight(pathToDirectory,root,startX,startY,finishX,finishY,24,5,5);
    }

    public static void downLeft(Group root, int startX, int startY, int finishX, int finishY) {
        MoveAnimation.downLeft(pathToDirectory, root, startX, startY, finishX, finishY, 24, 5, 5);
    }

    public static void battle(Group root, int cellX, int cellY) {
        MoveAnimation.battle(pathToDirectory, root, cellX, cellY,  20, 4, 5);
    }
}
