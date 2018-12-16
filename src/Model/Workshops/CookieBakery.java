package Model.Workshops;

import Model.Player;
import Model.Products.Cookie;
import Model.Products.Flour;
import Model.Warehouse;

public class CookieBakery extends Workshop<Flour, Cookie> {

    public CookieBakery(Warehouse warehouse, Class<Flour> inputClazz, Class<Cookie> outputClazz, Player player) {
        super(warehouse, inputClazz, outputClazz, player);
    }

    @Override
    public void show() {

    }
}
