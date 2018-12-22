package Model.TimeDependentRequesets;

import Interfaces.TimeDependent;
import Model.Mission;

public class AnimalsMovements implements TimeDependent {
    private Mission mission;

    public AnimalsMovements(Mission mission) {
        this.mission = mission;
    }

    @Override
    public void run() {

    }
}
