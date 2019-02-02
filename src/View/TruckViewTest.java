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
        //stage.setFullScreen(true);
        primaryStage.setScene(scene);
        /*stage.setMaxHeight(image.getHeight());
        stage.setMaxWidth(image.getWidth());
        stage.setMinHeight(image.getHeight());
        stage.setMinWidth(image.getWidth());*/

        warehouse.getItems().add(new Egg());
        warehouse.getItems().add(new Wool());
        warehouse.getItems().add(new Wool());
        warehouse.getItems().add(new Lion(new Map(mission), new Direction(), new Position(1,1)));

        borderPane.setLeft(setLeft(primaryStage, warehouse, truck));


        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);

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
        Text goods1 = new Text("Goods");
        goods1.setFont(Font.font("Arial Rounded MT Bold", 20));
        goods1.setFill(Color.BLANCHEDALMOND);
        goods1.setX(35);
        goods1.setY(105);
        pane.getChildren().add(goods1);

        Text price1 = new Text("Price");
        price1.setFont(Font.font("Arial Rounded MT Bold", 20));
        price1.setFill(Color.BLANCHEDALMOND);
        price1.setX(110);
        price1.setY(105);
        pane.getChildren().add(price1);

        Text sheep = new Text("Sheep");
        sheep.setFont(Font.font("Arial Rounded MT Bold", 20));
        sheep.setFill(Color.BLANCHEDALMOND);
        sheep.setX(185);
        sheep.setY(105);
        pane.getChildren().add(sheep);

        Text goods2 = new Text("Goods");
        goods2.setFont(Font.font("Arial Rounded MT Bold", 20));
        goods2.setFill(Color.BLANCHEDALMOND);
        goods2.setX(300);
        goods2.setY(105);
        pane.getChildren().add(goods2);

        Text price3 = new Text("Price");
        price3.setFont(Font.font("Arial Rounded MT Bold", 20));
        price3.setFill(Color.BLANCHEDALMOND);
        price3.setX(375);
        price3.setY(105);
        pane.getChildren().add(price3);

        Text sheep2 = new Text("Sheep");
        sheep2.setFont(Font.font("Arial Rounded MT Bold", 20));
        sheep2.setFill(Color.BLANCHEDALMOND);
        sheep2.setX(450);
        sheep2.setY(105);
        pane.getChildren().add(sheep2);

        Text animals = new Text("Animals");
        animals.setFont(Font.font("Arial Rounded MT Bold", 20));
        animals.setFill(Color.BLANCHEDALMOND);
        animals.setX(580);
        animals.setY(105);
        pane.getChildren().add(animals);

        Text price2 = new Text("Price");
        price2.setFont(Font.font("Arial Rounded MT Bold", 20));
        price2.setFill(Color.BLANCHEDALMOND);
        price2.setX(665);
        price2.setY(105);
        pane.getChildren().add(price2);

        Text ship = new Text("Ship");
        ship.setFont(Font.font("Arial Rounded MT Bold", 20));
        ship.setFill(Color.BLANCHEDALMOND);
        ship.setX(740);
        ship.setY(105);
        pane.getChildren().add(ship);

        Text title = new Text("Ship Products");
        title.setFont(Font.font("Arial Rounded MT Bold", 40));
        title.setFill(Color.WHITE);
        title.setX(285);
        title.setY(40);
        pane.getChildren().add(title);


        Image truckImage = new Image("file:C:\\Users\\Kasra\\Desktop\\FarmFrenzy\\Textures\\UI\\Truck\\0" + String.valueOf(truck.getLevel() + 1) + ".png");
        ImageView imageView1 = new ImageView(truckImage);
        imageView1.setX(540);
        imageView1.setY(180);
        pane.getChildren().add(imageView1);


        Image coinImage = new Image("file:Textures\\pictures\\coin_32.png");
        ImageView imageView2 = new ImageView(coinImage);
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

            Image productImage = new Image("file:Textures\\pictures\\" + names.get(i) + ".png");
            ImageView imageView3 = new ImageView(productImage);
            imageView3.setX(30);
            imageView3.setY(120 + 40 * i);
            if (names.get(i).equals("Lion") || names.get(i).equals("Bear")){
                imageView3.setFitHeight(productImage.getHeight() / 3);
                imageView3.setFitWidth(productImage.getWidth() / 3);
            }
            else {
                imageView3.setFitHeight(productImage.getHeight() / 2);
                imageView3.setFitWidth(productImage.getWidth() / 2);
            }
            pane.getChildren().add(imageView3);
            Image crossImage = new Image("file:Textures\\pictures\\cross.png");
            ImageView imageView4 = new ImageView(crossImage);
            imageView4.setX(60);
            imageView4.setY(128 + 38 * i);
            pane.getChildren().add(imageView4);

            Text numberOfProduct = new Text(String.valueOf(num));
            numberOfProduct.setX(82);
            numberOfProduct.setY(145 + 38 * i);
            numberOfProduct.setFont(Font.font("Arial Rounded MT Bold", 20));
            numberOfProduct.setFill(Color.BLANCHEDALMOND);
            pane.getChildren().add(numberOfProduct);

            Text priceOfProduct = new Text(String.valueOf(price));
            priceOfProduct.setFill(Color.BLANCHEDALMOND);
            priceOfProduct.setFont(Font.font("Arial Rounded MT Bold", 20));
            priceOfProduct.setX(110);
            priceOfProduct.setY(145 + 38 * i);
            pane.getChildren().add(priceOfProduct);

            Image coinImage2 = new Image("file:Textures\\pictures\\coin_32.png");
            ImageView imageView5 = new ImageView(coinImage2);
            imageView5.setFitHeight(coinImage2.getHeight() * 2 / 3);
            imageView5.setFitWidth(coinImage2.getWidth() * 2 / 3);
            imageView5.setX(155);
            imageView5.setY(125 + 38 * i);
            pane.getChildren().add(imageView5);

            Image buttonImage = new Image("file:Textures\\pictures\\button.png");
            ImageView imageView6 = new ImageView(buttonImage);
            imageView6.setX(175);
            imageView6.setY(126 + 38 * i);
            imageView6.setFitWidth(buttonImage.getWidth() - 10);
            oneButtons.add(imageView6);
            pane.getChildren().add(imageView6);

            Text buttonTextOne = new Text("1");
            buttonTextOne.setFont(Font.font("Arial Rounded MT Bold", 20));
            buttonTextOne.setFill(Color.WHITE);
            buttonTextOne.setX(187);
            buttonTextOne.setY(142 + 38 * i);
            pane.getChildren().add(buttonTextOne);

            ImageView imageView7 = new ImageView(buttonImage);
            imageView7.setX(210);
            imageView7.setY(126 + 38 * i);
            imageView7.setFitWidth(buttonImage.getWidth());
            allButtons.add(imageView7);
            pane.getChildren().add(imageView7);

            Text buttonTextAll = new Text("all");
            buttonTextAll.setFont(Font.font("Arial Rounded MT Bold", 20));
            buttonTextAll.setFill(Color.WHITE);
            buttonTextAll.setX(223);
            buttonTextAll.setY(142 + 38 * i);
            pane.getChildren().add(buttonTextAll);


            final int index = i;
            final int no = num;

            buttonTextOne.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int price = Integer.parseInt(priceText.getText());
                    for (int k = 0; k < warehouse.getItems().size(); k++) {
                        if (names.get(index).equals(warehouse.getItems().get(k).getName())){
                            if (Integer.parseInt(numberOfProduct.getText()) > 0) {
                                pane.getChildren().remove(priceText);
                                pane.getChildren().remove(numberOfProduct);
                                price += warehouse.getItems().get(k).getSellCost();
                                priceText.setText(String.valueOf(price));
                                numberOfProduct.setText(String.valueOf(Integer.parseInt(numberOfProduct.getText()) - 1));
                                pane.getChildren().add(numberOfProduct);
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
                            if (Integer.parseInt(numberOfProduct.getText()) > 0) {
                                pane.getChildren().remove(priceText);
                                pane.getChildren().remove(numberOfProduct);
                                price += warehouse.getItems().get(k).getSellCost();
                                priceText.setText(String.valueOf(price));
                                numberOfProduct.setText(String.valueOf(Integer.parseInt(numberOfProduct.getText()) - 1));
                                pane.getChildren().add(numberOfProduct);
                                pane.getChildren().add(priceText);
                                break;
                            }
                        }
                    }
                }
            });

            buttonTextAll.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int price = Integer.parseInt(priceText.getText());
                    for (int k = 0; k < warehouse.getItems().size(); k++) {
                        if (names.get(index).equals(warehouse.getItems().get(k).getName())) {
                            pane.getChildren().remove(priceText);
                            pane.getChildren().remove(numberOfProduct);
                            price += warehouse.getItems().get(k).getSellCost() * Integer.parseInt(numberOfProduct.getText());
                            priceText.setText(String.valueOf(price));
                            numberOfProduct.setText(String.valueOf(0));
                            pane.getChildren().add(numberOfProduct);
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
                            pane.getChildren().remove(numberOfProduct);
                            price += warehouse.getItems().get(k).getSellCost() * Integer.parseInt(numberOfProduct.getText());
                            priceText.setText(String.valueOf(price));
                            numberOfProduct.setText(String.valueOf(0));
                            pane.getChildren().add(numberOfProduct);
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


