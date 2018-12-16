package Exceptions;

public class MapCellGettingException extends Exception {
    @Override
    public String getMessage() {
        return "Wrong coordinates entered for getting Cell.";
    }
}
