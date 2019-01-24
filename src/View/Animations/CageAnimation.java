package View.Animations;

import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class CageAnimation {
    private static final Duration breakingDuration = Duration.millis(800);

    public static void play(ImageView imageView, Image image) {
        int frameWidth = (int) image.getWidth() / 5;
        int frameHeight = (int) image.getHeight() / 5;
        Animation processingAnimation = new SpriteAnimation(imageView, breakingDuration, 24, 5,
                0, 0, frameWidth, frameHeight);
        processingAnimation.setCycleCount(4);
        processingAnimation.play();
        processingAnimation.setOnFinished(event ->
                imageView.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight)));
    }
}
