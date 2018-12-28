package Model.TimeDependentRequests;

import Exceptions.NotEnoughMoneyException;
import Exceptions.TradingListIsEmptyException;
import Model.Mission;
import Model.Vehicles.Helicopter;

public class HelicopterGoingRequest extends TimeDependentRequest {
    private Mission mission;
    private Helicopter helicopter;

    public HelicopterGoingRequest(Mission mission) {
        this.mission = mission;
        helicopter = mission.getHelicopter();
        turnsRemained = helicopter.getTravelDuration();
    }

    @Override
    public void run() {
        mission.addTimeDependentRequest(new HelicopterGettingBackRequest(mission));
    }
}
