package Controller;

import Exceptions.WellNotEnoughWaterException;
import Model.Mission;
import Model.Placement.Position;
import Model.Plant;
import Model.Well;
import View.Animations.BuzzAnimation;
import View.Animations.GrassAnimation;
import View.CellViewer;
import View.GamePlayView;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static Model.Placement.Map.MAP_SIZE;

public class MapController {


    public static void plant(GamePlayView gamePlayView, int row, int column) {
        Well well = gamePlayView.getMission().getWell();
        int minRow = Math.max(0, row - 1);
        int minColumn = Math.max(0, column - 1);
        int maxRow = Math.min(MAP_SIZE - 1, row + 1);
        int maxColumn = Math.min(MAP_SIZE - 1, column + 1);

        int numberOfPlantsPlanted = calculatePlantsNeeded(gamePlayView, row, column);
        if (numberOfPlantsPlanted == 0) {
            //yani hich planti nemishe kasht -> hameja pore
            return;
        }

        if (!WellController.getWater(gamePlayView))
            return;

        for (int row1 = minRow; row1 <= maxRow; row1++)
            for (int column1 = minColumn; column1 <= maxColumn; column1++) {
                CellViewer cellViewer = gamePlayView.getCellViewer(row1, column1);
                if (!gamePlayView.getMission().getMap().isPlanted(row1, column1)) {
                    Plant plant = new Plant(new Position(row1, column1));
                    gamePlayView.getMission().getMap().addToMap(plant);
                    String url = "File:Textures\\Grass\\grass1.png";
                    Image image = new Image(url);
                    ImageView imageView = new ImageView(image);
                    imageView.setViewport(new Rectangle2D(0,0,
                            image.getWidth() / 4, image.getHeight() / 4));
                    imageView.relocate(cellViewer.getCellCenterX() - 20, cellViewer.getCellCenterY()- 30);
                    cellViewer.setGrassImageView(imageView);
                    gamePlayView.getRoot().getChildren().add(imageView);
                    GrassAnimation.play(imageView);
                }
            }
    }

    private static int calculatePlantsNeeded(GamePlayView gamePlayView, int row, int column) {
        Mission mission = gamePlayView.getMission();
        int minRow = Math.max(0, row - 1);
        int minColumn = Math.max(0, column - 1);
        int maxRow = Math.min(MAP_SIZE - 1, row + 1);
        int maxColumn = Math.min(MAP_SIZE - 1, column + 1);

        int numberOfPlantsPlanted = 0;
        for (int row1 = minRow; row1 <= maxRow; row1++)
            for (int column1 = minColumn; column1 <= maxColumn; column1++)
                if (!mission.getMap().isPlanted(row1, column1))
                    numberOfPlantsPlanted++;

        return numberOfPlantsPlanted;
    }
}
