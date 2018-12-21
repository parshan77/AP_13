package Model.Animals;
import Exceptions.NoPlantFoundException;
import Interfaces.Movable;
import Interfaces.VisibleInMap;
import Model.Direction;
import Model.Position;
import Exceptions.DirectionInitializingException;
import Exceptions.DirectionNotPossibleSettingException;
import Exceptions.PositionNotPossibleSettingxception;
import Model.Direction;
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
        } catch (PositionNotPossibleSettingxception e) {
            if (position.getRow() == 0) {
                try {
                    direction.setDirection(1, direction.getColumnDirection());
                } catch (DirectionNotPossibleSettingException e1) {}
            }

            if (position.getColumn() == 0) {
                try {
                    direction.setDirection(direction.getRowDirection(), 1);
                } catch (DirectionNotPossibleSettingException e4) {}
            }

            if (position.getRow() == Map.MAP_SIZE - 1) {
                try {
                    direction.setDirection(-1, direction.getColumnDirection());
                } catch (DirectionNotPossibleSettingException e6) {}
            }

            if (position.getColumn() == Map.MAP_SIZE - 1) {
                try {
                    direction.setDirection(direction.getRowDirection(), -1);
                } catch (DirectionNotPossibleSettingException e8) {}
            }
        }
    }

    private void smartStep(){
        Position closestPlant = null;
        try {
            closestPlant = map.getClosestPlant(position);
        } catch (NoPlantFoundException e) {
            step();
            return;
        }
        if (position.getRow() < closestPlant.getRow()){
            try {
                direction.setRowDirection(1);
            } catch (DirectionNotPossibleSettingException e) {}
        }
        else if (position.getRow() > closestPlant.getRow()){
            try {
                direction.setRowDirection(-1);
            } catch (DirectionNotPossibleSettingException e) {}
        }
        else{
            try {
                direction.setRowDirection(0);
            } catch (DirectionNotPossibleSettingException e) {}
        }

        if (position.getColumn() < closestPlant.getColumn()){
            try {
                direction.setColumnDirection(1);
            } catch (DirectionNotPossibleSettingException e) {}
        }
        else if (position.getColumn() > closestPlant.getColumn()){
            try {
                direction.setColumnDirection(-1);
            } catch (DirectionNotPossibleSettingException e) {}
        }
        else{
            try {
                direction.setColumnDirection(0);
            } catch (DirectionNotPossibleSettingException e) {}
        }
    }
}



