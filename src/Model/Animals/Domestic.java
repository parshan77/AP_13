package Model.Animals;
import Exceptions.NotFoundException;
import Exceptions.NotValidCoordinatesException;
import Model.Placement.Direction;
import Model.Plant;
import Model.Placement.Position;
import Model.Products.Product;
import Model.Screen.Map;
import Utils.Utils;


public abstract class Domestic extends Animal {
    protected String outputName;
    private double hunger = 0;
    private double hungerIncrese = 0.2;

    public Domestic(Map map, Direction direction, Position position) {
        super(map, direction, position);
    }

    public void makeProduct(String outputName){
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

    public void checkDomestic(){
        if (hunger >= 10){
            try {
                map.discardAnimal(this);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void step() {
        super.step();
        if (map.isPlanted(position)){
            if (hunger >= 3){   //todo: haminjuri ye adad dadam
                eat();
            }
        }
    }

    public void smartStep(){
        Plant closestPlant ;
        closestPlant = map.getClosestPlant(position);
        if (position.getRow() < closestPlant.getPosition().getRow()){
            direction.setRowDirection(1);
        }
        else if (position.getRow() > closestPlant.getPosition().getRow()){
            direction.setRowDirection(-1);
        }
        else{
                direction.setRowDirection(0);
        }

        if (position.getColumn() < closestPlant.getPosition().getColumn()){
            direction.setColumnDirection(1);
        }
        else if (position.getColumn() > closestPlant.getPosition().getColumn()){
            direction.setColumnDirection(-1);
        }
        else{
            direction.setColumnDirection(0);
        }

        Position previousPosition = position;
        position.changePosition(direction);

        try {
            map.updateAnimalPosition(this, previousPosition.getRow(), previousPosition.getColumn(),
                        position.getRow(), position.getColumn());
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        if (map.isPlanted(position))
            eat();

    }

    public void eat(){
        hunger -= 1;
        map.removePlant(position);
    }
}



