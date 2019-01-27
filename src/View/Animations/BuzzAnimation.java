package View.Animations;

import javafx.animation.PathTransition;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class BuzzAnimation {

    public static void play(Control object) {
        double startingX = object.getTranslateX() + object.getWidth() / 2;
        double startingY = object.getTranslateY() + object.getHeight() / 2;
        Path path = new Path(new MoveTo(startingX, startingY));
        path.getElements().addAll(new LineTo(startingX - 5, startingY + 5),
                new LineTo(startingX + 5, startingY + 5),
                new LineTo(startingX - 5, startingY - 5),
                new LineTo(startingX + 5, startingY - 5),
                new LineTo(startingX, startingY));
        PathTransition buzzAnimation = new PathTransition(Duration.millis(150), path, object);
        object.setTranslateX(startingX);
        object.setTranslateY(startingY);
        buzzAnimation.setCycleCount(3);
        buzzAnimation.play();
    }


    // TODO: 1/26/2019 test kon ino
    public static void play(ImageView object) {
        double startingX = object.getTranslateX() + object.getLayoutX() / 2;
        double startingY = object.getTranslateY() + object.getLayoutY() / 2;
        Path path = new Path(new MoveTo(startingX, startingY));
        path.getElements().addAll(new LineTo(startingX - 5, startingY + 5),
                new LineTo(startingX + 5, startingY + 5),
                new LineTo(startingX - 5, startingY - 5),
                new LineTo(startingX + 5, startingY - 5),
                new LineTo(startingX, startingY));
        PathTransition buzzAnimation = new PathTransition(Duration.millis(150), path, object);
        object.setTranslateX(startingX);
        object.setTranslateY(startingY);
        buzzAnimation.setCycleCount(3);
        buzzAnimation.play();
    }
}
