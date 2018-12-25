package Model.TimeDependentRequests;

import Model.Animals.Animal;
import Model.Mission;

public class AnimalsMovements extends TimeDependentRequest {
    private Mission mission;

    public AnimalsMovements(Mission mission) {
        this.mission = mission;
    }

    @Override
    public void run() {
        for (Animal animal : mission.getMap().getAllAnimalsInMap()) {
            animal.move();
        }
    }
}
