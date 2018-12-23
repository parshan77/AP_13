package Model.TimeDependentRequesets;

import Exceptions.WorkshopNotEnoughResourcesException;
import Model.Mission;
import Model.Workshops.Workshop;

public class StartWorkshopRequest extends Request {
    private Mission mission;
    private String workshopName;

    public StartWorkshopRequest(String workshopName) {
        this.workshopName = workshopName;
    }

    @Override
    public void run() {
        try {
            Workshop workshop = mission.getWorkshop(workshopName);
            if (workshop == null) {
                System.out.println("This workshop isn't exists!- not valid workshop name");
                return;
            }
            workshop.start();
        } catch (WorkshopNotEnoughResourcesException e) {
            System.out.printf("not enough resources for starting %s", workshopName);
        }
    }
}
