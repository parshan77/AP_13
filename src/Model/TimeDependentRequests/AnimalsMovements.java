package Model.TimeDependentRequests;

import Exceptions.LevelFinishedException;
import Model.Animals.Animal;
import Model.Animals.Seekers.Cat;
import Model.Mission;

public class AnimalsMovements extends TimeDependentRequest {
    private Mission mission;

    public AnimalsMovements(Mission mission) {
        this.mission = mission;
        turnsRemained = 1;
    }

    // TODO: 12/28/2018 harkate gorbe!
    @Override
    public void run() {
        for (Animal animal : mission.getMap().getAllAnimalsInMap()) {
            if (!(animal instanceof Cat))
                animal.move();
            else {
                try {
                    ((Cat) animal).moveCat();
                } catch (LevelFinishedException e) {
                    mission.setMissionAsCompleted();
                }
            }
        }
        mission.addTimeDependentRequest(new AnimalsMovements(mission));
    }
}
