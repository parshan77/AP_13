package Model;

import Exceptions.*;
import Interfaces.Storable;
import Interfaces.Upgradable;
import Interfaces.VisibleInMap;
import Model.Animals.Domestics.Cow;
import Model.Animals.Domestics.Hen;
import Model.Animals.Domestics.Sheep;
import Model.Animals.Predator;
import Model.Animals.Seekers.Cat;
import Model.Animals.Seekers.Dog;
import Model.Placement.Direction;
import Model.Placement.Position;
import Model.Products.Product;
import Model.TimeDependentRequests.*;
import Model.Vehicles.Helicopter;
import Model.Vehicles.Truck;
import Model.Workshops.Workshop;
import Utils.Utils;

import java.util.ArrayList;
import java.util.Scanner;

import static Model.Placement.Map.MAP_SIZE;

public class MissionRunner {
    private Mission mission;

    public MissionRunner(Mission mission) throws MissionCompletedException, ExitMissionException {
        this.mission = mission;
        runMission();
    }

    private void runMission() throws MissionCompletedException, ExitMissionException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toLowerCase();
        while (!input.toLowerCase().equals("exit")) {

            String[] splittedInput = input.split(" ");
            switch (splittedInput[0]) {
                //todo: switch case baraye string ha kar mikone( .equals va == )
                case "buy":
                    buyAnimalRequestHandler(splittedInput[1]);
                    break;

                case "pickup":
                    int row = Integer.parseInt(splittedInput[1]);
                    int column = Integer.parseInt(splittedInput[2]);
                    try {
                        pickupRequestController(row, column);
                    } catch (MissionCompletedException e) {
                        mission.setMissionAsCompleted();
                    }
                    break;

                case "plant":
                    row = Integer.parseInt(splittedInput[1]);
                    column = Integer.parseInt(splittedInput[2]);
                    plantRequestHandler(row, column);
                    break;

                case "cage":
                    row = Integer.parseInt(splittedInput[1]);
                    column = Integer.parseInt(splittedInput[2]);
                    cageAnimalController(row, column);
                    break;

                case "well":
                    wellRequestHandler();
                    break;

                case "start":
                    startWorkshopRequestHandler(splittedInput[1]);
                    break;

                case "upgrade":
                    upgradeRequestHandler(splittedInput[1]);
                    break;

                case "print":
                    printRequestHandler(splittedInput[1], mission.getLevelRequirementsChecker());
                    break;

                case "turn":
                    turnRequestHandler(Integer.parseInt(splittedInput[1]));
                    break;

                case "truck":
                    switch (splittedInput[1]) {
                        case "add":
                            addToTruckListRequestHandler(splittedInput[2], Integer.parseInt(splittedInput[3]));
                            break;
                        case "clear":
                            clearTruckListRequestHandler();
                            break;
                        case "go":
                            goTruckRequestHandler();
                            break;
                    }
                    break;

                case "helicopter":
                    switch (splittedInput[1]) {
                        case "add":
                            addToHelicopterListRequestHandler(splittedInput[2], Integer.parseInt(splittedInput[3]));
                            break;
                        case "clear":
                            clearHelicopterListRequestHandler();
                            break;
                        case "go":
                            goHelicopterRequestHandler();
                            break;
                    }
                    break;
            }

            if (mission.isCompleted()) {
                System.out.println("Mission is completed!");
                throw new MissionCompletedException();
            }

            input = scanner.nextLine().toLowerCase();
            if (input.toLowerCase().equals("exit")) {
                System.out.println("are you sure?(y/n)");
                if (scanner.nextLine().toLowerCase().equals("y"))
                    throw new ExitMissionException();
            }
        }
    }


    private void startWorkshopRequestHandler(String workshopName) {
        Workshop workshop;
        try {
            workshop = mission.getWorkshop(workshopName);
        } catch (NotFoundException e) {
            System.out.println("entered name isn't a valid name.");
            return;
        }

        if (workshop == null) {
            System.out.println("there isn't any custom workshop in this mission.");
        }

        ArrayList<Product> inputs;
        try {
            inputs = workshop.collectInputs();      //intellij eshtebah mikone
        } catch (NotEnoughResourcesException e) {
            System.out.println("there isn't enough resources to start workshop.");
            return;
        }
        mission.addTimeDependentRequest(new StartWorkshopRequest(workshop, inputs));
    }

    private void goTruckRequestHandler() {
        Truck truck = mission.getTruck();

        if (isVehicleOnWay("truck")) {
            System.out.println("Truck is on its way.");
            return;
        }

        if (truck.getList().isEmpty()) {
            System.out.println("Truck list is empty.");
            return;
        }
        mission.addTimeDependentRequest(new TruckGoingRequest(mission));
    }

    private void goHelicopterRequestHandler() {
        Helicopter helicopter = mission.getHelicopter();

        if (isVehicleOnWay("helicopter")) {
            System.out.println("Helicopter is on its way");
            return;
        }

        if (helicopter.getList().isEmpty()) {
            System.out.println("Helicopter List is empty.");
            return;
        }

        int cost = helicopter.calculateCost();
        try {
            mission.spendMoney(cost);
        } catch (NotEnoughMoneyException e) {
            System.out.println("you don't have enough money to buy these items.");
            return;
        }

        mission.addTimeDependentRequest(new HelicopterGoingRequest(mission));
    }

    private boolean isVehicleOnWay(String vehicleName) {
        if (vehicleName.toLowerCase().equals("helicopter")) {
            ArrayList<TimeDependentRequest> remainedRequests = mission.getRemainedRequests();
            for (TimeDependentRequest request : remainedRequests) {
                if ((request instanceof HelicopterGoingRequest) || (request instanceof HelicopterGettingBackRequest)) {
                    return true;
                }
            }
            return false;
        } else /*if (vehicleName.toLowerCase().equals("truck"))*/ {     // TODO: 12/28/2018 esme vasile haro dorost benevis
            ArrayList<TimeDependentRequest> remainedRequests = mission.getRemainedRequests();
            for (TimeDependentRequest request : remainedRequests) {
                if ((request instanceof TruckGoingRequest) || (request instanceof TruckGettingBackRequest)) {
                    return true;
                }
            }
            return false;
        }
    }

    private void clearHelicopterListRequestHandler() {
        if (isVehicleOnWay("helicopter")) {
            System.out.println("Helicopter is on its way. wait till it gets back and try again.");
            return;
        }
        mission.getHelicopter().clearList();
    }

    private void addToHelicopterListRequestHandler(String itemName, int itemCount) {
        int numberOfAddedObjects = 0;
        Helicopter helicopter = mission.getHelicopter();

        if (isVehicleOnWay("helicopter")) {
            System.out.println("Truck is on its way.wait till it gets back and try again.");
            return;
        }

        for (int i = 0; i < itemCount; i++) {
            Product product;
            try {
                product = Utils.getProductObject(itemName);
                helicopter.addToList(product);
                numberOfAddedObjects++;
            } catch (CapacityExceededException e) {
                if (numberOfAddedObjects == 0) {
                    System.out.println("there isn't enough space in helicopter, no items added to helicopter list.");
                    break;
                } else {
                    System.out.printf("there isn't enough space in helicopter, %d %ss added to helicopters list.\n",
                            numberOfAddedObjects, itemName);
                    break;
                }
            } catch (NotFoundException e) {
                System.out.println("not valid item name.");
                break;
            }
        }
    }

    private void clearTruckListRequestHandler() {
        //truck o warehousemun joda an -> momkene natunim list ro khali konim
        Truck truck = mission.getTruck();
        Warehouse warehouse = mission.getWarehouse();

        if (isVehicleOnWay("truck")) {
            System.out.println("Truck is on its way.wait till it gets back and try again.");
            return;
        }

        ArrayList<Storable> itemsInList = truck.getList();
        mission.getTruck().clearList();
        ArrayList<Storable> storedItems = new ArrayList<>();
        for (Storable storable : itemsInList) {
            try {
                warehouse.store(storable);
                storedItems.add(storable);
            } catch (CapacityExceededException e) {
                break;
            } catch (MissionCompletedException e1) {
                e1.printStackTrace();      //nabayad rokh bede
            }
        }
        if (storedItems.isEmpty()) {
            System.out.println("there isn't enough space in warehouse to store items in truck list.");
            return;
        }

        itemsInList.removeAll(storedItems);
        for (Storable storable : itemsInList) {
            try {
                truck.addToList(storable);
            } catch (CapacityExceededException e) {
                e.printStackTrace();        //nabayad rokh bede -> az khodesh gereftim!
            }
        }
    }

    private void addToTruckListRequestHandler(String itemName, int itemCount) {
        int numberOfAddedObjects = 0;

        if (isVehicleOnWay("truck")) {
            System.out.println("Truck is on its way.wait till it gets back and try again.");
            return;
        }

        for (int i = 0; i < itemCount; i++) {
            Storable obj = null;
            try {
                obj = mission.getWarehouse().getAndDiscard(itemName);
                mission.getTruck().addToList(obj);
                numberOfAddedObjects++;
            } catch (NotFoundException e) {
                if (numberOfAddedObjects == 0)
                    System.out.println("This item does not exist in warehouse!");
                else
                    System.out.printf("you only have %d %ss.\n", numberOfAddedObjects, itemName);
                break;

            } catch (CapacityExceededException e) {
                System.out.printf("there isn't enough space in Truck,%d %ss added to list.\n", numberOfAddedObjects, itemName);
                try {
                    mission.getWarehouse().store(obj);
                } catch (CapacityExceededException | MissionCompletedException ignored) {
                }
                break;

            }
        }
    }

    private void turnRequestHandler(int turnsPassed) {
        mission.passSeveralTurns(turnsPassed);
    }

    private void printRequestHandler(String input, LevelRequirementsChecker lrc) {
        boolean requestHandled = false;
        switch (input.toLowerCase()) {
            case "predators":
                for (Predator predator : mission.getMap().getAllPredatorsInMap()) {
                    System.out.print(predator.getName() + ", ");
                }
                System.out.println();
                requestHandled = true;
                break;

            case "requests":
                for (TimeDependentRequest remainedRequest : mission.getRemainedRequests()) {
                    System.out.println(remainedRequest.getClass().getName());
                }
                requestHandled = true;
                break;

            case "info":
                System.out.println("Money :" + mission.getMoney());
                System.out.println("Turn : " + mission.getTimeNow());
                lrc.printInfo();
                requestHandled = true;
                break;

            case "map":
                mission.getMap().print();
                requestHandled = true;
                break;

            case "helicopter":
                mission.getHelicopter().printInfo();
                requestHandled = true;
                break;

            case "truck":
                mission.getTruck().printInfo();
                requestHandled = true;
                break;

            case "well":
                mission.getWell().printInfo();
                requestHandled = true;
                break;

            case "warehouse":
                mission.getWarehouse().printInfo();
                requestHandled = true;
                break;

            case "levels":
                printLevelsRequestHandler();
                // TODO: 12/28/2018
                requestHandled = true;
                break;
        }
        if (!requestHandled) {
            Workshop workshop;
            try {
                workshop = mission.getWorkshop(input);
            } catch (NotFoundException e) {
                System.out.println("not valid request.");
                return;
            }
            workshop.printInfo();
        }

    }

    private void printLevelsRequestHandler() {
        int i = 1;
        for (Cat cat : mission.getMap().getCats()) {
            System.out.println("Cat" + i + "'s Level : " + cat.getLevel());
            i++;
        }
        for (Workshop workshop : mission.getAllWorkshops())
            if (workshop != null)
                System.out.println(workshop.getName() + "'s Level : " + workshop.getLevel());

        System.out.println("Helicopter's Level : " + mission.getHelicopter().getLevel());
        System.out.println("Truck's Level : " + mission.getTruck().getLevel());
        System.out.println("Warehouse's Level : " + mission.getWarehouse().getLevel());
    }

    private void upgradeRequestHandler(String upgradingUnitName) {
        if (upgradingUnitName.toLowerCase().equals("cat")) {
            ArrayList<Cat> catsInMap = mission.getMap().getCats();
            if (catsInMap.isEmpty()) {
                try {
                    mission.increaseCatsBeginningLevel();
                    mission.spendMoney(Cat.getCatUpgradeCost());
                } catch (MaxLevelExceededException e) {
                    System.out.println("cats are at their maximum possible level");
                } catch (NotEnoughMoneyException e) {
                    System.out.println("your money isn't enough.");
                }
            } else {
                try {
                    catsInMap.get(0).upgrade();
                } catch (NotEnoughMoneyException e) {
                    System.out.println("your money isn't enough.");
                } catch (MaxLevelExceededException e) {
                    System.out.println("cats are at their maximum possible level.");
                }
            }
        } else
            try {
                Upgradable upgradableUnit = mission.getUpgradableUnit(upgradingUnitName);
                if (upgradableUnit == null) {   //momkene custom workshop nadashte bashim va gofte bashe uno upgrade kon.
                    System.out.println("this unit doesn't exist.");
                    return;
                }
                upgradableUnit.upgrade();
            } catch (NotEnoughMoneyException e) {
                System.out.printf("Your money is not enough for upgrading %s\n", upgradingUnitName);
            } catch (MaxLevelExceededException maxLevelExceeded) {
                System.out.printf("%s is already at it's max possible level\n", upgradingUnitName);
            } catch (NotFoundException e2) {
                System.out.println("enter a correct name.");
            }
    }

    private void wellRequestHandler() {
        if (!mission.getWell().isEmpty()) {
            System.out.println("Well isn't Empty.");
            return;
        }
        if (mission.getWell().getRefillCost() > mission.getMoney()) {
            System.out.println("Your money isn't enough!");
            return;
        }
        try {
            mission.spendMoney(mission.getWell().getRefillCost());
        } catch (NotEnoughMoneyException e) {
            System.out.println("You don't have enough money to refill well.");
            return;
        }
        mission.addTimeDependentRequest(new RefillWellRequest(mission));
    }

    private void cageAnimalController(int row, int column) {
        ArrayList<Predator> predators = mission.getMap().getPredatorsInCell(row, column);

        if (predators == null) {
            System.out.println("There isn't any predator in that cell");
            return;
        }

        for (Predator predator : predators) {
            Cage cage = new Cage(new Position(row, column), predator);
            mission.getMap().addToMap(cage);
            try {
                mission.getMap().discardAnimal(predator);
            } catch (NotFoundException e) {
                e.printStackTrace();    //chon predator haro az map gereftim nabayad in exception ettefagh biofte
            }
        }
    }

    private void plantRequestHandler(int row, int column) {
        int minRow = Math.max(0, row - 1);
        int minColumn = Math.max(0, column - 1);
        int maxRow = Math.min(MAP_SIZE - 1, row + 1);
        int maxColumn = Math.min(MAP_SIZE - 1, column + 1);

        int numberOfPlantsPlanted = calculatePlantsNeeded(row, column);
        if (numberOfPlantsPlanted == 0) {
            System.out.println("all cells in that area are already planted.");
            return;
        }

        try {
            mission.getWell().extractWater(Plant.PLANTING_NEEDED_WATER);
        } catch (WellNotEnoughWaterException e) {
            System.out.println("refill well first.");
            return;
        }

        for (int row1 = minRow; row1 <= maxRow; row1++)
            for (int column1 = minColumn; column1 <= maxColumn; column1++)
                if (!mission.getMap().isPlanted(row1, column1)) {
                    Plant plant = new Plant(new Position(row1, column1));
                    mission.getMap().addToMap(plant);
                }
    }

    private int calculatePlantsNeeded(int row, int column) {
        int minRow = Math.max(0, row - 1);
        int minColumn = Math.max(0, column - 1);
        int maxRow = Math.min(MAP_SIZE - 1, row + 1);
        int maxColumn = Math.min(MAP_SIZE - 1, column + 1);

        int numberOfPlantsPlanted = 0;
        for (int row1 = minRow; row1 <= maxRow; row1++)
            for (int column1 = minColumn; column1 <= maxColumn; column1++)
                if (!mission.getMap().isPlanted(row1, column1))
                    numberOfPlantsPlanted++;

        return numberOfPlantsPlanted;
    }

    private void pickupRequestController(int row, int column) throws MissionCompletedException {
        Warehouse warehouse = mission.getWarehouse();
        ArrayList<Storable> items = mission.getMap().getAndDiscardProductsAndCagedAnimals(row, column);
        ArrayList<Storable> storedItems = new ArrayList<>();

        if (items.isEmpty()) {
            System.out.println("This cell is empty.");
            return;
        }

        for (Storable item : items) {
            try {
                warehouse.store(item);
                storedItems.add(item);
            } catch (CapacityExceededException e) {
                System.out.println("there isn't enough capacity in warehouse.");
                break;
            }
        }

        items.removeAll(storedItems);
        //age natuneste bashim ye seri az kala haro bar darim(exception) bareshun migardunim be map
        for (Storable item : items)
            mission.getMap().addToMap((VisibleInMap) item);
    }

    private void buyAnimalRequestHandler(String animalName) {
        Direction direction = Utils.getRandomDirection();
        Position position = Utils.getRandomPosition();
        switch (animalName.toLowerCase()) {
            case "cow":
                try {
                    mission.spendMoney(Cow.getBuyCost());
                } catch (NotEnoughMoneyException e) {
                    System.out.println("You don't have enough money!");
                    return;
                }
                Cow cow = new Cow(mission.getMap(), direction, position);
                mission.getMap().addToMap(cow);
                break;

            case "hen":
                try {
                    mission.spendMoney(Hen.getBuyCost());
                } catch (NotEnoughMoneyException e) {
                    System.out.println("You don't have enough money!");
                    return;
                }
                Hen hen = new Hen(mission.getMap(), direction, position);
                mission.getMap().addToMap(hen);
                break;

            case "sheep":
                try {
                    mission.spendMoney(Sheep.getBuyCost());
                } catch (NotEnoughMoneyException e) {
                    System.out.println("You don't have enough money!");
                    return;
                }
                Sheep sheep = new Sheep(mission.getMap(), direction, position);
                mission.getMap().addToMap(sheep);
                break;

            case "cat":
                try {
                    mission.spendMoney(Cat.getBuyCost());
                } catch (NotEnoughMoneyException e) {
                    System.out.println("You don't have enough money!");
                    return;
                }
                Cat cat = new Cat(mission, direction, position);
                mission.getMap().addToMap(cat);
                break;

            case "dog":
                try {
                    mission.spendMoney(Dog.getBuyCost());
                } catch (NotEnoughMoneyException e) {
                    System.out.println("You don't have enough money!");
                    return;
                }
                Dog dog = new Dog(mission.getMap(), direction, position);
                mission.getMap().addToMap(dog);
                break;
        }
    }

}
