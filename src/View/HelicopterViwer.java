package View;

import Model.LevelRequirementsChecker;
import Model.Mission;
import Model.Products.*;
import Model.Vehicles.Helicopter;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class HelicopterViwer extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        Image back = new Image("file:Textures\\pictures\\helicopter.png");
        ImageView backView = new ImageView(back);
        backView.setFitWidth(Screen.getPrimary().getVisualBounds().getWidth());
        backView.setFitHeight(Screen.getPrimary().getVisualBounds().getHeight());
        pane.getChildren().add(backView);
        LevelRequirementsChecker lrc = new LevelRequirementsChecker(0, 3, 0,
                0, 0, 0, 0, 3, 0, 0, 0, 0, 0);
        Mission mission = new Mission(10000, "GraphicTest", lrc, null, null);

        Helicopter helicopter = new Helicopter(mission);

        Image helicopterImage = new Image("file:Textures\\UI\\Helicopter\\0"
                + String.valueOf(helicopter.getLevel() + 1) + ".png");
        ImageView heliView = new ImageView(helicopterImage);
        heliView.setFitHeight(helicopterImage.getHeight() + 200);
        heliView.setFitWidth(helicopterImage.getWidth() + 200);
        heliView.setX(750);
        heliView.setY(280);
        pane.getChildren().add(heliView);

        Text goods = new Text("Goods");
        goods.setFill(Color.BLANCHEDALMOND);
        goods.setFont(Font.font("Arial Rounded MT Bold", 30));
        goods.setX(85);
        goods.setY(145);
        pane.getChildren().add(goods);

        Text price1 = new Text("Price");
        price1.setFill(Color.BLANCHEDALMOND);
        price1.setFont(Font.font("Arial Rounded MT Bold", 30));
        price1.setX(250);
        price1.setY(145);
        pane.getChildren().add(price1);

        Text order = new Text("Order");
        order.setFill(Color.BLANCHEDALMOND);
        order.setFont(Font.font("Arial Rounded MT Bold", 30));
        order.setX(450);
        order.setY(145);
        pane.getChildren().add(order);

        Text title = new Text("Order Goods");
        title.setFill(Color.BLANCHEDALMOND);
        title.setFont(Font.font("Arial Rounded MT Bold", 50));
        title.setX(185);
        title.setY(65);
        pane.getChildren().add(title);

        int cost = 0;
        Text costText = new Text(String.valueOf(cost));
        costText.setFont(Font.font("Arial Rounded MT Bold", 40));
        costText.setFill(Color.YELLOW);
        costText.setX(355);
        costText.setY(668);
        pane.getChildren().add(costText);

        Image coin = new Image("file:Textures\\pictures\\coin_48.png");
        ImageView coinView = new ImageView(coin);
        coinView.setX(180);
        coinView.setY(630);
        pane.getChildren().add(coinView);

        Image button = new Image("file:Textures\\pictures\\button.png");
        ImageView okButton = new ImageView(button);
        okButton.setX(250);
        okButton.setY(725);
        okButton.setFitWidth(button.getWidth() * 2);
        okButton.setFitHeight(button.getHeight() + 10);
        pane.getChildren().add(okButton);

        ImageView cancelButton = new ImageView(button);
        cancelButton.setX(350);
        cancelButton.setY(725);
        cancelButton.setFitWidth(button.getWidth() * 2);
        cancelButton.setFitHeight(button.getHeight() + 10);
        pane.getChildren().add(cancelButton);

        Text ok = new Text("OK");
        ok.setFill(Color.WHITE);
        ok.setFont(Font.font("Arial Rounded MT Bold", 25));
        ok.setX(280);
        ok.setY(747);
        pane.getChildren().add(ok);

        Text cancel = new Text("Cancel");
        cancel.setFont(Font.font("Arial Rounded MT Bold", 20));
        cancel.setFill(Color.WHITE);
        cancel.setX(365);
        cancel.setY(747);
        pane.getChildren().add(cancel);

        ImageView buyFromserver = new ImageView(button);
        buyFromserver.setFitHeight(button.getHeight() + 10);
        buyFromserver.setFitWidth(button.getWidth() * 3 + 20);
        buyFromserver.setX(450);
        buyFromserver.setY(725);
        pane.getChildren().add(buyFromserver);

        Text buyFromServerText = new Text("buy from server");
        buyFromServerText.setFill(Color.WHITE);
        buyFromServerText.setFont(Font.font("Arial Rounded MT Bold", 15));
        buyFromServerText.setX(475);
        buyFromServerText.setY(745);
        pane.getChildren().add(buyFromServerText);


        Product[] products = {new Cake(), new Cloth(), new Cookie(), new Dress(), new EggPowder(), new Fiber(), new Flour(), new Milk()};
        for (int i = 0; i < products.length; i++) {
            Image image = new Image("file:Textures\\Product\\" + products[i].getName() + ".png");
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(image.getHeight() * 3 / 4);
            imageView.setFitWidth(image.getWidth() * 3 / 4);
            imageView.setX(130);
            imageView.setY(160 + 45 * i);
            pane.getChildren().add(imageView);

            int price = products[i].getBuyCost();
            Text priceOfProduct = new Text(String.valueOf(price));
            priceOfProduct.setFont(Font.font("Arial Rounded MT Bold", 23));
            priceOfProduct.setFill(Color.BLANCHEDALMOND);
            priceOfProduct.setX(265);
            priceOfProduct.setY(185 + 45 * i);
            pane.getChildren().add(priceOfProduct);

            Image buttonImage = new Image("file:Textures\\pictures\\button.png");
            ImageView orderButton = new ImageView(buttonImage);
            orderButton.setFitHeight(image.getHeight() * 2 / 3);
            orderButton.setFitWidth(image.getWidth() * 2 / 3);
            orderButton.setX(485);
            orderButton.setY(165 + 44 * i);
            pane.getChildren().add(orderButton);

            Text buttonTextOne = new Text("1");
            buttonTextOne.setFont(Font.font("Arial Rounded MT Bold", 23));
            buttonTextOne.setFill(Color.WHITE);
            buttonTextOne.setX(493);
            buttonTextOne.setY(186 + 44 * i);
            pane.getChildren().add(buttonTextOne);


            final int index = i;
            orderButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int cost = Integer.parseInt(costText.getText());
                    cost += products[index].getBuyCost();
                    pane.getChildren().remove(costText);
                    costText.setText(String.valueOf(cost));
                    pane.getChildren().add(costText);

                }
            });

            buttonTextOne.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int cost = Integer.parseInt(costText.getText());
                    cost += products[index].getBuyCost();
                    pane.getChildren().remove(costText);
                    costText.setText(String.valueOf(cost));
                    pane.getChildren().add(costText);
                }
            });
        }




        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
