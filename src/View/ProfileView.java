package View;

import Model.Network.Packet.Profile;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ProfileView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane pane = new StackPane();
        Profile profile = new Profile();
        Image back = new Image("file:Textures\\pictures\\nature.png");
        ImageView backView = new ImageView(back);
        primaryStage.setFullScreen(true);
        backView.setFitWidth(primaryStage.getWidth());
        backView.setFitHeight(primaryStage.getHeight());
        pane.getChildren().add(backView);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
