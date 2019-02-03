package View;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;

public class ServerView extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        Image back = new Image("file:Textures\\pictures\\background 2.jpg");
        ImageView backGround = new ImageView(back);
        backGround.setFitWidth(Screen.getPrimary().getVisualBounds().getWidth());
        backGround.setFitHeight(Screen.getPrimary().getVisualBounds().getHeight());
        pane.getChildren().add(backGround);
        ArrayList<String> onlinePeople = new ArrayList<>();
        ArrayList<String> messages = new ArrayList<>();
        ArrayList<String> ranks = new ArrayList<>();

        Text title = new Text("Online People :");
        title.setFill(Color.BLACK);
        title.setFont(Font.font("Arial Rounded MT Bold", 30));
        title.relocate(10, 10);
        pane.getChildren().add(title);

        for (int i = 0; i < onlinePeople.size(); i++) {
            Text name = new Text(onlinePeople.get(i));
            name.setFill(Color.BLACK);
            name.setFont(Font.font("Arial Rounded MT Bold", 25));
            name.relocate(250, 15 + 30 * i);
            pane.getChildren().add(name);
        }


        Rectangle rectangle = new Rectangle();
        rectangle.relocate(backGround.getFitWidth() / 2 - 250, backGround.getFitHeight() / 2 - 400);
        rectangle.setArcWidth(50);
        rectangle.setArcHeight(50);
        rectangle.setFill(Color.LIGHTBLUE);
        rectangle.setHeight(400);
        rectangle.setWidth(500);
        pane.getChildren().add(rectangle);

        TextArea textArea = new TextArea();
        textArea.relocate(backGround.getFitWidth() / 2 - 250 + 10, backGround.getFitHeight() / 2 - 400 + 10);
        textArea.setPrefWidth(rectangle.getWidth() - 20);
        textArea.setPrefHeight(rectangle.getHeight() - 60);
        pane.getChildren().add(textArea);

        TextField textField = new TextField();
        textField.setPromptText("Type something...");
        textField.setPrefWidth(410);
        textField.relocate(backGround.getFitWidth() / 2 - 250 + 10, backGround.getFitHeight() / 2 - 400 + 340 +20);
        pane.getChildren().add(textField);

        Button button = new Button("Send");
        button.relocate(backGround.getFitWidth() / 2 - 250 + 10 + 415, backGround.getFitHeight() / 2 - 400 + 340 +20);
        button.setPrefWidth(65);
        pane.getChildren().add(button);

        for (int i = 0; i < messages.size(); i++) {
            textArea.setText(textArea.getText() + messages.get(i) + "\n");
        }

        Text leagerBoard = new Text("Leader Board :");
        leagerBoard.setFont(Font.font("Arial Rounded MT Bold", 30));
        leagerBoard.setFill(Color.BLACK);
        leagerBoard.relocate(backGround.getFitWidth() - 500, 10);
        pane.getChildren().add(leagerBoard);

        for (int i = 0; i < ranks.size(); i++) {
            Text name = new Text(ranks.get(i));
            name.relocate(backGround.getFitWidth() - 200, 15 + 30 * i);
            name.setFill(Color.BLACK);
            name.setFont(Font.font("Arial Rounded MT Bold", 25));
            pane.getChildren().add(name);
        }

        primaryStage.setFullScreen(true);
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
