package Ship.Bussiness;

import java.util.ArrayList;
import java.util.List;
public abstract class Truck {

    private String truckNumber;
    private int totalWeight;
    private int truckWeight;
    private String model;

    private List<Days> inUse;



    public Truck(String truck_Number, int totalWeight, int truckWeight, String model) {
        truckNumber = truck_Number;
        this.totalWeight = totalWeight;
        this.truckWeight = truckWeight;
        this.model = model;
        this.inUse = new ArrayList<>();
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

    public List<Days> getInUse()
    {
        return inUse;
    }

    public abstract Training getStorageType();

    public boolean addNewDay(Days newDay){
        for (Days day : inUse){
            if (day == newDay)
                return false;
        }
        inUse.add(newDay);
        return true;
    }

    public void removeDay(Days dayToRemove)
    {
        for(Days day : inUse)
        {
            if(day == dayToRemove) {
                inUse.remove(dayToRemove);
                return;
            }
        }
    }
    public boolean checkDay(Days dayNum)
    {
        return !(inUse.contains(dayNum));
    }

    public abstract void printTruck();
}
