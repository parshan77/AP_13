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

        Text text = new Text("Goods");
        text.setFill(Color.BLANCHEDALMOND);
        text.setFont(Font.font("Arial Rounded MT Bold", 20));
        text.setX(35);
        text.setY(102);
        pane.getChildren().add(text);

        Text text1 = new Text("Price");
        text1.setFill(Color.BLANCHEDALMOND);
        text1.setFont(Font.font("Arial Rounded MT Bold", 20));
        text1.setX(130);
        text1.setY(102);
        pane.getChildren().add(text1);

        Text text2 = new Text("Order");
        text2.setFill(Color.BLANCHEDALMOND);
        text2.setFont(Font.font("Arial Rounded MT Bold", 20));
        text2.setX(220);
        text2.setY(102);
        pane.getChildren().add(text2);

        Text text3 = new Text("Order Goods");
        text3.setFill(Color.BLANCHEDALMOND);
        text3.setFont(Font.font("Arial Rounded MT Bold", 30));
        text3.setX(90);
        text3.setY(47);
        pane.getChildren().add(text3);

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
            Text text4 = new Text(String.valueOf(price));
            text4.setFont(Font.font("Arial Rounded MT Bold", 20));
            text4.setFill(Color.BLANCHEDALMOND);
            text4.setX(135);
            text4.setY(140 + 32 * i);
            pane.getChildren().add(text4);

            Image image1 = new Image("file:Textures\\pictures\\button.png");
            ImageView imageView1 = new ImageView(image1);
            imageView1.setFitHeight(image.getHeight() / 2);
            imageView1.setFitWidth(image.getWidth() / 2);
            imageView1.setX(235);
            imageView1.setY(120 + 32 * i);
            pane.getChildren().add(imageView1);

            Text text5 = new Text("1");
            text5.setFont(Font.font("Arial Rounded MT Bold", 20));
            text5.setFill(Color.WHITE);
            text5.setX(240);
            text5.setY(137 + 32 * i);
            pane.getChildren().add(text5);

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

            text5.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
