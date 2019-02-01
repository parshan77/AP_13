package Model.TimeDependentRequests;

import Model.Animals.Animal;
import Model.Animals.Domestic;
import Model.Animals.Predator;
import Model.Animals.Seekers.Cat;
import Model.Animals.Seekers.Dog;
import Model.Mission;

import java.util.ArrayList;

public class AnimalsExceptDomesticsMovementRequest extends TimeDependentRequest {
    private Mission mission;

    public AnimalsExceptDomesticsMovementRequest(Mission mission) {
        this.mission = mission;
        turnsRemained = 1;
    }

    // TODO: 12/28/2018 harkate gorbe!
    @Override
    public void run() {
//        ArrayList<Domestic> domestics = mission.getMap().getAllDomesticsInMap();
        // TODO: 2/1/2019 avval bayad domestic haro harkat bedim
        ArrayList<Predator> predators = mission.getMap().getAllPredatorsInMap();
        ArrayList<Cat> cats = mission.getMap().getCats();
        ArrayList<Dog> dogs = new ArrayList<>(mission.getMap().getDogs());


//        for (Domestic domestic : domestics) domestic.move();
        for (Predator predator : predators) predator.move();
        for (Dog dog : dogs) dog.move();
        for (Cat cat : cats) cat.move();

        AnimalsExceptDomesticsMovementRequest newMovement = new AnimalsExceptDomesticsMovementRequest(mission);
        mission.addTimeDependentRequest(newMovement);
        // TODO: 2/1/2019 baraye har kudum bayad add koni movementesho
    }
}
