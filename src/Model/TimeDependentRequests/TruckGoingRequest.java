package Model.TimeDependentRequests;

import Exceptions.TradingListIsEmptyException;
import Model.Mission;
import Model.Vehicles.Truck;

public class TruckGoingRequest extends TimeDependentRequest {
    private Mission mission;
    private Truck truck;

    public TruckGoingRequest(Mission mission) {
        this.mission = mission;
        this.truck = mission.getTruck();
        turnsRemained = truck.getTravelDuration();
    }

    @Override
    public void run() {
        try {
            mission.getTruck().go();
        } catch (TradingListIsEmptyException e) {
            e.printStackTrace();            //nabayad rokh bede -> ghablan inaro check kardim
        }
        mission.addTimeDependentRequest(new TruckGettingBackRequest(mission));
    }
}
