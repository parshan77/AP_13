package Controller;

import Model.Identity.Game;
import View.MenuView;
import com.gilecode.yagson.YaGson;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Game game = loadGame();
//        MenuView menuView = new MenuView(game);
//        menuView.start(primaryStage);
    }

    private static Game loadGame() throws FileNotFoundException {
        File file = new File("SaveGame\\SavedGame\\game.txt");
        try (Scanner scanner = new Scanner(file)) {
            YaGson yaGson = new YaGson();
            String objectString = scanner.nextLine();
            return yaGson.fromJson(objectString, Game.class);
        }
    }


}
