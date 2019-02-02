package View;

import Model.Identity.Game;
import View.Animations.SpriteAnimation;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class MenuView extends Application {
    private Game game;
    private Stage primaryStage;
    private Group root;
    private ImageView background;
    private ImageView singlePlayer;
    private ImageView multiPlayer;
    private ImageView options;
    private ImageView scoreBoard;
    private ImageView exit;
    private ImageView sheep;
    private ImageView hen;
    private ImageView hen2;
    private MediaPlayer birdSound;
    private ImageView logo;
    private ImageView cloud;
    private Label sound;
    private Rectangle subMenusBox;
    private TextField nameField;
    private Label enterYourName;
    private ImageView subMenuClose;
    private ImageView goPlay;
    private RadioButton clientButton;
    private RadioButton serverButton;
    private ImageView onOrOffSound;
    boolean scoreboardState=false;

    public MenuView(Game game) {
        this.game = game;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        showBackground();
        showLogo();
        showCloud();
        showHens();
        showSheep();
        playBirdSound();




        subMenusBox = buildRectangle(root,
                0.3 * primaryStage.getWidth(), 0.2 * primaryStage.getHeight(),
                0.45 * primaryStage.getWidth(), 0.6 * primaryStage.getHeight(),
                Color.BLUE, false, 100, 100);
        subMenusBox.setOpacity(0.6);

        final boolean[] buttonsBaz = {true, true, true, true};
////////////////////////////////////////////////////////// what is in singlePlay
        enterYourName = buildLabel(root, "Enter Your Name",
                0.34 * primaryStage.getWidth(), 0.3 * primaryStage.getHeight(),
                Font.font(40), false, "-fx-font-weight: bold");

        goPlay = buildImageView(root, "File:Textures\\MenuResources\\goPlay.png",
                0.55 * primaryStage.getWidth(), 0.45 * primaryStage.getHeight(),
                50, 50,
                false);
        goPlay.setOnMouseClicked(event -> {
        });


        nameField = buildField(root, 0.35 * primaryStage.getWidth(), 0.43 * primaryStage.getHeight(),
                "-fx-font-weight: bold; -fx-background-color: #ff6528");
        nameField.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (isFieldFull(nameField)) {
                    goPlay.setVisible(true);
                } else {
                    goPlay.setVisible(false);
                }
            }
        });
        nameField.setVisible(false);

/////////////////////////////////////////////// what is in multiPlay
        clientButton = buildRadioButton(root, "Client",
                0.35 * primaryStage.getWidth(), 0.3 * primaryStage.getHeight(),
                "-fx-background-color: #ff6528",
                Font.font(30), false);
        serverButton = buildRadioButton(root, "Server",
                0.35 * primaryStage.getWidth(), 0.4 * primaryStage.getHeight(),
                "-fx-background-color: #ff6528",
                Font.font(30), false);
        clientButton.setOnMouseClicked(event -> {
            if (serverButton.isSelected()) {
                serverButton.fire();
            }
        });
        serverButton.setOnMouseClicked(event -> {
            if (clientButton.isSelected()) {
                clientButton.fire();
            }
        });

/////////////////////////////////////////////// what is in options
        final boolean[] soundState = {true};

        sound = buildLabel(root, "sound",
                0.35 * primaryStage.getWidth(), 0.3 * primaryStage.getHeight(),
                Font.font(40), false, "-fx-font-weight: bold");

        Image onImage = new Image("File:Textures\\MenuResources\\on.png");
        Image offImage = new Image("File:Textures\\MenuResources\\off.png");
        onOrOffSound = buildImageView(root, "File:Textures\\MenuResources\\off.png",
                0.35 * primaryStage.getWidth(), 0.4 * primaryStage.getHeight(),
                0.14 * primaryStage.getWidth(), 0.07 * primaryStage.getHeight(),
                false);

        onOrOffSound.setOnMouseClicked(event -> {
            if (soundState[0]) {
                birdSound.pause();
                onOrOffSound.setImage(onImage);
                soundState[0] = false;
            } else {
                birdSound.play();
                onOrOffSound.setImage(offImage);
                soundState[0] = true;
            }
        });
//////////////////////////////////////////////////////////
        subMenuClose = buildImageView(root, "File:Textures\\MenuResources\\exitSmallMenu.png",
                primaryStage.getWidth() * 0.7, primaryStage.getHeight() * 0.25,
                40, 40, false);
/////////////////////////////////////////////////////////
        showSinglePleyerButton();
        showMultiPleyerButton();
        showOptionsButton();
        showScoreBoardButton();
        showExitButton();


        ImageView[] buttonsViews = new ImageView[4];
        buttonsViews[0] = singlePlayer;
        buttonsViews[1] = multiPlayer;
        buttonsViews[2] = options;
        buttonsViews[3] = scoreBoard;

        singlePlayer.setOnMouseClicked(event -> {
            showSinglePlayerSubMenu();
        });

        multiPlayer.setOnMouseClicked(event -> {
            showMultiPlayerSubMenu();
        });

        options.setOnMouseClicked(event -> {
            showOptionSubMenu();
        });

        scoreBoard.setOnMouseClicked(event -> {
            showScoreBoardSubMenu();
        });


        subMenuClose.setOnMouseClicked(event -> {
            closeSinglePlayer();
            closeMultiPlayer();
            closeOptions();
            closeScoreBoard();
        });



    }
    public void showBackground() {
        Image backGroundImg = new Image("File:Textures\\MenuResources\\backg.jpg");
        background = new ImageView(backGroundImg);
        background.setFitHeight(primaryStage.getHeight());
        background.setFitWidth(primaryStage.getWidth());
        background.setPreserveRatio(false);
        root.getChildren().add(background);
    }

    public void showSinglePleyerButton() {
        double buttonsWidth = 0.2 * primaryStage.getWidth();
        double buttonsHeight = 0.1 * primaryStage.getHeight();
        singlePlayer = buildImageView(root, "File:Textures\\MenuResources\\singlePlayer.png",
                0.78 * primaryStage.getWidth(), 0.1 * primaryStage.getHeight(),
                buttonsWidth, buttonsHeight,
                true);
        singlePlayer.setOnMouseEntered(event -> {
            becomeBigger(singlePlayer);
        });
        singlePlayer.setOnMouseExited(event -> {
            becomeSmaller(singlePlayer);
        });
    }

    public void showMultiPleyerButton() {
        double buttonsWidth = 0.2 * primaryStage.getWidth();
        double buttonsHeight = 0.1 * primaryStage.getHeight();
        multiPlayer = buildImageView(root, "File:Textures\\MenuResources\\multiPlayer.png",
                0.78 * primaryStage.getWidth(), 2.5 * buttonsHeight,
                buttonsWidth, buttonsHeight,
                true);
        multiPlayer.setOnMouseEntered(event -> {
            becomeBigger(multiPlayer);
        });
        multiPlayer.setOnMouseExited(event -> {
            becomeSmaller(multiPlayer);
        });

    }

    public void showExitButton() {
        double buttonsWidth = 0.2 * primaryStage.getWidth();
        double buttonsHeight = 0.1 * primaryStage.getHeight();

        exit = buildImageView(root, "File:Textures\\MenuResources\\exit.png",
                0.78 * primaryStage.getWidth(), 7 * buttonsHeight,
                buttonsWidth, buttonsHeight,
                true);
        exit.setOnMouseEntered(event -> {
            becomeBigger(exit);
        });
        exit.setOnMouseExited(event -> {
            becomeSmaller(exit);
        });
        exit.setOnMouseClicked(event -> {
            primaryStage.close();
        });

    }
    public void showOptionsButton() {
        double buttonsWidth = 0.2 * primaryStage.getWidth();
        double buttonsHeight = 0.1 * primaryStage.getHeight();

        options = buildImageView(root, "File:Textures\\MenuResources\\options.png",
                0.78 * primaryStage.getWidth(), 4 * buttonsHeight,
                buttonsWidth, buttonsHeight,
                true);
        options.setOnMouseEntered(event -> {
            becomeBigger(options);
        });
        options.setOnMouseExited(event -> {
            becomeSmaller(options);
        });
    }

    public void showScoreBoardButton() {
        double buttonsWidth = 0.2 * primaryStage.getWidth();
        double buttonsHeight = 0.1 * primaryStage.getHeight();

        scoreBoard = buildImageView(root, "File:Textures\\MenuResources\\scoreBoard.png",
                0.78 * primaryStage.getWidth(), 5.5 * buttonsHeight,
                buttonsWidth, buttonsHeight,
                true);
        scoreBoard.setOnMouseEntered(event -> {
            becomeBigger(scoreBoard);
        });
        scoreBoard.setOnMouseExited(event -> {
            becomeSmaller(scoreBoard);
        });

    }

    public void showSinglePlayerSubMenu() {
        closeMultiPlayer();
        closeOptions();
        closeScoreBoard();
        openSinglePlayer();
    }

    public void showMultiPlayerSubMenu() {
        closeSinglePlayer();
        closeOptions();
        closeScoreBoard();
        openMultiPlayer();
    }

    public void showOptionSubMenu() {
        closeSinglePlayer();
        closeMultiPlayer();
        closeScoreBoard();
        openOptions();
    }

    public void showScoreBoardSubMenu() {
        closeSinglePlayer();
        closeMultiPlayer();
        closeOptions();
        openScoreBoard();
    }

    public void closeSinglePlayer(){
        subMenusBox.setVisible(false);
        subMenuClose.setVisible(false);
        nameField.setVisible(false);
        goPlay.setVisible(false);
    }

    public void closeMultiPlayer() {
        subMenusBox.setVisible(false);
        subMenuClose.setVisible(false);
        serverButton.setVisible(false);
        clientButton.setVisible(false);
    }

    public void closeOptions() {
        subMenusBox.setVisible(false);
        subMenuClose.setVisible(false);
        onOrOffSound.setVisible(false);
        sound.setVisible(false);
    }

    public void closeScoreBoard() {
        subMenusBox.setVisible(false);
        subMenuClose.setVisible(false);
        scoreboardState = false;
    }

    public void openSinglePlayer(){
        subMenusBox.setVisible(true);
        subMenuClose.setVisible(true);
        nameField.setVisible(true);
        goPlay.setVisible(true);
    }
    public void openMultiPlayer(){
        subMenusBox.setVisible(true);
        subMenuClose.setVisible(true);
        serverButton.setVisible(true);
        clientButton.setVisible(true);
    }

    public void openOptions() {
        subMenusBox.setVisible(true);
        subMenuClose.setVisible(true);
        onOrOffSound.setVisible(true);
    }

    public void openScoreBoard() {
        scoreboardState = true;
    }

    public void showHens() {
        hen = buildImageView(root, "File:Textures\\MenuResources\\hen.png",
                170, 620,
                74, 64,
                true);

        hen2 = buildImageView(root, "File:Textures\\MenuResources\\hen.png",
                140, 620,
                74, 64,
                true);

        Animation henAnimation = new SpriteAnimation(hen, Duration.millis(1000),
                24, 5,
                0, 0,
                74, 64);
        henAnimation.setAutoReverse(true);
        henAnimation.setCycleCount(Animation.INDEFINITE);
        henAnimation.play();

        Animation hen2Animation = new SpriteAnimation(hen, Duration.millis(1000),
                24, 5,
                0, 0,
                74, 64);
        hen2Animation.setAutoReverse(true);
        hen2Animation.setCycleCount(Animation.INDEFINITE);
        hen2Animation.play();

    }

    public void showSheep() {
        sheep = buildImageView(root, "File:Textures\\MenuResources\\sheep.png",
                430, 550,
                110, 78,
                true);
        Animation sheepAnimation = new SpriteAnimation(sheep, Duration.millis(1000),
                24, 4,
                0, 0,
                110, 78);
        sheepAnimation.setAutoReverse(true);
        sheepAnimation.setCycleCount(Integer.MAX_VALUE);
        sheepAnimation.play();
    }

    public void showLogo() {
        String url = "File:Textures\\MenuResources\\logo.png";
        Image logoImg = new Image(url);
        logo = new ImageView(logoImg);
        logo.setPreserveRatio(true);
        logo.setFitHeight(250);
        logo.relocate((primaryStage.getWidth() / 2) - (logoImg.getWidth() / 2),
                (primaryStage.getHeight() / 2) - (logoImg.getHeight() / 2) - 200);
        root.getChildren().add(logo);
    }

    public void showCloud() {
        cloud = buildImageView(root, "File:Textures\\MenuResources\\cloud.png",
                0, -100,
                300, 300,
                true);

        PathTransition pathTransition = new PathTransition(Duration.millis(50000),
                new Path(new MoveTo(0, 300), new LineTo(1580, 300)),
                cloud);
        pathTransition.setAutoReverse(true);
        pathTransition.setCycleCount(Integer.MAX_VALUE);
        pathTransition.play();
    }

    public void playBirdSound() {
        birdSound = buildMedia(root, "Textures\\MenuResources\\birdSound.mp3");
        birdSound.setAutoPlay(true);
        birdSound.setCycleCount(Integer.MAX_VALUE);
        birdSound.play();
    }

    public void closeOpenButtons(boolean[] buttonSbaz, int buttonNumber, ImageView[] views) {
        for (int i = 0; i < buttonSbaz.length; i++) {
            if (i == buttonNumber) {
                continue;
            }
            if (!buttonSbaz[i]) {
                views[i].getOnMouseClicked().handle(null);
            }
        }
    }

    public static void becomeBigger(ImageView view) {
        view.setFitWidth(view.getFitWidth() * 11.0 / 10);
        view.setFitHeight(view.getFitHeight() * 11.0 / 10);

    }

    public static void becomeSmaller(ImageView view) {
        view.setFitWidth(view.getFitWidth() * 10.0 / 11);
        view.setFitHeight(view.getFitHeight() * 10.0 / 11);
    }

    public static ImageView buildImageView(Group root, String uri, double x, double y, double width, double height, boolean visible) {
        Image image = new Image(String.valueOf(uri));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setVisible(visible);
        imageView.relocate(x, y);
        root.getChildren().add(imageView);
        return imageView;
    }

    public MediaPlayer buildMedia(Group root, String uri) {
        File file = new File(uri);
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        return mediaPlayer;
    }

    public Rectangle buildRectangle(Group root, double x, double y, double width, double height, Color color, boolean visible,
                                    int arcWidth, int arcHeight) {
        Rectangle rectangle = new Rectangle(x, y, width, height);
        rectangle.setFill(color);
        rectangle.setVisible(visible);
        rectangle.setArcWidth(arcWidth);
        rectangle.setArcHeight(arcHeight);
        root.getChildren().add(rectangle);
        return rectangle;
    }

    public static Label buildLabel(Group root, String text, double x, double y, Font font, boolean visible, String setStyle) {
        Label label = new Label(text);
        label.relocate(x, y);
        label.setFont(font);
        label.setVisible(visible);
        root.getChildren().add(label);
        label.setStyle(setStyle);
        return label;
    }

    public TextField buildField(Group root, double x, double y, String setStyle) {
        TextField textField = new TextField();
        textField.relocate(x, y);
        textField.setStyle(setStyle);
        root.getChildren().add(textField);
        return textField;
    }

    public RadioButton buildRadioButton(Group root, String text, double x, double y, String setStyle, Font font, boolean visible) {
        RadioButton radioButton = new RadioButton(text);
        radioButton.relocate(x, y);
        radioButton.setStyle(setStyle);
        radioButton.setVisible(visible);
        radioButton.setFont(font);
        root.getChildren().add(radioButton);
        return radioButton;
    }

    public boolean isFieldFull(TextField field) {
        if (!field.getText().equals("")) {
            return true;
        }
        return false;
    }

    public int whatSubMenuIsOpen() {
        if (nameField.isVisible() && goPlay.isVisible()) return 0;
        else if (serverButton.isVisible() && clientButton.isVisible()) return 1;
        else if (onOrOffSound.isVisible() && sound.isVisible()) return 2;
        else if (scoreboardState) return 3;
        else return -1;
    }
}
