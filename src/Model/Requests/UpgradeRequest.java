package Model.Requests;

import Exceptions.MaxLevelExceededException;
import Exceptions.NotEnoughMoneyException;
import Model.Mission;

public class UpgradeRequest extends Request {
    private String upgradingUnitName;
    private Mission mission;

    public UpgradeRequest(String upgradingUnitName, Mission mission) {
        this.upgradingUnitName = upgradingUnitName;
        this.mission = mission;
    }

    @Override
    public void run() {
        try {
            mission.getUpgradableUnit(upgradingUnitName).upgrade();
        } catch (NotEnoughMoneyException e) {
            System.out.printf("Your money is not enough for upgrading %s\n", upgradingUnitName);
        } catch (MaxLevelExceededException maxLevelExceeded) {
            System.out.printf("%s is already at it's max possible level\n", upgradingUnitName);
        }

    }
}
