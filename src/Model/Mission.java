package Model;

import Exceptions.NotEnoughMoneyException;
import Exceptions.NotFoundException;
import Interfaces.Upgradable;
import Model.Placement.Map;
import Model.TimeDependentRequests.TimeDependentRequest;
import Model.Vehicles.Helicopter;
import Model.Vehicles.Truck;
import Model.Workshops.*;

import java.util.ArrayList;

public class Mission {
    private int money;
    private int timeNow = 0;
    private ArrayList<TimeDependentRequest> remainedRequests = new ArrayList<>();
    private String name;

    private LevelRequirementsChecker levelRequirementsChecker;
    private Map map = new Map();
    private Warehouse warehouse = new Warehouse(this);

    private CakeBakery cakeBakery = new CakeBakery(this, warehouse);
    private CookieBakery cookieBakery = new CookieBakery(this, warehouse);
    private EggPowderPlant eggPowderPlant = new EggPowderPlant(this, warehouse);
    private SewingFactory sewingFactory = new SewingFactory(this, warehouse);
    private Spinnery spinnery = new Spinnery(this, warehouse);
    private WeavingFactory weavingFactory = new WeavingFactory(this, warehouse);
    private CostumeWorkshop costumeWorkshop;// TODO: 12/25/2018

    private Helicopter helicopter = new Helicopter(this);
    private Truck truck = new Truck(this);
    private Well well = new Well(this);

    public Mission(int money, String name, LevelRequirementsChecker levelRequirementsChecker,
                   CostumeWorkshop costumeWorkshop) {
        this.money = money;
        this.name = name;
        this.levelRequirementsChecker = levelRequirementsChecker;
        this.costumeWorkshop = costumeWorkshop;
    }

    public void addTimeDependentRequest(TimeDependentRequest request) {
        remainedRequests.add(request);
    }

    private void clock() {
        ArrayList<TimeDependentRequest> mustBeRemoved = new ArrayList<>();
        timeNow++;
        for (TimeDependentRequest request : remainedRequests) {
            request.clock();
            if (request.getTurnsRemained() == 0) request.run();
            mustBeRemoved.add(request);
        }
        remainedRequests.removeAll(mustBeRemoved);
    }

    public LevelRequirementsChecker getLevelRequirementsChecker() {
        return levelRequirementsChecker;
    }

    public String getName() {
        return name;
    }

    public Workshop getWorkshop(String workshopName) throws NotFoundException {
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
        throw new NotFoundException();
    }

    public Upgradable getUpgradableUnit(String unitName) throws NotFoundException {
        //todo:cat ro bar nemigardunim -> joda check beshe
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
        throw new NotFoundException();
    }

    public void passSeveralTurn(int passedTurnsNumber) {
        for (int i = 0; i < passedTurnsNumber; i++)
            clock();
    }

    public void addMoney(long amount) {
        money += amount;
    }

    public void spendMoney(int amount) throws NotEnoughMoneyException {
        if (amount > money)
            throw new NotEnoughMoneyException();
        money -= amount;
    }

    public int getTimeNow() {
        return timeNow;
    }

    public int getMoney() {
        return money;
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

}
