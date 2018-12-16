package Exceptions;

public class MapAnimalPositionUpdatingException extends Exception {
    @Override
    public String getMessage() {
        return "Animal not found in this cell";
    }
}
