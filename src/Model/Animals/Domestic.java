package Model.Animals;
import Exceptions.NotFoundException;
import Exceptions.NotValidCoordinatesException;
import Model.Direction;
import Model.Plant;
import Model.Map;
import Model.Position;
import Model.Products.Product;

public abstract class Domestic extends Prey{

    private double hunger = 0;
    private double hungerIncrese = 0.2;
    private Map map;

    public Domestic(Map map, Direction direction, Position position) {
        super(map, direction, position);
    }

    public Product product;
    public void makeProduct(){

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
        try {
            closestPlant = map.getClosestPlant(position);
        } catch (NotFoundException e) {
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

    public void eat(){
        //todo : ??
    }
}



