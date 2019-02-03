package View;

import Controller.MapController;
import Model.Placement.Cell;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class CellViewer {
    private Cell cell;
    private Rectangle rectangle;
    private GamePlayView gamePlayView;
    private ImageView grassImageView;
    private int row;
    private int column;

    public CellViewer(GamePlayView gamePlayView, int row, int column) {
        this.row = row;
        this.column = column;
        cell = gamePlayView.getMission().getMap().getCell(row, column);

        this.gamePlayView = gamePlayView;
        show();
    }

    public void setGrassImageView(ImageView grassImageView) {
        this.grassImageView = grassImageView;
    }

    private void show() {
        rectangle = new Rectangle(gamePlayView.getMapX() + column * gamePlayView.getCellWidth(),
                gamePlayView.getMapY() + row * gamePlayView.getCellHeight(),
                gamePlayView.getCellWidth(), gamePlayView.getCellHeight());
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeType(StrokeType.CENTERED);
        rectangle.setStrokeWidth(2);
        gamePlayView.getRoot().getChildren().add(rectangle);
        rectangle.setOnMouseClicked(event -> MapController.plant(gamePlayView, row, column));
    }

    public ImageView getGrassImageView() {
        return grassImageView;
    }

    public int getCellCenterX() {
        return gamePlayView.getMapX() + gamePlayView.getCellWidth() / 2 + column * gamePlayView.getCellWidth();
    }

    public int getCellCenterY() {
        return gamePlayView.getMapY() + gamePlayView.getCellHeight() / 2 + row * gamePlayView.getCellHeight();
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public GamePlayView getGamePlayView() {
        return gamePlayView;
    }

    public void setGamePlayView(GamePlayView gamePlayView) {
        this.gamePlayView = gamePlayView;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
