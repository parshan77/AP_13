package View;
import Exceptions.CapacityExceededException;
import Exceptions.NotFoundException;
import Interfaces.Storable;
import Model.Animals.Domestics.Hen;
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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
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
import javafx.stage.Screen;
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
        primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        /*primaryStage.setMaxHeight(image.getHeight());
        primaryStage.setMaxWidth(image.getWidth());
        primaryStage.setMinHeight(image.getHeight());
        primaryStage.setMinWidth(image.getWidth());*/

        warehouse.getItems().add(new Egg());
        warehouse.getItems().add(new Wool());
        warehouse.getItems().add(new Wool());
        warehouse.getItems().add(new Lion(new Map(mission), new Direction(), new Position(1,1)));
        warehouse.getItems().add(new Cookie());
        warehouse.getItems().add(new Cake());
        warehouse.getItems().add(new Dress());
        warehouse.getItems().add(new Milk());
        warehouse.getItems().add(new Bear(new Map(mission), new Direction(), new Position(1,1)));



        borderPane.setCenter(setCenter(primaryStage, warehouse, truck));


        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Parent setCenter(Stage primaryStage, Warehouse warehouse, Truck truck) {
        StackPane stackPane = new StackPane();
        Pane pane = new Pane();
        Image image = new Image("file:Textures\\pictures\\page.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(false);

        imageView.setFitHeight(Screen.getPrimary().getVisualBounds().getHeight());
        imageView.setFitWidth(Screen.getPrimary().getVisualBounds().getWidth());


        pane.getChildren().add(imageView);
        Text goods1 = new Text("Goods");
        goods1.setFont(Font.font("Arial Rounded MT Bold", 30));
        goods1.setFill(Color.BLANCHEDALMOND);
        goods1.setX(80);
        goods1.setY(130);
        pane.getChildren().add(goods1);

        Text price1 = new Text("Price");
        price1.setFont(Font.font("Arial Rounded MT Bold", 30));
        price1.setFill(Color.BLANCHEDALMOND);
        price1.setX(220);
        price1.setY(130);
        pane.getChildren().add(price1);

        Text sheep = new Text("Ship");
        sheep.setFont(Font.font("Arial Rounded MT Bold", 30));
        sheep.setFill(Color.BLANCHEDALMOND);
        sheep.setX(395);
        sheep.setY(130);
        pane.getChildren().add(sheep);

        Text goods2 = new Text("Goods");
        goods2.setFont(Font.font("Arial Rounded MT Bold", 30));
        goods2.setFill(Color.BLANCHEDALMOND);
        goods2.setX(570);
        goods2.setY(130);
        pane.getChildren().add(goods2);

        Text price3 = new Text("Price");
        price3.setFont(Font.font("Arial Rounded MT Bold", 30));
        price3.setFill(Color.BLANCHEDALMOND);
        price3.setX(710);
        price3.setY(130);
        pane.getChildren().add(price3);

        Text sheep2 = new Text("Ship");
        sheep2.setFont(Font.font("Arial Rounded MT Bold", 30));
        sheep2.setFill(Color.BLANCHEDALMOND);
        sheep2.setX(900);
        sheep2.setY(130);
        pane.getChildren().add(sheep2);

        Text animals = new Text("Animals");
        animals.setFont(Font.font("Arial Rounded MT Bold", 30));
        animals.setFill(Color.BLANCHEDALMOND);
        animals.setX(1050);
        animals.setY(130);
        pane.getChildren().add(animals);

        Text price2 = new Text("Price");
        price2.setFont(Font.font("Arial Rounded MT Bold", 30));
        price2.setFill(Color.BLANCHEDALMOND);
        price2.setX(1200);
        price2.setY(130);
        pane.getChildren().add(price2);

        Text ship = new Text("Ship");
        ship.setFont(Font.font("Arial Rounded MT Bold", 30));
        ship.setFill(Color.BLANCHEDALMOND);
        ship.setX(1380);
        ship.setY(130);
        pane.getChildren().add(ship);

        Text title = new Text("Ship Products");
        title.setFont(Font.font("Arial Rounded MT Bold", 50));
        title.setFill(Color.WHITE);
        title.setX(600);
        title.setY(50);
        pane.getChildren().add(title);


        Image truckImage = new Image("file:C:\\Users\\Kasra\\Desktop\\FarmFrenzy\\Textures\\UI\\Truck\\0" + String.valueOf(truck.getLevel() + 1) + ".png");
        ImageView imageView1 = new ImageView(truckImage);
        imageView1.setFitWidth(truckImage.getWidth() + 100);
        imageView1.setFitHeight(truckImage.getHeight() + 100);
        imageView1.setX(1050);
        imageView1.setY(200);
        pane.getChildren().add(imageView1);


        Image coinImage = new Image("file:Textures\\pictures\\coin_48.png");
        ImageView imageView2 = new ImageView(coinImage);
        imageView2.setX(1100);
        imageView2.setY(665);
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
        priceText.setFont(Font.font("Arial Rounded MT Bold", 40));
        priceText.setFill(Color.YELLOW);
        priceText.setX(1280);
        priceText.setY(705);
        pane.getChildren().add(priceText);






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
            imageView3.setX(60);
            imageView3.setY(145 + 45 * i);
            if (names.get(i).equals("Lion") || names.get(i).equals("Bear")){
                imageView3.setFitHeight(productImage.getHeight() / 2);
                imageView3.setFitWidth(productImage.getWidth() / 2);
            }
            else {
                imageView3.setFitHeight(productImage.getHeight() * 2 / 3);
                imageView3.setFitWidth(productImage.getWidth() * 2 / 3);
            }

            pane.getChildren().add(imageView3);
            Image crossImage = new Image("file:Textures\\pictures\\cross.png");
            ImageView imageView4 = new ImageView(crossImage);
            imageView4.setX(115);
            imageView4.setY(150 + 45 * i);
            pane.getChildren().add(imageView4);

            Text numberOfProduct = new Text(String.valueOf(num));
            numberOfProduct.setX(140);
            numberOfProduct.setY(165 + 45 * i);
            numberOfProduct.setFont(Font.font("Arial Rounded MT Bold", 23));
            numberOfProduct.setFill(Color.BLANCHEDALMOND);
            pane.getChildren().add(numberOfProduct);

            Text priceOfProduct = new Text(String.valueOf(price));
            priceOfProduct.setFill(Color.BLANCHEDALMOND);
            priceOfProduct.setFont(Font.font("Arial Rounded MT Bold", 23));
            priceOfProduct.setX(235);
            priceOfProduct.setY(165 + 45 * i);
            pane.getChildren().add(priceOfProduct);

            Image coinImage2 = new Image("file:Textures\\pictures\\coin_48.png");
            ImageView imageView5 = new ImageView(coinImage2);
            imageView5.setFitHeight(coinImage2.getHeight() * 2 / 3);
            imageView5.setFitWidth(coinImage2.getWidth() * 2 / 3);
            imageView5.setX(285);
            imageView5.setY(145 + 44 * i);
            pane.getChildren().add(imageView5);

            Image buttonImage = new Image("file:Textures\\pictures\\button.png");
            ImageView imageView6 = new ImageView(buttonImage);
            imageView6.setX(375);
            imageView6.setY(150 + 44 * i);
            imageView6.setFitWidth(buttonImage.getWidth());
            pane.getChildren().add(imageView6);

            Text buttonTextOne = new Text("1");
            buttonTextOne.setFont(Font.font("Arial Rounded MT Bold", 23));
            buttonTextOne.setFill(Color.WHITE);
            buttonTextOne.setX(390);
            buttonTextOne.setY(168 + 44 * i);
            pane.getChildren().add(buttonTextOne);

            ImageView imageView7 = new ImageView(buttonImage);
            imageView7.setX(420);
            imageView7.setY(150 + 44 * i);
            imageView7.setFitWidth(buttonImage.getWidth());
            pane.getChildren().add(imageView7);

            Text buttonTextAll = new Text("all");
            buttonTextAll.setFont(Font.font("Arial Rounded MT Bold", 20));
            buttonTextAll.setFill(Color.WHITE);
            buttonTextAll.setX(432);
            buttonTextAll.setY(165 + 44 * i);
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
        ImageView cancelButton = new ImageView(button);
        cancelButton.setX(500);
        cancelButton.setY(750);
        cancelButton.setFitWidth(button.getWidth() * 2);
        cancelButton.setFitHeight(button.getHeight() + 10);
        pane.getChildren().add(cancelButton);

        ImageView okButton = new ImageView(button);
        okButton.setX(400);
        okButton.setY(750);
        okButton.setFitWidth(button.getWidth() * 2);
        okButton.setFitHeight(button.getHeight() + 10);
        pane.getChildren().add(okButton);

        Text ok = new Text("Ok");
        ok.setFill(Color.WHITE);
        ok.setFont(Font.font("Arial Rounded MT Bold", 20));
        ok.setX(435);
        ok.setY(770);
        pane.getChildren().add(ok);

        Text cancel = new Text("Cancel");
        cancel.setFont(Font.font("Arial Rounded MT Bold", 20));
        cancel.setFill(Color.WHITE);
        cancel.setX(515);
        cancel.setY(770);
        pane.getChildren().add(cancel);

        ImageView sellToServer = new ImageView(button);
        sellToServer.setFitWidth(button.getWidth() * 3);
        sellToServer.setFitHeight(button.getHeight() + 10);
        sellToServer.setX(600);
        sellToServer.setY(750);
        pane.getChildren().add(sellToServer);

        Text sellToServerText = new Text("Sell to server");
        sellToServerText.setFill(Color.WHITE);
        sellToServerText.setFont(Font.font("Arial Rounded MT Bold", 15));
        sellToServerText.setX(625);
        sellToServerText.setY(770);
        pane.getChildren().add(sellToServerText);

        ArrayList<Storable> list = new ArrayList<>(truck.getList());


        return pane;
    }
}


