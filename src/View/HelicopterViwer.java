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
import javafx.stage.Stage;

public class HelicopterViwer extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        Image back = new Image("file:Textures\\pictures\\helicopter.png");
        ImageView backView = new ImageView(back);
        pane.getChildren().add(backView);
        LevelRequirementsChecker lrc = new LevelRequirementsChecker(0, 3, 0,
                0, 0, 0, 0, 3, 0, 0, 0, 0, 0);
        Mission mission = new Mission(10000, "GraphicTest", lrc, null, null);

        Helicopter helicopter = new Helicopter(mission);

        Image helicopterImage = new Image("file:Textures\\UI\\Helicopter\\0"
                + String.valueOf(helicopter.getLevel() + 1) + ".png");
        ImageView heliView = new ImageView(helicopterImage);
        heliView.setX(340);
        heliView.setY(280);
        pane.getChildren().add(heliView);

        Text goods = new Text("Goods");
        goods.setFill(Color.BLANCHEDALMOND);
        goods.setFont(Font.font("Arial Rounded MT Bold", 20));
        goods.setX(35);
        goods.setY(102);
        pane.getChildren().add(goods);

        Text price1 = new Text("Price");
        price1.setFill(Color.BLANCHEDALMOND);
        price1.setFont(Font.font("Arial Rounded MT Bold", 20));
        price1.setX(130);
        price1.setY(102);
        pane.getChildren().add(price1);

        Text order = new Text("Order");
        order.setFill(Color.BLANCHEDALMOND);
        order.setFont(Font.font("Arial Rounded MT Bold", 20));
        order.setX(220);
        order.setY(102);
        pane.getChildren().add(order);

        Text title = new Text("Order Goods");
        title.setFill(Color.BLANCHEDALMOND);
        title.setFont(Font.font("Arial Rounded MT Bold", 30));
        title.setX(90);
        title.setY(47);
        pane.getChildren().add(title);

        int cost = 0;
        Text costText = new Text(String.valueOf(cost));
        costText.setFont(Font.font("Arial Rounded MT Bold", 25));
        costText.setFill(Color.YELLOW);
        costText.setX(180);
        costText.setY(485);
        pane.getChildren().add(costText);

        Image coin = new Image("file:Textures\\pictures\\coin_32.png");
        ImageView coinView = new ImageView(coin);
        coinView.setX(90);
        coinView.setY(460);
        pane.getChildren().add(coinView);

        Image button = new Image("file:Textures\\pictures\\button.png");
        ImageView button1View = new ImageView(button);
        button1View.setX(60);
        button1View.setY(525);
        button1View.setFitWidth(button.getWidth() * 2);
        button1View.setFitHeight(button.getHeight() + 10);
        pane.getChildren().add(button1View);

        ImageView button2View = new ImageView(button);
        button2View.setX(190);
        button2View.setY(525);
        button2View.setFitWidth(button.getWidth() * 2);
        button2View.setFitHeight(button.getHeight() + 10);
        pane.getChildren().add(button2View);

        Text ok = new Text("Ok");
        ok.setFill(Color.WHITE);
        ok.setFont(Font.font("Arial Rounded MT Bold", 20));
        ok.setX(95);
        ok.setY(547);
        pane.getChildren().add(ok);

        Text cancel = new Text("Cancel");
        cancel.setFont(Font.font("Arial Rounded MT Bold", 20));
        cancel.setFill(Color.WHITE);
        cancel.setX(205);
        cancel.setY(547);
        pane.getChildren().add(cancel);

        Product[] products = {new Cake(), new Cloth(), new Cookie(), new Dress(), new EggPowder(), new Fiber(), new Flour(), new Milk()};
        for (int i = 0; i < products.length; i++) {
            Image image = new Image("file:Textures\\Product\\" + products[i].getName() + ".png");
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(image.getHeight() * 2 / 3);
            imageView.setFitWidth(image.getWidth() * 2 / 3);
            imageView.setX(52);
            imageView.setY(116 + 32 * i);
            pane.getChildren().add(imageView);

            int price = products[i].getBuyCost();
            Text priceOfProduct = new Text(String.valueOf(price));
            priceOfProduct.setFont(Font.font("Arial Rounded MT Bold", 20));
            priceOfProduct.setFill(Color.BLANCHEDALMOND);
            priceOfProduct.setX(135);
            priceOfProduct.setY(140 + 32 * i);
            pane.getChildren().add(priceOfProduct);

            Image buttonImage = new Image("file:Textures\\pictures\\button.png");
            ImageView imageView1 = new ImageView(buttonImage);
            imageView1.setFitHeight(image.getHeight() / 2);
            imageView1.setFitWidth(image.getWidth() / 2);
            imageView1.setX(235);
            imageView1.setY(120 + 32 * i);
            pane.getChildren().add(imageView1);

            Text buttonTextOne = new Text("1");
            buttonTextOne.setFont(Font.font("Arial Rounded MT Bold", 20));
            buttonTextOne.setFill(Color.WHITE);
            buttonTextOne.setX(240);
            buttonTextOne.setY(137 + 32 * i);
            pane.getChildren().add(buttonTextOne);

            final int index = i;
            imageView1.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
