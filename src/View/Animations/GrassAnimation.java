package View.Animations;

import javafx.animation.Animation;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class GrassAnimation {
    private static final Duration growingDuration = Duration.millis(800);

    public static void play(ImageView imageView) {
        Image image = imageView.getImage();
        int frameWidth = (int) image.getWidth() / 4;
        int frameHeight = (int) image.getHeight() / 4;
        Animation processingAnimation = new SpriteAnimation(imageView, growingDuration, 16, 4,
                0, 0, frameWidth, frameHeight);
        processingAnimation.setCycleCount(1);
        processingAnimation.play();
    }
}
