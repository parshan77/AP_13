package Model.TimeDependentRequesets;

import Exceptions.MaxLevelExceeded;
import Exceptions.NotEnoughMoneyException;
import Interfaces.TimeDependent;
import Model.Mission;

public class UpgradeRequest implements TimeDependent {
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
        } catch (MaxLevelExceeded maxLevelExceeded) {
            System.out.printf("%s is already at it's max possible level\n", upgradingUnitName);
        }

    }
}
