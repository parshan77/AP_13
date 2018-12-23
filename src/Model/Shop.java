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

    public static void upgrade(String name, Mission mission) throws MaxLevelExceededException, NotEnoughMoneyException {
        if (name == "Cat") {
           mission.getCat().upgrade();
        }
        else if (name == "Cage") {
            mission.getCage().upgrade();
        }
        else if (name == "Warehouse") {
            mission.getWarehouse().upgrade();
        }
        else if (name == "Helicopter") {
            mission.getHelicopter().upgrade();
        }
        else if (name == "Truck") {
            mission.getTruck().upgrade();
        }
        else if (name == "EggPowderPlant") {
            mission.getEggPowderPlant().upgrade();
        }
        else if (name == "CookieBakery") {
            mission.getCookieBakery().upgrade();
        }
        else if (name == "CakeBakery") {
            mission.getCakeBakery().upgrade();
        }
        else if (name == "Spinnery") {
            mission.getSpinnery().upgrade();
        }
        else if (name == "WeavingFactory") {
            mission.getWeavingFactory().upgrade();
        }
        else if (name == "SewingFactory") {
            mission.getSewingFactory().upgrade();
        }
        else if (name == "Well") {
            mission.getWell().upgrade();
        }
        else if (name =="CostomWorkshop")
            mission.getCostumeWorkshop().upgrade();
    }

}
