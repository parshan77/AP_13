package Model;

import Exceptions.LevelFinishedException;
import Interfaces.Storable;
import Interfaces.VisibleInMap;
import Model.Animals.Animal;
import Model.Animals.Domestics.Cow;
import Model.Animals.Domestics.Hen;
import Model.Animals.Domestics.Sheep;
import Model.Products.*;

public class LevelRequirementsChecker {
    //    private HashMap<String, Integer> requirements = new HashMap<>();
    // TODO: 12/25/2018 inhame kharkari lazeme?! rahe sade tar?
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

    public void updateState(Animal obj) throws LevelFinishedException {
        if (obj instanceof Cow) {
            collectedCows++;
            if (collectedCows == requiredCows)
                throw new LevelFinishedException();
        } else if (obj instanceof Hen) {
            collectedHens++;
            if (collectedHens == requiredHens)
                throw new LevelFinishedException();
        } else if (obj instanceof Sheep) {
            collectedSheep++;
            if (collectedSheep == requiredSheep)
                throw new LevelFinishedException();
        }
    }

    void updateState(Storable obj) throws LevelFinishedException {
        if (obj instanceof Cake) {
            collectedCakes++;
            if (collectedCakes == requiredCakes)
                throw new LevelFinishedException();
        } else if (obj instanceof Cloth) {
            collectedClothes++;
            if (collectedClothes == requiredClothes)
                throw new LevelFinishedException();
        } else if (obj instanceof Cookie) {
            collectedCookies++;
            if (collectedCookies == requiredCookies)
                throw new LevelFinishedException();
        } else if (obj instanceof Dress) {
            collectedDresses++;
            if (collectedDresses == requiredDresses)
                throw new LevelFinishedException();
        } else if (obj instanceof Egg) {
            collectedEggs++;
            if (collectedEggs == requiredEggs)
                throw new LevelFinishedException();
        } else if (obj instanceof EggPowder) {
            collectedEggPowders++;
            if (collectedEggPowders == requiredEggPowders)
                throw new LevelFinishedException();
        } else if (obj instanceof Fiber) {
            collectedFibers++;
            if (collectedFibers == requiredFibers)
                throw new LevelFinishedException();
        } else if (obj instanceof Flour) {
            collectedFlours++;
            if (collectedFlours == requiredFlours)
                throw new LevelFinishedException();
        } else if (obj instanceof Milk) {
            collectedMilks++;
            if (collectedMilks == requiredMilks)
                throw new LevelFinishedException();
        } else if (obj instanceof Wool) {
            collectedWools++;
            if (collectedWools == requiredWools)
                throw new LevelFinishedException();
        }
    }

    public LevelRequirementsChecker(int requiredCows, int requiredHens, int requiredSheep, int requiredCakes,
                                    int requiredClothes, int requiredCookies, int requiredDresses, int requiredEggs,
                                    int requiredEggPowders, int requiredFibers, int requiredFlours, int requiredMilks,
                                    int requiredWools) {
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
