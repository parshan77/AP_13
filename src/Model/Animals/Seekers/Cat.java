package Model.Animals.Seekers;

import Exceptions.MaxLevelExceededException;
import Exceptions.NotEnoughMoneyException;
import Exceptions.NotFoundException;
import Interfaces.Upgradable;
import Model.Animals.Seeker;
import Model.Placement.Direction;
import Model.Placement.Position;
import Model.Plant;
import Model.Products.Product;
import Model.Screen.Map;

public class Cat extends Seeker implements Upgradable {
    private static int level = 0;
    public Cat(Map map, Direction direction, Position position) {
        super(map, direction, position);
    }

    public void collect(){
        map.getProductsInCell(position);
    }

    @Override
    public void move() {
        if (level == 0)
            for (int i = 0; i < pace; i++) {
                step();
            }
        else
            for (int i = 0; i < pace; i++){
                smartStep();
            }
    }

    public void step(){
        super.step();
    }

    public void smartStep(){
        Product closestObject = null;
        //todo: position e closestObject ro zakhireh konim
        if (position.getRow() < closestObject.getPosition().getRow()){
            direction.setRowDirection(1);
        }
        else if (position.getRow() > closestObject.getPosition().getRow()){
            direction.setRowDirection(-1);
        }
        else{
            direction.setRowDirection(0);
        }

        if (position.getColumn() < closestObject.getPosition().getColumn()){
            direction.setColumnDirection(1);
        }
        else if (position.getColumn() > closestObject.getPosition().getColumn()){
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

        if (map.getProductsInCell(position) != null)
            collect();


    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void upgrade() throws NotEnoughMoneyException, MaxLevelExceededException {
        if (level == 0)
            level++;
        else {
            //todo: what to do?
        }
    }

    @Override
    public void show() {

    }
}
