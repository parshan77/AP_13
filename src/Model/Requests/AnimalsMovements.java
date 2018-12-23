package Model.Requests;

import Model.Animals.Animal;
import Model.Mission;

public class AnimalsMovements extends Request {
    private Mission mission;

    public AnimalsMovements(Mission mission) {
        this.mission = mission;
    }

    @Override
    public void run() {
        for (Animal animal : mission.getMap().getAnimals()) {
            animal.move();
        }
    }
}
