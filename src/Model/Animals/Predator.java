package Model.Animals;

import Exceptions.NotFoundException;

import Interfaces.Storable;
import Model.Placement.Direction;
import Model.Placement.Map;
import Model.Placement.Position;

import java.util.ArrayList;


public abstract class Predator extends Animal implements Storable {
    private int volume;
    private int sellCost;

    public Predator(Map map, Direction direction, Position position, int volume, int sellCost) {
        super(map, direction, position);
        this.volume = volume;
        this.sellCost = sellCost;
    }

    private void kill() {
        ArrayList<Domestic> domestics = map.getDomesticsInCell(position);
        if (domestics.isEmpty())
            return;
        for (Domestic domestic : domestics)
            try {
                map.discardAnimal(domestic);
            } catch (NotFoundException e) {
                e.printStackTrace();        //rokh nemide
            }
    }

    @Override
    public void step() {
        super.step();
        kill();
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public int getSellCost() {
        return sellCost;
    }

    @Override
    public int getBuyCost() {
        return 0;
    }
}
