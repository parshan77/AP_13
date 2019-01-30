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
        PathTransition buzzAnimation = new PathTransition(Duration.millis(200), path, object);
        buzzAnimation.setCycleCount(1);
        buzzAnimation.play();
    }

    // TODO: 1/31/2019 chera kar nemikone?!
    public static void play(ImageView imageView) {
        Path path = new Path(new MoveTo(imageView.getImage().getWidth() , imageView.getImage().getHeight() ));
        path.getElements().addAll(new LineTo( -5,  5),
                new LineTo( 10,  0),
                new LineTo( -10,  -10),
                new LineTo( 10,  0),
                new LineTo(-5, 5));
        PathTransition buzzAnimation = new PathTransition(Duration.millis(1000), path);
        buzzAnimation.setNode(imageView);
        buzzAnimation.setCycleCount(2);
        buzzAnimation.play();
    }
}
