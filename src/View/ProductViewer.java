package View;

import Controller.WarehouseController;
import Model.Products.Product;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ProductViewer {
    private GamePlayView gamePlayView;
    private ImageView imageView;
    private Product product;

    public ProductViewer(GamePlayView gamePlayView, Product product) {
        this.gamePlayView = gamePlayView;
        this.product = product;
        showProduct();
    }

    private void showProduct() {
        String url = "File:Textures\\Product\\" + product.getName() + ".png";
        Image image = new Image(url);
        imageView = new ImageView(image);
        imageView.setScaleX(0.7);
        imageView.setScaleY(0.7);

        int row = product.getPosition().getRow();
        int column = product.getPosition().getColumn();
        int x = gamePlayView.getCellCenterX(row, column) - (int) image.getWidth() / 2;
        int y = gamePlayView.getCellCenterY(row, column) - (int) image.getHeight() / 2;

        imageView.relocate(x, y);
        imageView.setOnMouseClicked(event -> WarehouseController.storeOneProduct(this));
        gamePlayView.getRoot().getChildren().add(imageView);
    }

    public GamePlayView getGamePlayView() {
        return gamePlayView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Product getProduct() {
        return product;
    }
}
