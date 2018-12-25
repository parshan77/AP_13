package Model;

import Exceptions.MaxLevelExceededException;
import Exceptions.NotEnoughMoneyException;
import Interfaces.VisibleOutOfMap;
import Model.Animals.Seekers.Cat;
import Model.Animals.Seekers.Dog;
import Model.Vehicles.Helicopter;
import Model.Vehicles.Truck;
import Model.Workshops.*;

import java.util.ArrayList;
import java.util.HashSet;

public class Shop implements VisibleOutOfMap {

    @Override
    public void show() {

    }

/*
    public static void upgrade(String name, Mission mission) throws MaxLevelExceededException, NotEnoughMoneyException {
        if (name.equals("Cat")) {
           mission.getCat().upgrade();
        }
        else if (name.equals("Cage")) {
            mission.getCage().upgrade();
        }
        else if (name.equals("Warehouse")) {
            mission.getWarehouse().upgrade();
        }
        else if (name.equals("Helicopter")) {
            mission.getHelicopter().upgrade();
        }
        else if (name.equals("Truck")) {
            mission.getTruck().upgrade();
        }
        else if (name.equals("EggPowderPlant")) {
            mission.getEggPowderPlant().upgrade();
        }
        else if (name.equals("CookieBakery")) {
            mission.getCookieBakery().upgrade();
        }
        else if (name.equals("CakeBakery")) {
            mission.getCakeBakery().upgrade();
        }
        else if (name.equals("Spinnery")) {
            mission.getSpinnery().upgrade();
        }
        else if (name.equals("WeavingFactory")) {
            mission.getWeavingFactory().upgrade();
        }
        else if (name.equals("SewingFactory")) {
            mission.getSewingFactory().upgrade();
        }
        else if (name.equals("Well")) {
            mission.getWell().upgrade();
        }
        else if (name.equals("CostomWorkshop"))
            mission.getCostumeWorkshop().upgrade();
    }
*/

}
