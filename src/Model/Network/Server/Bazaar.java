package Model.Network.Server;

import Exceptions.NotFoundException;
import Interfaces.Storable;
import Model.Products.*;

import java.util.ArrayList;

public class Bazaar {
    private int cakeCost = 100;
    private int clothCost = 120;
    private int cookieCost = 200;
    private int dressCost = 220;
    private int eggCost = 240;
    private int eggPowderCost = 260;
    private int fiberCost = 290;
    private int milkCost = 310;
    private int woolCost = 380;
    private int lionCost = 1000;
    private int bearCost = 1400;

    private ArrayList<Storable> items = new ArrayList<Storable>();

    public void add(Storable storable) {
        items.add(storable);
    }

    public Storable get(String storableName) throws NotFoundException {
        ArrayList<Storable> productsCopy = new ArrayList<>(items);
        for (Storable storable : productsCopy) {
            if (storable.getName().toLowerCase().equals(storableName.toLowerCase())) {
                items.remove(storable);
                return storable;
            }
        }
        throw new NotFoundException();
    }

    public void addAll(ArrayList<Storable> storables) {
        items.addAll(storables);
    }

    public int getPrice(String itemName) throws NotFoundException {
        switch (itemName.toLowerCase()) {
            case "cake":
                return cakeCost;
            case "cloth":
                return clothCost;
            case "cookie":
                return cookieCost;
            case "dress":
                return dressCost;
            case "egg":
                return eggCost;
            case "eggpowder":
                return eggPowderCost;
            case "fiber":
                return fiberCost;
            case "milk":
                return milkCost;
            case "wool":
                return woolCost;
            case "lion":
                return lionCost;
            case "bear":
                return bearCost;
        }
        throw new NotFoundException();
    }

    public int getPrice(Storable item) {
        try {
            return getPrice(item.getName());
        } catch (NotFoundException e) {
            return -1;
        }
    }

    public void setCakeCost(int cakeCost) {
        this.cakeCost = cakeCost;
    }

    public void setClothCost(int clothCost) {
        this.clothCost = clothCost;
    }

    public void setCookieCost(int cookieCost) {
        this.cookieCost = cookieCost;
    }

    public void setDressCost(int dressCost) {
        this.dressCost = dressCost;
    }

    public void setEggCost(int eggCost) {
        this.eggCost = eggCost;
    }

    public void setEggPowderCost(int eggPowderCost) {
        this.eggPowderCost = eggPowderCost;
    }

    public void setFiberCost(int fiberCost) {
        this.fiberCost = fiberCost;
    }

    public void setMilkCost(int milkCost) {
        this.milkCost = milkCost;
    }

    public void setWoolCost(int woolCost) {
        this.woolCost = woolCost;
    }
}
