package Model.TimeDependentRequests;

import Model.Animals.Domestic;
import Model.Mission;

public class DomesticMovingRequest extends TimeDependentRequest {
    private Mission mission;
    private Domestic domestic;

    public DomesticMovingRequest(Mission mission, Domestic domestic) {
        this.mission = mission;
        this.domestic = domestic;
    }

    @Override
    public void run() {
        domestic.move();
        DomesticMovingRequest nextRequeset = new DomesticMovingRequest(mission, domestic);
        domestic.setMovingRequest(nextRequeset);
        mission.addDomesticMovementRequest(nextRequeset);
    }
}
