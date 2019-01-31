package Model.TimeDependentRequests;

import Model.Animals.Domestic;
import Model.Mission;

public class DomesticsProducingRequest extends TimeDependentRequest {
    private Mission mission;

    public DomesticsProducingRequest(Mission mission) {
        this.mission = mission;
        turnsRemained = 3;
    }

    @Override
    public void run() {
        for (Domestic domestic : mission.getMap().getAllDomesticsInMap())
            domestic.makeProduct();
        mission.addTimeDependentRequest(new DomesticsProducingRequest(mission));
    }
}
