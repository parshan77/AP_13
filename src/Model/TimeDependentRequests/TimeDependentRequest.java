package Model.TimeDependentRequests;

public abstract class TimeDependentRequest {
    protected int turnsRemained;

    public abstract void run();

    public int getTurnsRemained() {
        return turnsRemained;
    }

    public void clock() {
        turnsRemained--;
    }
}
