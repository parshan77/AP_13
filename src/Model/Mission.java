package Model;

import Exceptions.MaxLevelExceededException;
import Exceptions.NotEnoughMoneyException;
import Exceptions.NotFoundException;
import Interfaces.Upgradable;
import Model.Animals.Seekers.Cat;
import Model.Placement.Map;
import Model.TimeDependentRequests.*;
import Model.Vehicles.Helicopter;
import Model.Vehicles.Truck;
import Model.Workshops.*;
import View.GamePlayView;

import java.util.ArrayList;

public class Mission {

    private ArrayList<TimeDependentRequest> remainedRequests = new ArrayList<>();
    private ArrayList<DomesticMovingRequest> domesticsMovingRequests = new ArrayList<>();

    private int money;
//    private int timeNow = 0;
    private String name;
    private int catsBeginningLevel = 0;
    private boolean isCompleted = false;

    private LevelRequirementsChecker levelRequirementsChecker;
    private Map map = new Map(this);
    private Warehouse warehouse = new Warehouse(this);

    private CakeBakery cakeBakery = new CakeBakery(this);
    private CookieBakery cookieBakery = new CookieBakery(this);
    private EggPowderPlant eggPowderPlant = new EggPowderPlant(this);
    private SewingFactory sewingFactory = new SewingFactory(this);
    private Spinnery spinnery = new Spinnery(this);
    private WeavingFactory weavingFactory = new WeavingFactory(this);
    private CustomWorkshop customWorkshop;

    private Helicopter helicopter = new Helicopter(this);
    private Truck truck = new Truck(this);
    private Well well = new Well(this);

    private GamePlayView gamePlayView;

    public Mission(int startingMoney, String name, LevelRequirementsChecker levelRequirementsChecker,
                   CustomWorkshop customWorkshop, GamePlayView gamePlayView) {
        this.gamePlayView = gamePlayView;
        this.money = startingMoney;
        this.name = name;
        this.levelRequirementsChecker = levelRequirementsChecker;
        this.customWorkshop = customWorkshop;

        remainedRequests.add(new MakeDomesticsHungryRequest(this));
        remainedRequests.add(new PutWildAnimalInMapRequest(this));
    }


    public void clock() {
        ArrayList<DomesticMovingRequest> domesticsMovingRequestsCopy = new ArrayList<>(domesticsMovingRequests);
        for (DomesticMovingRequest request : domesticsMovingRequests) {
            request.run();
        }
        domesticsMovingRequests.removeAll(domesticsMovingRequestsCopy);

        ArrayList<TimeDependentRequest> finishedRequests = new ArrayList<>();
        for (TimeDependentRequest request : remainedRequests) {
            request.clock();
            if (request.getTurnsRemained() == 0) {
                finishedRequests.add(request);
            }
        }
        for (TimeDependentRequest request : finishedRequests) {
            request.run();
        }
        remainedRequests.removeAll(finishedRequests);
    }

    public void removeDomesticMovingRequest(DomesticMovingRequest request) {
        domesticsMovingRequests.remove(request);
    }

    public void addDomesticMovementRequest(DomesticMovingRequest movementRequest) {
        domesticsMovingRequests.add(movementRequest);
    }

    public void removeTimeDependentRequest(TimeDependentRequest timeDependentRequest) throws NotFoundException {
        if(!remainedRequests.remove(timeDependentRequest))
            throw new NotFoundException();
    }

    public GamePlayView getGamePlayView() {
        return gamePlayView;
    }

    public void setCustomWorkshop(CustomWorkshop customWorkshop) {
        this.customWorkshop = customWorkshop;
    }

    public CustomWorkshop getCustomWorkshop() {
        return customWorkshop;
    }

    public void setMissionAsCompleted() {
        this.isCompleted = true;
    }

    boolean isCompleted() {
        return isCompleted;
    }

    public ArrayList<TimeDependentRequest> getRemainedRequests() {
        return remainedRequests;
    }

    public int getCatsBeginningLevel() {
        return catsBeginningLevel;
    }

    public void increaseCatsBeginningLevel() throws MaxLevelExceededException {
        if (catsBeginningLevel == Cat.CAT_MAX_LEVEL)
            throw new MaxLevelExceededException();
        catsBeginningLevel++;
    }

    public void addTimeDependentRequest(TimeDependentRequest request) {
        remainedRequests.add(request);
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
        return null;
    }

    public ArrayList<Workshop> getAllWorkshops() {
        ArrayList<Workshop> workshops = new ArrayList<>();
        workshops.add(cakeBakery);
        workshops.add(cookieBakery);
        workshops.add(customWorkshop);
        workshops.add(eggPowderPlant);
        workshops.add(sewingFactory);
        workshops.add(spinnery);
        workshops.add(weavingFactory);
        return workshops;
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

    public void addMoney(int amount) {
        money += amount;
        int moneyBefore = Integer.parseInt(gamePlayView.getMoneyLabel().getText());
        gamePlayView.getMoneyLabel().setText(Integer.toString(moneyBefore + amount));
    }

    public void spendMoney(int amount) throws NotEnoughMoneyException {
        if (amount > money)
            throw new NotEnoughMoneyException();
        money -= amount;
        gamePlayView.getMoneyLabel().setText(Integer.toString(money));
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
