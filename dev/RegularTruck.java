public class RegularTruck extends Truck{


    public RegularTruck(String truck_Number, int totalWeight, int truckWeight, String model) {
        super(truck_Number, totalWeight, truckWeight, model);
    }

    @Override
    public Training getStorageType() {
        return Training.Regular;
    }


}
