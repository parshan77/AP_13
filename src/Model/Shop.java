package Model;

import Interfaces.VisibleOutOfMap;
import Model.Animals.Seekers.Cat;
import Model.Animals.Seekers.Dog;
import Model.Vehicles.Helicopter;
import Model.Workshops.*;

import java.util.ArrayList;
import java.util.HashSet;

public class Shop implements VisibleOutOfMap {
    private ArrayList things = new ArrayList();

    public ArrayList getThings() {
        return things;
    }
    @Override
    public void show() {
    }

    public void buy(String name) {
        if (name == "Cat") {
            things.add(new Cat());
        }
        else if (name == "Dog") {
            /*check number
            check stars?
            stars;--*/
            things.add(new Dog());

        }
        else if (name == "Cage") {
            things.add(new Cage());////////???????????????
        }
        else if (name == "Warehouse") {
            things.add(new Warehouse());
        }
        else if (name == "Helicopter") {
            things.add(new Helicopter());
        }
        else if (name == "Truck") {
            things.add(new Helicopter());
        }
        else if (name == "EggPowderPlant") {
            things.add(new EggPowderPlant());
        }
        else if (name == "CookieBakery") {
            things.add(new CookieBakery());
        }
        else if (name == "CakeBakery") {
            things.add(new CakeBakery());
        }
        else if (name == "Spinnery") {
            things.add(new Spinnery());
        }
        else if (name == "WeavingFactory") {
            things.add(new WeavingFactory());
        }
        else if (name == "SewingFactory") {
            things.add(new SewingFactory())
        }
        else if (name == "Well") {
            things.add(new Well());
        }
    }


}
