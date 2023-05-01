package Ship;

public class RegularTruck extends Truck{


    public RegularTruck(String truck_Number, int totalWeight, int truckWeight, String model) {
        super(truck_Number, totalWeight, truckWeight, model);
    }

    @Override
    public Training getStorageType() {
        return Training.Regular;
    }
    @Override
    public void printTruck()
    {
        System.out.println("Ship.Truck number: " + getTruckNumber());
        System.out.println("Ship.Truck weight: " + getTruckWeight());
        System.out.println("Ship.Truck Total weight: " + getTotalWeight());
        System.out.println("Ship.Truck model: " + getModel());
        System.out.println("Ship.Truck storage capability's: Regular\n");
    }

}
