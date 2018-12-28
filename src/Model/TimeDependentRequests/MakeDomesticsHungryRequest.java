package Model.TimeDependentRequests;

import Exceptions.AnimalDiedException;
import Exceptions.NotFoundException;
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
        ArrayList<Domestic> diedAnimals = new ArrayList<>();

        for (Domestic domestic : allDomesticInMap)
            try {
                domestic.makeHungry();
            } catch (AnimalDiedException e) {
                diedAnimals.add(domestic);
            }

        for (Domestic domestic : diedAnimals) {
            try {
                mission.getMap().discardAnimal(domestic);
            } catch (NotFoundException e) {
                e.printStackTrace();        //rokh nemide
            }
        }
        mission.addTimeDependentRequest(new MakeDomesticsHungryRequest(mission));
    }
}
