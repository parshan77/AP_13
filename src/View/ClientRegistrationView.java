package View;

import Model.Network.Client.Client;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class ClientRegistrationView extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private Client client;

    public ClientRegistrationView(Client client) {
        this.client = client;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene loginScene = new Scene(root);

        primaryStage.setScene(loginScene);
        primaryStage.setTitle("FarmFrenzy");
        primaryStage.setFullScreen(true);
        primaryStage.show();

        Image backgroundimg = new Image("file:Textures\\MenuResources\\backg.jpg");
        ImageView backgroundview = new ImageView(backgroundimg);
        backgroundview.relocate(0, 0);
        backgroundview.setPreserveRatio(false);
        backgroundview.setFitHeight(primaryStage.getHeight());
        backgroundview.setFitWidth(primaryStage.getWidth());
        root.getChildren().add(backgroundview);

        Rectangle backBox = new Rectangle(primaryStage.getWidth() / 2 - 300, 200, 500, 300);
        backBox.setOpacity(0.5);
        backBox.setFill(Color.BEIGE);
        root.getChildren().add(backBox);

        Text nameLabel = new Text("Name:");
        nameLabel.setFill(Color.BLACK);
        TextField nameField = new TextField();

        Text usernameLabel = new Text("Username:");
        usernameLabel.setFill(Color.BLACK);
        TextField usernameField = new TextField();

        Text moneyLabel = new Text("Money:");
        moneyLabel.setFill(Color.BLACK);
        TextField moneyField = new TextField();

        Text passedLevelsLabel = new Text("numberOfPassedLevels:");
        passedLevelsLabel.setFill(Color.BLACK);
        TextField passedLevelsField = new TextField();

        Button enterButton = new Button("Enter");
        Button clearButton = new Button("Clear");
        enterButton.setPrefSize(70, 40);
        clearButton.setPrefSize(70,40);
        enterButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        clearButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(primaryStage.getWidth() - 100, 700);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(usernameLabel, 0, 1);
        gridPane.add(usernameField, 1, 1);
        gridPane.add(moneyLabel, 0, 2);
        gridPane.add(moneyField, 1, 2);
        gridPane.add(passedLevelsLabel, 0, 3);
        gridPane.add(passedLevelsField, 1, 3);

        gridPane.add(enterButton, 0, 4);
        gridPane.add(clearButton, 1, 4);

        final String[] usernames = new String[2];
        enterButton.setOnMouseClicked(event -> {
            usernames[0] = nameField.getText();
            usernames[1] = moneyField.getText();
        });

        clearButton.setOnMouseClicked(event -> {
            nameField.clear();
            moneyField.clear();
        });
        root.getChildren().add(gridPane);

    }
}
