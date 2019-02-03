package View;

import Model.Identity.Game;
import Model.Network.Client.Client;
import Model.Network.Server.Server;
import View.Animations.SpriteAnimation;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private GamePlayView gamePlayView;
    private LevelSelectionViewer levelSelectionViewer;

    private Stage stage;
    private Group root;
    private ImageView background;
    private ImageView singlePlayerButton;
    private ImageView multiPlayerButton;
    private ImageView optionsButton;
    private ImageView scoreBoardButton;
    private ImageView exit;
    private ImageView sheep;
    private ImageView hen;
    private ImageView hen2;
    private MediaPlayer birdSound;
    private ImageView logo;
    private ImageView cloud;
    private Label soundLabel;
    private Rectangle subMenusBox;
    private TextField nameField;
    private Label enterYourNameLabel;
    private ImageView subMenuClose;
    private ImageView playSinglePlayer;
    private RadioButton clientButton;
    private RadioButton serverButton;
    private ImageView onOrOffSound;
    boolean scoreboardState = false;

    public MenuView() {
        this.game = Game.getInstance();
        levelSelectionViewer = new LevelSelectionViewer();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
        this.stage = primaryStage;

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

        enterYourNameLabel = buildLabel(root, "Enter Your Name",
                0.34 * primaryStage.getWidth(), 0.3 * primaryStage.getHeight(),
                Font.font(40), false, "-fx-font-weight: bold");

        playSinglePlayer = buildImageView(root, "File:Textures\\MenuResources\\goPlay.png",
                0.55 * primaryStage.getWidth(), 0.45 * primaryStage.getHeight(),
                50, 50,
                false);



        String url = "File:Textures\\MenuResources\\goPlay.png";
        Image image = new Image(url);
        ImageView playMultiplayerButton = new ImageView(image);
        playMultiplayerButton.setFitHeight(40);
        playMultiplayerButton.relocate(0.6 * primaryStage.getWidth(), 0.5* primaryStage.getHeight());
        playMultiplayerButton.setVisible(false);
        playMultiplayerButton.setPreserveRatio(true);
        root.getChildren().addAll(playMultiplayerButton);

        nameField = buildField(root, 0.35 * primaryStage.getWidth(), 0.43 * primaryStage.getHeight(),
                "-fx-font-weight: bold; -fx-background-color: #ff6528");
        nameField.setOnKeyTyped(event -> {
            if (isFieldFull(nameField)) {
                playSinglePlayer.setVisible(true);
            } else {
                playSinglePlayer.setVisible(false);
            }
        });
        nameField.setVisible(false);

/////////////////////////////////////////////// what is in multiPlayer
        clientButton = buildRadioButton(root, "Client",
                0.35 * primaryStage.getWidth(), 0.3 * primaryStage.getHeight(),
                "-fx-background-color: #ff6528",
                Font.font(30), false);
        serverButton = buildRadioButton(root, "Server",
                0.35 * primaryStage.getWidth(), 0.4 * primaryStage.getHeight(),
                "-fx-background-color: #ff6528",
                Font.font(30), false);


        Label serverIpLabel = new Label("Server IP : 127.0.0.1");
        TextField serverPortTextField = new TextField("8050");
        serverIpLabel.relocate(subMenusBox.getX() + subMenusBox.getWidth() / 2 + 50,
                subMenusBox.getY() + subMenusBox.getHeight() / 2 + 100);
        serverIpLabel.setStyle("-fx-background-color: white");
        serverPortTextField.relocate(subMenusBox.getX() + subMenusBox.getWidth() / 2 - 200,
                subMenusBox.getY() + subMenusBox.getHeight() / 2 + 100);
        root.getChildren().addAll(serverIpLabel, serverPortTextField);
        serverIpLabel.setVisible(false);
        serverPortTextField.setVisible(false);

        TextField clientPortTextField = new TextField("8050");
        clientPortTextField.relocate(subMenusBox.getX() + subMenusBox.getWidth() / 2 + 50,
                subMenusBox.getY() + subMenusBox.getHeight() / 2 + 100);
        clientPortTextField.setStyle("-fx-background-color: white");
        clientPortTextField.relocate(subMenusBox.getX() + subMenusBox.getWidth() / 2 - 200,
                subMenusBox.getY() + subMenusBox.getHeight() / 2 + 100);
        root.getChildren().addAll(clientPortTextField);
        clientPortTextField.setVisible(false);

        clientButton.setOnMouseClicked(event -> {
            if (serverButton.isSelected()) {
                serverButton.fire();
                serverIpLabel.setVisible(true);
                serverPortTextField.setVisible(true);
                clientPortTextField.setVisible(false);
            }
        });
        serverButton.setOnMouseClicked(event -> {
            if (clientButton.isSelected()) {
                clientButton.fire();
                serverPortTextField.setVisible(false);
                clientPortTextField.setVisible(true);
            }
        });
        serverButton.fire();

/////////////////////////////////////////////// what is in optionsButton
        final boolean[] soundState = {true};

        soundLabel = buildLabel(root, "soundLabel",
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

        subMenuClose = buildImageView(root, "File:Textures\\MenuResources\\exitSmallMenu.png",
                primaryStage.getWidth() * 0.7, primaryStage.getHeight() * 0.25,
                40, 40, false);

        showSinglePlayerButton();
        showMultiPlayerButton();
        showOptionsButton();
        showScoreBoardButton();
        showExitButton();

        playSinglePlayer.setOnMouseClicked(event -> {
            try {
                levelSelectionViewer.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        playMultiplayerButton.setOnMouseClicked(event -> {
            if (serverButton.isSelected()) {
                int port = Integer.parseInt(serverPortTextField.getText());
                Server server = new Server(port);
                // TODO: 2/3/2019 server Viewer
            } else {
                String host = serverIpLabel.getText();
                int port = Integer.parseInt(clientPortTextField.getText());
                Client client = new Client(port, host);
            }
        });

        singlePlayerButton.setOnMouseClicked(event -> {
            showSinglePlayerSubMenu();
        });

        multiPlayerButton.setOnMouseClicked(event -> {
            showMultiPlayerSubMenu();
            playMultiplayerButton.setVisible(true);
            playSinglePlayer.setVisible(false);
            serverIpLabel.setVisible(true);
            serverPortTextField.setVisible(true);
        });

        optionsButton.setOnMouseClicked(event -> {
            showOptionSubMenu();
        });

        scoreBoardButton.setOnMouseClicked(event -> {
            showScoreBoardSubMenu();
        });

        subMenuClose.setOnMouseClicked(event -> {
            closeSinglePlayer();
            closeMultiPlayer();
            closeOptions();
            closeScoreBoard();
            playSinglePlayer.setVisible(false);
            playMultiplayerButton.setVisible(false);
            serverIpLabel.setVisible(false);
            serverPortTextField.setVisible(false);
            clientPortTextField.setVisible(false);
        });
    }

    private void showBackground() {
        Image backGroundImg = new Image("File:Textures\\MenuResources\\backg.jpg");
        background = new ImageView(backGroundImg);
        background.setFitHeight(stage.getHeight());
        background.setFitWidth(stage.getWidth());
        background.setPreserveRatio(false);
        root.getChildren().add(background);
    }

    private void showSinglePlayerButton() {
        double buttonsWidth = 0.2 * stage.getWidth();
        double buttonsHeight = 0.1 * stage.getHeight();
        singlePlayerButton = buildImageView(root, "File:Textures\\MenuResources\\singlePlayer.png",
                0.78 * stage.getWidth(), 0.1 * stage.getHeight(),
                buttonsWidth, buttonsHeight,
                true);
        singlePlayerButton.setOnMouseEntered(event -> {
            becomeBigger(singlePlayerButton);
        });
        singlePlayerButton.setOnMouseExited(event -> {
            becomeSmaller(singlePlayerButton);
        });
    }

    private void showMultiPlayerButton() {
        double buttonsWidth = 0.2 * stage.getWidth();
        double buttonsHeight = 0.1 * stage.getHeight();
        multiPlayerButton = buildImageView(root, "File:Textures\\MenuResources\\multiPlayer.png",
                0.78 * stage.getWidth(), 2.5 * buttonsHeight,
                buttonsWidth, buttonsHeight,
                true);
        multiPlayerButton.setOnMouseEntered(event -> {
            becomeBigger(multiPlayerButton);
        });
        multiPlayerButton.setOnMouseExited(event -> {
            becomeSmaller(multiPlayerButton);
        });

    }

    private void showExitButton() {
        double buttonsWidth = 0.2 * stage.getWidth();
        double buttonsHeight = 0.1 * stage.getHeight();

        exit = buildImageView(root, "File:Textures\\MenuResources\\exit.png",
                0.78 * stage.getWidth(), 7 * buttonsHeight,
                buttonsWidth, buttonsHeight,
                true);
        exit.setOnMouseEntered(event -> {
            becomeBigger(exit);
        });
        exit.setOnMouseExited(event -> {
            becomeSmaller(exit);
        });
        exit.setOnMouseClicked(event -> {
            stage.close();
        });

    }

    private void showOptionsButton() {
        double buttonsWidth = 0.2 * stage.getWidth();
        double buttonsHeight = 0.1 * stage.getHeight();

        optionsButton = buildImageView(root, "File:Textures\\MenuResources\\options.png",
                0.78 * stage.getWidth(), 4 * buttonsHeight,
                buttonsWidth, buttonsHeight,
                true);
        optionsButton.setOnMouseEntered(event -> {
            becomeBigger(optionsButton);
        });
        optionsButton.setOnMouseExited(event -> {
            becomeSmaller(optionsButton);
        });
    }

    private void showScoreBoardButton() {
        double buttonsWidth = 0.2 * stage.getWidth();
        double buttonsHeight = 0.1 * stage.getHeight();

        scoreBoardButton = buildImageView(root, "File:Textures\\MenuResources\\scoreBoard.png",
                0.78 * stage.getWidth(), 5.5 * buttonsHeight,
                buttonsWidth, buttonsHeight,
                true);
        scoreBoardButton.setOnMouseEntered(event -> {
            becomeBigger(scoreBoardButton);
        });
        scoreBoardButton.setOnMouseExited(event -> {
            becomeSmaller(scoreBoardButton);
        });

    }

    private void showSinglePlayerSubMenu() {
        closeMultiPlayer();
        closeOptions();
        closeScoreBoard();
        openSinglePlayer();
    }

    private void showMultiPlayerSubMenu() {
        closeSinglePlayer();
        closeOptions();
        closeScoreBoard();
        openMultiPlayer();
    }

    private void showOptionSubMenu() {
        closeSinglePlayer();
        closeMultiPlayer();
        closeScoreBoard();
        openOptions();
    }

    private void showScoreBoardSubMenu() {
        closeSinglePlayer();
        closeMultiPlayer();
        closeOptions();
        openScoreBoard();
    }

    private void closeSinglePlayer() {
        subMenusBox.setVisible(false);
        subMenuClose.setVisible(false);
        nameField.setVisible(false);
        playSinglePlayer.setVisible(false);
    }

    private void closeMultiPlayer() {
        subMenusBox.setVisible(false);
        subMenuClose.setVisible(false);
        serverButton.setVisible(false);
        clientButton.setVisible(false);
    }

    private void closeOptions() {
        subMenusBox.setVisible(false);
        subMenuClose.setVisible(false);
        onOrOffSound.setVisible(false);
        soundLabel.setVisible(false);
    }

    private void closeScoreBoard() {
        subMenusBox.setVisible(false);
        subMenuClose.setVisible(false);
        scoreboardState = false;
    }

    private void openSinglePlayer() {
        subMenusBox.setVisible(true);
        subMenuClose.setVisible(true);
        nameField.setVisible(true);
        playSinglePlayer.setVisible(true);
    }

    private void openMultiPlayer() {
        subMenusBox.setVisible(true);
        subMenuClose.setVisible(true);
        serverButton.setVisible(true);
        clientButton.setVisible(true);
    }

    private void openOptions() {
        subMenusBox.setVisible(true);
        subMenuClose.setVisible(true);
        onOrOffSound.setVisible(true);
    }

    private void openScoreBoard() {
        scoreboardState = true;
    }

    private void showHens() {
        hen = buildImageView(root, "File:Textures\\MenuResources\\hen.png",
                170, 620,
                74, 64,
                true);

        hen2 = buildImageView(root, "File:Textures\\MenuResources\\hen.png",
                80, 620,
                74, 64,
                true);
        hen2.setScaleX(-1);

        Animation henAnimation = new SpriteAnimation(hen, Duration.millis(1000),
                24, 5,
                0, 0,
                74, 64);
        henAnimation.setAutoReverse(true);
        henAnimation.setCycleCount(Animation.INDEFINITE);
        henAnimation.play();

        Animation hen2Animation = new SpriteAnimation(hen2, Duration.millis(1200),
                24, 5,
                0, 0,
                74, 64);
        hen2Animation.setAutoReverse(true);
        hen2Animation.setCycleCount(Animation.INDEFINITE);
        hen2Animation.play();

    }

    private void showSheep() {
        sheep = buildImageView(root, "File:Textures\\MenuResources\\sheep.png",
                570, 560,
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

    private void showLogo() {
        String url = "File:Textures\\MenuResources\\logo.png";
        Image logoImg = new Image(url);
        logo = new ImageView(logoImg);
        logo.setPreserveRatio(true);
        logo.setFitHeight(250);
        logo.relocate((stage.getWidth() / 2) - (logoImg.getWidth() / 2),
                (stage.getHeight() / 2) - (logoImg.getHeight() / 2) - 200);
        root.getChildren().add(logo);
    }

    private void showCloud() {
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

    private void playBirdSound() {
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

    static void becomeBigger(ImageView view) {
        view.setFitWidth(view.getFitWidth() * 11.0 / 10);
        view.setFitHeight(view.getFitHeight() * 11.0 / 10);

    }

    static void becomeSmaller(ImageView view) {
        view.setFitWidth(view.getFitWidth() * 10.0 / 11);
        view.setFitHeight(view.getFitHeight() * 10.0 / 11);
    }

    static ImageView buildImageView(Group root, String uri,
                                    double x, double y, double width, double height, boolean visible) {
        Image image = new Image(String.valueOf(uri));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setVisible(visible);
        imageView.relocate(x, y);
        root.getChildren().add(imageView);
        return imageView;
    }

    private MediaPlayer buildMedia(Group root, String uri) {
        File file = new File(uri);
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        return mediaPlayer;
    }

    private Rectangle buildRectangle(Group root, double x, double y, double width, double height, Color color, boolean visible,
                                     int arcWidth, int arcHeight) {
        Rectangle rectangle = new Rectangle(x, y, width, height);
        rectangle.setFill(color);
        rectangle.setVisible(visible);
        rectangle.setArcWidth(arcWidth);
        rectangle.setArcHeight(arcHeight);
        root.getChildren().add(rectangle);
        return rectangle;
    }

    static Label buildLabel(Group root, String text, double x, double y, Font font, boolean visible, String setStyle) {
        Label label = new Label(text);
        label.relocate(x, y);
        label.setFont(font);
        label.setVisible(visible);
        root.getChildren().add(label);
        label.setStyle(setStyle);
        return label;
    }

    private TextField buildField(Group root, double x, double y, String setStyle) {
        TextField textField = new TextField();
        textField.relocate(x, y);
        textField.setStyle(setStyle);
        root.getChildren().add(textField);
        return textField;
    }

    private RadioButton buildRadioButton(Group root, String text, double x, double y, String setStyle, Font font, boolean visible) {
        RadioButton radioButton = new RadioButton(text);
        radioButton.relocate(x, y);
        radioButton.setStyle(setStyle);
        radioButton.setVisible(visible);
        radioButton.setFont(font);
        root.getChildren().add(radioButton);
        return radioButton;
    }

    private boolean isFieldFull(TextField field) {
        if (!field.getText().equals("")) {
            return true;
        }
        return false;
    }
}
