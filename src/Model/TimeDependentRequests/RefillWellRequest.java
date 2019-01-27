package Model.TimeDependentRequests;

import Exceptions.NotEnoughMoneyException;
import Exceptions.WellIsNotEmptyException;
import Model.Mission;

public class RefillWellRequest extends TimeDependentRequest {
    private Mission mission;

    public RefillWellRequest(Mission mission) {
        this.mission = mission;
        turnsRemained = mission.getWell().getRefillTime();
    }

    @Override
    public void run() {
        /*try {
            mission.getWell().refill();
        } catch (WellIsNotEmptyException e) {
            e.printStackTrace();    //nabayad rokh bede
        }*/
    }
}
