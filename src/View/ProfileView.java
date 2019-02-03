package View;

import Model.Network.Packet.Profile;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ProfileView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Profile profile = new Profile();
        profile.getFriends().add("Ali");
        profile.getFriends().add("mammad");
        GridPane gridPane = new GridPane();
        Image back = new Image("file:Textures\\pictures\\farm.png");
        ImageView backView = new ImageView(back);
        backView.setFitHeight(back.getHeight());
        backView.setFitWidth(back.getWidth());
        backView.setFitHeight(Screen.getPrimary().getVisualBounds().getHeight());
        backView.setFitWidth(Screen.getPrimary().getVisualBounds().getWidth());
        root.getChildren().add(backView);

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        Text nameText = new Text("Name : ");
        nameText.setFont(Font.font("Bernard MT Condensed", 30));
        nameText.setFill(Color.BLACK);
        gridPane.add(nameText, 0, 0);

        Text name = new Text(profile.getName());
        name.setFill(Color.BLACK);
        name.setFont(Font.font("Arial Rounded MT Bold", 25));
        gridPane.add(name, 1, 0);

        Text unText = new Text("User name : ");
        unText.setFont(Font.font("Bernard MT Condensed", 30));
        unText.setFill(Color.BLACK);
        gridPane.add(unText, 0, 1);

        Text un = new Text(profile.getUsername());
        un.setFill(Color.BLACK);
        un.setFont(Font.font("Arial Rounded MT Bold", 25));
        gridPane.add(un, 1, 1);

        Text tradesText = new Text("Trades : ");
        tradesText.setFont(Font.font("Bernard MT Condensed", 30));
        tradesText.setFill(Color.BLACK);
        gridPane.add(tradesText, 0, 2);

        Text numberOfTrades = new Text(String.valueOf(profile.getNumberOfTrades()));
        numberOfTrades.setFill(Color.BLACK);
        numberOfTrades.setFont(Font.font("Arial Rounded MT Bold", 25));
        gridPane.add(numberOfTrades, 1, 2);

        Text commonGames = new Text("Common Games : ");
        commonGames.setFont(Font.font("Bernard MT Condensed", 30));
        commonGames.setFill(Color.BLACK);
        gridPane.add(commonGames, 0, 3);

        Text numberOfCommonGames = new Text(String.valueOf(profile.getNumbrOfCommonGames()));
        numberOfCommonGames.setFill(Color.BLACK);
        numberOfCommonGames.setFont(Font.font("Arial Rounded MT Bold", 25));
        gridPane.add(numberOfCommonGames, 1, 3);

        Text friends = new Text("Friends : ");
        friends.setFont(Font.font("Bernard MT Condensed", 30));
        friends.setFill(Color.BLACK);
        gridPane.add(friends, 2, 0);

        boolean[] isChatOpen = new boolean[profile.getFriends().size()];
        for (int i = 0; i < isChatOpen.length; i++) {
            isChatOpen[i] = false;
        }

        for (int i = 0; i < profile.getFriends().size(); i++) {
            Text friend = new Text(profile.getFriends().get(i));
            friend.setFill(Color.BLACK);
            friend.setFont(Font.font("Arial Rounded MT Bold", 25));
            gridPane.add(friend, 3, i);

            friend.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    friend.setFill(Color.RED);
                    friend.setFont(Font.font("Arial Rounded MT Bold", 30));
                }
            });

            friend.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    friend.setFill(Color.BLACK);
                    friend.setFont(Font.font("Arial Rounded MT Bold", 25));
                }
            });

            final int index = i;
            friend.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (!isChatOpen[index]) {
                        Image cross = new Image("file:Textures\\pictures\\exit.png");
                        ImageView crossView = new ImageView(cross);
                        crossView.setFitWidth(10);
                        crossView.setFitHeight(10);
                        crossView.relocate(primaryStage.getWidth() - 500, 0);

                        Button button = new Button("Send");
                        button.relocate(primaryStage.getWidth() - 160, 270);
                        button.setPrefWidth(50);

                        Rectangle rectangle = new Rectangle(primaryStage.getWidth() - 500, 0, 400, 300);
                        rectangle.setFill(Color.LIGHTBLUE);

                        TextArea textArea = new TextArea();
                        textArea.relocate(primaryStage.getWidth() - 490, 10);
                        textArea.setPrefHeight(250);
                        textArea.setPrefWidth(380);

                        TextField textField = new TextField();
                        textField.relocate(primaryStage.getWidth() - 490, 270);
                        textField.setPromptText("Type something...");
                        textField.setPrefWidth(325);

                        isChatOpen[index] = true;
                        root.getChildren().add(rectangle);
                        root.getChildren().add(textField);
                        root.getChildren().add(textArea);
                        root.getChildren().add(button);
                        root.getChildren().add(crossView);

                        crossView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                root.getChildren().removeAll(rectangle, textArea, textField, button);
                                isChatOpen[index] = false;
                            }
                        });


                    }

                }
            });


        }


        root.getChildren().add(gridPane);
        primaryStage.setFullScreen(true);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
