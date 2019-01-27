package View.Animations;

import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class WorkshopAnimation {
    private static final Duration processingDuration = Duration.millis(800);

    public static Animation play(ImageView imageView) {
        Image image = imageView.getImage();
        int frameWidth = (int) image.getWidth() / 4;
        int frameHeight = (int) image.getHeight() / 4;
        Animation processingAnimation = new SpriteAnimation(imageView, processingDuration, 16, 4,
                0, 0, frameWidth, frameHeight);
        processingAnimation.setCycleCount(4);
        processingAnimation.play();
        processingAnimation.setOnFinished(event ->
                imageView.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight)));
        return processingAnimation;
    }
}
