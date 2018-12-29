package Model.TimeDependentRequests;

import Model.Animals.Animal;
import Model.Animals.Predator;
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
        for (Predator predator : mission.getMap().getAllPredatorsInMap()) {
            predator.move();
        }

        //bekhatere concurrent modification exception:
        ArrayList<Animal> animalsExceptPredators = new ArrayList<>(mission.getMap().getAllAnimalsInMap());
        animalsExceptPredators.removeAll(mission.getMap().getAllPredatorsInMap());
        for (Animal animalsExceptPredator : animalsExceptPredators)
            animalsExceptPredator.move();
        mission.addTimeDependentRequest(new AnimalsMovements(mission));
    }
}
