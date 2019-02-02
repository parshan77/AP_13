package View;

import Model.Identity.Game;
import View.Animations.SpriteAnimation;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.Event;
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

    public MenuView(Game game) {
        this.game = game;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Group root = new Group();
        Scene startMenuScene = new Scene(root);
        primaryStage.setScene(startMenuScene);
        primaryStage.setFullScreen(true);
        primaryStage.show();


        Image backGroundImg = new Image("File:Textures\\MenuResources\\backg.jpg");
        ImageView backgroundImageView = new ImageView(backGroundImg);
        backgroundImageView.setFitHeight(primaryStage.getHeight());
        backgroundImageView.setFitWidth(primaryStage.getWidth());
        backgroundImageView.setPreserveRatio(false);
        root.getChildren().add(backgroundImageView);

//        ImageView logo = buildImageView(root, "File:Textures\\MenuResources\\logo.png",
//                500, 20,
//                400, 150,
//                true);

        String url = "File:Textures\\MenuResources\\logo.png";
        Image logoImg = new Image(url);
        ImageView logo = new ImageView(logoImg);
        logo.setPreserveRatio(true);
        logo.setFitHeight(250);
        logo.relocate((primaryStage.getWidth() / 2) - (logoImg.getWidth() / 2),
                (primaryStage.getHeight() / 2) - (logoImg.getHeight() / 2) - 200);
        root.getChildren().add(logo);

        ImageView cloud = buildImageView(root, "File:Textures\\MenuResources\\cloud.png",
                0, -100,
                300, 300,
                true);

        PathTransition pathTransition = new PathTransition(Duration.millis(50000),
                new Path(new MoveTo(0, 300), new LineTo(1580, 300)),
                cloud);
        pathTransition.setAutoReverse(true);
        pathTransition.setCycleCount(Integer.MAX_VALUE);
        pathTransition.play();

        ImageView hen = buildImageView(root, "File:Textures\\MenuResources\\hen.png",
                170, 620,
                74, 64,
                true);

        ImageView hen2 = buildImageView(root, "File:Textures\\MenuResources\\hen.png",
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

        ImageView sheep = buildImageView(root, "File:Textures\\MenuResources\\sheep.png",
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

        MediaPlayer soundPlayer = buildMedia(root, "Textures\\MenuResources\\birdSound.mp3");
        soundPlayer.setAutoPlay(true);
        soundPlayer.setCycleCount(Integer.MAX_VALUE);
        soundPlayer.play();

        Rectangle box = buildRectangle(root,
                0.3 * primaryStage.getWidth(), 0.2 * primaryStage.getHeight(),
                0.45 * primaryStage.getWidth(), 0.6 * primaryStage.getHeight(),
                Color.BLUE, false, 100, 100);
        box.setOpacity(0.6);

        final boolean[] buttonsBaz = {true, true, true, true};
////////////////////////////////////////////////////////// what is in singlePlay
        Label enterYourName = buildLabel(root, "Enter Your Name",
                0.34 * primaryStage.getWidth(), 0.3 * primaryStage.getHeight(),
                Font.font(40), false, "-fx-font-weight: bold");

        ImageView goPlayButton = buildImageView(root, "File:Textures\\MenuResources\\goPlay.png",
                0.55 * primaryStage.getWidth(), 0.45 * primaryStage.getHeight(),
                50, 50,
                false);
        goPlayButton.setOnMouseClicked(event -> {
        });


        TextField nameField = buildField(root, 0.35 * primaryStage.getWidth(), 0.43 * primaryStage.getHeight(),
                "-fx-font-weight: bold; -fx-background-color: #ff6528");
        nameField.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (isFieldFull(nameField)) {
                    goPlayButton.setVisible(true);
                } else {
                    goPlayButton.setVisible(false);
                }
            }
        });
        nameField.setVisible(false);

        /*ImageView recentUsersButton = buildImageView(root, "File:Textures\\MenuResources\\recentUsers.png",
                0.35*primaryStage.getWidth(),0.53*primaryStage.getHeight(),
                150,50,
                false);
        recentUsersButton.setOnMouseEntered(event -> {
            becomeBigger(recentUsersButton);
        });
        recentUsersButton.setOnMouseExited(event -> {
            becomeSmaller(recentUsersButton);
        });
        recentUsersButton.setOnMouseClicked(event -> { });*/

/////////////////////////////////////////////// what is in multiPlay
        RadioButton clientRadioButton = buildRadioButton(root, "Client",
                0.35 * primaryStage.getWidth(), 0.3 * primaryStage.getHeight(),
                "-fx-background-color: #ff6528",
                Font.font(30), false);
        RadioButton serverRadioButton = buildRadioButton(root, "Server",
                0.35 * primaryStage.getWidth(), 0.4 * primaryStage.getHeight(),
                "-fx-background-color: #ff6528",
                Font.font(30), false);
        clientRadioButton.setOnMouseClicked(event -> {
            if (serverRadioButton.isSelected()) {
                serverRadioButton.fire();
            }
        });
        serverRadioButton.setOnMouseClicked(event -> {
            if (clientRadioButton.isSelected()) {
                clientRadioButton.fire();
            }
        });

/////////////////////////////////////////////// what is in options
        final boolean[] soundState = {true};

        Label sound = buildLabel(root, "sound",
                0.35 * primaryStage.getWidth(), 0.3 * primaryStage.getHeight(),
                Font.font(40), false, "-fx-font-weight: bold");

        Image onImage = new Image("File:Textures\\MenuResources\\on.png");
        Image offImage = new Image("File:Textures\\MenuResources\\off.png");
        ImageView onOffButton = buildImageView(root, "File:Textures\\MenuResources\\off.png",
                0.35 * primaryStage.getWidth(), 0.4 * primaryStage.getHeight(),
                0.14 * primaryStage.getWidth(), 0.07 * primaryStage.getHeight(),
                false);

        onOffButton.setOnMouseClicked(event -> {
            if (soundState[0]) {
                soundPlayer.pause();
                onOffButton.setImage(onImage);
                soundState[0] = false;
            } else {
                soundPlayer.play();
                onOffButton.setImage(offImage);
                soundState[0] = true;
            }
        });
        //////////////////////////////////////////////////
        ImageView exitSmallMenu = buildImageView(root, "File:Textures\\MenuResources\\exitSmallMenu.png",
                primaryStage.getWidth() * 0.7, primaryStage.getHeight() * 0.25,
                40, 40, false);
///////////////////////////////////////////////
        double buttonsWidth = 0.2 * primaryStage.getWidth();
        double buttonsHeight = 0.1 * primaryStage.getHeight();
        ImageView singlePlayButton = buildImageView(root, "File:Textures\\MenuResources\\singlePlayer.png",
                0.78 * primaryStage.getWidth(), 0.1 * primaryStage.getHeight(),
                buttonsWidth, buttonsHeight,
                true);
        singlePlayButton.setOnMouseEntered(event -> {
            becomeBigger(singlePlayButton);
        });
        singlePlayButton.setOnMouseExited(event -> {
            becomeSmaller(singlePlayButton);
        });

        ImageView multiPlayButton = buildImageView(root, "File:Textures\\MenuResources\\multiPlayer.png",
                0.78 * primaryStage.getWidth(), 2.5 * buttonsHeight,
                buttonsWidth, buttonsHeight,
                true);
        multiPlayButton.setOnMouseEntered(event -> {
            becomeBigger(multiPlayButton);
        });
        multiPlayButton.setOnMouseExited(event -> {
            becomeSmaller(multiPlayButton);
        });

        ImageView optionButton = buildImageView(root, "File:Textures\\MenuResources\\options.png",
                0.78 * primaryStage.getWidth(), 4 * buttonsHeight,
                buttonsWidth, buttonsHeight,
                true);
        optionButton.setOnMouseEntered(event -> {
            becomeBigger(optionButton);
        });
        optionButton.setOnMouseExited(event -> {
            becomeSmaller(optionButton);
        });

        ImageView scoreBoardButton = buildImageView(root, "File:Textures\\MenuResources\\scoreBoard.png",
                0.78 * primaryStage.getWidth(), 5.5 * buttonsHeight,
                buttonsWidth, buttonsHeight,
                true);
        scoreBoardButton.setOnMouseEntered(event -> {
            becomeBigger(scoreBoardButton);
        });
        scoreBoardButton.setOnMouseExited(event -> {
            becomeSmaller(scoreBoardButton);
        });


        ImageView[] buttonsViews = new ImageView[4];
        buttonsViews[0] = singlePlayButton;
        buttonsViews[1] = multiPlayButton;
        buttonsViews[2] = optionButton;
        buttonsViews[3] = scoreBoardButton;

        singlePlayButton.setOnMouseClicked(event -> {
            closeOpenButtons(buttonsBaz, 0, buttonsViews);
            nameField.setVisible(buttonsBaz[0]);
            enterYourName.setVisible(buttonsBaz[0]);
            box.setVisible(buttonsBaz[0]);
//            recentUsersButton.setVisible(buttonsBaz[0]);
            exitSmallMenu.setVisible(buttonsBaz[0]);
            if (!buttonsBaz[0]) {
                goPlayButton.setVisible(buttonsBaz[0]);
            }
            nameField.clear();
            buttonsBaz[0] = !buttonsBaz[0];
        });

        multiPlayButton.setOnMouseClicked(event -> {
            closeOpenButtons(buttonsBaz, 1, buttonsViews);
            box.setVisible(buttonsBaz[1]);
            serverRadioButton.setVisible(buttonsBaz[1]);
            clientRadioButton.setVisible(buttonsBaz[1]);
            exitSmallMenu.setVisible(buttonsBaz[1]);
            buttonsBaz[1] = !buttonsBaz[1];
        });

        optionButton.setOnMouseClicked(event -> {
            closeOpenButtons(buttonsBaz, 2, buttonsViews);
            sound.setVisible(buttonsBaz[2]);
            onOffButton.setVisible(buttonsBaz[2]);
            box.setVisible(buttonsBaz[2]);
            exitSmallMenu.setVisible(buttonsBaz[2]);
            buttonsBaz[2] = !buttonsBaz[2];
        });

        scoreBoardButton.setOnMouseClicked(event -> {
            closeOpenButtons(buttonsBaz, 3, buttonsViews);
        });


        exitSmallMenu.setOnMouseClicked(event -> {
            closeOpenButtons(buttonsBaz, 0, buttonsViews);
            closeOpenButtons(buttonsBaz, 1, buttonsViews);
        });


        ImageView exitButton = buildImageView(root, "File:Textures\\MenuResources\\exit.png",
                0.78 * primaryStage.getWidth(), 7 * buttonsHeight,
                buttonsWidth, buttonsHeight,
                true);
        exitButton.setOnMouseEntered(event -> {
            becomeBigger(exitButton);
        });
        exitButton.setOnMouseExited(event -> {
            becomeSmaller(exitButton);
        });
        exitButton.setOnMouseClicked(event -> {
            primaryStage.close();
        });

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
}
