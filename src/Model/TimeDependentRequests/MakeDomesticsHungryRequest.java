package Model.TimeDependentRequests;

import Model.Animals.Domestic;
import Model.Mission;

import java.util.ArrayList;

public class MakeDomesticsHungryRequest extends TimeDependentRequest {
    private Mission mission;

    public MakeDomesticsHungryRequest(Mission mission) {
        this.mission = mission;
        turnsRemained = 1;
    }

    @Override
    public void run() {
        ArrayList<Domestic> allDomesticInMap = mission.getMap().getAllDomesticsInMap();
        for (Domestic domestic : allDomesticInMap) {
            domestic.makeHungry();
        }
        mission.addTimeDependentRequest(new MakeDomesticsHungryRequest(mission));
    }
}
