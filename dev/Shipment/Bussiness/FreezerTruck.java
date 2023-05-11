package Shipment.Bussiness;

public class FreezerTruck extends Truck{



    public FreezerTruck(String truck_Number, int totalWeight, int truckWeight, String model) {
        super(truck_Number, totalWeight, truckWeight, model);
    }

    @Override
    public Training getStorageType() {
        return Training.Freezer;
    }
    @Override
    public void printTruck()
    {
        System.out.println("Truck number: " + getTruckNumber());
        System.out.println("Truck weight: " + getTruckWeight());
        System.out.println("Truck Total weight: " + getTotalWeight());
        System.out.println("Truck model: " + getModel());
        System.out.println("Truck storage capability's: Freezer\n");
    }
}