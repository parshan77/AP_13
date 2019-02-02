package View;

import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

import static View.MenuView.becomeBigger;
import static View.MenuView.becomeSmaller;
import static View.MenuView.buildImageView;


public class LevelSelectionViewer {

    boolean baz = true;

    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene missionScene = new Scene(root);
        primaryStage.setScene(missionScene);

        primaryStage.setFullScreen(true);
        primaryStage.show();

        ImageView level = buildImageView(root, "File:Textures\\MenuResources\\missionView2.jpg",
                0, 0, primaryStage.getWidth(), primaryStage.getHeight(), true);

        ImageView shop = buildImageView(root, "File:Textures\\MenuResources\\shop.png",
                primaryStage.getWidth() * 0.03, primaryStage.getHeight() * 0.9,
                150, 70, true);
        shop.setOnMouseEntered(event -> {
            becomeSmaller(shop);
        });
        shop.setOnMouseExited(event -> {
            becomeBigger(shop);
        });

        ImageView menu = buildImageView(root, "File:Textures\\MenuResources\\menu.png",
                primaryStage.getWidth() * 0.9, primaryStage.getHeight() * 0.9,
                150, 70, true);
        menu.setOnMouseEntered(event -> {
            becomeSmaller(menu);
        });
        menu.setOnMouseExited(event -> {
            becomeBigger(menu);
        });

        ImageView level1 = buildImageView(root, "File:Textures\\MenuResources\\level1.png",
                0, 0, 100, 100, false);
        level1.setOnMouseEntered(event -> {

        });
        level1.setOnMouseExited(event -> {

        });

        ImageView level2 = buildImageView(root, "File:Textures\\MenuResources\\level2.png",
                0, 0, 100, 100, false);
        level2.setOnMouseEntered(event -> {

        });
        level2.setOnMouseExited(event -> {

        });

        ImageView level3 = buildImageView(root, "File:Textures\\MenuResources\\level3.png",
                0, 0, 100, 100, false);
        level3.setOnMouseEntered(event -> {

        });
        level3.setOnMouseExited(event -> {

        });

        ImageView level4 = buildImageView(root, "File:Textures\\MenuResources\\level4.png",
                0, 0, 100, 100, false);
        level4.setOnMouseEntered(event -> {

        });
        level4.setOnMouseExited(event -> {

        });

        ImageView levels = buildImageView(root, "File:Textures\\MenuResources\\levels.png",
                250, 450, 100, 100, true);
        levels.setOnMouseClicked(event -> {
            if (baz) {
                new PathTransition(
                        Duration.millis(1000),
                        new Path(new MoveTo(300, 500), new LineTo(550, 250)),
                        level1
                ).play();

                new PathTransition(
                        Duration.millis(1000),
                        new Path(new MoveTo(300, 500), new LineTo(550, 400)),
                        level2
                ).play();

                new PathTransition(
                        Duration.millis(1000),
                        new Path(new MoveTo(300, 500), new LineTo(550, 550)),
                        level3
                ).play();

                new PathTransition(
                        Duration.millis(1000),
                        new Path(new MoveTo(300, 500), new LineTo(550, 700)),
                        level4
                ).play();

                level1.setVisible(true);
                level2.setVisible(true);
                level3.setVisible(true);
                level4.setVisible(true);

                baz = false;
            } else {
                level1.setVisible(false);
                level2.setVisible(false);
                level3.setVisible(false);
                level4.setVisible(false);

                baz = true;
            }

        });
    }
}
