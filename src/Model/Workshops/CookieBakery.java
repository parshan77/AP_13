package Model.Workshops;

import Model.Player;
import Model.Products.Cookie;
import Model.Products.Flour;
import Model.Warehouse;

public class CookieBakery extends Workshop {

    public CookieBakery(Player player, Warehouse warehouse) {
        super("CookieBakery", new String[]{"EggPowder"}, "Cookie", player, warehouse);
    }

    @Override
    public void show() {

    }
}
