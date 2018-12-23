package Model.Requests;

import Model.Mission;
import Model.Plant;

public class EatGrassRequest extends Request {
    private Plant plant;
    private Mission mission;

    public EatGrassRequest(Plant plant, Mission mission) {
        this.plant = plant;
        this.mission = mission;
    }

    @Override
    public void run() {
        mission.getMap().removePlant(plant);
    }
}
