package View.Animations;

import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class WellExtractingAnimation {

    public static Animation play(ImageView wellImageView, int refillTurns) {
        Animation wellExtractingAnimation = new SpriteAnimation(wellImageView, Duration.millis(1000), 16,
                4, 0, 0,
                (int) wellImageView.getImage().getWidth() / 4,
                (int) wellImageView.getImage().getHeight() / 4);
        wellExtractingAnimation.setCycleCount(refillTurns);
        wellExtractingAnimation.play();
        return wellExtractingAnimation;
    }
}
