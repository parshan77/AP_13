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
        turnsRemained = 30;
    }

    @Override
    public void run() {
        domestic.makeProduct();
        DomesticsProducingRequest newRequest = new DomesticsProducingRequest(mission, domestic);
        mission.addTimeDependentRequest(newRequest);
        domestic.setProducingRequest(newRequest);
    }
}
