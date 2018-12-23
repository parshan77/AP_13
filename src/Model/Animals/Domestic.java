package Model.Animals;
import Exceptions.NotFoundException;
import Exceptions.NotValidCoordinatesException;
import Exceptions.PlantingFailureException;
import Model.Direction;
import Model.Plant;
import Model.Position;
import Model.Products.Product;
import Model.Screen.Map;
import Utils.Utils;

public abstract class Domestic extends Animal {
    private String outputName;
    private double hunger = 0;
    private double hungerIncrese = 0.2;

    public Domestic(Map map, Direction direction, Position position) {
        super(map, direction, position);
    }

    public void makeProduct(){
        try {
            Product output = Utils.getProductObject(outputName);
            output.setPosition(new Position(this.position.getRow(),this.position.getColumn()));
            map.addToMap(output);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (NotValidCoordinatesException e) {
            e.printStackTrace();
        }
    }
    public void makeHungry(){
        hunger += hungerIncrese;
    }
    private int hungryPace = 5;

    public void move() {
        if (hunger >= 5)
            for (int i = 0; i < hungryPace; i++) {
                smartStep();
            }
        else
            for (int i = 0; i < pace; i++) {
                if (hunger >= 5)
                    smartStep();
                step();
            }
    }

    public void step() {
        super.step();
    }

    public void smartStep(){
        Plant closestPlant ;
        closestPlant = map.getClosestPlant(position);
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
                direction.setRowDirection(0);

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

    public void eat(){
        //todo : ??
    }
}



