package Model;

import Model.Animals.Animal;
import Model.Animals.Domestic;
import Model.Animals.Seeker;

import java.util.ArrayList;

public class Cell {
    private Plant plant;
    private ArrayList<Animal> animals = new ArrayList<>();

    public Animal getDomesticOrSeekerAnimal() {
        for (Animal animal : animals)
            if ((animal instanceof Domestic) || (animal instanceof Seeker))
                return animal;
        return null;
    }


}
