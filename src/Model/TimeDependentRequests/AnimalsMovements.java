package Model.TimeDependentRequests;

import Model.Animals.Animal;
import Model.Animals.Domestic;
import Model.Animals.Predator;
import Model.Animals.Seekers.Cat;
import Model.Animals.Seekers.Dog;
import Model.Mission;

import java.util.ArrayList;

public class AnimalsMovements extends TimeDependentRequest {
    private Mission mission;

    public AnimalsMovements(Mission mission) {
        this.mission = mission;
        turnsRemained = 1;
    }

    // TODO: 12/28/2018 harkate gorbe!
    @Override
    public void run() {
        ArrayList<Domestic> domestics = mission.getMap().getAllDomesticsInMap();
        ArrayList<Predator> predators = mission.getMap().getAllPredatorsInMap();
        ArrayList<Cat> cats = mission.getMap().getCats();
        ArrayList<Dog> dogs = mission.getMap().getDogs();

        //bekhatere concurrent modification exception:
        for (Domestic domestic : domestics) domestic.move();
        for (Predator predator : predators) predator.move();
        for (Dog dog : dogs) dog.move();
        for (Cat cat : cats) cat.move();

        ArrayList<Animal> animalsExceptPredators = new ArrayList<>(mission.getMap().getAllAnimalsInMap());
        animalsExceptPredators.removeAll(mission.getMap().getAllPredatorsInMap());
        for (Animal animalsExceptPredator : animalsExceptPredators)
            animalsExceptPredator.move();
        mission.addTimeDependentRequest(new AnimalsMovements(mission));
    }
}
