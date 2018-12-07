package Model;
import Model.Animals.Predator;

public class Cage {
    private Predator predator;
    private Position position;

    public Cage(Predator predator){
        this.position.setPosition(predator.position.getX(),predator.position.getY());
        //agar predator class takmil shavad ,in error dorost mishe
        this.predator = predator;
    }
}
