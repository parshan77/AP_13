package View;

import javafx.animation.PathTransition;
import javafx.application.Application;
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
import static javafx.application.Application.launch;


public class LevelSelectionViewer extends Application{

    private ImageView backGround;
    private ImageView levelsView;
    private ImageView level1View;
    private ImageView level2View;
    private ImageView level3View;
    private ImageView level4View;
    private ImageView menuView;
    private ImageView shopView;
    Group root = new Group();
    Stage stage;

    boolean baz = true;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Scene missionScene = new Scene(root);
        primaryStage.setScene(missionScene);
        primaryStage.setFullScreen(true);
        primaryStage.show();

        showBackground();
        showShopButton();
        showMenuButton();
        showLevel1Button();
        showLevel2Button();
        showLevel3Button();
        showLevel4Button();
        showLevelsButton();

    }

    public void showBackground() {
        backGround = buildImageView(root, "File:Textures\\MenuResources\\missionView2.jpg",
                0, 0, stage.getWidth(), stage.getHeight(), true);

    }

    public void showLevelsButton(){
        levelsView = buildImageView(root, "File:Textures\\MenuResources\\levels.png",
                250, 450, 100, 100, true);

        levelsView.setOnMouseClicked(event -> {
            if (baz) {
                new PathTransition(
                        Duration.millis(1000),
                        new Path(new MoveTo(300, 500), new LineTo(550, 250)),
                        level1View
                ).play();

                new PathTransition(
                        Duration.millis(1000),
                        new Path(new MoveTo(300, 500), new LineTo(550, 400)),
                        level2View
                ).play();

                new PathTransition(
                        Duration.millis(1000),
                        new Path(new MoveTo(300, 500), new LineTo(550, 550)),
                        level3View
                ).play();

                new PathTransition(
                        Duration.millis(1000),
                        new Path(new MoveTo(300, 500), new LineTo(550, 700)),
                        level4View
                ).play();

                level1View.setVisible(true);
                level2View.setVisible(true);
                level3View.setVisible(true);
                level4View.setVisible(true);

                baz = false;
            } else {
                level1View.setVisible(false);
                level2View.setVisible(false);
                level3View.setVisible(false);
                level4View.setVisible(false);

                baz = true;
            }

        });
    }
    public void showLevel1Button() {
        level1View = buildImageView(root, "File:Textures\\MenuResources\\level1.png",
                0, 0, 100, 100, false);
        level1View.setOnMouseEntered(event -> {

        });
        level1View.setOnMouseExited(event -> {

        });
    }

    public void showLevel2Button() {
        level2View = buildImageView(root, "File:Textures\\MenuResources\\level2.png",
                0, 0, 100, 100, false);
        level2View.setOnMouseEntered(event -> {

        });
        level2View.setOnMouseExited(event -> {

        });
    }
    public void showLevel3Button() {
        level3View = buildImageView(root, "File:Textures\\MenuResources\\level3.png",
                0, 0, 100, 100, false);
        level3View.setOnMouseEntered(event -> {

        });
        level3View.setOnMouseExited(event -> {

        });
    }
    public void showLevel4Button() {
        level4View = buildImageView(root, "File:Textures\\MenuResources\\level4.png",
                0, 0, 100, 100, false);
        level4View.setOnMouseEntered(event -> {

        });
        level4View.setOnMouseExited(event -> {

        });
    }

    public void showShopButton() {
        shopView = buildImageView(root, "File:Textures\\MenuResources\\shop.png",
                stage.getWidth() * 0.03, stage.getHeight() * 0.9,
                150, 70, true);
        shopView.setOnMouseEntered(event -> {
            becomeSmaller(shopView);
        });
        shopView.setOnMouseExited(event -> {
            becomeBigger(shopView);
        });
    }
    public void showMenuButton() {
        menuView = buildImageView(root, "File:Textures\\MenuResources\\menu.png",
                stage.getWidth() * 0.9, stage.getHeight() * 0.9,
                150, 70, true);
        menuView.setOnMouseEntered(event -> {
            becomeSmaller(menuView);
        });
        menuView.setOnMouseExited(event -> {
            becomeBigger(menuView);
        });
    }
}
