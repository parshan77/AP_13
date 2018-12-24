package Model;

import Exceptions.NotEnoughMoneyException;
import Interfaces.Upgradable;
import Model.Placement.Map;
import Model.TimeDependentRequests.TimeDependentRequest;
import Model.Vehicles.Helicopter;
import Model.Vehicles.Truck;
import Model.Workshops.*;

import java.util.ArrayList;

public class Mission {
    private long money ;
    private long timeNow = 0;
    private ArrayList<TimeDependentRequest> requests = new ArrayList<>();
    private String name;

    private LevelRequirementsChecker levelRequirementsChecker;
    private Map map = new Map();
    private Warehouse warehouse = new Warehouse(this);
    private CakeBakery cakeBakery = new CakeBakery(this,warehouse);
    private CookieBakery cookieBakery = new CookieBakery(this, warehouse);
    private CostumeWorkshop costumeWorkshop ;
    private EggPowderPlant eggPowderPlant;
    private SewingFactory sewingFactory;
    private Spinnery spinnery;
    private WeavingFactory weavingFactory;
    private Helicopter helicopter = new Helicopter(this);//todo:chera constructoresh private E!
    private Truck truck = new Truck(this);//todo: inam hamintor!
    private Well well = new Well(this);

    public Mission(long money, String name, LevelRequirementsChecker levelRequirementsChecker) {
        this.money = money;
        this.name = name;
        this.levelRequirementsChecker = levelRequirementsChecker;
    }

    public void addTimeDependentRequest(TimeDependentRequest request) {
        requests.add(request);
    }

    public void clock() {
        ArrayList<TimeDependentRequest> mustBeRemoved = new ArrayList<>();
        timeNow++;
        for (TimeDependentRequest request : requests) {
            request.clock();
            if (request.getTurnsRemained() == 0) request.run();
            mustBeRemoved.add(request);
        }
        requests.removeAll(mustBeRemoved);
    }

    public LevelRequirementsChecker getLevelRequirementsChecker() {
        return levelRequirementsChecker;
    }

    public String getName() {
        return name;
    }

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
