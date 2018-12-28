package Model.TimeDependentRequests;

import Exceptions.NotEnoughMoneyException;
import Exceptions.TradingListIsEmptyException;
import Model.Mission;
import Model.Vehicles.Helicopter;

public class HelicopterGettingBackRequest extends TimeDependentRequest {
    private Mission mission;
    private Helicopter helicopter;

    public HelicopterGettingBackRequest(Mission mission) {
        this.mission = mission;
        helicopter = mission.getHelicopter();
        turnsRemained = helicopter.getTravelDuration();
    }

    @Override
    public void run() {
        try {
            helicopter.go();
        } catch (TradingListIsEmptyException e) {
            e.printStackTrace();        //ghablan bayad check mikardim
        }
    }
}
