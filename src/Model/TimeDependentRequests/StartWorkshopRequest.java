package Model.TimeDependentRequests;

import Exceptions.NotEnoughResourcesException;
import Exceptions.NotFoundException;
import Model.Mission;
import Model.Products.Product;
import Model.Workshops.Workshop;

import java.util.ArrayList;

public class StartWorkshopRequest extends TimeDependentRequest {
    private Mission mission;
    private Workshop workshop;
    private ArrayList<Product> inputs;

    public StartWorkshopRequest(Workshop workshop, ArrayList<Product> inputs) {
        this.inputs = inputs;
        this.workshop = workshop;
        turnsRemained = workshop.getProcessTime();
    }

    @Override
    public void run() {
        workshop.start(inputs);
    }
}
