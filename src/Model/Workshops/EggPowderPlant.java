package Model.Workshops;

import Model.Products.ProcessedProducts.Flour;
import Model.Products.RawProducts.Egg;
import Model.Warehouse;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EggPowderPlant extends Workshop {

    public EggPowderPlant(Warehouse warehouse, String name) {
        super(warehouse, name);
    }

    public ArrayList<Flour>  process(ArrayList<Egg> Eggs){
        if(isMoneyEnought){
            ArrayList<Flour> Flours = new ArrayList<>();
            //TODO :  process bayad takmil beshe.aslan be ezaye chand ta Egg,chand ta Flour mide?
            return Flours;
        }
        else
            return null;
    }
}
