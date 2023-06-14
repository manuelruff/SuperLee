package Shipment.Bussiness;

public class CoolingTruck extends Truck{



    public CoolingTruck(String truck_Number, int totalWeight, int truckWeight, String model) {
        super(truck_Number, totalWeight, truckWeight, model);
    }

    @Override
    public Training getStorageType() {
        return Training.Cooling;
    }

    @Override
    public void printTruck()
    {
        System.out.println("Truck number: " + getTruckNumber());
        System.out.println("Truck weight: " + getTruckWeight());
        System.out.println("Truck Total weight: " + getTotalWeight());
        System.out.println("Truck model: " + getModel());
        System.out.println("Truck storage capability's: Cooling\n");
    }
    public String getFormattedTruckDetails() {
        StringBuilder truckDetails = new StringBuilder();
        truckDetails.append("Truck number: ").append(getTruckNumber()).append("\n");
        truckDetails.append("Truck weight: ").append(getTruckWeight()).append("\n");
        truckDetails.append("Truck Total weight: ").append(getTotalWeight()).append("\n");
        truckDetails.append("Truck model: ").append(getModel()).append("\n");
        truckDetails.append("Truck storage capability: ").append(getStorageType()).append("\n");
        return truckDetails.toString();
    }

}
