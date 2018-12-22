package Model.TimeDependentRequesets;

import Exceptions.WorkshopExceptions.WorkshopNotEnoughResourcesException;
import Interfaces.TimeDependent;
import Model.Mission;

public class StartWorkshopRequest implements TimeDependent {
    private Mission mission;
    private String workshopName;

    public StartWorkshopRequest(String workshopName) {
        this.workshopName = workshopName;
    }

    @Override
    public void run() {
        try {
            mission.getWorkshop(workshopName).start();
        } catch (WorkshopNotEnoughResourcesException e) {
            System.out.printf("not enough resources for starting %s", workshopName);
        }
    }
}
