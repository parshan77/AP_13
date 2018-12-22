package Model.Animals;
import Exceptions.NotValidCoordinatesException;
import Model.Plant;
import Model.Map;

public abstract class Domestic extends Prey{

    private double hunger = 0;
    private double hungerIncrese = 0.2;
    private Map map;

    public Domestic(Map map) {
        this.map = map;
    }

    public void makeHungry(){
        hunger += hungerIncrese;
    }
    private int hungryPace;     //meqdar dehi beshe

    public void move() {
        if (hunger >= 5)
            for (int i = 0; i < pace; i++) {
                smartStep();
            }
        else
            for (int i = 0; i < pace; i++) {
                if (hunger >= 5)
                    smartStep();
                step();
            }
    }

    private void step() {
        try {
            position.changePosition(direction);
        } catch (NotValidCoordinatesException e) {
            if (position.getRow() == 0) {
                try {
                    direction.setDirection(1, direction.getColumnDirection());
                } catch (NotValidCoordinatesException e1) {
                    e1.printStackTrace();
                }
            }

            if (position.getColumn() == 0) {
                try {
                    direction.setDirection(direction.getRowDirection(), 1);
                } catch (NotValidCoordinatesException e4) {
                    e4.printStackTrace();
                }
            }

            if (position.getRow() == Map.MAP_SIZE - 1) {
                try {
                    direction.setDirection(-1, direction.getColumnDirection());
                } catch (NotValidCoordinatesException e6) {
                    e6.printStackTrace();
                }
            }

            if (position.getColumn() == Map.MAP_SIZE - 1) {
                try {
                    direction.setDirection(direction.getRowDirection(), -1);
                } catch (NotValidCoordinatesException e8) {
                    e8.printStackTrace();
                }
            }
        }
    }

    private void smartStep(){
        Plant closestPlant ;
        try {
            closestPlant = map.getClosestPlant(position);
        } catch (NoPlantFoundException e) {
            step();
            return;
        }
        if (position.getRow() < closestPlant.getPosition().getRow()){
            try {
                direction.setRowDirection(1);
            } catch (NotValidCoordinatesException e) {
                e.printStackTrace();
            }
        }
        else if (position.getRow() > closestPlant.getPosition().getRow()){
            try {
                direction.setRowDirection(-1);
            } catch (NotValidCoordinatesException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                direction.setRowDirection(0);
            } catch (NotValidCoordinatesException e) {
                e.printStackTrace();
            }
        }

        if (position.getColumn() < closestPlant.getPosition().getColumn()){
            try {
                direction.setColumnDirection(1);
            } catch (NotValidCoordinatesException e) {
                e.printStackTrace();
            }
        }
        else if (position.getColumn() > closestPlant.getPosition().getColumn()){
            try {
                direction.setColumnDirection(-1);
            } catch (NotValidCoordinatesException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                direction.setColumnDirection(0);
            } catch (NotValidCoordinatesException e) {
                e.printStackTrace();
            }
        }
    }
}



