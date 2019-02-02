package View;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;
import java.util.*;
import java.util.List;


public class ScoreBoard extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        //Label title = new Label("Score Board")
        Text text = new Text("Score Board");
        text.setFont(Font.font("Bernard MT Condensed", FontWeight.EXTRA_BOLD, 35));

        HashMap<String, Integer> hashMap = new HashMap<>();


        Scene scene = new Scene(root, 1500, 800, Color.YELLOW);
        text.setFill(Color.RED);
        root.setTop(text);
        root.setAlignment(root.getTop(), Pos.CENTER);
        root.setCenter(setCenter(hashMap, primaryStage));
        root.setAlignment(root.getCenter(), Pos.CENTER);
        root.setBottom(setBottom(hashMap));
        root.setAlignment(root.getBottom(), Pos.CENTER);





        //scene.getStylesheets().add("file:x.css");
        //scene.getStylesheets().add("file:y.css");
        scene.setFill(Color.YELLOW);
        primaryStage.setTitle("Score Board");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public static Parent setCenter(HashMap<String, Integer> hashMap, Stage stage){
        ScrollPane scrollPane = new ScrollPane();
        GridPane gridPane = new GridPane();
        Image image = new Image("file:Textures\\pictures\\farm.png");
        ImageView imageView = new ImageView(image);


        StackPane stackPane = new StackPane();

        stackPane.getChildren().add(imageView);
        stackPane.getChildren().add(gridPane);


        scrollPane.setContent(stackPane);
        //stackPane.getChildren().add(scrollPane);

        Text name = new Text("Player Name");
        name.setFont(Font.font("Bernard MT Condensed", FontWeight.EXTRA_BOLD, 30));
        name.setFill(Color.RED);
        gridPane.add(name, 1, 0);
        gridPane.setVgap(10);
        gridPane.setHgap(20);
        gridPane.setAlignment(Pos.CENTER);

        Text score = new Text("Score");
        score.setFont(Font.font("Bernard MT Condensed", FontWeight.EXTRA_BOLD, 30));
        score.setFill(Color.RED);
        gridPane.add(score, 3, 0);
        gridPane.setAlignment(Pos.CENTER);
        GridPane.setHalignment(name, HPos.CENTER);
        GridPane.setHalignment(score, HPos.CENTER);


        List<Integer> values = new ArrayList<>(hashMap.values());
        Collections.sort(values);
        List<String> keys = new ArrayList<>(hashMap.keySet());

        int n = hashMap.size();
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (values.get(i) == hashMap.get(keys.get(j))){
                    Text text1 = new Text(keys.get(j));
                    text1.setFont(Font.font("Arial Rounded MT Bold", 25));
                    text1.setFill(Color.GREEN);
                    GridPane.setHalignment(text1, HPos.CENTER);
                    gridPane.add(text1, 1, 1 + (n - 1 - i));
                    Text text2 = new Text(String.valueOf(values.get(i)));
                    text2.setFont(Font.font("Arial Rounded MT Bold", 25));
                    text2.setFill(Color.GREEN);
                    GridPane.setHalignment(text2, HPos.CENTER);
                    gridPane.add(text2, 3, 1 + (n - 1 - i));
                    text1.setOnMouseEntered(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            text1.setFill(Color.BLUE);
                            text1.setFont(Font.font("Arial Rounded MT Bold", 35));
                            text2.setFill(Color.BLUE);
                            text2.setFont(Font.font("Arial Rounded MT Bold", 35));
                        }
                    });

                    text1.setOnMouseExited(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            text1.setFill(Color.GREEN);
                            text1.setFont(Font.font("Arial Rounded MT Bold", 25));
                            text2.setFill(Color.GREEN);
                            text2.setFont(Font.font("Arial Rounded MT Bold", 25));
                        }
                    });

                    text2.setOnMouseEntered(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            text1.setFill(Color.BLUE);
                            text1.setFont(Font.font("Arial Rounded MT Bold", 35));
                            text2.setFill(Color.BLUE);
                            text2.setFont(Font.font("Arial Rounded MT Bold", 35));
                        }
                    });

                    text2.setOnMouseExited(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            text1.setFill(Color.GREEN);
                            text1.setFont(Font.font("Arial Rounded MT Bold", 25));
                            text2.setFill(Color.GREEN);
                            text2.setFont(Font.font("Arial Rounded MT Bold", 25));
                        }
                    });
                    keys.remove(j);
                    break;
                }
            }
        }

        //imageView.setFitHeight(gridPane.getRowCount() * (gridPane.getHeight()));
        //imageView.setFitWidth(1500);
        imageView.setFitHeight(Screen.getPrimary().getVisualBounds().getHeight());
        imageView.setFitWidth(Screen.getPrimary().getVisualBounds().getWidth());



        //scrollPane.setFitToWidth(true);
        gridPane.setPadding(new Insets(10));
        scrollPane.setPannable(true);
        return scrollPane;
    }

    public Parent setBottom(HashMap<String, Integer> hashMap){
        GridPane gridPane = new GridPane();
        gridPane.setHgap(15);
        gridPane.setVgap(10);
        TextField textField = new TextField();
        textField.setPromptText("Enter a name to see the score");
        textField.setPrefWidth(200);
        Button button = new Button("SEARCH");
        gridPane.add(textField, 0, 0);
        gridPane.add(button, 1, 0);
        Text text = new Text("");
        gridPane.add(text, 2, 0);
        final Object[] temp = {text};

        List<String> names = new ArrayList<>(hashMap.keySet());
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String name = textField.getText();
                boolean flag = false;
                gridPane.getChildren().remove(temp[0]);
                for (int i = 0; i < hashMap.size(); i++) {
                    if (names.get(i).equals(name)){
                        Text text = new Text("Score = " + String.valueOf(hashMap.get(names.get(i))));
                        text.setFill(Color.RED);
                        text.setFont(Font.font("Arial Rounded MT Bold", 20));
                        gridPane.add(text, 2, 0);
                        temp[0] = text;
                        flag = true;
                        break;
                    }
                }
                if (!flag){
                    Text text = new Text("NOT Found...");
                    text.setFill(Color.RED);
                    text.setFont(Font.font("Arial Rounded MT Bold", 20));
                    temp[0] = text;
                    gridPane.add(text, 2, 0);
                }
            }
        });
        Text text1 = new Text("");
        gridPane.add(text1, 0, 5);
        gridPane.setAlignment(Pos.CENTER);
        return gridPane;
    }

}