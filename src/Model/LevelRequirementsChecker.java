package Model;

import Exceptions.LevelFinishedException;
import Interfaces.Storable;
import Model.Animals.Domestics.Cow;
import Model.Animals.Domestics.Hen;
import Model.Animals.Domestics.Sheep;
import Model.Products.*;

import java.util.HashMap;
import java.util.Map;

public class LevelRequirementsChecker {
    //    private HashMap<String, Integer> requirements = new HashMap<>();
    private int requiredCows;
    private int requiredHens;
    private int requiredSheeps;

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

    //    private HashMap<String, Integer> collecteds = new HashMap<>();
    private int collectedCows = 0;
    private int collectedHens = 0;
    private int collectedSheeps = 0;

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


    void updateRequirements(Storable obj) throws LevelFinishedException {
        if (obj instanceof Cow) {
            collectedCows++;
            if (collectedCows == requiredCows)
                throw new LevelFinishedException();
        } else if (obj instanceof Hen) {
            collectedHens++;
            if (collectedHens == requiredHens)
                throw new LevelFinishedException();
        } else if (obj instanceof Sheep) {
            collectedSheeps++;
            if (collectedSheeps == requiredSheeps)
                throw new LevelFinishedException();
        } else if (obj instanceof Cake) {
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

    public LevelRequirementsChecker(int requiredCows, int requiredHens, int requiredSheeps, int requiredCakes,
                                    int requiredClothes, int requiredCookies, int requiredDresses, int requiredEggs,
                                    int requiredEggPowders, int requiredFibers, int requiredFlours, int requiredMilks,
                                    int requiredWools) {
        this.requiredCows = requiredCows;
        this.requiredHens = requiredHens;
        this.requiredSheeps = requiredSheeps;
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

}
