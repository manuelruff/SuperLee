import java.util.List;
public abstract class Truck {

    private String truckNumber;
    private int totalWeight;
    private int truckWeight;
    private String model;

    private Days inUse;



    public Truck(String truck_Number, int totalWeight, int truckWeight, String model) {
        truckNumber = truck_Number;
        this.totalWeight = totalWeight;
        this.truckWeight = truckWeight;
        this.model = model;
    }
    public String getTruckNumber() {
        return truckNumber;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public int getTruckWeight() {
        return truckWeight;
    }

    public String getModel() {
        return model;
    }




}
