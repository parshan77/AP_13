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
        try {
            mission.getWell().refill();
            // TODO: 12/27/2018 exception hasho ghabl az dorost kardan e request esh dorost mikonim
        } catch (NotEnoughMoneyException | WellIsNotEmptyException e) {
            e.printStackTrace();
        }
    }
}
