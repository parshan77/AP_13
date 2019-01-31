package Model.TimeDependentRequests;

import Model.Animals.Animal;
import Model.Animals.Domestic;
import Model.Mission;

public class DomesticsProducingRequest extends TimeDependentRequest {
    private Mission mission;
    private Domestic domestic;

    public DomesticsProducingRequest(Mission mission, Domestic domestic) {
        this.mission = mission;
        this.domestic = domestic;
        turnsRemained = 3;
    }

    @Override
    public void run() {
        domestic.makeProduct();
        mission.addTimeDependentRequest(new DomesticsProducingRequest(mission, domestic));
    }
}
