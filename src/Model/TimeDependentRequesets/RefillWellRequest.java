package Model.TimeDependentRequesets;

import Exceptions.NotEnoughMoneyException;
import Exceptions.WellExceptions.WellIsNotEmptyException;
import Interfaces.TimeDependent;
import Model.Mission;

public class RefillWellRequest implements TimeDependent{
    private Mission mission;

    public RefillWellRequest(Mission mission) {
        this.mission = mission;
    }

    @Override
    public void run() {
        try {
            mission.getWell().refill();
        } catch (NotEnoughMoneyException e) {
            System.out.println("Your Money is not enough!");
        } catch (WellIsNotEmptyException e) {
            System.out.println("Well is not empty(well must be empty before upgrading)");
        }
    }
}
