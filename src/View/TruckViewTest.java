package View;
import Exceptions.CapacityExceededException;
import Exceptions.NotFoundException;
import Interfaces.Storable;
import Model.Animals.Predators.Bear;
import Model.Animals.Predators.Lion;
import Model.LevelRequirementsChecker;
import Model.Mission;
import Model.Placement.Direction;
import Model.Placement.Map;
import Model.Placement.Position;
import Model.Products.*;
import Model.Vehicles.Truck;
import Model.Warehouse;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class TruckViewTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        LevelRequirementsChecker lrc = new LevelRequirementsChecker(0, 3, 0,
                0, 0, 0, 0, 3, 0, 0, 0, 0, 0);
        Mission mission = new Mission(10000, "GraphicTest", lrc, null, null);
        Warehouse warehouse = new Warehouse(mission);
        Truck truck = new Truck(mission);

        //borderPane.setAlignment(borderPane.getLeft(), Pos.TOP_CENTER);
        Image image = new Image("file:Textures\\pictures\\page.jpg");


        Scene scene = new Scene(borderPane);
        //primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        /*primaryStage.setMaxHeight(image.getHeight());
        primaryStage.setMaxWidth(image.getWidth());
        primaryStage.setMinHeight(image.getHeight());
        primaryStage.setMinWidth(image.getWidth());*/

        warehouse.getItems().add(new Egg());
        warehouse.getItems().add(new Wool());
        warehouse.getItems().add(new Lion(new Map(mission), new Direction(), new Position(1,1)));

        borderPane.setLeft(setLeft(primaryStage, warehouse, truck));


        primaryStage.setScene(scene);


        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Parent setLeft(Stage primaryStage, Warehouse warehouse, Truck truck) {
        Pane pane = new Pane();
        Image image = new Image("file:Textures\\pictures\\page.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(image.getHeight() + 100);
        imageView.setFitWidth(image.getWidth() + 50);

        ArrayList<ImageView> oneButtons = new ArrayList<>();
        ArrayList<ImageView> allButtons = new ArrayList<>();

        pane.getChildren().add(imageView);
        Text text1 = new Text("Goods");
        text1.setFont(Font.font("Arial Rounded MT Bold", 20));
        text1.setFill(Color.BLANCHEDALMOND);
        text1.setX(35);
        text1.setY(105);
        pane.getChildren().add(text1);

        Text text2 = new Text("Price");
        text2.setFont(Font.font("Arial Rounded MT Bold", 20));
        text2.setFill(Color.BLANCHEDALMOND);
        text2.setX(110);
        text2.setY(105);
        pane.getChildren().add(text2);

        Text text3 = new Text("Sheep");
        text3.setFont(Font.font("Arial Rounded MT Bold", 20));
        text3.setFill(Color.BLANCHEDALMOND);
        text3.setX(185);
        text3.setY(105);
        pane.getChildren().add(text3);

        Text text4 = new Text("Goods");
        text4.setFont(Font.font("Arial Rounded MT Bold", 20));
        text4.setFill(Color.BLANCHEDALMOND);
        text4.setX(300);
        text4.setY(105);
        pane.getChildren().add(text4);

        Text text5 = new Text("Price");
        text5.setFont(Font.font("Arial Rounded MT Bold", 20));
        text5.setFill(Color.BLANCHEDALMOND);
        text5.setX(375);
        text5.setY(105);
        pane.getChildren().add(text5);

        Text text6 = new Text("Sheep");
        text6.setFont(Font.font("Arial Rounded MT Bold", 20));
        text6.setFill(Color.BLANCHEDALMOND);
        text6.setX(450);
        text6.setY(105);
        pane.getChildren().add(text6);

        Text text7 = new Text("Animals");
        text7.setFont(Font.font("Arial Rounded MT Bold", 20));
        text7.setFill(Color.BLANCHEDALMOND);
        text7.setX(580);
        text7.setY(105);
        pane.getChildren().add(text7);

        Text text8 = new Text("Price");
        text8.setFont(Font.font("Arial Rounded MT Bold", 20));
        text8.setFill(Color.BLANCHEDALMOND);
        text8.setX(665);
        text8.setY(105);
        pane.getChildren().add(text8);

        Text text9 = new Text("Ship");
        text9.setFont(Font.font("Arial Rounded MT Bold", 20));
        text9.setFill(Color.BLANCHEDALMOND);
        text9.setX(740);
        text9.setY(105);
        pane.getChildren().add(text9);

        Text text = new Text("Ship Products");
        text.setFont(Font.font("Arial Rounded MT Bold", 40));
        text.setFill(Color.WHITE);
        text.setX(285);
        text.setY(40);
        pane.getChildren().add(text);


        Image image1 = new Image("file:C:\\Users\\Kasra\\Desktop\\FarmFrenzy\\Textures\\UI\\Truck\\0" + String.valueOf(truck.getLevel() + 1) + ".png");
        ImageView imageView1 = new ImageView(image1);
        imageView1.setX(540);
        imageView1.setY(180);
        pane.getChildren().add(imageView1);


        Image image2 = new Image("file:Textures\\pictures\\coin_32.png");
        ImageView imageView2 = new ImageView(image2);
        imageView2.setX(605);
        imageView2.setY(570);
        pane.getChildren().add(imageView2);


        Set<String> n = new HashSet<>();
        for (int i = 0; i < warehouse.getItems().size(); i++) {
            n.add(warehouse.getItems().get(i).getName());
        }


        int ctr = 0;
        int cost = 0;
        ArrayList<String> names = new ArrayList<>(n);
        /*if (warehouse.getItems().size() > 0)
            names.add(warehouse.getItems().get(0).getName());
        for (int i = 1; i < warehouse.getItems().size(); i++) {
            for (int j = 0; j < i; j++) {
                if (!warehouse.getItems().get(i).getName().toLowerCase().equals(warehouse.getItems().get(j).getName().toLowerCase())) {
                    ctr++;
                }
                else{
                    System.out.println(warehouse.getItems().get(j).getName().toUpperCase());
                }
                if (ctr == i) {
                    names.add(warehouse.getItems().get(i).getName());

                }
                ctr = 0;
            }
        }*/
        Text priceText = new Text("0");
        priceText.setFont(Font.font("Arial Rounded MT Bold", 30));
        priceText.setFill(Color.YELLOW);
        priceText.setX(690);
        priceText.setY(595);
        pane.getChildren().add(priceText);

        Image label = new Image("file:Textures\\pictures\\label.png");
        ImageView labelView = new ImageView(label);
        labelView.setFitWidth(100);
        labelView.setFitHeight(50);
        labelView.setX(580);
        labelView.setY(450);
        pane.getChildren().add(labelView);





        int[] nums = new int[names.size()];
        for (int i = 0; i < names.size(); i++) {
            int num = 0, price = 0;
            for (int j = 0; j < warehouse.getItems().size(); j++) {
                if (names.get(i).toLowerCase().equals(warehouse.getItems().get(j).getName().toLowerCase())) {
                    num++;
                    price = warehouse.getItems().get(j).getSellCost();
                }
            }
            nums[i] = num;
            /*if (names.get(i).toLowerCase().equals("hen")) {
                Image image3 = new Image("file:C:\\Users\\Kasra\\Desktop\\FarmFrenzy\\Textures\\guinea_fowl.png");
                ImageView imageView3 = new ImageView(image3);
                imageView3.setX(540);
                imageView3.setY(90 + 20 * i);
                pane.getChildren().add(imageView3);
            }*/

            Image image3 = new Image("file:Textures\\pictures\\" + names.get(i) + ".png");
            ImageView imageView3 = new ImageView(image3);
            imageView3.setX(30);
            imageView3.setY(120 + 40 * i);
            if (names.get(i).equals("Lion") || names.get(i).equals("Bear")){
                imageView3.setFitHeight(image3.getHeight() / 3);
                imageView3.setFitWidth(image3.getWidth() / 3);
            }
            else {
                imageView3.setFitHeight(image3.getHeight() / 2);
                imageView3.setFitWidth(image3.getWidth() / 2);
            }
            pane.getChildren().add(imageView3);
            Image image4 = new Image("file:Textures\\pictures\\cross.png");
            ImageView imageView4 = new ImageView(image4);
            imageView4.setX(60);
            imageView4.setY(128 + 38 * i);
            pane.getChildren().add(imageView4);

            Text text10 = new Text(String.valueOf(num));
            text10.setX(82);
            text10.setY(145 + 38 * i);
            text10.setFont(Font.font("Arial Rounded MT Bold", 20));
            text10.setFill(Color.BLANCHEDALMOND);
            pane.getChildren().add(text10);

            Text text11 = new Text(String.valueOf(price));
            text11.setFill(Color.BLANCHEDALMOND);
            text11.setFont(Font.font("Arial Rounded MT Bold", 20));
            text11.setX(110);
            text11.setY(145 + 38 * i);
            pane.getChildren().add(text11);

            Image image5 = new Image("file:Textures\\pictures\\coin_32.png");
            ImageView imageView5 = new ImageView(image5);
            imageView5.setFitHeight(image5.getHeight() * 2 / 3);
            imageView5.setFitWidth(image5.getWidth() * 2 / 3);
            imageView5.setX(155);
            imageView5.setY(125 + 38 * i);
            pane.getChildren().add(imageView5);

            Image image6 = new Image("file:Textures\\pictures\\button.png");
            ImageView imageView6 = new ImageView(image6);
            imageView6.setX(175);
            imageView6.setY(126 + 38 * i);
            imageView6.setFitWidth(image6.getWidth() - 10);
            oneButtons.add(imageView6);
            pane.getChildren().add(imageView6);

            Text text12 = new Text("1");
            text12.setFont(Font.font("Arial Rounded MT Bold", 20));
            text12.setFill(Color.WHITE);
            text12.setX(187);
            text12.setY(142 + 38 * i);
            pane.getChildren().add(text12);

            ImageView imageView7 = new ImageView(image6);
            imageView7.setX(210);
            imageView7.setY(126 + 38 * i);
            imageView7.setFitWidth(image6.getWidth());
            allButtons.add(imageView7);
            pane.getChildren().add(imageView7);

            Text text13 = new Text("all");
            text13.setFont(Font.font("Arial Rounded MT Bold", 20));
            text13.setFill(Color.WHITE);
            text13.setX(223);
            text13.setY(142 + 38 * i);
            pane.getChildren().add(text13);


            final int index = i;
            final int no = num;

            text12.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int price = Integer.parseInt(priceText.getText());
                    for (int k = 0; k < warehouse.getItems().size(); k++) {
                        if (names.get(index).equals(warehouse.getItems().get(k).getName())){
                            if (Integer.parseInt(text10.getText()) > 0) {
                                pane.getChildren().remove(priceText);
                                pane.getChildren().remove(text10);
                                price += warehouse.getItems().get(k).getSellCost();
                                priceText.setText(String.valueOf(price));
                                text10.setText(String.valueOf(Integer.parseInt(text10.getText()) - 1));
                                pane.getChildren().add(text10);
                                pane.getChildren().add(priceText);
                                break;
                            }
                        }
                    }
                }
            });

            imageView6.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int price = Integer.parseInt(priceText.getText());
                    for (int k = 0; k < warehouse.getItems().size(); k++) {
                        if (names.get(index).equals(warehouse.getItems().get(k).getName())){
                            if (Integer.parseInt(text10.getText()) > 0) {
                                pane.getChildren().remove(priceText);
                                pane.getChildren().remove(text10);
                                price += warehouse.getItems().get(k).getSellCost();
                                priceText.setText(String.valueOf(price));
                                text10.setText(String.valueOf(Integer.parseInt(text10.getText()) - 1));
                                pane.getChildren().add(text10);
                                pane.getChildren().add(priceText);
                                break;
                            }
                        }
                    }
                }
            });

            text13.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int price = Integer.parseInt(priceText.getText());
                    for (int k = 0; k < warehouse.getItems().size(); k++) {
                        if (names.get(index).equals(warehouse.getItems().get(k).getName())) {
                            pane.getChildren().remove(priceText);
                            pane.getChildren().remove(text10);
                            price += warehouse.getItems().get(k).getSellCost() * Integer.parseInt(text10.getText());
                            priceText.setText(String.valueOf(price));
                            text10.setText(String.valueOf(0));
                            pane.getChildren().add(text10);
                            pane.getChildren().add(priceText);
                            break;
                        }
                    }
                }
            });

            imageView7.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int price = Integer.parseInt(priceText.getText());
                    for (int k = 0; k < warehouse.getItems().size(); k++) {
                        if (names.get(index).equals(warehouse.getItems().get(k).getName())) {
                            pane.getChildren().remove(priceText);
                            pane.getChildren().remove(text10);
                            price += warehouse.getItems().get(k).getSellCost() * Integer.parseInt(text10.getText());
                            priceText.setText(String.valueOf(price));
                            text10.setText(String.valueOf(0));
                            pane.getChildren().add(text10);
                            pane.getChildren().add(priceText);
                            break;
                        }
                    }
                }
            });
        }

        Image button = new Image("file:Textures\\pictures\\button.png");
        ImageView button1View = new ImageView(button);
        button1View.setX(175);
        button1View.setY(620);
        button1View.setFitWidth(button.getWidth() * 2);
        button1View.setFitHeight(button.getHeight() + 10);
        pane.getChildren().add(button1View);

        ImageView button2View = new ImageView(button);
        button2View.setX(305);
        button2View.setY(620);
        button2View.setFitWidth(button.getWidth() * 2);
        button2View.setFitHeight(button.getHeight() + 10);
        pane.getChildren().add(button2View);

        Text ok = new Text("Ok");
        ok.setFill(Color.WHITE);
        ok.setFont(Font.font("Arial Rounded MT Bold", 20));
        ok.setX(210);
        ok.setY(642);
        pane.getChildren().add(ok);

        Text cancel = new Text("Cancel");
        cancel.setFont(Font.font("Arial Rounded MT Bold", 20));
        cancel.setFill(Color.WHITE);
        cancel.setX(320);
        cancel.setY(642);
        pane.getChildren().add(cancel);

        ArrayList<Storable> list = new ArrayList<>(truck.getList());


        return pane;
    }
}


