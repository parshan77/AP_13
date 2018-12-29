package Model;

import Exceptions.MissionCompletedException;
import Interfaces.Storable;
import Model.Animals.Domestic;
import Model.Animals.Domestics.Cow;
import Model.Animals.Domestics.Hen;
import Model.Animals.Domestics.Sheep;
import Model.Products.*;

public class LevelRequirementsChecker {
    //    private HashMap<String, Integer> requirements = new HashMap<>();
    // TODO: 12/25/2018 inhame kharkari lazeme?! rahe sade tar?

    private Mission mission;

    private int requiredCows;
    private int requiredHens;
    private int requiredSheep;

    private int requiredCakes;
    private int requiredClothes;
    private int requiredCookies;
    private int requiredDresses;
    private int requiredEggs;
    private int requiredEggPowders;
    private int requiredFibers;
    private int requiredFlours;
    private int requiredMilks;
    private int requiredWools;

    //    private HashMap<String, Integer> collectedItems = new HashMap<>();
    private int collectedCows = 0;
    private int collectedHens = 0;
    private int collectedSheep = 0;

    private int collectedCakes = 0;
    private int collectedClothes = 0;
    private int collectedCookies = 0;
    private int collectedDresses = 0;
    private int collectedEggs = 0;
    private int collectedEggPowders = 0;
    private int collectedFibers = 0;
    private int collectedFlours = 0;
    private int collectedMilks = 0;
    private int collectedWools = 0;

    public void domesticIsAddedToMap(Domestic obj) throws MissionCompletedException {
        if (obj instanceof Cow) collectedCows++;
        else if (obj instanceof Hen) collectedHens++;
        else if (obj instanceof Sheep) collectedSheep++;
        if (isFinished())
            throw new MissionCompletedException();
    }

    private boolean isFinished() {
        int flag = 0;
        if (collectedCows >= requiredCows) flag++;
        if (collectedHens >= requiredHens) flag++;
        if (collectedSheep >= requiredSheep) flag++;

        if (collectedCakes >= requiredCakes) flag++;
        if (collectedClothes >= requiredClothes) flag++;
        if (collectedCookies >= requiredCookies) flag++;
        if (collectedDresses >= requiredDresses) flag++;
        if (collectedEggs >= requiredEggs) flag++;
        if (collectedEggPowders >= requiredEggPowders) flag++;
        if (collectedFibers >= requiredFibers) flag++;
        if (collectedFlours >= requiredFlours) flag++;
        if (collectedMilks >= requiredMilks) flag++;
        if (collectedWools >= requiredWools) flag++;

        return flag == 13;
    }

    public void domesticIsDiscardedFromMap(Domestic domestic) {
        if (domestic instanceof Cow)
            collectedCows--;
        else if (domestic instanceof Hen)
            collectedHens--;
        else if (domestic instanceof Sheep)
            collectedSheep--;
    }
    void productIsAddedToMap(Storable obj) throws MissionCompletedException {
        if (obj instanceof Cake) collectedCakes++;
        else if (obj instanceof Cloth) collectedClothes++;
        else if (obj instanceof Cookie) collectedCookies++;
        else if (obj instanceof Dress) collectedDresses++;
        else if (obj instanceof Egg) collectedEggs++;
        else if (obj instanceof EggPowder) collectedEggPowders++;
        else if (obj instanceof Fiber) collectedFibers++;
        else if (obj instanceof Flour) collectedFlours++;
        else if (obj instanceof Milk) collectedMilks++;
        else if (obj instanceof Wool) collectedWools++;
        if (isFinished())
            throw new MissionCompletedException();
    }

    public LevelRequirementsChecker(Mission mission, int requiredCows, int requiredHens, int requiredSheep, int requiredCakes,
                                    int requiredClothes, int requiredCookies, int requiredDresses, int requiredEggs,
                                    int requiredEggPowders, int requiredFibers, int requiredFlours, int requiredMilks,
                                    int requiredWools) {
        this.mission = mission;
        this.requiredCows = requiredCows;
        this.requiredHens = requiredHens;
        this.requiredSheep = requiredSheep;
        this.requiredCakes = requiredCakes;
        this.requiredClothes = requiredClothes;
        this.requiredCookies = requiredCookies;
        this.requiredDresses = requiredDresses;
        this.requiredEggs = requiredEggs;
        this.requiredEggPowders = requiredEggPowders;
        this.requiredFibers = requiredFibers;
        this.requiredFlours = requiredFlours;
        this.requiredMilks = requiredMilks;
        this.requiredWools = requiredWools;
    }

    public void printInfo() {
        System.out.printf("Required Cows: %d - Cows Now : %d \n", requiredCows, collectedCows);
        System.out.printf("Required Hens : %d - Hens Now : %d \n", requiredHens, collectedHens);
        System.out.printf("Required Sheep: %d - Sheep Now : %d \n", requiredSheep, collectedSheep);

        System.out.printf("Required Cakes: %d - Cakes Now : %d \n", requiredCakes, collectedCakes);
        System.out.printf("Required Clothes: %d - Clothes Now : %d \n", requiredClothes, collectedClothes);
        System.out.printf("Required Cookies: %d - Cookies Now : %d \n", requiredCookies, collectedCookies);
        System.out.printf("Required Dresses: %d - Dresses Now : %d \n", requiredDresses, collectedDresses);
        System.out.printf("Required Eggs: %d - Eggs Now : %d \n", requiredEggs, collectedEggs);
        System.out.printf("Required EggPowders: %d - EggPowders Now : %d \n", requiredEggPowders, collectedEggPowders);
        System.out.printf("Required Fibers: %d - Fibers Now : %d \n", requiredFibers, collectedFibers);
        System.out.printf("Required Flours: %d - Flours Now : %d \n", requiredFlours, collectedFlours);
        System.out.printf("Required Milks: %d - Milks Now : %d \n", requiredMilks, collectedMilks);
        System.out.printf("Required Wools: %d - Wools Now : %d \n", requiredWools, collectedWools);
    }

}
