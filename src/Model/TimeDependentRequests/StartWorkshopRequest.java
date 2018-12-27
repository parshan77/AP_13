package Model.TimeDependentRequests;

import Exceptions.NotEnoughResourcesException;
import Exceptions.NotFoundException;
import Model.Mission;
import Model.Workshops.Workshop;

public class StartWorkshopRequest extends TimeDependentRequest {
    private Mission mission;
    private String workshopName;

    public StartWorkshopRequest(String workshopName) {
        this.workshopName = workshopName;
    }

    @Override
    public void run() {
        try {
            Workshop workshop = mission.getWorkshop(workshopName);
            workshop.start();
        } catch (NotEnoughResourcesException e) {
            System.out.printf("not enough resources for starting %s", workshopName);
        } catch (NotFoundException e) {
            System.out.println("This workshop isn't exists!- not valid workshop name");
        }
    }
}
