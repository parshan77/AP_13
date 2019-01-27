package View.Animations;

import javafx.animation.Animation;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class WellExtractingAnimation {

    public static Animation play(ImageView wellImageView, Duration duration) {
        Animation wellExtractingAnimation = new SpriteAnimation(wellImageView, duration, 16,
                4, 0, 0,
                (int) wellImageView.getImage().getWidth() / 4,
                (int) wellImageView.getImage().getHeight() / 4);
        wellExtractingAnimation.setCycleCount(10);
        wellExtractingAnimation.play();
        return wellExtractingAnimation;
    }
}
