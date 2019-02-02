package View;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

import javafx.stage.Stage;

import java.util.ArrayList;

import static View.MenuView.buildImageView;
import static View.MenuView.buildLabel;

public class LevelRequirementViewer {

    private ArrayList<String> list = new ArrayList();

    private ImageView[] imageViews = new ImageView[4];
    private Label[] required = new Label[4];
    private Label[] received = new Label[4];

    public void change() {
        increaseLabelText(received[whatIndexOfListEqualsTo(getLastReceivedProductName())]);
    }

    public void showUnderBar(Group root, Stage primaryStage, int requiredCows,
                             int requiredHens, int requiredSheep, int requiredClothes,
                             int requiredCakes, int requiredCookies, int requiredDresses,
                             int requiredEggs, int requiredEggPowders, int requiredFibers,
                             int requiredFlours, int requiredMilks, int requiredWools) {

        addToUnderBar(requiredCows, requiredHens, requiredSheep,
                requiredClothes, requiredCakes, requiredCookies,
                requiredDresses, requiredEggs, requiredEggPowders,
                requiredFibers, requiredFlours, requiredMilks,
                requiredWools);

        buildImageView(root, "File:underBar.png",
                0.01 * primaryStage.getWidth(), 0.73 * primaryStage.getHeight(),
                0.98 * primaryStage.getWidth(), 0.26 * primaryStage.getHeight(), true);

        double x = primaryStage.getWidth();
        double y = primaryStage.getHeight();
        buildLabel(root, "20:20", x * 0.924, y * 0.926, Font.font(20), true, "");


        imageViews[0] = buildImageView(root, null, x * 0.795, y * 0.810, x * 0.03, y * 0.05, true);
        imageViews[1] = buildImageView(root, null, x * 0.845, y * 0.810, x * 0.03, y * 0.05, true);
        imageViews[2] = buildImageView(root, null, x * 0.895, y * 0.810, x * 0.03, y * 0.05, true);
        imageViews[3] = buildImageView(root, null, x * 0.945, y * 0.810, x * 0.03, y * 0.05, true);

        required[0] = buildLabel(root, null, x * 0.802, y * 0.890, Font.font(16), true, "-fx-font-weight: bold");
        required[1] = buildLabel(root, null, x * 0.855, y * 0.890, Font.font(16), true, "-fx-font-weight: bold");
        required[2] = buildLabel(root, null, x * 0.905, y * 0.890, Font.font(16), true, "-fx-font-weight: bold");
        required[3] = buildLabel(root, null, x * 0.955, y * 0.890, Font.font(16), true, "-fx-font-weight: bold");

        received[0] = buildLabel(root, null, x * 0.802, y * 0.86, Font.font(16), true, "-fx-font-weight: bold");
        received[1] = buildLabel(root, null, x * 0.855, y * 0.86, Font.font(16), true, "-fx-font-weight: bold");
        received[2] = buildLabel(root, null, x * 0.905, y * 0.86, Font.font(16), true, "-fx-font-weight: bold");
        received[3] = buildLabel(root, null, x * 0.955, y * 0.86, Font.font(16), true, "-fx-font-weight: bold");

        Line line = new Line(x * 0.79, y * 0.89, x * 0.98, y * 0.89);
        root.getChildren().add(line);
    }

    public void addToUnderBar(int requiredCows,
                              int requiredHens, int requiredSheep, int requiredClothes,
                              int requiredCakes, int requiredCookies, int requiredDresses,
                              int requiredEggs, int requiredEggPowders, int requiredFibers,
                              int requiredFlours, int requiredMilks, int requiredWools) {
        if (requiredCows != 0) {
            list.add("Cow");
            imageViews[list.size() - 1].setImage(new Image("File:Textures\\UI\\Icons\\Products\\cow.png"));
            required[list.size() - 1].setText(String.valueOf(requiredCows));
            received[list.size() - 1].setText(String.valueOf(0));
        }
        if (requiredHens != 0) {
            list.add("Hen");
            imageViews[list.size() - 1].setImage(new Image("File:Textures\\UI\\Icons\\Products\\File:chicken.png"));
            required[list.size() - 1].setText(String.valueOf(requiredHens));
            received[list.size() - 1].setText(String.valueOf(0));
        }
        if (requiredSheep != 0) {
            list.add("Sheep");
            imageViews[list.size() - 1].setImage(new Image("File:Textures\\UI\\Icons\\Products\\sheep.png"));
            required[list.size() - 1].setText(String.valueOf(requiredSheep));
            received[list.size() - 1].setText(String.valueOf(0));
        }
        if (requiredClothes != 0) {
            list.add("Cloth");
            imageViews[list.size() - 1].setImage(new Image("File:Textures\\Products\\Fabric.png"));
            required[list.size() - 1].setText(String.valueOf(requiredClothes));
            received[list.size() - 1].setText(String.valueOf(0));
        }
        if (requiredCakes != 0) {
            list.add("Cake");
            imageViews[list.size() - 1].setImage(new Image("File:Textures\\Products\\Cake.png"));
            required[list.size() - 1].setText(String.valueOf(requiredCakes));
            received[list.size() - 1].setText(String.valueOf(0));
        }
        if (requiredCookies != 0) {
            list.add("Cookie");
            imageViews[list.size() - 1].setImage(new Image("File:Textures\\Products\\Cake.png"));
            required[list.size() - 1].setText(String.valueOf(requiredCookies));
            received[list.size() - 1].setText(String.valueOf(0));
        }
        if (requiredDresses != 0) {
            list.add("Dress");
            imageViews[list.size() - 1].setImage(new Image("File:Textures\\Products\\CarnivalDress.png"));
            required[list.size() - 1].setText(String.valueOf(requiredDresses));
            received[list.size() - 1].setText(String.valueOf(0));
        }
        if (requiredEggs != 0) {
            list.add("Egg");
            imageViews[list.size() - 1].setImage(new Image("File:Textures\\Products\\Egg\\normal_1.png"));
            required[list.size() - 1].setText(String.valueOf(requiredEggs));
            received[list.size() - 1].setText(String.valueOf(0));
        }
        if (requiredEggPowders != 0) {
            list.add("EggPowder");
            imageViews[list.size() - 1].setImage(new Image("File:Textures\\Products\\EggPowder.png"));
            required[list.size() - 1].setText(String.valueOf(requiredEggPowders));
            received[list.size() - 1].setText(String.valueOf(0));
        }
        if (requiredFibers != 0) {
            list.add("Fiber");
            imageViews[list.size() - 1].setImage(new Image("File:Textures\\Products\\Sewing.png"));
            required[list.size() - 1].setText(String.valueOf(requiredFibers));
            received[list.size() - 1].setText(String.valueOf(0));
        }
        if (requiredFlours != 0) {
            list.add("Flour");
            imageViews[list.size() - 1].setImage(new Image("File:Textures\\Products\\Flour.png"));
            required[list.size() - 1].setText(String.valueOf(requiredFlours));
            received[list.size() - 1].setText(String.valueOf(0));
        }
        if (requiredMilks != 0) {
            list.add("Milk");
            imageViews[list.size() - 1].setImage(new Image("File:Textures\\Products\\Milk.png"));
            required[list.size() - 1].setText(String.valueOf(requiredMilks));
            received[list.size() - 1].setText(String.valueOf(0));
        }
        if (requiredWools != 0) {
            list.add("Wool");
            imageViews[list.size() - 1].setImage(new Image("File:Textures\\Products\\Wool\\normal_3.png"));
            required[list.size() - 1].setText(String.valueOf(requiredWools));
            received[list.size() - 1].setText(String.valueOf(0));
        }
    }

    public void increaseLabelText(Label label) {
        int x = Integer.parseInt(label.getText()) + 1;
        label.setText(String.valueOf(x));
    }

    public int whatIndexOfListEqualsTo(String s) {
        for (String text : list) {
            if (text == s) {
                return list.indexOf(text);
            }
        }
        return -1;
    }

    public String getLastReceivedProductName() {

        return null;
    }
}
