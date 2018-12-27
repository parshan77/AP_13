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

    private CakeBakery cakeBakery = new CakeBakery(this);
    private CookieBakery cookieBakery = new CookieBakery(this);
    private EggPowderPlant eggPowderPlant = new EggPowderPlant(this);
    private SewingFactory sewingFactory = new SewingFactory(this);
    private Spinnery spinnery = new Spinnery(this);
    private WeavingFactory weavingFactory = new WeavingFactory(this);
    private CustomWorkshop customWorkshop;// TODO: 12/25/2018

    private Helicopter helicopter = new Helicopter(this);
    private Truck truck = new Truck(this);
    private Well well = new Well(this);

    public Mission(int money, String name, LevelRequirementsChecker levelRequirementsChecker,
                   CustomWorkshop customWorkshop) {
        this.money = money;
        this.name = name;
        this.levelRequirementsChecker = levelRequirementsChecker;
        this.customWorkshop = customWorkshop;
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

    LevelRequirementsChecker getLevelRequirementsChecker() {
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
            case "customworkshop":
                return customWorkshop;
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
            case "customworkshop":
                return customWorkshop;
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

    public void passSeveralTurns(int passedTurnsNumber) {
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
}
