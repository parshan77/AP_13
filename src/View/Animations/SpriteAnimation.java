package View.Animations;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation extends Transition {
    private ImageView imageView;
    private int count;
    private int columns;
    private int offsetx;
    private int offsety;
    private int width;
    private int height;
    private int lastIndex;

    public SpriteAnimation(ImageView imageView, Duration duration, int count, int columns, int offsetx, int offsety,
                           int width, int height) {
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
        this.imageView = imageView;
        this.count = count;
        this.columns = columns;
        this.offsetx = offsetx;
        this.offsety = offsety;
        this.width = width;
        this.height = height;
    }


    @Override
    protected void interpolate(double k) {
        final int index = Math.min((int)(k * count), count - 1);
        if (index != lastIndex) {
            final int x = (index % columns) * width + offsetx;
            final int y = (index / columns) * height + offsety;
            imageView.setViewport(new Rectangle2D(x, y, width, height));
            lastIndex = index;
        }
    }
}

