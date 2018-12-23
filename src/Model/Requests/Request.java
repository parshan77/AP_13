package Model.Requests;

public abstract class Request {
    int turnsRemained;

    public abstract void run();

    public int getTurnsRemained() {
        return turnsRemained;
    }

    public void clock() {
        turnsRemained--;
    }
}
