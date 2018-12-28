package Model.TimeDependentRequests;

import Model.Mission;

public class TruckGettingBackRequest extends TimeDependentRequest {

    public TruckGettingBackRequest(Mission mission) {
        turnsRemained = mission.getTruck().getTravelDuration();
    }

    @Override
    public void run() {
    }
}
