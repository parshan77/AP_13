package View;

import Model.Network.Packet.Profile;
import Model.Network.Server.Server;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class ServerViewer extends Application {
    private ArrayList<String> onlinePeople = new ArrayList<>();
    private ArrayList<String> ranks = new ArrayList<>();
    private ArrayList<String> messages = new ArrayList<>();

    private Server server;

    private TextField typingBox;
    private TextArea messageBox;
    private TextArea leaderboardBox;
    private TextArea onlinesBox;

    private HashMap<String, Profile> nameToProfileHashmap = new HashMap<>();

    public ServerViewer(Server server) {
        this.server = server;
        server.setServerViewer(this);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
        primaryStage.setTitle("FarmFrenzy");
        server.setup();

        ImageView backGround = showBackground(primaryStage, pane);
        Rectangle chatBackground = showChatBackground(pane, backGround);
        setupMessageBox(pane, backGround, chatBackground);
        showTypingBox(pane, backGround);
        showSendButton(pane, backGround);
        showOnlinesMenus(pane);
        showLeaderboardMenus(primaryStage, pane);


    }

    private void showLeaderboardMenus(Stage primaryStage, Pane pane) {
        Text leaderBoard = new Text("Leader Board");
        leaderBoard.setFont(Font.font("Arial Rounded MT Bold", 30));
        leaderBoard.setFill(Color.BLACK);
        leaderBoard.relocate(primaryStage.getWidth() - 300, 30);
        pane.getChildren().add(leaderBoard);

        leaderboardBox = new TextArea();
        leaderboardBox.relocate(primaryStage.getWidth() - 350, 100);
        leaderboardBox.setPrefSize(300, 400);
        leaderboardBox.setStyle("-fx-font-size: 20;-fx-background-color: lightgreen;");
        leaderboardBox.setEditable(false);
        pane.getChildren().addAll(leaderboardBox);
    }

    private void showOnlinesMenus(Pane pane) {
        Text title = new Text("Online People");
        title.setFill(Color.BLACK);
        title.setFont(Font.font("Arial Rounded MT Bold", 25));
        title.relocate(10, 10);
        pane.getChildren().add(title);

        onlinesBox = new TextArea();
        onlinesBox.relocate(20, 100);
        onlinesBox.setPrefSize(300, 400);
        onlinesBox.setStyle("-fx-font-size: 20;-fx-background-color: lightgreen;");
        onlinesBox.setEditable(false);
        pane.getChildren().addAll(onlinesBox);
    }

    private void showSendButton(Pane pane, ImageView backGround) {
        Button sendButton = new Button("Send");
        sendButton.relocate(backGround.getFitWidth() / 2 - 250 + 10 + 415, backGround.getFitHeight() / 2 - 200 + 340 + 20);
        sendButton.setPrefWidth(65);
        pane.getChildren().add(sendButton);

        sendButton.setOnMouseClicked(event ->{
            String text = typingBox.getText();
            typingBox.setText("");
            server.sendTextFromServer(text);
        });
    }

    private void showTypingBox(Pane pane, ImageView backGround) {
        typingBox = new TextField();
        typingBox.setPromptText("Type something...");
        typingBox.setPrefWidth(410);
        typingBox.relocate(backGround.getFitWidth() / 2 - 250 + 10, backGround.getFitHeight() / 2 - 200 + 340 + 20);
        pane.getChildren().add(typingBox);
    }

    private void setupMessageBox(Pane pane, ImageView backGround, Rectangle chatBackground) {
        messageBox = new TextArea();
        messageBox.relocate(backGround.getFitWidth() / 2 - 250 + 10, backGround.getFitHeight() / 2 - 200 + 10);
        messageBox.setPrefWidth(chatBackground.getWidth() - 20);
        messageBox.setPrefHeight(chatBackground.getHeight() - 60);
        messageBox.setEditable(false);
        pane.getChildren().add(messageBox);
    }

    private Rectangle showChatBackground(Pane pane, ImageView backGround) {
        Rectangle chatBackground = new Rectangle();
        chatBackground.relocate(backGround.getFitWidth() / 2 - 250, backGround.getFitHeight() / 2 - 200);
        chatBackground.setArcWidth(50);
        chatBackground.setArcHeight(50);
        chatBackground.setFill(Color.LIGHTBLUE);
        chatBackground.setHeight(400);
        chatBackground.setWidth(500);
        pane.getChildren().add(chatBackground);
        return chatBackground;
    }

    private ImageView showBackground(Stage primaryStage, Pane pane) {
        Image back = new Image("file:Textures\\pictures\\background 2.jpg");
        ImageView backGround = new ImageView(back);
        backGround.setFitWidth(primaryStage.getWidth());
        backGround.setFitHeight(primaryStage.getHeight());
        backGround.setPreserveRatio(false);
        pane.getChildren().add(backGround);
        return backGround;
    }


    private void showOnlines() {
        onlinesBox.setText("");
        for (int i = 0; i < onlinePeople.size(); i++) {
            onlinesBox.setText(onlinesBox.getText() + onlinePeople.get(i) + "\n");
        }
    }

    public void showMessage(String message) {
        messages.add(message);
        String s = messageBox.getText() + "\n" + message;
        messageBox.setText(s);
    }

    public void addNameToOnlines(String username) {
        // TODO: 2/3/2019
    }

    public void discardFromOnlinesList(String username) {
        onlinePeople.remove(username);
        showOnlines();
    }

    public void discardFromLeaderBoard(String username) {
        ranks.remove(username);
        showLeaderBoard();
    }

    public void sortLeaderBoard() {
        leaderboardBox.setText("");
    }

    private void showLeaderBoard() {
        leaderboardBox.setText("");
        for (String rank : ranks) {
            leaderboardBox.setText(leaderboardBox.getText() + rank + "\n");
        }
    }

    public void addToLeaderBoard(String username, Profile profile) {

    }
}