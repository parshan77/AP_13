package Model;

import Model.Animals.Animal;
import Model.Animals.Domestic;
import Model.Animals.Prey;
import Model.Animals.Seeker;

import java.util.ArrayList;

public class Cell {
    private Plant plant;
    private ArrayList<Animal> animals = new ArrayList<>();

    public Prey getPrey() {
        for (Animal animal : animals)
            if (animal instanceof Prey)
                return (Prey) animal;
        return null;
    }

    public void discardKilledPrey(Prey prey) {
        animals.remove(prey);
    }

}
