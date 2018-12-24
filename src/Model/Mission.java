package Model;

import Exceptions.NotEnoughMoneyException;
import Interfaces.Upgradable;
import Model.Animals.Seekers.Cat;
import Model.Animals.Seekers.Dog;
import Model.Screen.Map;
import Model.Requests.Request;
import Model.Vehicles.Helicopter;
import Model.Vehicles.Truck;
import Model.Workshops.*;

import java.util.ArrayList;

public class Mission {
    private long money = 0;
    private long timeNow = 0;
    private ArrayList<Request> requests = new ArrayList<>();
    private int stars = 0;  //baraye shop va kharide kargah o ina be kar miad

    private Map map = new Map();
    private CakeBakery cakeBakery = new CakeBakery(this,warehouse);
    private CookieBakery cookieBakery = new CakeBakery(this, warehouse);
    private CostumeWorkshop costumeWorkshop ;
    private EggPowderPlant eggPowderPlant;
    private SewingFactory sewingFactory;
    private Spinnery spinnery;
    private WeavingFactory weavingFactory;
    private Helicopter helicopter = new Helicopter(this);//todo:chera constructoresh private E!
    private Truck truck = new Truck(this);//todo: inam hamintor!
    private Warehouse warehouse = new Warehouse(this);
    private Well well = new Well(this);
    private Dog dog;
    private Cat cat;



    public Workshop getWorkshop(String workshopName) {
        switch (workshopName.toLowerCase()) {
            case "cakebakery":
                return cakeBakery;
            case "cookiebakery":
                return cookieBakery;
            case "costumeworkshop":
                return costumeWorkshop;
            case "eggpowderplant":
                return eggPowderPlant;
            case "sewingfactory":
                return sewingFactory;
            case "spinnery":
                return spinnery;
            case "weavingfactory":
                return weavingFactory;
        }
        return null;
    }

    public Upgradable getUpgradableUnit(String unitName) {
        //todo:cat ro bar nemigardunim
        switch (unitName.toLowerCase()) {
            case "cakebakery":
                return cakeBakery;
            case "cookiebakery":
                return cookieBakery;
            case "costumeworkshop":
                return costumeWorkshop;
            case "eggpowderplant":
                return eggPowderPlant;
            case "sewingfactory":
                return sewingFactory;
            case "spinnery":
                return spinnery;
            case "weavingfactory":
                return weavingFactory;
            case "truck":
                return truck;
            case "helicopter":
                return helicopter;
            case "warehouse":
                return warehouse;
            case "well":
                return well;
        }
        return null;
    }

    public Map getMap() {
        return map;
    }

    public Helicopter getHelicopter() {
        return helicopter;
    }

    public Truck getTruck() {
        return truck;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public Well getWell() {
        return well;
    }

    public CakeBakery getCakeBakery() {
        return cakeBakery;
    }

    public CookieBakery getCookieBakery() {
        return cookieBakery;
    }

    public CostumeWorkshop getCostumeWorkshop() {
        return costumeWorkshop;
    }

    public EggPowderPlant getEggPowderPlant() {
        return eggPowderPlant;
    }

    public SewingFactory getSewingFactory() {
        return sewingFactory;
    }

    public Spinnery getSpinnery() {
        return spinnery;
    }

    public WeavingFactory getWeavingFactory() {
        return weavingFactory;
    }

    private boolean isMissionCompleted() {
        //todo
        return true;
    }

    public void passTurnRequest(int timeNow) {
        for (int i = 0; i < timeNow; i++) {
            clock();
        }
    }

    public void clock() {
        ArrayList<Request> mustBeRemoved = new ArrayList<>();
        timeNow++;
        for (Request request : requests) {
            request.clock();
            if (request.getTurnsRemained() == 0) request.run();
            mustBeRemoved.add(request);
        }
        requests.removeAll(mustBeRemoved);
        mustBeRemoved.clear();
    }

    public void addMoney(long amount) {
        money += amount;
    }

    public void spendMoney(long amount) throws NotEnoughMoneyException {
        if (amount > money)
            throw new NotEnoughMoneyException();
        money -= amount;
    }

    public long getMoney() {
        return money;
    }
}
